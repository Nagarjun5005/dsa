package arrays.twoPointers.easy;

/**
 * IS SUBSEQUENCE
 * ----------------
 *
 * Given two strings s and t, determine whether s is a subsequence of t.
 *
 * A subsequence is formed by deleting some (or none) characters from t
 * without changing the relative order of the remaining characters.
 *
 * Example:
 *   s = "abc"
 *   t = "ahbgdc"
 *   Output: true
 *
 *   s = "axc"
 *   t = "ahbgdc"
 *   Output: false
 *
 *
 * APPROACH: Two Pointers (Greedy)
 * --------------------------------
 * - Use two pointers:
 *      i → traverses string s
 *      j → traverses string t
 *
 * - Traverse string t and try to match characters of s in order.
 * - When characters match, move pointer i.
 * - Always move pointer j.
 *
 * - If all characters of s are matched (i reaches s.length()),
 *   then s is a subsequence of t.
 *
 *
 * TIME COMPLEXITY:
 * ----------------
 * O(n), where n = length of string t
 *
 * SPACE COMPLEXITY:
 * -----------------
 * O(1), constant extra space
 *
 *
 * PATTERN:
 * --------
 * Two Pointers / Greedy
 *
 *
 * @author Charu
 */
public class IsSubSequence {

    public static void main(String[] args) {

        String s = "abc";
        String t = "ahbgdc";

        boolean isSubsequence = isSubsequence(s, t);
        System.out.println(isSubsequence);  // true
    }

    /**
     * Checks whether string s is a subsequence of string t.
     *
     * @param s the subsequence string
     * @param t the target string
     * @return true if s is a subsequence of t, otherwise false
     */
    public static boolean isSubsequence(String s, String t) {

        // Pointer for string s
        int i = 0;

        // Pointer for string t
        int j = 0;

        // Traverse both strings
        while (i < s.length() && j < t.length()) {

            // If characters match, move pointer of s
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }

            // Always move pointer of t
            j++;
        }

        // If all characters of s were matched
        return i == s.length();
    }
}
