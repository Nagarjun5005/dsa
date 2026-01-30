package arrays.twoPointers.medium;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * RotateArray
 *
 * This class demonstrates two approaches to rotate an array to the right by k steps:
 *
 * 1. Extra Space Approach
 *    - Uses an auxiliary list
 *    - Time Complexity: O(n)
 *    - Space Complexity: O(n)
 *
 * 2. Two Pointer In-Place Approach (Optimal)
 *    - Uses array reversal with two pointers
 *    - Time Complexity: O(n)
 *    - Space Complexity: O(1)
 *
 * The in-place approach is preferred in interviews.
 */
public class RotateArray {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};

        // rotate(nums, 3);
        // System.out.println(Arrays.toString(nums));

        rotateTwoPointer(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * Rotates the array using extra space.
     * Copies last k elements first, then remaining elements.
     */
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        List<Integer> list = new ArrayList<>();

        // Add last k elements
        for (int i = n - k; i < n; i++) {
            list.add(nums[i]);
        }

        // Add first n-k elements
        for (int i = 0; i < n - k; i++) {
            list.add(nums[i]);
        }

        // Copy back to original array
        int index = 0;
        for (int val : list) {
            nums[index++] = val;
        }
    }

    /**
     * Rotates the array in-place using the two pointer reversal technique.
     *
     * Steps:
     * 1. Reverse the entire array
     * 2. Reverse the first k elements
     * 3. Reverse the remaining elements
     */
    public static void rotateTwoPointer(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        // Step 1: Reverse entire array
        reverse(nums, 0, n - 1);

        // Step 2: Reverse first k elements
        reverse(nums, 0, k - 1);

        // Step 3: Reverse remaining elements
        reverse(nums, k, n - 1);
    }

    /**
     * Reverses elements in the array between left and right indices.
     */
    public static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
