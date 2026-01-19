package arrays.twoPointers.easy;

/**
 * =============================================================================
 * PROBLEM: Valid Palindrome II (LeetCode 680)
 *
 * Given a string s, return true if the string can become a palindrome
 * after deleting at most one character.
 *
 * "At most one" means:
 * - 0 deletions are allowed
 * - 1 deletion is allowed
 * - More than 1 deletion is NOT allowed
 * =============================================================================
 */
public class ValidPalindrome2 {

    /**
     * =========================================================================
     * APPROACH 1: BRUTE FORCE
     *
     * IDEA:
     * 1. First, check if the original string is already a palindrome.
     * 2. If not, try removing each character one by one.
     * 3. After each removal, check if the new string is a palindrome.
     * 4. If any removal results in a palindrome, return true.
     * 5. Otherwise, return false.
     *
     * WHY THIS WORKS:
     * - We explicitly try all possible single deletions.
     * - If at least one deletion fixes the string, the answer is true.
     *
     * WHY THIS IS BRUTE FORCE:
     * - We generate a new string for every possible deletion.
     * - Palindrome check is repeated many times.
     *
     * TIME COMPLEXITY:
     * - O(n²)
     *   n deletions × O(n) palindrome check
     *
     * SPACE COMPLEXITY:
     * - O(n)
     *   Due to substring and string concatenation.
     * =========================================================================
     */
    public static boolean palindrome(String s) {

        // Case 1: The original string is already a palindrome
        if (isPalindrome(s)) {
            return true;
        }

        // Case 2: Try deleting each character once
        for (int i = 0; i < s.length(); i++) {
            String newString = s.substring(0, i) + s.substring(i + 1);
            if (isPalindrome(newString)) {
                return true;
            }
        }

        // No single deletion can make it a palindrome
        return false;
    }

    /**
     * =========================================================================
     * APPROACH 2: OPTIMAL SOLUTION (OPPOSITE-DIRECTION TWO POINTERS)
     *
     * CORE IDEA:
     * - Use two pointers starting from both ends.
     * - If characters match, move inward.
     * - On the first mismatch:
     *     * We are allowed to delete ONLY ONE character.
     *     * Try skipping the left character OR the right character.
     *     * If either resulting substring is a palindrome, return true.
     *
     * WHY THIS WORKS:
     * - Only the first mismatch matters.
     * - After one deletion, the rest must be a perfect palindrome.
     *
     * TIME COMPLEXITY:
     * - O(n)
     *
     * SPACE COMPLEXITY:
     * - O(1)
     * =========================================================================
     */
    public static boolean validPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            // Characters match → move inward
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            }
            else {
                // At most one deletion allowed:
                // Try skipping either the left or the right character
                return isPalindrome(s, left + 1, right)
                        || isPalindrome(s, left, right - 1);
            }
        }

        // No mismatch or at most one deletion needed
        return true;
    }

    /**
     * =========================================================================
     * HELPER METHOD (SUBSTRING PALINDROME CHECK)
     *
     * Checks if s[left..right] is a palindrome.
     * Used after skipping one character.
     *
     * This method does NOT allow any deletions.
     * =========================================================================
     */
    private static boolean isPalindrome(String s, int left, int right) {

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * =========================================================================
     * HELPER METHOD (FULL STRING PALINDROME CHECK)
     *
     * Checks whether the entire string is a palindrome.
     * =========================================================================
     */
    public static boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * =========================================================================
     * MAIN METHOD (TESTING)
     * =========================================================================
     */
    public static void main(String[] args) {

        String check = "agan";

        // Brute force check
        boolean bruteResult = palindrome(check);
        System.out.println(bruteResult); // true

        // Optimal two-pointer check
        boolean optimalResult = validPalindrome(check);
        System.out.println(optimalResult); // true
    }
}
