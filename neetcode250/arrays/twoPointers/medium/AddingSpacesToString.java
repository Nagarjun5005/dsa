package arrays.twoPointers.medium;

/**
 * AddingSpacesToString
 *
 * Problem:
 * Given a string and an array of indices, insert a space character at
 * each given index in the string.
 *
 * Example:
 * Input:
 *   s = "LeetcodeHelpsMeLearn"
 *   spaces = [8, 13, 15]
 *
 * Output:
 *   "Leetcode Helps Me Learn"
 *
 * This class demonstrates two efficient approaches.
 */
public class AddingSpacesToString {

    public static void main(String[] args) {
        String word = "LeetcodeHelpsMeLearn";
        int[] spaces = {8, 13, 15};

        String result1 = addSpaces(word, spaces);
        System.out.println(result1);

        String result2 = addSpacesOther(word, spaces);
        System.out.println(result2);
    }

    /**
     * Approach 1: Two Pointers + StringBuilder (Optimal & Preferred)
     *
     * Idea:
     * - Traverse the string character by character
     * - Maintain a pointer on the spaces array
     * - Insert a space when the index matches
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static String addSpaces(String s, int[] spaces) {
        int spaceIndex = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (spaceIndex < spaces.length && i == spaces[spaceIndex]) {
                sb.append(' ');
                spaceIndex++;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    /**
     * Approach 2: Reverse Insertion
     *
     * Idea:
     * - Insert spaces from right to left
     * - Prevents index shifting issues
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static String addSpacesOther(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder(s);

        for (int i = spaces.length - 1; i >= 0; i--) {
            sb.insert(spaces[i], ' ');
        }
        return sb.toString();
    }
}

