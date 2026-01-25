package arrays.twoPointers.easy;

/**
 * REMOVE ELEMENT
 * ---------------
 *
 * Given an integer array `nums` and an integer `val`,
 * remove all occurrences of `val` in-place and return
 * the number of elements that are not equal to `val`.
 *
 * The order of the remaining elements may change.
 * Elements beyond the returned length are not important.
 *
 * Example:
 * --------
 * Input:
 *   nums = [3, 2, 2, 3], val = 3
 *
 * Output:
 *   k = 2
 *   nums (first k elements) = [2, 2]
 *
 *
 * APPROACH: Two Pointers (Read–Write / Slow–Fast)
 * ------------------------------------------------
 * - Use one pointer `fast` to read each element in the array.
 * - Use another pointer `fast` to track the position where the
 *   next valid element (not equal to `val`) should be written.
 *
 * - When nums[fast] != val:
 *      → copy nums[slow] to nums[fast]
 *      → increment slow
 *
 * - At the end, `slow` represents the number of elements
 *   not equal to `val`.
 *
 *
 * TIME COMPLEXITY:
 * ----------------
 * O(n), where n is the length of the array
 *
 * SPACE COMPLEXITY:
 * -----------------
 * O(1), constant extra space (in-place modification)
 *
 *
 * PATTERN:
 * --------
 * Two Pointers (Slow–Fast / Read–Write)
 *
 *
 * @author Charu
 */
public class RemoveElement {

    public static void main(String[] args) {

        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 3;

        int newLength = removeElement(nums, val);
        System.out.println(newLength); // Output: 2

        // Optional: Print first k elements
        for (int i = 0; i < newLength; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    /**
     * Removes all occurrences of val from nums in-place.
     *
     * @param nums the input array
     * @param val  the value to be removed
     * @return the number of elements not equal to val
     */
    public static int removeElement(int[] nums, int val) {

        // Pointer to track the position for valid elements
        int slow = 0;

        // Traverse the array
        for (int fast = 0; fast < nums.length; fast++) {

            // If current element is not equal to val,
            // copy it to the slow-th position
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }

        // slow represents the new length of the array
        return slow;
    }
}
