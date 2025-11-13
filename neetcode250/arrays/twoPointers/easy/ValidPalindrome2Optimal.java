package arrays.twoPointers.easy;

/**
 * Class to check if a given string can be a palindrome
 * by removing at most one character.
 *
 * Problem Type: Two Pointers | String | Palindrome Checking
 */
public class ValidPalindrome2Optimal {

    /**
     * Main method to determine if the input string can be a valid palindrome
     * after removing at most one character.
     *
     * @param s The input string to check
     * @return true if it can be made palindrome by removing at most one character, false otherwise
     */
    public boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1; // Initialize two pointers at start and end

        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                // Mismatch found
                // Try skipping either left character or right character
                return isPalindrome(s, l + 1, r) ||
                        isPalindrome(s, l, r - 1);
            }
            // Characters matched, move pointers inward
            l++;
            r--;
        }

        // Entire string checked with no more than one mismatch
        return true;
    }

    /**
     * Helper method to check if the substring s[l..r] is a palindrome.
     *
     * @param s The input string
     * @param l Left pointer (start index)
     * @param r Right pointer (end index)
     * @return true if the substring is a palindrome, false otherwise
     */
    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                // Characters mismatch, not a palindrome
                return false;
            }
            l++;
            r--;
        }
        // All characters matched, it's a palindrome
        return true;
    }
}
