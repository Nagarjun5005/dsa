package neetcode250.arrays.medium;

import java.util.*;

/**
 * FourSum Problem:
 * Find all unique quadruplets [nums[a], nums[b], nums[c], nums[d]] in the array such that:
 * nums[a] + nums[b] + nums[c] + nums[d] == target.
 * Solution must not contain duplicate quadruplets.
 */
public class FourSum {

    /**
     * Brute-force approach to find all quadruplets that sum to target (0 in this example).
     * Time Complexity: O(n^4)
     * Space Complexity: O(n) due to storing unique quadruplets in a Set.
     */
    public static List<List<Integer>> fourSumBrute(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == 0) { // Target is hardcoded as 0
                            List<Integer> list = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                            Collections.sort(list); // Sort to ensure uniqueness in Set
                            res.add(list); // Set automatically removes duplicates
                        }
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    /**
     * Optimal solution using Sorting + Two Pointers.
     * Time Complexity: O(n^3)
     * Space Complexity: O(1) extra space (excluding result storage)
     */
    public static List<List<Integer>> fourSumOptimal(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {

            // Skip duplicates for the first number
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n - 2; j++) {

                // Skip duplicates for the second number
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right]; // Prevent overflow

                    if (sum == 0) { // Here target is hardcoded as 0
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        left++;
                        right--;

                        // Skip duplicates for the third number
                        while (left < right && nums[left] == nums[left - 1]) left++;

                        // Skip duplicates for the fourth number
                        while (left < right && nums[right] == nums[right + 1]) right--;

                    } else if (sum < 0) {
                        left++; // Need a larger sum
                    } else {
                        right--; // Need a smaller sum
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int arr[] = {-1, 0, 1, 2, -1, -4};

        // Brute-force solution
        List<List<Integer>> lists = fourSumBrute(arr);
        System.out.println("Brute Force Result: " + lists);

        // Optimal solution
        List<List<Integer>> lists1 = fourSumOptimal(arr);
        System.out.println("Optimal Result: " + lists1);
    }
}
