package phonebook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    }
    public static ArrayList<String> fileToList(String filename) {
        ArrayList<String> result = new ArrayList<String>();
        try{
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNext()) {
                result.add(scanner.nextLine())
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        } catch (Exception e) {
            System.out.println("Unexpected Error");
            System.out.println(e.getMessage());
        }
        return result;
    }
}
