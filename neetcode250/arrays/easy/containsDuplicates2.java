package neetcode250.arrays.easy;

import java.util.HashMap;

/**
 * This class provides two methods to solve the problem of checking if an array contains
 * duplicate elements within a given distance 'k'.
 *
 * Problem:
 * Given an integer array and an integer k, return true if there are two distinct indices i and j
 * such that arr[i] == arr[j] and abs(i - j) <= k.
 *
 * Approaches:
 * 1. Brute-force (O(n^2) time)
 * 2. Optimized using HashMap (O(n) time)
 */
public class containsDuplicates2 {

    /**
     * Brute-force method to check for nearby duplicates.
     * It checks all pairs (i, j) such that i < j and abs(i - j) <= k.
     *
     * @param arr The input integer array.
     * @param k   The maximum allowed distance between duplicate elements.
     * @return true if such a pair exists, false otherwise.
     */
    public static boolean containsDuplicatesBrute(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j] && Math.abs(i - j) <= k) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Optimized method to check for nearby duplicates using a HashMap.
     * It stores the most recent index of each element and checks if the previous
     * occurrence is within distance k.
     *
     * @param arr The input integer array.
     * @param k   The maximum allowed distance between duplicate elements.
     * @return true if such a pair exists, false otherwise.
     */
    public static boolean containsDuplicatesBetter(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                int prevIndex = map.get(arr[i]);
                if (i - prevIndex <= k) {
                    return true;
                }
            }
            map.put(arr[i], i);
        }
        return false;
    }

    /**
     * Main method for testing both the brute-force and optimized methods.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 1};
        int k = 3;

        System.out.println("Brute-force result: " + containsDuplicatesBrute(arr, k));
        System.out.println("Optimized result: " + containsDuplicatesBetter(arr, k));
    }
}
