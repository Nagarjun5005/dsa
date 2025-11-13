package arrays.twoPointers.easy;

import java.util.Arrays;

/**
 * This class provides methods to merge two sorted integer arrays.
 * It includes both brute-force and optimal solutions.
 *
 * The main use case is to merge the sorted array nums2 into nums1 such that
 * nums1 becomes a single sorted array.
 *
 * Constraints:
 * - nums1 has a size of m + n, where the first m elements are valid,
 *   and the last n elements are initialized to 0 (to be filled).
 * - nums2 has n elements.
 */
public class MergeSortedArrays {

    /**
     * Main method to test the merge functions.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        // Test optimal merge
        int[] nums4 = mergeOptimal(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums4));
    }

    /**
     * Brute-force method to merge two sorted arrays.
     * It appends elements from nums2 to nums1 and sorts the combined array.
     *
     * Time Complexity: O((m + n) log(m + n)) due to sorting.
     * Space Complexity: O(1) (in-place sort, though sorting algorithm may use stack space).
     *
     * @param nums1 the first array with size m + n, where the first m elements are valid.
     * @param m     number of valid elements in nums1.
     * @param nums2 the second array with n elements.
     * @param n     number of elements in nums2.
     * @return the merged sorted array (nums1 is modified in-place).
     */
    public static int[] mergeBrute(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[i + m] = nums2[i];
        }
        Arrays.sort(nums1);
        return nums1;
    }

    /**
     * Optimal in-place method to merge two sorted arrays.
     * It starts from the end and fills nums1 in reverse order,
     * comparing the largest remaining elements from both arrays.
     *
     * Time Complexity: O(m + n)
     * Space Complexity: O(1) — in-place merging with no extra space.
     *
     * @param nums1 the first array with size m + n, where the first m elements are valid.
     * @param m     number of valid elements in nums1.
     * @param nums2 the second array with n elements.
     * @param n     number of elements in nums2.
     * @return the merged sorted array (nums1 is modified in-place).
     */
    public static int[] mergeOptimal(int[] nums1, int m, int[] nums2, int n) {
        int last = m + n - 1;
        while (m > 0 && n > 0) {
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[last] = nums1[m - 1];
                m--;
            } else {
                nums1[last] = nums2[n - 1];
                n--;
            }
            last--;
        }

        // Copy any remaining elements from nums2
        while (n > 0) {
            nums1[last] = nums2[n - 1];
            n--;
            last--;
        }

        return nums1;
    }
}
