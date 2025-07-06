package neetcode250.arrays.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Problem:
 * Given a string s, find the length of the longest substring without duplicate characters.
 *
 * This class provides both brute force and optimized (sliding window) solutions.
 */
public class LengthOfLongestSubstring {

    /**
     * Brute Force Approach:
     * Checks all possible substrings and verifies if each has unique characters.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     *
     * @param s the input string
     * @return the length of the longest substring without repeating characters
     */
    public static int lengthOfLongestSubstringBrute(String s) {
        int n = s.length();
        int result = 0;

        // Try all possible starting points of substrings
        for (int i = 0; i < n; i++) {
            Set<Character> set = new HashSet<>();

            // Try all possible ending points of substrings starting from i
            for (int j = i; j < n; j++) {
                // If duplicate character is found, break the inner loop
                if (set.contains(s.charAt(j))) {
                    break;
                }
                set.add(s.charAt(j));
                // Update the result if the current substring is longer
                result = Math.max(result, j - i + 1);
            }
        }
        return result;
    }

    /**
     * Optimized Approach (Sliding Window with Two Pointers):
     * Efficiently finds the longest substring without repeating characters using a dynamic window.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param s the input string
     * @return the length of the longest substring without repeating characters
     */
    public static int lengthOfLongestSubstringOptimal(String s) {
        int left = 0;
        int right = 0;
        int length = 0;
        Set<Character> set = new HashSet<>();

        // Expand the window using the right pointer
        while (right < s.length()) {
            // If duplicate character is found, shrink the window from the left
            if (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            } else {
                // Add the new character to the window and move right pointer
                set.add(s.charAt(right));
                right++;
                // Update maximum length found so far
                length = Math.max(length, right - left);
            }
        }
        return length;
    }

    /**
     * Main method to test both brute force and optimized solutions.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        String s = "abcdeabc";

        int length = lengthOfLongestSubstringOptimal(s);
        System.out.println("Length of the longest substring (Optimal): " + length);

        int brute = lengthOfLongestSubstringBrute(s);
        System.out.println("Length of the longest substring (Brute Force): " + brute);
    }
}
