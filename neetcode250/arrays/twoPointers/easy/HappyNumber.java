package arrays.twoPointers.easy;

import java.util.HashSet;

/**
 * HAPPY NUMBER
 * ============
 *
 * A number is called a Happy Number if:
 * - Starting with the number, replace it by the sum of the squares of its digits.
 * - Repeat the process until the number becomes 1, OR
 * - It enters a loop that does not include 1.
 *
 * If the process ends in 1 → Happy Number
 * If the process enters a cycle → Not a Happy Number
 *
 *
 * Examples:
 * ---------
 * Input: 19
 * 19 → 1² + 9² = 82
 * 82 → 8² + 2² = 68
 * 68 → 6² + 8² = 100
 * 100 → 1² + 0² + 0² = 1
 * Output: true
 *
 * Input: 2
 * 2 → 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 (cycle)
 * Output: false
 *
 *
 * APPROACHES IMPLEMENTED
 * ---------------------
 * 1. HashSet (Cycle Detection using Extra Space)
 * 2. Floyd’s Cycle Detection (Two Pointers, O(1) Space)
 *
 *
 * CORE INSIGHT
 * ------------
 * Each number maps to exactly one next number.
 * This forms a linked structure that either:
 * - Ends at 1 (happy), or
 * - Contains a cycle (not happy)
 *
 *
 * PATTERNS USED
 * -------------
 * - Digit Extraction (% 10, / 10)
 * - Cycle Detection
 * - Two Pointers (Slow–Fast)
 *
 *
 * @author Charu
 */
class HappyNumber {

    public static void main(String[] args) {

        int number = 19;

        // Approach 1: Using HashSet
        boolean happyHashSet = isHappyHashSet(number);
        System.out.println(happyHashSet);

        // Approach 2: Using Floyd’s Cycle Detection
        boolean happyFloyd = isHappyFloyd(number);
        System.out.println(happyFloyd);
    }

    /**
     * APPROACH 1: HashSet (Cycle Detection)
     *
     * Idea:
     * -----
     * - Keep track of all numbers seen during the process.
     * - If a number repeats, a cycle exists → not a happy number.
     * - If the number becomes 1, it is a happy number.
     *
     * Time Complexity:
     * ----------------
     * O(log n) per iteration (digit processing),
     * total iterations are small due to rapid shrinking.
     *
     * Space Complexity:
     * -----------------
     * O(log n) due to HashSet storage.
     *
     * @param n the input number
     * @return true if the number is happy, false otherwise
     */
    public static boolean isHappyHashSet(int n) {

        // Stores numbers already seen to detect a cycle
        HashSet<Integer> seen = new HashSet<>();

        // Continue until we reach 1 or detect a loop
        while (n != 1) {

            // Cycle detected
            if (seen.contains(n)) {
                return false;
            }

            // Mark current number as seen
            seen.add(n);

            // Compute the next number
            n = getNext(n);
        }

        // Reached 1 → happy number
        return true;
    }

    /**
     * APPROACH 2: Floyd’s Cycle Detection (Two Pointers)
     *
     * Idea:
     * -----
     * - Treat the number transformation as a linked list.
     * - Use two pointers:
     *     slow → moves one step at a time
     *     fast → moves two steps at a time
     *
     * - If there is a cycle, fast will eventually meet slow.
     * - If fast reaches 1, the number is happy.
     *
     * Time Complexity:
     * ----------------
     * O(log n) per iteration
     *
     * Space Complexity:
     * -----------------
     * O(1), no extra data structures used
     *
     * @param n the input number
     * @return true if the number is happy, false otherwise
     */
    public static boolean isHappyFloyd(int n) {

        int slow = n;
        int fast = getNext(n);

        // Continue until fast reaches 1 or a cycle is detected
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);                 // move one step
            fast = getNext(getNext(fast));        // move two steps
        }

        // If fast reaches 1 → happy number
        return fast == 1;
    }

    /**
     * Helper Method: Computes the sum of squares of digits of a number.
     *
     * Example:
     * --------
     * Input: 19
     * Output: 1² + 9² = 82
     *
     * @param n the input number
     * @return sum of squares of its digits
     */
    private static int getNext(int n) {

        int sum = 0;

        // Extract digits and compute square sum
        while (n > 0) {
            int digit = n % 10;      // get last digit
            sum += digit * digit;    // add square
            n /= 10;                 // remove last digit
        }

        return sum;
    }
}
