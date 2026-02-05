package arrays.sliding_window.medium;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Longest Substring With At Most K Distinct Characters
 *
 * PROBLEM:
 * --------
 * Given a string s and an integer k, find the length of the longest substring
 * that contains at most k distinct characters.
 *
 * This is a generalized version of:
 * - Longest Substring With At Most Two Distinct Characters (k = 2)
 *
 * APPROACHES IMPLEMENTED:
 * ----------------------
 * 1. Brute Force
 * 2. Optimized Sliding Window using HashMap
 */
public class LongestSubstringWithAtMostkDistinctCharacters {

    public static void main(String[] args) {

        // Sample input
        String s = "ccaabbb";
        int k = 3;

        // Brute Force solution
        int lengthOfLongestSubstringKDistinctBruteForce =
                lengthOfLongestSubstringTwoDistinctBruteForce(s, k);
        System.out.println(lengthOfLongestSubstringKDistinctBruteForce);

        // Sliding Window solution
        int lengthOfLongestSubstringKDistinctSlidingWindow =
                lengthOfLongestSubstringTwoDistinctSlidingWindow(s, k);
        System.out.println(lengthOfLongestSubstringKDistinctSlidingWindow);
    }

    /**
     * BRUTE FORCE APPROACH
     *
     * Idea:
     * -----
     * - Start a substring from every index
     * - Extend the substring until the number of distinct characters exceeds k
     * - Use a HashSet to track distinct characters
     *
     * Algorithm:
     * ----------
     * for each index i:
     *   create empty HashSet
     *   currLen = 0
     *   for j from i to end:
     *       add s[j] to set
     *       if set size > k → break
     *       currLen++
     *   update maxLength
     *
     * Time Complexity:
     * ----------------
     * O(n²)
     *
     * Space Complexity:
     * -----------------
     * O(1) → at most k + 1 characters stored
     */
    public static int lengthOfLongestSubstringTwoDistinctBruteForce(String s, int k) {

        int maxLength = 0;

        // Choose starting index
        for (int i = 0; i < s.length(); i++) {

            HashSet<Character> set = new HashSet<>();
            int currLen = 0;

            // Extend substring from index i
            for (int j = i; j < s.length(); j++) {

                // Add character to set
                set.add(s.charAt(j));

                // Stop when distinct character count exceeds k
                if (set.size() > k) {
                    break;
                }

                currLen++;
            }

            // Update maximum length found so far
            maxLength = Math.max(currLen, maxLength);
        }

        return maxLength;
    }

    /**
     * SLIDING WINDOW APPROACH (OPTIMIZED)
     *
     * Idea:
     * -----
     * - Use two pointers (left, right) to maintain a window
     * - Window must always contain at most k distinct characters
     * - Use HashMap to store character frequencies
     * - Expand window using right pointer
     * - Shrink window using left pointer when distinct count exceeds k
     *
     * Why HashMap?
     * ------------
     * - We need frequency counts to correctly remove characters
     * - HashSet alone cannot track duplicates
     *
     * Sliding Window Invariant:
     * -------------------------
     * Window always contains ≤ k distinct characters
     *
     * Time Complexity:
     * ----------------
     * O(n)
     *
     * Space Complexity:
     * -----------------
     * O(1) → at most k + 1 characters in the map
     */
    public static int lengthOfLongestSubstringTwoDistinctSlidingWindow(String s, int k) {

        HashMap<Character, Integer> map = new HashMap<>(); // frequency map
        int left = 0;   // left boundary of window
        int res = 0;    // maximum length found

        // Right pointer expands the window
        for (int right = 0; right < s.length(); right++) {

            // Add current character to the frequency map
            map.put(
                    s.charAt(right),
                    map.getOrDefault(s.charAt(right), 0) + 1
            );

            // Shrink window until distinct count is within k
            while (map.size() > k) {

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
