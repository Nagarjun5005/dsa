package binarySearch.easy;

/**
 * This class solves the "Guess Number Higher or Lower" game using Binary Search.
 *
 * Problem Summary:
 * ----------------
 * You need to guess a hidden number between 1 and n.
 * An API `guess(int num)` provides feedback:
 *
 *   - return -1 : your guess is HIGHER than the picked number
 *   - return  1 : your guess is LOWER than the picked number
 *   - return  0 : your guess is CORRECT
 *
 * Using this feedback, the goal is to efficiently find the hidden number.
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 */
public class GuessNumberHigherOrLower {

    // Simulated PREFERRED NUMBER — in LeetCode this is hidden.
    private static int pick = 6;

    public static void main(String[] args) {
        int guessedNumber = guessNumber(10);
        System.out.println("Guessed Number --> " + guessedNumber);
    }

    /**
     * The guess API used by the game to provide hints about a guess.
     *
     * @param num the guessed number
     * @return -1 if num is higher than the target
     *          1 if num is lower than the target
     *          0 if num equals the target
     *
     * This exactly simulates the LeetCode-provided API.
     */
    public static int guess(int num) {
        if (num > pick) {
            return -1;  // Guess is too high
        } else if (num < pick) {
            return 1;   // Guess is too low
        } else {
            return 0;   // Guess is correct
        }
    }

    /**
     * Uses Binary Search to find the hidden number between 1 and n.
     *
     * Strategy:
     * ---------
     * - Compute mid = (left + right) / 2
     * - Call guess(mid)
     * - If guess(mid) == 0  → found the number
     * - If guess(mid) == -1 → mid is too high → search left side
     * - If guess(mid) ==  1 → mid is too low  → search right side
     *
     * Stops when left <= right.
     *
     * @param n the upper bound of the range [1, n]
     * @return the hidden number that was picked
     */
    public static int guessNumber(int n) {
        int left = 1;
        int right = n;

        while (left <= right) {
            // Prevents overflow and finds mid safely
            int mid = left + (right - left) / 2;

            int result = guess(mid);

            if (result == 0) {
                return mid; // Correct guess
            } else if (result == -1) {
                right = mid - 1; // Guess is too high → reduce range
            } else {
                left = mid + 1;  // Guess is too low → increase range
            }
        }

        return -1; // This never happens logically
    }
}
