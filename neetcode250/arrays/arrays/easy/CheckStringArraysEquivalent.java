package arrays.arrays.easy;

/**
 * Problem: Check if Two String Arrays are Equivalent
 *
 * Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
 * A string is represented by an array if the array elements concatenated in order form the string.
 *
 * Example 1:
 * Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
 * Output: true
 * Explanation: "ab" + "c" = "abc" and "a" + "bc" = "abc"
 *
 * Example 2:
 * Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
 * Output: false
 * Explanation: "a" + "cb" = "acb" is not equal to "ab" + "c" = "abc"
 *
 * Example 3:
 * Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
 * Output: true
 * Explanation: "abc" + "d" + "defg" = "abcddefg"
 *
 * Solution provided using:
 * 1. Brute-force approach with StringBuilder.
 * 2. Optimal two-pointer approach.
 */
public class CheckStringArraysEquivalent {

    /**
     * Method: stringsEqual
     * Brute-force approach.
     * Concatenates all strings from both arrays and compares the final strings.
     *
     * @param word1 First string array
     * @param word2 Second string array
     * @return true if concatenated strings are equal, false otherwise
     */
    public static boolean stringsEqual(String[] word1, String[] word2) {
        // Building the first complete string
        StringBuilder sb1 = new StringBuilder();
        for (String letter : word1) {
            sb1.append(letter);
        }

        // Building the second complete string
        StringBuilder sb2 = new StringBuilder();
        for (String letter : word2) {
            sb2.append(letter);
        }

        // Comparing the final built strings
        return sb1.toString().equals(sb2.toString());
    }

    /**
     * Method: stringsEqualOptimal
     * Optimal solution using a two-pointer technique.
     * Traverses the characters of both arrays without concatenating them.
     *
     * @param word1 First string array
     * @param word2 Second string array
     * @return true if concatenated strings are equal, false otherwise
     *
     * Explanation:
     * - w1 and w2: indexes of the current string in word1 and word2 respectively.
     * - i and j: indexes of the current character in the current string.
     */
    public static boolean stringsEqualOptimal(String[] word1, String[] word2) {
        int w1 = 0, w2 = 0; // Indexes of current words
        int i = 0, j = 0;   // Indexes of current characters within the current word

        // Traverse both arrays until one is fully traversed
        while (w1 < word1.length && w2 < word2.length) {
            // Compare current characters
            if (word1[w1].charAt(i) != word2[w2].charAt(j)) {
                return false; // Mismatch found
            }

            // Move to the next character in both words
            i++;
            j++;

            // If end of current word1 string is reached, move to the next word
            if (i == word1[w1].length()) {
                w1++;
                i = 0;
            }

            // If end of current word2 string is reached, move to the next word
            if (j == word2[w2].length()) {
                w2++;
                j = 0;
            }
        }

        // Both arrays should be completely traversed for a valid match
        return w1 == word1.length && w2 == word2.length;
    }

    /**
     * Main method to test both brute-force and optimal solutions.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        String[] word1 = {"ab", "c"};
        String[] word2 = {"a", "bc"};

        // Testing brute-force method
        boolean stringsEqual = stringsEqual(word1, word2);
        System.out.println("Are strings equal (Brute-force)? " + stringsEqual);

        // Testing optimal method
        boolean equalOptimal = stringsEqualOptimal(word1, word2);
        System.out.println("Are strings equal (Optimal)? " + equalOptimal);
    }
}
