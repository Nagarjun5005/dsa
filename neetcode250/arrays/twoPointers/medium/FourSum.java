package arrays.twoPointers.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * FOUR SUM
 * ========
 *
 * Problem Statement:
 * ------------------
 * Given an integer array `nums` and an integer `target`,
 * return all unique quadruplets [nums[a], nums[b], nums[c], nums[d]]
 * such that:
 *
 *   a != b != c != d
 *   nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * The solution set must not contain duplicate quadruplets.
 *
 *
 * APPROACHES IMPLEMENTED:
 * ----------------------
 * 1. Brute Force using 4 loops + HashSet (for learning)
 * 2. Optimized Two-Pointer approach (interview-expected)
 *
 *
 * PATTERNS USED:
 * --------------
 * - Sorting
 * - Two Pointers (Opposite-Direction / Converging)
 * - Duplicate Skipping
 *
 *
 * TIME COMPLEXITY:
 * ----------------
 * Brute Force      : O(n^4)
 * Two Pointer      : O(n^3)
 *
 *
 * SPACE COMPLEXITY:
 * -----------------
 * Brute Force      : O(k)  (HashSet for unique quadruplets)
 * Two Pointer      : O(1)  (excluding output)
 */
public class FourSum {

    public static void main(String[] args) {

        int[] nums = {1, 0, -1, 0, -2, 2};

        // Brute force approach
        List<List<Integer>> brute = fourSumBrute(nums, 0);
        System.out.println(brute);

        // Optimized two-pointer approach
        List<List<Integer>> optimized = fourSumTwoPointer(nums, 0);
        System.out.println(optimized);
    }

    /**
     * BRUTE FORCE APPROACH
     * -------------------
     *
     * Idea:
     * -----
     * - Sort the array to normalize quadruplets
     * - Use 4 nested loops to generate all possible quadruplets
     * - Use a HashSet to remove duplicate quadruplets
     *
     * NOTE:
     * -----
     * - Sorting is required so that [a,b,c,d] is always in a fixed order
     * - HashSet removes duplicates AFTER generation (no skipping logic)
     *
     * Time Complexity:
     * ----------------
     * O(n^4)
     *
     * Space Complexity:
     * -----------------
     * O(k), where k is the number of unique quadruplets
     */
    public static List<List<Integer>> fourSumBrute(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();

        // Sort array to normalize quadruplets
        Arrays.sort(nums);

        // Generate all possible quadruplets
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    for (int l = k + 1; l < nums.length; l++) {

                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            set.add(Arrays.asList(
                                    nums[i], nums[j], nums[k], nums[l]
                            ));
                        }
                    }
                }
            }
        }

        // Convert set to list
        result.addAll(set);
        return result;
    }

    /**
     * OPTIMIZED APPROACH: Two Pointers
     * --------------------------------
     *
     * Idea:
     * -----
     * - Sort the array
     * - Fix first index `i`
     * - Fix second index `j`
     * - Use two pointers (`left`, `right`) for the remaining two values
     *
     * Duplicate Handling:
     * -------------------
     * - Skip duplicate values for `i`
     * - Skip duplicate values for `j`
     * - Skip duplicate values for `left` and `right` after a match
     *
     * Overflow Safety:
     * ----------------
     * - Use `long` for sum calculation to avoid integer overflow
     *
     * Time Complexity:
     * ----------------
     * O(n^3)
     *
     * Space Complexity:
     * -----------------
     * O(1) (excluding output list)
     */
    public static List<List<Integer>> fourSumTwoPointer(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {

            // Skip duplicate first element
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {

                // Skip duplicate second element for the same i
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = n - 1;

                while (left < right) {

                    long sum = (long) nums[i]
                            + nums[j]
                            + nums[left]
                            + nums[right];

                    if (sum == target) {

                        result.add(Arrays.asList(
                                nums[i], nums[j], nums[left], nums[right]
                        ));

                        // Skip duplicate third element
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }

                        // Skip duplicate fourth element
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }

                        left++;
                        right--;
                    }
                    else if (sum > target) {
                        right--;
                    }
                    else {
                        left++;
                    }
                }
            }
        }

        return result;
    }
}
