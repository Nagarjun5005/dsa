package arrays.sliding_window.medium;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Longest Substring With At Most Two Distinct Characters
 *
 * PROBLEM:
 * --------
 * Given a string s, find the length of the longest substring
 * that contains at most two distinct characters.
 *
 * This class demonstrates:
 * 1. Brute Force approach
 * 2. Optimized Sliding Window approach using HashMap
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {

    public static void main(String[] args) {

        // Sample input
        String s = "ccaabbb";

        // Brute Force solution
        int lengthOfLongestSubstringTwoDistinct =
                lengthOfLongestSubstringTwoDistinctBruteForce(s);
        System.out.println(lengthOfLongestSubstringTwoDistinct);

        // Sliding Window solution
        int lengthOfLongestSubstringTwoDistinctSlidingWindow =
                lengthOfLongestSubstringTwoDistinctSlidingWindow(s);
        System.out.println(lengthOfLongestSubstringTwoDistinctSlidingWindow);
    }

    /**
     * BRUTE FORCE APPROACH
     *
     * Idea:
     * -----
     * - Start substring from every index
     * - Extend substring until number of distinct characters exceeds 2
     * - Use HashSet to track distinct characters
     *
     * Time Complexity:
     * ----------------
     * O(n²)
     *
     * Space Complexity:
     * -----------------
     * O(1) → at most 3 characters stored
     */
    public static int lengthOfLongestSubstringTwoDistinctBruteForce(String s) {

        int maxLength = 0;

        // Choose starting index
        for (int i = 0; i < s.length(); i++) {

            HashSet<Character> set = new HashSet<>();
            int currLen = 0;

            // Extend substring from index i
            for (int j = i; j < s.length(); j++) {

                // Add character to set
                set.add(s.charAt(j));

                // If more than 2 distinct characters, stop
                if (set.size() > 2) {
                    break;
                }

                currLen++;
            }

            // Update maximum length
            maxLength = Math.max(currLen, maxLength);
        }

        return maxLength;
    }

    /**
     * SLIDING WINDOW APPROACH (OPTIMIZED)
     *
     * Idea:
     * -----
     * - Use two pointers (left, right)
     * - Maintain a window with at most 2 distinct characters
     * - Use HashMap to store character frequencies
     * - Expand window using right pointer
     * - Shrink window using left pointer when distinct count exceeds 2
     *
     * Why HashMap?
     * ------------
     * - We need frequency count to safely remove characters
     * - HashSet alone cannot track duplicates
     *
     * Time Complexity:
     * ----------------
     * O(n)
     *
     * Space Complexity:
     * -----------------
     * O(1) → at most 3 characters in map
     */
    public static int lengthOfLongestSubstringTwoDistinctSlidingWindow(String s) {

        HashMap<Character, Integer> map = new HashMap<>(); // frequency map
        int left = 0;   // left boundary of window
        int res = 0;    // result (max length)

        // Right pointer expands the window
        for (int right = 0; right < s.length(); right++) {

            // Add current character to map (frequency count)
            map.put(s.charAt(right),
                    map.getOrDefault(s.charAt(right), 0) + 1);

            // If more than 2 distinct characters, shrink window
            while (map.size() > 2) {

                char leftChar = s.charAt(left);

                // Reduce frequency of left character
                map.put(leftChar, map.get(leftChar) - 1);

                // Remove character if frequency becomes zero
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }

                left++; // Move left pointer
            }

            // Update maximum window size
            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}
