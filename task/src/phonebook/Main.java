package phonebook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> data = fileToList("C:\\Users\\a.ress\\test\\directory.txt");
        ArrayList<String> find = fileToList("C:\\Users\\a.ress\\test\\find.txt");

        //Linear Search
        int count = 0;                                                      //counts number of found instances
        System.out.println("Start searching... (linear search)");
        long startTime = System.currentTimeMillis();
        for (String query: find) {
            if (linearSearchContains(query, data)) {
                count++;
            }
        }
        long endTime = System.currentTimeMillis();
        long mins = (endTime-startTime) / 60000;
        long secs = ((endTime-startTime) % 60000) / 1000;
        long msecs = (endTime-startTime) % 1000;
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                count, find.size(), mins, secs, msecs);

        //Bubble Sort + Jump Search
    }
    public static ArrayList<String> fileToList(String filename) {
        ArrayList<String> result = new ArrayList<String>();
        try{
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNext()) {
                result.add(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("File not found.");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error");
            System.out.println(e.getMessage());
        }
        return result;
    }
    public static boolean linearSearch(Object element, ArrayList<Object> list) {
        for (Object current: list) {
            if (element.equals(current)) {
                return true;
            }
        }
        return false;
    }

    public static boolean linearSearchContains(String element, ArrayList<String> list) {
        for (String current: list) {
            if (current.contains(element)) {
                return true;
            }
        }
        return false;
    }

    public static void bubbleSort(ArrayList<Comparable> list) {
        while (!isSorted(list)) {
            for (int i = 0; i < list.size()-1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    swap(list, i, i + 1);
                }
            }
        }
    }
    public static void swap(ArrayList<Comparable> list, int i1, int i2) {
        Comparable temp = list.get(i1);
        list.set(i1, list.get(i2));
        list.set(i2,temp);
    }

    public static boolean isSorted(ArrayList<Comparable> list) {
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
}
