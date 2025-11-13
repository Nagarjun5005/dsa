package arrays.twoPointers.medium;

import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {
      int[] nums= {-1,0,1,2,-1,-4};
        List<List<Integer>> lists = threeSumBrute(nums);
        System.out.println(lists);

        List<List<Integer>> lists1 = threeSumBetter(nums);
        System.out.println(lists1);
        List<List<Integer>> twoPointer= threeSumTwoPointer(nums);
        System.out.println(twoPointer);

    }

    /**
     * Solves the 3Sum problem using the brute-force approach (three nested loops).
     * Finds all unique triplets in the array which sum to zero.
     *
     * This method checks all possible triplet combinations.
     *
     * @param nums The input integer array.
     * @return A list of lists containing unique triplets that sum to zero.
     */
    public static List<List<Integer>> threeSumBrute(int[] nums) {
        // Create a set to store unique triplets
        Set<List<Integer>> res = new HashSet<>();

        // Step 1: Sort the array to handle duplicates easily
        Arrays.sort(nums);

        // Step 2: Iterate through each possible triplet combination
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    // Check if the sum of the triplet is zero
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        // Form a triplet list
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k]);

                        // Add the triplet to the set to ensure uniqueness
                        res.add(temp);
                    }
                }
            }
        }

        // Convert the set of triplets into a list to match the expected return type
        return new ArrayList<>(res);
    }


    /**
     * Solves the 3Sum problem using a hashing-based approach.
     * Finds all unique triplets in the array which sum to zero.
     *
     * This method uses a HashSet to avoid adding duplicate triplets.
     *
     * @param nums The input integer array.
     * @return A list of lists containing unique triplets that sum to zero.
     */
    public static List<List<Integer>> threeSumBetter(int[] nums) {
        // Create a set to store unique triplets
        Set<List<Integer>> set = new HashSet<>();

        int n = nums.length;

        // Outer loop: Fix the first element of the triplet
        for (int i = 0; i < n; i++) {
            // Create a new HashSet to store elements for the current iteration
            Set<Integer> hashset = new HashSet<>();

            // Inner loop: Try to find two numbers such that their sum with nums[i] == 0
            for (int j = i + 1; j < n; j++) {
                // Calculate the required third number to form a zero-sum triplet
                int third = -(nums[i] + nums[j]);

                // If the third number is already seen (present in the hashset)
                if (hashset.contains(third)) {
                    // Form the triplet
                    List<Integer> temp = Arrays.asList(nums[i], nums[j], third);

                    // Sort the triplet to handle different orderings (like [-1,0,1] and [0,1,-1])
                    temp.sort(null);

                    // Add the sorted triplet to the set (to ensure uniqueness)
                    set.add(temp);
                }

                // Add the current element to the hashset for future checks
                hashset.add(nums[j]);
            }
        }

        // Convert the set of triplets into a list to match the expected return type
        List<List<Integer>> ans = new ArrayList<>(set);

        // Return the list of unique triplets
        return ans;
    }



    /**
     * Solves the 3Sum problem using the two pointer technique.
     * Finds all unique triplets in the array which sum to zero.
     *
     * @param nums The input integer array.
     * @return A list of lists containing unique triplets that sum to zero.
     */
    public static List<List<Integer>> threeSumTwoPointer(int[] nums) {
        // Result list to store the triplets
        List<List<Integer>> result = new ArrayList<>();

        // Step 1: Sort the array to enable two pointer approach and easy duplicate handling
        Arrays.sort(nums);

        // Step 2: Iterate through the array, considering each element as the first element of the triplet
        for (int i = 0; i < nums.length; i++) {
            // Skip duplicate elements for the first number
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Initialize two pointers
            int left = i + 1;
            int right = nums.length - 1;

            // Step 3: Use two pointers to find the other two numbers
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // Found a triplet which gives sum = 0, add it to the result
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Move the left pointer to the next different number
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    // Move the right pointer to the previous different number
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // Move both pointers inward after adding the valid triplet
                    left++;
                    right--;
                }
                else if (sum < 0) {
                    // If the sum is less than 0, move the left pointer to increase the sum
                    left++;
                }
                else {
                    // If the sum is greater than 0, move the right pointer to decrease the sum
                    right--;
                }
            }
        }

        // Return the list of unique triplets
        return result;
    }


}

/*
✅ Idea:
Sort the array first.
Fix one number nums[i] (first number).
Then use two pointers (left and right) to find pairs such that:
nums[i] + nums[left] + nums[right] == 0


If the sum is:
0 ➔ store the triplet, move both pointers.
< 0 ➔ move left++ (to get bigger numbers).
> 0 ➔ move right-- (to get smaller numbers).
Skip duplicates for i, left, and right.
 */