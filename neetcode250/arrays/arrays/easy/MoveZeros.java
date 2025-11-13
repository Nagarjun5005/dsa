package arrays.arrays.easy;

import java.util.Arrays;

public class MoveZeros {

    /**
     * Problem :
     * Given an array of integers, move all the zeros to the end of the array while maintaining the relative order of the non-zero elements.
     * You must perform the operation in-place (without using extra space) if possible.
     *
     * Pattern : two pointer
     * Brute Force Approach
     * ---------------------
     * This method creates a new array and copies all non-zero elements from the original array
     * to the new array, maintaining their order. Remaining elements in the new array will be zero by default.
     *
     * Time Complexity: O(n) - Single traversal of the array.
     * Space Complexity: O(n) - Requires additional array of size n.
     *
     * @param nums The input array containing integers with zeros and non-zero elements.
     * @return A new array with all non-zero elements shifted to the front and zeros at the end.
     */
    public static int[] moveArraysBrute(int[] nums) {
        int[] newArr = new int[nums.length];
        int index = 0;

        // Traverse the original array
        for (int i = 0; i < nums.length; i++) {
            // Copy non-zero elements to the new array
            if (nums[i] != 0) {
                newArr[index] = nums[i];
                index++;
            }
        }
        // Remaining elements in newArr are already zeros by default
        return newArr;
    }

    /**
     * Optimal In-Place Approach (Two Pointer Method)
     * -----------------------------------------------
     * This method rearranges the elements within the same array (in-place) using two pointers.
     * One pointer (i) traverses the entire array.
     * The other pointer (nextIndex) tracks the position to place the next non-zero element.
     *
     * Whenever a non-zero element is found, it is swapped with the element at nextIndex, and nextIndex is incremented.
     *
     * Time Complexity: O(n) - Single traversal of the array.
     * Space Complexity: O(1) - In-place rearrangement, no extra space used.
     *
     * @param nums The input array containing integers with zeros and non-zero elements.
     * @return The same array with all zeros moved to the end and non-zero elements maintaining their order.
     */
    public static int[] moveArraysOptimal(int[] nums) {
        int n = nums.length;
        int nextIndex = 0; // Points to the next position to place a non-zero element

        // Traverse the array
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                // Swap the current element with the element at nextIndex
                int temp = nums[i];
                nums[i] = nums[nextIndex];
                nums[nextIndex] = temp;

                // Move nextIndex forward
                nextIndex++;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int nums[] = {1, 0, 2, 0, 3};

        // Brute Force Approach (uses extra array)
        int[] brute = moveArraysBrute(nums);
        System.out.println("Brute Force Result: " + Arrays.toString(brute));

        // Optimal In-Place Approach (modifies the same array)
        int[] nums1 = moveArraysOptimal(nums);
        System.out.println("Optimal In-Place Result: " + Arrays.toString(nums1));
    }
}
