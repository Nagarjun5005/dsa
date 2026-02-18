package binarySearch.medium;

/**
 * SingleElementInSortedArray
 *
 * Problem Statement:
 * -------------------
 * Given a sorted array where every element appears exactly twice,
 * except for one element which appears exactly once,
 * return the single element.
 *
 * Constraints:
 * - Array is sorted.
 * - Every element appears twice except one.
 * - Must solve in O(log n) time and O(1) space (optimal requirement).
 *
 * Approaches Implemented:
 * -----------------------
 * 1. Brute Force        -> O(n)
 * 2. Better Linear Scan -> O(n)
 * 3. Optimal (Binary Search using Index Pattern) -> O(log n)
 *
 * Key Binary Search Insight:
 * --------------------------
 * Before the single element:
 *      Even index -> matches next element
 *      Odd index  -> matches previous element
 *
 * After the single element:
 *      The pairing pattern shifts.
 *
 * We use this index parity property to eliminate half of the array
 * in each step, achieving O(log n) time complexity.
 */
public class SingleElementInSortedArray {

    public static void main(String[] args) {

        int[] nums = {1,1,2,3,3,4,4,8,8};

        System.out.println("Brute Force Result  : " + singleNonDuplicateBrute(nums));
        System.out.println("Better Approach     : " + singleNonDuplicateBetter(nums));
        System.out.println("Optimal (Binary)    : " + singleNonDuplicate(nums));
    }


    /**
     * Brute Force Approach
     *
     * Idea:
     * Since elements appear in pairs, we iterate with step size 2.
     * Compare nums[i] with nums[i+1].
     * If they differ, nums[i] is the single element.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int singleNonDuplicateBrute(int[] nums) {

        for (int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }

        // If not found, last element is single
        return nums[nums.length - 1];
    }


    /**
     * Better Linear Scan Approach
     *
     * Idea:
     * Traverse normally and check:
     * - If first element is unique
     * - If last element is unique
     * - Otherwise check if current element differs
     *   from both neighbors
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int singleNonDuplicateBetter(int[] nums) {

        int n = nums.length;

        for (int i = 0; i < n; i++) {

            // First element
            if (i == 0) {
                if (nums[i] != nums[i + 1]) {
                    return nums[i];
                }
            }
            // Last element
            else if (i == n - 1) {
                if (nums[i] != nums[i - 1]) {
                    return nums[i];
                }
            }
            // Middle elements
            else {
                if (nums[i] != nums[i - 1] && nums[i] != nums[i + 1]) {
                    return nums[i];
                }
            }
        }

        return -1; // Should never reach here if input is valid
    }


    /**
     * Optimal Binary Search Approach
     *
     * Core Insight:
     * Before the single element:
     *      Even index -> equals next element
     *      Odd index  -> equals previous element
     *
     * After the single element:
     *      This pattern breaks.
     *
     * We use binary search on index (not value).
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int singleNonDuplicate(int[] nums) {

        int n = nums.length;

        // Edge case: only one element
        if (n == 1) return nums[0];

        // Check first element
        if (nums[0] != nums[1]) return nums[0];

        // Check last element
        if (nums[n - 1] != nums[n - 2]) return nums[n - 1];

        int low = 1;
        int high = n - 2;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            // If mid is the single element
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }

            /*
             * Check pairing pattern:
             *
             * Case 1:
             * If mid is odd and equals previous element,
             * OR
             * If mid is even and equals next element,
             *
             * Then left side is correctly paired.
             * So single element must be on right side.
             */
            if ((mid % 2 == 1 && nums[mid] == nums[mid - 1]) ||
                    (mid % 2 == 0 && nums[mid] == nums[mid + 1])) {

                low = mid + 1;
            }
            else {
                // Pattern broken → single element on left side
                high = mid - 1;
            }
        }

        return -1; // Safety fallback
    }
}
