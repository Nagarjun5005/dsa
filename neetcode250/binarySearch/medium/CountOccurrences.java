package binarySearch.medium;

import static binarySearch.medium.BinarySearchFirstOccurrence.firstOccurrence;
import static binarySearch.medium.BinarySearchLastOccurrence.lastOccurrence;


public class CountOccurrences {
    public static void main(String[] args) {
        int arr[] = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;

        int first = firstOccurrence(arr, target);
        int last = lastOccurrence(arr, target);

        if (first == -1) {
            System.out.println("Element not found");
        } else {
            int count = last - first + 1;
            System.out.println("Count of " + target + " is: " + count);
        }
    }
}
