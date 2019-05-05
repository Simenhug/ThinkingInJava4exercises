//: interfaces/HorrorShow.java
// Extending an interface with inheritance.
package interfaces;
import static net.mindview.util.Print.*;
//innerclasses Exercise 14: Modify HorrorShow.java to implement
//DangerousMonster and Vampire using anonymous classes.

interface Monster {
  void menace();
}

interface DangerousMonster extends Monster {
  void destroy();
}

interface Lethal {
  void kill();
}

class DragonZilla implements DangerousMonster {
  public void menace() {}
  public void destroy() {}
}	

interface Vampire extends DangerousMonster, Lethal {
  void drinkBlood();
}

class VeryBadVampire implements Vampire {
  public void menace() {}
  public void destroy() {}
  public void kill() {}
  public void drinkBlood() {}
}	

public class HorrorShow {
  static void u(Monster b) { b.menace(); }
  static void v(DangerousMonster d) {
    d.menace();
    d.destroy();
  }
  static void w(Lethal l) { l.kill(); }
  public DangerousMonster monsterMaker() {
    return new DangerousMonster() {
      public void menace() { print("DangerousMonster Menace"); }
      public void destroy() { print("DangerousMonster Destroy"); }
    };
  }
  public Vampire vampireMaker() {
    return new Vampire() {
      public void menace() { print("Vampire Menace"); }
      public void destroy() { print("Vampire Destroy"); }
      public void kill() { print("Vampire Kill"); }
      public void drinkBlood() { print("Vampire DrinkBlood"); }
    };
  }
  public static void main(String[] args) {
    HorrorShow show = new HorrorShow();
    show.u(show.monsterMaker());
    show.v(show.monsterMaker());
    show.u(show.vampireMaker());
    show.v(show.vampireMaker());
    show.w(show.vampireMaker());
  }
//  public static void main(String[] args) {
//    DangerousMonster barney = new DragonZilla();
//    u(barney);
//    v(barney);
//    Vampire vlad = new VeryBadVampire();
//    u(vlad);
//    v(vlad);
//    w(vlad);
//  }
} ///:~
