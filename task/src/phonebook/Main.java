package phonebook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
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
        System.out.println();

        //Bubble Sort + Jump Search
        count = 0;                                                      //counts number of found instances
        double timeout = 10 * (endTime - startTime);                    //Calculates timeout of bubblesort
        System.out.println("Start searching... (bubble sort + jump search)");
        //Bubble Sort
        startTime = System.currentTimeMillis();
        if (bubbleSort(filtered,timeout)) {
            //If bubblesort didn't time out
            long sortTime = System.currentTimeMillis();
            //Jump Search
            for (String query: find) {
                if (jumpSearch(query, filtered)) {
                    count++;
                }
            }
            endTime = System.currentTimeMillis();
            mins = (endTime-startTime) / 60000;
            secs = ((endTime-startTime) % 60000) / 1000;
            msecs = (endTime-startTime) % 1000;
            System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                    count, find.size(), mins, secs, msecs);
            System.out.println();
            mins = (sortTime-startTime) / 60000;
            secs = ((sortTime-startTime) % 60000) / 1000;
            msecs = (sortTime-startTime) % 1000;
            System.out.printf("Sorting time: %d min. %d sec. %d ms.", mins, secs, msecs);
            System.out.println();
            mins = (endTime-sortTime) / 60000;
            secs = ((endTime-sortTime) % 60000) / 1000;
            msecs = (endTime-sortTime) % 1000;
            System.out.printf("Searching time: %d min. %d sec. %d ms.", mins, secs, msecs);
            System.out.println();

        } else {
            //If bubblesort did time out
            long sortTime = System.currentTimeMillis();
            //Linear Search
            for (String query: find) {
                if (linearSearch(query, filtered)) {
                    count++;
                }
            }
            endTime = System.currentTimeMillis();
            mins = (endTime-startTime) / 60000;
            secs = ((endTime-startTime) % 60000) / 1000;
            msecs = (endTime-startTime) % 1000;
            System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                    count, find.size(), mins, secs, msecs);
            System.out.println();
            mins = (sortTime-startTime) / 60000;
            secs = ((sortTime-startTime) % 60000) / 1000;
            msecs = (sortTime-startTime) % 1000;
            System.out.printf("Sorting time: %d min. %d sec. %d ms. - STOPPED, moved to linear search", mins, secs, msecs);
            System.out.println();
            mins = (endTime-sortTime) / 60000;
            secs = ((endTime-sortTime) % 60000) / 1000;
            msecs = (endTime-sortTime) % 1000;
            System.out.printf("Searching time: %d min. %d sec. %d ms.", mins, secs, msecs);
            System.out.println();
        }
        //Quicksort + Binary Search
        count = 0;                                                      //counts number of found instances
        System.out.println("Start searching... (quick sort + binary search)");
        //Quick Sort
        startTime = System.currentTimeMillis();
        filtered = quicksort(filtered);
        long sortTime = System.currentTimeMillis();
        //Binary Search
        for (String query: find) {
            if (binarySearch(query, filtered)) {
                count++;
            }
        }
        endTime = System.currentTimeMillis();
        mins = (endTime-startTime) / 60000;
        secs = ((endTime-startTime) % 60000) / 1000;
        msecs = (endTime-startTime) % 1000;
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                count, find.size(), mins, secs, msecs);
        System.out.println();
        mins = (sortTime-startTime) / 60000;
        secs = ((sortTime-startTime) % 60000) / 1000;
        msecs = (sortTime-startTime) % 1000;
        System.out.printf("Sorting time: %d min. %d sec. %d ms.", mins, secs, msecs);
        System.out.println();
        mins = (endTime-sortTime) / 60000;
        secs = ((endTime-sortTime) % 60000) / 1000;
        msecs = (endTime-sortTime) % 1000;
        System.out.printf("Searching time: %d min. %d sec. %d ms.", mins, secs, msecs);
        System.out.println();

        //HashTable
        count = 0;                                                      //counts number of found instances
        System.out.println("Start searching... (hash table)");
        //Create Table
        startTime = System.currentTimeMillis();
        Hashtable<String, String> table = new Hashtable<>();
        for (String name: filtered) {
            table.put(name,name);
        }
        long tableTime = System.currentTimeMillis();
        //Binary Search
        for (String query: find) {
            if (table.containsKey(query)) {
                count++;
            }
        }
        endTime = System.currentTimeMillis();
        mins = (endTime-startTime) / 60000;
        secs = ((endTime-startTime) % 60000) / 1000;
        msecs = (endTime-startTime) % 1000;
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.",
                count, find.size(), mins, secs, msecs);
        System.out.println();
        mins = (tableTime-startTime) / 60000;
        secs = ((tableTime-startTime) % 60000) / 1000;
        msecs = (tableTime-startTime) % 1000;
        System.out.printf("Creating time: %d min. %d sec. %d ms.", mins, secs, msecs);
        System.out.println();
        mins = (endTime-tableTime) / 60000;
        secs = ((endTime-tableTime) % 60000) / 1000;
        msecs = (endTime-tableTime) % 1000;
        System.out.printf("Searching time: %d min. %d sec. %d ms.", mins, secs, msecs);
        System.out.println();
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
            if (split.length == 2) {
                result.add(split[1]);
            } else {
                result.add(split[1]+" "+split[2]);
            }
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
    public static boolean bubbleSort(ArrayList<String> list, double timeout) {
        double start = System.currentTimeMillis();
        while (!isSorted(list)) {
            if (System.currentTimeMillis() - start > timeout) {
                return false;
            }
            for (int i = 0; i < list.size()-1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    swap(list, i, i + 1);
                }
            }
        }
        return true;
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

    public static ArrayList<String> quicksort(ArrayList<String> list) {
        if (list.size() < 2) {
            //recursion exit
            return (ArrayList<String>) list.clone();
        } else {
            //recursion step

            //determine pivot
            String pivot = list.get(list.size() / 2);
            swap(list,list.size() / 2,0);
            ArrayList<String> smaller = new ArrayList<>();
            ArrayList<String> bigger = new ArrayList<>();
            //divide
            for (String element: list.subList(1,list.size())) {
                if (element.compareTo(pivot) > 0) {
                    bigger.add(element);
                } else {
                    smaller.add(element);
                }
            }
            //and...
            ArrayList<String> result = new ArrayList<>();
            smaller = quicksort(smaller);
            bigger = quicksort(bigger);
            //CONQUER!!
            result.addAll(smaller);
            result.add(pivot);
            result.addAll(bigger);
            return result;
        }
    }

    public static boolean binarySearch(String element, ArrayList<String> list) {
        int minInd = 0;
        int maxInd = list.size()-1;
        int curr = 0;
        while (minInd != maxInd) {
            curr = (maxInd + minInd) / 2;
            if (list.get(curr).equals(element)) {
                return true;
            } else if (element.compareTo(list.get(curr)) > 0) {
                minInd = curr;
            } else {
                maxInd = curr;
            }
        }
        return list.get(curr).equals(element);
    }
}
