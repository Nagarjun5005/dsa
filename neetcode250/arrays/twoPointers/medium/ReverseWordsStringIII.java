package arrays.twoPointers.medium;

import java.util.Stack;

/**
 * ReverseWordsStringIII
 *
 * Problem:
 * Given a string s, reverse the characters of each word while preserving
 * the original word order and spaces.
 *
 * Example:
 * Input:  "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 *
 * This class demonstrates multiple approaches to solve the problem,
 * highlighting different patterns and trade-offs.
 */
public class ReverseWordsStringIII {

    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";

        System.out.println(reverseWords(s));
        System.out.println(reverseWordsTwoPointer(s));
        System.out.println(reverseWordsStack(s));
        System.out.println(reverseWordsCharArray(s));
    }

    /**
     * Approach 1: Split + Two Pointers (Most straightforward)
     *
     * Idea:
     * - Split the sentence into words
     * - Reverse each word using two pointers
     * - Join the words back with spaces
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static String reverseWords(String s) {
        String[] words = s.split(" ");

        for (int i = 0; i < words.length; i++) {
            char[] chars = words[i].toCharArray();
            int left = 0, right = chars.length - 1;

            while (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
            words[i] = new String(chars);
        }
        return String.join(" ", words);
    }

    /**
     * Approach 2: Two Pointers Without Split (Interview Favorite)
     *
     * Idea:
     * - Traverse the string character by character
     * - Reverse each word on the fly
     * - Append spaces as they appear
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static String reverseWordsTwoPointer(String s) {
        StringBuilder result = new StringBuilder();
        int i = 0, n = s.length();

        while (i < n) {
            if (s.charAt(i) == ' ') {
                result.append(' ');
                i++;
            } else {
                int start = i;
                while (i < n && s.charAt(i) != ' ') {
                    i++;
                }
                for (int j = i - 1; j >= start; j--) {
                    result.append(s.charAt(j));
                }
            }
        }
        return result.toString();
    }

    /**
     * Approach 3: Stack-Based Reversal
     *
     * Idea:
     * - Push characters of a word onto a stack
     * - Pop them when a space is encountered
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Note:
     * Useful for conceptual clarity but not optimal due to stack overhead.
     */
    public static String reverseWordsStack(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                while (!stack.isEmpty()) {
                    result.append(stack.pop());
                }
                result.append(' ');
            } else {
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    /**
     * Approach 4: Char Array In-Place Reversal (Closest to In-Place in Java)
     *
     * Idea:
     * - Convert string to char array
     * - Reverse each word directly in the array
     * - Convert back to string
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static String reverseWordsCharArray(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int start = 0;

        for (int i = 0; i <= n; i++) {
            if (i == n || chars[i] == ' ') {
                reverse(chars, start, i - 1);
                start = i + 1;
            }
        }
        return new String(chars);
    }

    /**
     * Helper method to reverse a section of a char array.
     */
    private static void reverse(char[] chars, int l, int r) {
        while (l < r) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;
            r--;
        }
    }
}

