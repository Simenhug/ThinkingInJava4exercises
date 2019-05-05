package ShifuChen;
import static net.mindview.util.Print.*;

//输入一个文件，例如：C:\input.txt
//输出这个文件中的每一个email地址，一行一个，保存到另一个文件，例如：C:\output.txt

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class EmailReader {

    public static void main(String[] args) throws IOException{
        Path inputPath = Paths.get(args[0]);
        //read and store all lines from a file
        String content = new String(Files.readAllBytes(inputPath));
        //parse file content by space
        String[] allWords = content.split("\\s+");
        //keep every String that looks like an email address (contains "@")
        ArrayList<String> possibleEmails = new ArrayList<String>();
        for (String i : allWords) {
            if (i.contains("@")){
                //remove all punctuations in the word except "@" and "."
                String cleaned = i.replaceAll("[^a-zA-Z0-9@.]", "");
                //remove any period at the end of a word
                cleaned = cleaned.replaceAll("\\.(?!\\w)", "");
                //add new line
                cleaned += "\n";
                //print(cleaned);
                possibleEmails.add(cleaned);
            }
        }
        File outputFile = new File(args[1]);
        Writer output = new BufferedWriter(new FileWriter(outputFile));
        try {
            for (String i : possibleEmails) {
                output.write(i);
            }
            print("File has been written");
        } catch (Exception e) {
            print("an error occurred, could not create");
        }finally {
            output.close();
        }
//        print("final");
//        print(possibleEmails);
    }
}
