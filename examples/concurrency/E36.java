package concurrency;
import enumerated.menu.*;
import java.util.concurrent.*;
import java.util.*;
import static net.mindview.util.Print.*;

//Exercise 36: (10) Modify Restaurant36WithQueues.java so there’s one OrderTicket object per table. 
// Change order to orderTicket, 
// and add a Table class, with multiple Customers per table.
// add countdown latch to order tickets, make order tickets runnable

// This is given to the waiter, who gives it to the chef:
class Order {
    private static int counter = 0;
    private final int id = counter++;
    private final Customer customer;
    private final WaitPerson36 WaitPerson36;
    private final Food food;
    public Order(Customer cust, WaitPerson36 wp, Food f) {
        customer = cust;
        WaitPerson36 = wp;
        food = f;
    }
    public Food item() { return food; }
    public Customer getCustomer() { return customer; }
    public WaitPerson36 getWaitPerson36() { return WaitPerson36; }
    public String toString() {
        return "Order: " + id + " item: " + food +
                " for: " + customer +
                " served by: " + WaitPerson36;
    }
}

// This is what comes back from the chef:
class Plate {
    private final Order order;
    private final Food food;
    public Plate(Order ord, Food f) {
        order = ord;
        food = f;
    }
    public Order getOrder() { return order; }
    public Food getFood() { return food; }
    public String toString() { return food.toString(); }
}

class OrderTicket implements Runnable{
    private static int count;
    private final int id = count++;
    private final Table table;
    private final WaitPerson36 waitPerson36;
    private final Restaurant36 Restaurant36;
//    Set<Customer> customers = new HashSet<>();
    //private final CountDownLatch latch;
    BlockingQueue<Order> orders = new LinkedBlockingDeque<Order>();

    public OrderTicket(Table table, WaitPerson36 WaitPerson36, Restaurant36 Restaurant36) {
        this.table = table;
        this.waitPerson36 = WaitPerson36;
        this.Restaurant36 = Restaurant36;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                Order order = orders.take();
                Restaurant36.orders.put(order);
            }
        } catch (InterruptedException e) {
            print("Order ticket interrupted");
        }
    }
    @Override
    public String toString() {
        return "OrderTicket " + id + " at " + table;
    }
}

class TicketPlacer implements Runnable {
    private Table table;
    private OrderTicket orderTicket;
    public TicketPlacer(Table t, OrderTicket ot) {
        this.table = t;
        this.orderTicket = ot;
    }
    @Override
    public void run() {
        table.newTicket(orderTicket);
    }
}
class Table {
    SynchronousQueue<OrderTicket> orderTicket = new SynchronousQueue<OrderTicket>();

    public void newTicket(OrderTicket ticket) {
        try {
            print("table trying to get new order ticket");
            orderTicket.put(ticket);
            print("got new ticket");
        } catch (InterruptedException e) {
            print("creating ticket failed");
        }
    }
    private static int count;
    private final int id = count++;
    private Restaurant36 restaurant36;
    private volatile int customersDining = 0;
    public void setCustomersDining(int count) {
        customersDining = count;
    }
    public Table(Restaurant36 Restaurant36) {
        this.restaurant36 = Restaurant36;
    }
    public synchronized void oneCustomerFinishedMeal(){
        if (customersDining > 0) {
            customersDining --;
        }
        print("customers at " + this + " still dining: " + customersDining);
        if (customersDining == 0) {
            try {
                orderTicket.take();
            } catch (InterruptedException e) {
                print("trying to clear ticket " + e + this);
            }
            restaurant36.clearTable(this);
            print("customers leaving, " + this + " ready for next group");
        }
    }

    @Override
    public String toString() {
        return "Table " + id;
    }
}

class Customer implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private Random rand = new Random(99);
    private boolean ready = false;
    private Table table;
    public Table getTable(){
        return table;
    }
    private WaitPerson36 WaitPerson36;
    private OrderTicket orderTicket;
    //private CountDownLatch latch;
    // Only one course at a time can be received:
    private SynchronousQueue<Plate> placeSetting =
            new SynchronousQueue<Plate>();

    public void deliver(Plate p) throws InterruptedException {
        // Only blocks if customer is still
        // eating the previous course:
        placeSetting.put(p);
    }

    public synchronized void seating(Table table, WaitPerson36 WaitPerson36,
                                     OrderTicket orderTicket) {
        this.table = table;
        this.WaitPerson36 = WaitPerson36;
        this.orderTicket = orderTicket;
        ready = true;
        notifyAll();
    }
    public void run() {
        print(this + "came in");
        synchronized (this) {
            try {
                while (!ready) {
                    wait();
                }
                for(Course course : Course.values()) {
                    Food food = course.randomSelection();
                    try {
                        WaitPerson36.placeOrder(this, food, orderTicket);
                        // Blocks until course has been delivered:
                        print(this + " at " + table + " eating " + placeSetting.take());
                        TimeUnit.MILLISECONDS.sleep(rand.nextInt(100));
                    } catch (InterruptedException e) {
                        print(this + " at " + table + " waiting for " +
                                course + " interrupted");
                        return;
                    }
                }
                //TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
                print(this + "finished meal, ready to leave");
                table.oneCustomerFinishedMeal();
            } catch (InterruptedException e) {
                print(this + " at " + table + " waiting for a seat interrupted");
            }
        }
    }
    public String toString() {
        return "Customer " + id + " ";
    }
}

class WaitPerson36 implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Restaurant36 Restaurant36;
    public Restaurant36 getRestaurant(){
        return Restaurant36;
    }
    BlockingQueue<Plate> filledOrders =
            new LinkedBlockingQueue<Plate>();
    public WaitPerson36(Restaurant36 rest) { Restaurant36 = rest; }
    public void placeOrder(Customer cust, Food food, OrderTicket orderTicket) {
        try {
            // Shouldn't actually block because this is
            // a LinkedBlockingQueue with no size limit:
            orderTicket.orders.put(new Order(cust, this, food));
        } catch(InterruptedException e) {
            print(this + " placeOrder interrupted");
        }
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // Blocks until a course is ready
                Plate plate = filledOrders.take();
                print(this + "received " + plate +
                        " and delivering to " +
                        plate.getOrder().getCustomer());
                plate.getOrder().getCustomer().deliver(plate);
            }
        } catch(InterruptedException e) {
            print(this + " interrupted");
        }
        print(this + " off duty");
    }
    public String toString() {
        return "WaitPerson36 " + id + " ";
    }
}

class Chef implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Restaurant36 Restaurant36;
    private static Random rand = new Random(47);
    public Chef(Restaurant36 rest) { Restaurant36 = rest; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // Blocks until an order appears:
                Order order = Restaurant36.orders.take();
                Food requestedItem = order.item();
                // Time to prepare order:
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                Plate plate = new Plate(order, requestedItem);
                order.getWaitPerson36().filledOrders.put(plate);
            }
        } catch(InterruptedException e) {
            print(this + " interrupted");
        }
        print(this + " off duty");
    }
    public String toString() { return "Chef " + id + " "; }
}

class FrontDesk implements Runnable {
    private final Restaurant36 Restaurant36;
    private Random random = new Random(47);
    private ExecutorService exec;
    public FrontDesk(Restaurant36 Restaurant36, ExecutorService exec) {
        this.Restaurant36 = Restaurant36;
        this.exec = exec;
    }
    @Override
    public void run() {
        print("front desk running");
        try {
            while (!Thread.interrupted()) {
                //at most 4 customers per table
                int group = random.nextInt(4) + 1;
                print("this group has " + group + " people");
                Table t = Restaurant36.getTables().take();
                WaitPerson36 wp = Restaurant36.nextWaitPerson36();
                OrderTicket orderTicket = new OrderTicket(t, wp, Restaurant36);
                print(orderTicket);
                t.setCustomersDining(group);
                exec.execute(orderTicket);
                exec.execute(new TicketPlacer(t, orderTicket));
                try {
                    for (int i = 0; i < group; i++) {
                        Customer c = Restaurant36.getCustomerLine().take();
                        c.seating(t, wp, orderTicket);
                        print(c + " seated at " + group + "-size " + t + " with waiter " + wp +
                                " and order ticket " + orderTicket);
                        synchronized (this) {
                            notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    print("arrangement interrupted " + e);
                }
            }
        } catch (InterruptedException e) {
            print("Front desk interrupted");
        }
    }
}

class Restaurant36 implements Runnable {
    List<WaitPerson36> WaitPerson36s =
            new ArrayList<WaitPerson36>();
    public synchronized WaitPerson36 nextWaitPerson36(){
        return  WaitPerson36s.get(
                rand.nextInt(WaitPerson36s.size()));
    }
    private List<Chef> chefs = new ArrayList<Chef>();
    private BlockingQueue<Table> tables = new LinkedBlockingQueue<Table>();
    public void clearTable(Table t){
        try {
            print("a table is now available again");
            tables.put(t);
        } catch (InterruptedException e) {
            print("clearing " + this + " interrupted");
        }
    }
    private BlockingQueue<Customer> customerLine = new LinkedBlockingDeque<>();
    private ExecutorService exec;
    private FrontDesk frontDesk;
    private static Random rand = new Random(47);
    BlockingQueue<Order>
            orders = new LinkedBlockingQueue<Order>();
    public Restaurant36(ExecutorService e, int nWaitPerson36s,
                      int nChefs, int nTables) {
        exec = e;
        frontDesk = new FrontDesk(this, e);
        exec.execute(frontDesk);
        print("running front desk");
        for(int i = 0; i < nWaitPerson36s; i++) {
            WaitPerson36 WaitPerson36 = new WaitPerson36(this);
            WaitPerson36s.add(WaitPerson36);
            exec.execute(WaitPerson36);
        }
        for(int i = 0; i < nChefs; i++) {
            Chef chef = new Chef(this);
            chefs.add(chef);
            exec.execute(chef);
        }
        for(int i = 0; i < nTables; i++) {
            Table table = new Table(this);
            try {
                tables.put(table);
            } catch (InterruptedException e1) {
                print("initialzing tables interrupted");
            }
        }
    }
    public BlockingQueue<Table> getTables(){
        return tables;
    }
    public BlockingQueue<Customer> getCustomerLine() {
        return customerLine;
    }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                Customer c = new Customer();
                customerLine.put(c);
                exec.execute(c);
                print("Tables available " + tables.size());
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch(InterruptedException e) {
            print("Restaurant36 interrupted");
        }
        print("Restaurant36 closing");
    }
}

class Restaurant36WithQueues {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        Restaurant36 Restaurant36 = new Restaurant36(exec, 5, 2, 10);
        exec.execute(Restaurant36);
        if(args.length > 0) // Optional argument
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
            print("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
        System.exit(0);
    }
}

public class E36 {
    public static void main(String[] args) {
        try {
            Restaurant36WithQueues.main(args);
        } catch (Exception e) {
            print(e);
        }
    }
}