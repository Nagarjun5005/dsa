package arrays.sliding_window.medium;

import java.util.HashMap;

/**
 * Longest Repeating Character Replacement
 *
 * Problem:
 * --------
 * Given a string `s` and an integer `k`, you are allowed to replace
 * at most `k` characters in a substring so that all characters in
 * that substring are the same.
 *
 * Return the length of the longest such substring.
 *
 * Example:
 * --------
 * Input:  s = "AABABBA", k = 1
 * Output: 4
 *
 * Explanation:
 * ------------
 * Replace one character in "AABA" or "ABBA" to make all characters equal.
 *
 * This class demonstrates TWO approaches:
 * 1. Brute Force (O(n²))
 * 2. Optimized Sliding Window (O(n))
 */
public class LongestRepeatingCharacterReplacement {

    public static void main(String[] args) {

        String s = "AABABBA";
        int k = 1;

        // Brute force solution
        int characterReplacementBrute = characterReplacementBrute(s, k);
        System.out.println(characterReplacementBrute);

        // Optimized sliding window solution
        int characterReplacement = characterReplacement(s, k);
        System.out.println(characterReplacement);
    }

    /**
     * Brute Force Approach
     *
     * Idea:
     * -----
     * - Try every possible substring
     * - Count frequency of characters in that substring
     * - Find the most frequent character
     * - Calculate how many replacements are needed:
     *
     *   replacements = substringLength - maxFrequency
     *
     * - If replacements <= k, update the maximum length
     *
     * Time Complexity:
     * ----------------
     * O(n²)
     *
     * Space Complexity:
     * -----------------
     * O(1) (At most 26 uppercase letters)
     */
    public static int characterReplacementBrute(String s, int k) {

        int maxLength = 0;

        // Try every starting index
        for (int i = 0; i < s.length(); i++) {

            HashMap<Character, Integer> freqMap = new HashMap<>();
            int maxFreq = 0;

            // Try every ending index
            for (int j = i; j < s.length(); j++) {

                char ch = s.charAt(j);
                freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);

                // Track maximum frequency in current substring
                maxFreq = Math.max(maxFreq, freqMap.get(ch));

                int windowSize = j - i + 1;
                int replacements = windowSize - maxFreq;

                // Check if we can make all characters same
                if (replacements <= k) {
                    maxLength = Math.max(maxLength, windowSize);
                }
            }
        }

        return maxLength;
    }

    /**
     * Optimized Sliding Window Approach
     *
     * Idea:
     * -----
     * - Use two pointers (left and right) to maintain a window
     * - Track frequency of characters in the current window
     * - Keep track of the most frequent character (maxFreq)
     * - Window is valid if:
     *
     *   windowSize - maxFreq <= k
     *
     * - Shrink the window from the left if it becomes invalid
     * - Update the maximum window size
     *
     * Important Optimization:
     * -----------------------
     * `maxFreq` is NOT decremented when the window shrinks.
     * Even if it becomes stale, it never affects correctness
     * and keeps the algorithm O(n).
     *
     * Time Complexity:
     * ----------------
     * O(n)
     *
     * Space Complexity:
     * -----------------
     * O(1) (At most 26 uppercase letters)
     */
    public static int characterReplacement(String s, int k) {

        HashMap<Character, Integer> freqMap = new HashMap<>();
        int left = 0;
        int maxFreq = 0;
        int maxLength = 0;

        // Expand the window using right pointer
        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);

            // Update maximum frequency seen so far
            maxFreq = Math.max(maxFreq, freqMap.get(ch));

            // Shrink window if replacements exceed k
            while ((right - left + 1) - maxFreq > k) {
                char leftChar = s.charAt(left);
                freqMap.put(leftChar, freqMap.get(leftChar) - 1);
                left++;
            }

            // Update maximum valid window size
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}

/*
📊 FULL DRY RUN (TABLE + EXPLANATION)
🟢 Step 1: right = 0
Window = "A"


freqMap = {A=1}

maxFreq = 1

windowSize = 1

replacements = 1 - 1 = 0 ≤ k ✅

✔ Valid window
✔ maxLength = 1

🟢 Step 2: right = 1
Window = "AA"


freqMap = {A=2}

maxFreq = 2

windowSize = 2

replacements = 2 - 2 = 0 ≤ k ✅

✔ Valid window
✔ maxLength = 2

🟢 Step 3: right = 2
Window = "AAB"


freqMap = {A=2, B=1}

maxFreq = 2

windowSize = 3

replacements = 3 - 2 = 1 ≤ k ✅

✔ Replace B → A
✔ maxLength = 3

🟢 Step 4: right = 3
Window = "AABA"


freqMap = {A=3, B=1}

maxFreq = 3

windowSize = 4

replacements = 4 - 3 = 1 ≤ k ✅

✔ Replace B → A
✔ maxLength = 4 ⭐⭐⭐

🔴 Step 5: right = 4
Window = "AABAB"


freqMap = {A=3, B=2}

maxFreq = 3

windowSize = 5

replacements = 5 - 3 = 2 ❌ > k

❌ Invalid window → SHRINK

Shrink from left
Remove A at index 0
Window = "ABAB"
freqMap = {A=2, B=2}
left = 1


windowSize = 4

replacements = 4 - 3 = 1 ≤ k ✅

✔ Valid again
✔ maxLength remains 4

🟢 Step 6: right = 5
Window = "ABABB"


freqMap = {A=2, B=3}

maxFreq = 3

windowSize = 5

replacements = 5 - 3 = 2 ❌

❌ Invalid → SHRINK

Shrink from left
Remove A at index 1
Window = "BABB"
freqMap = {A=1, B=3}
left = 2


windowSize = 4

replacements = 4 - 3 = 1 ≤ k ✅

✔ Valid
✔ maxLength = 4

🟢 Step 7: right = 6
Window = "BABBA"


freqMap = {A=2, B=3}

maxFreq = 3

windowSize = 5

replacements = 5 - 3 = 2 ❌

❌ Invalid → SHRINK

Shrink from left
Remove B at index 2
Window = "ABBA"
freqMap = {A=2, B=2}
left = 3


windowSize = 4

replacements = 4 - 3 = 1 ≤ k ✅

✔ Valid
✔ maxLength = 4

✅ FINAL ANSWER
maxLength = 4


Example valid substrings:

"AABA"
"ABBA"
 */