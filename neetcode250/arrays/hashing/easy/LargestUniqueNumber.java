package arrays.hashing.easy;

import java.util.HashMap;
import java.util.Map;

/*
 =============================================================================
 PROBLEM:
 Largest Unique Number (LeetCode 1133)

 Given an integer array arr, return the LARGEST number that appears
 exactly once in the array.

 If no such number exists, return -1.

 -----------------------------------------------------------------------------
 EXAMPLE:
 Input:  arr = [5, 7, 3, 9, 4, 9, 8, 3, 1]

 Frequency:
 1 -> 1
 3 -> 2
 4 -> 1
 5 -> 1
 7 -> 1
 8 -> 1
 9 -> 2

 Unique numbers = {1, 4, 5, 7, 8}
 Largest unique = 8

 Output: 8
 =============================================================================
*/

public class LargestUniqueNumber {

    /*
     =========================================================================
     MAIN METHOD
     Used for local testing of both approaches
     =========================================================================
    */
    public static void main(String[] args) {

        int[] arr = {5, 7, 3, 9, 4, 9, 8, 3, 1};

        System.out.println("Brute Force Result:");
        System.out.println(largestUniqueNumberBrute(arr));

        System.out.println("HashMap (Optimized) Result:");
        System.out.println(largestUniqueNumberBruteHashMap(arr));
    }

    /*
     =========================================================================
     APPROACH 1: BRUTE FORCE

     IDEA:
     - For each element arr[i], count how many times it appears
       in the entire array.
     - If it appears exactly once (count == 1),
       consider it as a candidate.
     - Track the maximum among all unique elements.

     WHY THIS WORKS:
     - Uniqueness is determined ONLY after counting all occurrences.
     - Each element is checked independently.

     TIME COMPLEXITY:
     - O(n²)
       (For every element, we scan the whole array)

     SPACE COMPLEXITY:
     - O(1)
       (No extra data structures used)
     =========================================================================
    */
    public static int largestUniqueNumberBrute(int[] arr) {

        int n = arr.length;
        int largest = -1;

        for (int i = 0; i < n; i++) {
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }

            if (count == 1) {
                largest = Math.max(largest, arr[i]);
            }
        }
        return largest;
    }

    /*
     =========================================================================
     APPROACH 2: OPTIMAL SOLUTION USING HASHMAP

     IDEA:
     - Build a frequency map where:
         key   -> number
         value -> number of occurrences
     - Traverse the map and consider only those keys
       whose frequency is exactly 1.
     - Track the maximum among them.

     WHY HASHMAP:
     - Avoids repeated scanning of the array.
     - Provides O(1) average-time lookups.

     TIME COMPLEXITY:
     - O(n)
       (One pass to build the map + one pass to scan it)

     SPACE COMPLEXITY:
     - O(n)
       (HashMap storing frequencies of elements)
     =========================================================================
    */
    public static int largestUniqueNumberBruteHashMap(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();

        // Step 1: Build frequency map
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        // Step 2: Find the largest number with frequency = 1
        int largest = -1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                largest = Math.max(largest, entry.getKey());
            }
        }

        return largest;
    }
}
