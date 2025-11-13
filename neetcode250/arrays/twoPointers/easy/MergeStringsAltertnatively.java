package arrays.twoPointers.easy;

/**
 * A class that provides a method to merge two strings alternatively,
 * taking one character from each string at a time.
 * If the strings have different lengths, the remaining characters
 * from the longer string are appended at the end.
 */
public class MergeStringsAltertnatively {

    /**
     * Merges two input strings alternatively by taking characters from each string one by one.
     * If one string is longer, appends the remaining part of the longer string at the end.
     *
     * @param word1 The first input string.
     * @param word2 The second input string.
     * @return A new string consisting of alternating characters from word1 and word2.
     *
     * Time Complexity: O(n + m),
     * where n is the length of word1 and m is the length of word2.
     *
     * Space Complexity: O(n + m),
     * for storing the resulting merged string.
     */
    public static String mergeTwoStringsAlternatively(String word1, String word2) {
        // Initialize two pointers for traversing word1 and word2
        int left = 0;
        int right = 0;

        // StringBuilder for efficient string construction
        StringBuilder result = new StringBuilder();

        // Traverse both strings as long as both have remaining characters
        while (left < word1.length() && right < word2.length()) {
            result.append(word1.charAt(left));  // Append character from word1
            result.append(word2.charAt(right)); // Append character from word2
            left++;
            right++;
        }

        // Append any remaining characters from word1 (if any)
        result.append(word1.substring(left));

        // Append any remaining characters from word2 (if any)
        result.append(word2.substring(right));

        // Return the merged result as a string
        return result.toString();
    }

    /**
     * The main method to test the mergeTwoStringsAlternatively method.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String word1 = "abcd";
        String word2 = "pqrs";

        String stringsAlternatively = mergeTwoStringsAlternatively(word1, word2);

        // Output: apbqcrds
        System.out.println(stringsAlternatively);
    }
}
