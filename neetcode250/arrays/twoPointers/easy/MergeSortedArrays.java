package arrays.twoPointers.easy;

import java.util.Arrays;

/*
 =============================================================================
 PROBLEM: 88. Merge Sorted Array (LeetCode)

 You are given two sorted integer arrays nums1 and nums2, and two integers
 m and n representing the number of valid elements in nums1 and nums2.

 The task is to merge nums2 into nums1 such that nums1 becomes a single
 sorted array in non-decreasing order.

 IMPORTANT CONSTRAINT:
 - nums1 has a total size of (m + n)
 - The first m elements of nums1 are valid
 - The last n elements of nums1 are placeholders (0s) and must be ignored
 - nums2 contains n valid elements

 The final merged array must be stored IN-PLACE inside nums1.
 No additional array should be used for the result.
 =============================================================================
*/

public class MergeSortedArrays {

    /*
     =========================================================================
     MAIN METHOD (TESTING)
     =========================================================================
    */
    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;

        int[] nums2 = {2, 5, 6};
        int n = 3;

        mergeOptimal(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1)); // [1,2,2,3,5,6]
    }

    /*
     =========================================================================
     APPROACH 1: BRUTE FORCE

     IDEA:
     - Copy all elements of nums2 into the empty slots of nums1
     - Sort nums1

     WHY THIS WORKS:
     - Sorting ensures the final array is ordered

     WHY THIS IS NOT OPTIMAL:
     - Sorting costs extra time
     - Does not take advantage of the fact that both arrays are already sorted

     TIME COMPLEXITY:
     - O((m + n) log(m + n))

     SPACE COMPLEXITY:
     - O(1) (ignoring internal sorting stack)
     =========================================================================
    */
    public static int[] mergeBrute(int[] nums1, int m, int[] nums2, int n) {

        // Copy nums2 into nums1
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }

        // Sort the combined array
        Arrays.sort(nums1);

        return nums1;
    }

    /*
     =========================================================================
     APPROACH 2: OPTIMAL IN-PLACE MERGE (TWO POINTERS)

     PATTERN:
     - Two Pointers → Merge / Compare (Reverse Direction)

     CORE IDEA:
     - Since nums1 has extra space at the end, we fill it from BACK to FRONT
     - This avoids overwriting valid elements in nums1

     POINTERS:
     - m - 1 → last valid element in nums1
     - n - 1 → last element in nums2
     - last  → last index of nums1 (write position)

     MERGING STRATEGY:
     - Compare the largest remaining elements from nums1 and nums2
     - Place the larger one at the `last` position
     - Move pointers accordingly

     TIME COMPLEXITY:
     - O(m + n)

     SPACE COMPLEXITY:
     - O(1) (in-place)
     =========================================================================
    */
    public static int[] mergeOptimal(int[] nums1, int m, int[] nums2, int n) {

        // Index where the next largest element should be placed
        int last = m + n - 1;

        /*
         ---------------------------------------------------------------------
         STEP 1: Merge while BOTH arrays have elements remaining
         ---------------------------------------------------------------------
        */
        while (m > 0 && n > 0) {

            // Compare the largest unmerged elements
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[last] = nums1[m - 1];
                m--;
            } else {
                nums1[last] = nums2[n - 1];
                n--;
            }

            // Move write position backward
            last--;
        }

        /*
         ---------------------------------------------------------------------
         STEP 2: Copy remaining elements from nums2 (if any)

         WHY THIS IS REQUIRED:
         - If nums1 is exhausted first (m == 0),
           the remaining elements in nums2 must be copied.
         - If nums2 is exhausted first (n == 0),
           nums1's remaining elements are already in correct position
           and do NOT need to be copied.

         We copy from the back to avoid overwriting and to maintain order.
         ---------------------------------------------------------------------
        */
        while (n > 0) {
            nums1[last] = nums2[n - 1];
            n--;
            last--;
        }

        return nums1;
    }
}
