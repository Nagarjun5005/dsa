package arrays.twoPointers.easy;

/**
 * ValidPalindrome2Brute
 *
 * This class provides a brute force method to determine if a given string
 * can become a palindrome after deleting at most one character.
 *
 * Approach:
 * - First, check if the original string is already a palindrome.
 * - If not, try removing each character one by one.
 * - After each removal, check if the resulting string is a palindrome.
 * - If any removal leads to a palindrome, return true.
 * - If no such removal exists, return false.
 *
 * Time Complexity: O(n^2)
 * - For each character (n iterations), a new string of size n is created and checked.
 *
 * Space Complexity: O(n)
 * - Substring creation creates new string objects consuming additional space.
 */
public class ValidPalindrome2Brute {

    /**
     * Checks if the input string can become a palindrome by removing at most one character.
     *
     * @param s the input string to be checked
     * @return true if the string can be a palindrome after at most one deletion, false otherwise
     */
    public static boolean palindrome(String s) {
        // Check if the original string is already a palindrome
        if (isPalindrome(s)) {
            return true;
        }

        // Try removing each character and check if the result is a palindrome
        for (int i = 0; i < s.length(); i++) {
            String newString = s.substring(0, i) + s.substring(i + 1);
            if (isPalindrome(newString)) {
                return true;
            }
        }

        // If no possible removal leads to a palindrome, return false
        return false;
    }

    /**
     * Helper method to check if a given string is a palindrome.
     *
     * @param s the input string
     * @return true if the string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        // Compare characters from both ends moving towards the center
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
     * Main method to test the palindrome validation.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        String check = "mcada";
        boolean result = palindrome(check);
        System.out.println(result); // Output: true
    }
}
