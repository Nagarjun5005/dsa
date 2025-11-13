package arrays.arrays.easy;

/**
 * Problem: Longest Common Prefix
 *
 * Given an array of strings, find the longest common prefix shared among all strings.
 * If there is no common prefix, return an empty string.
 *
 * Approaches Implemented:
 * 1. Brute Force Approach - Character by Character Comparison
 * 2. Optimal Approach - Horizontal Scanning
 *
 * DSA Pattern:
 * 🔸 Scanning Pattern (Prefix Scanning)
 * 🔸 Two-Pointer / Two-Loop Traversal (Brute Force)
 * 🔸 String Reduction Pattern (Horizontal Scanning)
 *
 * Time Complexity:
 * 🔸 Brute Force: O(N * M) where N = number of strings, M = length of the shortest string
 * 🔸 Optimal: O(S) where S = total number of all characters in all strings
 *
 * Space Complexity:
 * 🔸 Both approaches: O(1) additional space
 */
public class LongestCommonPrefix {

    /**
     * Brute Force Approach:
     * 🔸 Step 1: Initialize an empty string 'prefix'.
     * 🔸 Step 2: Iterate character by character over the first string.
     * 🔸 Step 3: For each character position, compare the character across all other strings.
     * 🔸 Step 4: If all strings have the same character at the current position, add it to the prefix.
     * 🔸 Step 5: If any string mismatches or ends, return the prefix found so far.
     * 🔸 Step 6: If the loop completes, return the fully built prefix.
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = "";

        for (int i = 0; i < strs[0].length(); i++) {
            char currentChar = strs[0].charAt(i);

            for (int j = 1; j < strs.length; j++) {
                // Check if current string is too short or if character mismatches
                if (i >= strs[j].length() || strs[j].charAt(i) != currentChar) {
                    return prefix; // Return prefix if mismatch found
                }
            }

            prefix += currentChar; // Add matched character to prefix
        }

        return prefix;
    }

    /**
     * Optimal Approach (Horizontal Scanning):
     * 🔸 Step 1: Assume the prefix is the first string.
     * 🔸 Step 2: Compare this prefix with each string in the array.
     * 🔸 Step 3: While the current string does not start with the prefix, reduce the prefix by removing the last character.
     * 🔸 Step 4: If the prefix becomes empty, return "" immediately.
     * 🔸 Step 5: Continue until the prefix matches all strings.
     * 🔸 Step 6: Return the final prefix.
     */
    public static String longestCommonPrefixOptimal(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            // Keep reducing the prefix until the current string starts with it
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);

                // If prefix becomes empty, return no common prefix
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }

    public static void main(String[] args) {
        String[] strs = {"bat", "bag", "bank", "band"};

        System.out.println("Brute Force Result: " + longestCommonPrefix(strs)); // Output: "ba"
        System.out.println("Optimal Result: " + longestCommonPrefixOptimal(strs)); // Output: "ba"
    }
}
