package arrays.sliding_window.medium;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Longest Substring Without Repeating Characters
 *
 * Problem:
 * --------
 * Given a string, find the length of the longest substring
 * without repeating characters.
 *
 * Note:
 * -----
 * - A substring must be contiguous.
 * - The comparison is case-sensitive ('a' != 'A').
 *
 * This class demonstrates THREE approaches:
 * 1. Brute Force (O(n^2))
 * 2. Sliding Window using HashSet (O(n))
 * 3. Sliding Window using HashMap (O(n))
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {

        String s = "abcabcbbB";

        // Sliding Window (HashSet)
        System.out.println(lengthOfLongestSubstringSlidingWindow(s));

        // Brute Force
        System.out.println(lengthOfLongestSubstringBrute(s));

        // Sliding Window (HashMap)
        System.out.println(lengthOfLongestSubstringHashMap(s));
    }

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * -----
     * - Fix a starting index `i`
     * - Expand the substring from `i` to `j`
     * - Stop when a duplicate character is found
     * - Track the maximum length
     *
     * For every new starting index, a new HashSet is created
     * because each substring attempt is independent.
     *
     * Time Complexity:
     * ----------------
     * O(n^2)
     *
     * Space Complexity:
     * -----------------
     * O(n)
     */
    public static int lengthOfLongestSubstringBrute(String s) {

        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            // Tracks unique characters for substring starting at i
            HashSet<Character> set = new HashSet<>();

            for (int j = i; j < s.length(); j++) {

                char current = s.charAt(j);

                // Duplicate found, stop expanding
                if (set.contains(current)) {
                    break;
                }

                set.add(current);
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }

        return maxLength;
    }

    /**
     * Approach 2: Sliding Window using HashSet
     *
     * Idea:
     * -----
     * - Maintain a window using two pointers: left and right
     * - Expand the window by moving right
     * - Shrink the window from the left until duplicates are removed
     * - Track the maximum window size
     *
     * Time Complexity:
     * ----------------
     * O(n)
     *
     * Space Complexity:
     * -----------------
     * O(n)
     */
    public static int lengthOfLongestSubstringSlidingWindow(String s) {

        // Stores characters currently in the window
        HashSet<Character> set = new HashSet<>();

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            // Shrink window until duplicate is removed
            while (set.contains(ch)) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(ch);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    /**
     * Approach 3: Sliding Window using HashMap
     *
     * Idea:
     * -----
     * - Similar to HashSet sliding window
     * - HashMap stores the last seen index of each character
     * - Window is shrunk by removing characters until duplicate is resolved
     *
     * Note:
     * -----
     * This version can be further optimized by directly jumping
     * the left pointer instead of removing characters one by one.
     *
     * Time Complexity:
     * ----------------
     * O(n)
     *
     * Space Complexity:
     * -----------------
     * O(n)
     */
    public static int lengthOfLongestSubstringHashMap(String s) {

        // Maps character -> last seen index
        HashMap<Character, Integer> charSet = new HashMap<>();

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {

            char curr = s.charAt(right);

            // Shrink window until duplicate is removed
            while (charSet.containsKey(curr)) {
                charSet.remove(s.charAt(left));
                left++;
            }

            charSet.put(curr, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
