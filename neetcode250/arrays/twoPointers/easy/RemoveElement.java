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
 * - Use one pointer `i` to read each element in the array.
 * - Use another pointer `k` to track the position where the
 *   next valid element (not equal to `val`) should be written.
 *
 * - When nums[i] != val:
 *      → copy nums[i] to nums[k]
 *      → increment k
 *
 * - At the end, `k` represents the number of elements
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

        int[] nums = {3, 2, 2, 3};
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
        int k = 0;

        // Traverse the array
        for (int i = 0; i < nums.length; i++) {

            // If current element is not equal to val,
            // copy it to the k-th position
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }

        // k represents the new length of the array
        return k;
    }
}
