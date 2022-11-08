package phonebook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> data = fileToList("C:\\Users\\a.ress\\test\\directory.txt");
        ArrayList<String> find = fileToList("C:\\Users\\a.ress\\test\\find.txt");
        ArrayList<String> filtered = filterNumber(data);
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
        count = 0;                                                      //counts number of found instances
        System.out.println("Start searching... (bubble sort + jump search)");
        //Bubble Sort
        startTime = System.currentTimeMillis();
        bubbleSort(filtered);
        long sortTime = System.currentTimeMillis();
        for (String query: find) {
            if (linearSearchContains(query, data)) {
                count++;
            }
        }
        endTime = System.currentTimeMillis();
        mins = (endTime-startTime) / 60000;
        secs = ((endTime-startTime) % 60000) / 1000;
        msecs = (endTime-startTime) % 1000;
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                count, find.size(), mins, secs, msecs);

    }

    public static void test(Object o) {
        System.out.println("Success");
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

    public static ArrayList<String> filterNumber(ArrayList<String> list) {
        // filters out phone numbers from data
        ArrayList<String> result = new ArrayList<>();
        for (String element : list) {
            String[] split = element.split(" ");
            result.add(split[1]+" "+split[2]);
        }
        return result;
    }

    public static boolean linearSearch(Object element, ArrayList<String> list) {
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

    public static void bubbleSort(ArrayList<String> list) {
        while (!isSorted(list)) {
            for (int i = 0; i < list.size()-1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    swap(list, i, i + 1);
                }
            }
        }
    }
    public static void swap(ArrayList<String> list, int i1, int i2) {
        String temp = list.get(i1);
        list.set(i1, list.get(i2));
        list.set(i2,temp);
    }

    public static boolean isSorted(ArrayList<String> list) {
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean jumpSearch(String element, ArrayList<String> list) {
        int blocksize = (int) Math.sqrt(list.size());
        for (int i = 0; i < list.size(); i += blocksize) {
            if (element.equals(list.get(i))) {
                return true;
            } else if (element.compareTo(list.get(i)) < 0) {
                for (int j = i - 1; j > i-blocksize; j--){
                    if (element.equals(list.get(j))) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }
}
