package arrays.hashing.medium;

import java.util.*;

public class TopKFrequentElements {

    /*
     ============================================================================
     PROBLEM:
     Given an integer array nums and an integer k, return the k most frequent
     elements.

     Example:
     nums = [1,1,1,2,2,3], k = 2
     Output = [1,2]

     Note:
     - Order of output does not matter
     - k is always valid
     ============================================================================
    */

    /*
     ============================================================================
     APPROACH 1: BRUTE FORCE (NESTED LOOPS + SORTING)

     IDEA:
     - For each element in the array, count how many times it appears
     - Use a boolean[] visited array to avoid counting the same element again
     - Store each unique element with its frequency as (value, count)
     - Sort by frequency in descending order
     - Pick the first k elements

     DATA STRUCTURE USED:
     - boolean[] visited → to mark already counted elements
     - List<int[]> → each int[] = {element, frequency}

     TIME COMPLEXITY:
     - Counting using nested loops: O(n^2)
     - Sorting unique elements: O(u log u), where u = unique elements
     - Overall: O(n^2)

     SPACE COMPLEXITY:
     - visited array + list: O(n)
     ============================================================================
    */
    public static int[] topKFrequentBrute(int[] nums, int k) {

        // visited[i] = true means nums[i] is already counted
        boolean[] visited = new boolean[nums.length];

        // List to store {number, frequency}
        List<int[]> list = new ArrayList<>();

        // Outer loop picks each element
        for (int i = 0; i < nums.length; i++) {

            // Skip if already counted
            if (visited[i]) {
                continue;
            }

            int count = 0;

            // Inner loop counts frequency of nums[i]
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                    visited[j] = true;
                }
            }

            // Store element and its frequency
            list.add(new int[]{nums[i], count});
        }

        /*
         Sort list by frequency (descending)

         a[1] → frequency of first element
         b[1] → frequency of second element

         Comparator logic:
         - return positive → a comes after b
         - return negative → a comes before b
        */
        list.sort((a, b) -> b[1] - a[1]);

        // Collect top k elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i)[0];
        }

        return result;
    }

    /*
     ============================================================================
     APPROACH 2: BUCKET SORT (OPTIMAL)

     IDEA:
     - Count frequency using HashMap
     - Create an array of lists where:
         index = frequency
         value = list of numbers with that frequency
     - Traverse bucket array from highest frequency to lowest
     - Collect first k elements

     KEY INSIGHT:
     - Maximum frequency of any element is nums.length
     - No sorting required

     DATA STRUCTURE USED:
     - HashMap<Integer, Integer> → number → frequency
     - List<Integer>[] freq → index represents frequency

     TIME COMPLEXITY:
     - Frequency counting: O(n)
     - Bucket placement: O(n)
     - Collecting results: O(n)
     - Overall: O(n)

     SPACE COMPLEXITY:
     - HashMap + bucket array: O(n)
     ============================================================================
    */
    public static int[] topKFrequentBucketSort(int[] nums, int k) {

        // Map to store frequency of each number
        Map<Integer, Integer> count = new HashMap<>();

        /*
         Bucket array:
         - freq[i] contains list of numbers that appear exactly i times
         - Size = nums.length + 1 because max frequency = nums.length
        */
        List<Integer>[] freq = new List[nums.length + 1];

        // Initialize all buckets
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        // Count frequencies
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        /*
         Place numbers into buckets
         Example:
         number = 1, frequency = 3 → freq[3].add(1)
        */
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            freq[entry.getValue()].add(entry.getKey());
        }

        // Result array
        int[] res = new int[k];
        int index = 0;

        /*
         Traverse from highest frequency to lowest
         Stop once k elements are collected
        */
        for (int i = freq.length - 1; i > 0 && index < k; i--) {
            for (int n : freq[i]) {
                res[index++] = n;
                if (index == k) {
                    return res;
                }
            }
        }

        return res;
    }

    /*
     ============================================================================
     MAIN METHOD (TESTING)

     Input:
     nums = [1,1,1,2,2,3]
     k = 2

     Expected Output:
     [1,2]
     ============================================================================
    */
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        System.out.println(Arrays.toString(topKFrequentBrute(nums, k)));
        System.out.println(Arrays.toString(topKFrequentBucketSort(nums, k)));
    }
}
