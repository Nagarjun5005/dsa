package arrays.hashing.medium;

import java.util.ArrayList;
import java.util.List;

/*
 =============================================================================
 PROBLEM:
 Sort Characters By Frequency

 Given a string s, sort it in decreasing order based on the frequency
 of characters. Characters with higher frequency should appear first.
 If two characters have the same frequency, their relative order
 does NOT matter.

 Example:
 Input:  "tree"
 Output: "eetr" or "eert"
 =============================================================================
*/

public class SortCharactersByFrequency {

    /*
     =========================================================================
     APPROACH: BRUTE FORCE (NESTED LOOPS + SORTING)

     IDEA:
     - Traverse the string character by character
     - For each character, count how many times it appears using another loop
     - Use a boolean[] visited array to avoid counting the same character again
     - Store each unique character along with its frequency
     - Sort characters by frequency in descending order
     - Build the result string by repeating each character according to
       its frequency

     IMPORTANT NOTES:
     - No HashMap is used
     - No Bucket Sort is used
     - This approach is intentionally inefficient and serves as a baseline

     TIME COMPLEXITY:
     - Frequency counting using nested loops: O(n²)
     - Sorting unique characters: O(u log u)
       where u = number of unique characters
     - Overall: O(n²)

     SPACE COMPLEXITY:
     - visited array + list storage: O(n)
     =========================================================================
    */
    public static String frequencySortBruteForce(String s) {

        // visited[i] = true means character at index i is already counted
        boolean[] visited = new boolean[s.length()];

        /*
         List to store character-frequency pairs
         Each element is:
         pair[0] -> character
         pair[1] -> frequency (stored as int)
        */
        List<int[]> list = new ArrayList<>();

        // Outer loop: pick a character
        for (int i = 0; i < s.length(); i++) {

            // Skip if this character was already counted
            if (visited[i]) {
                continue;
            }

            int count = 0;

            // Inner loop: count frequency of s.charAt(i)
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    count++;
                    visited[j] = true;
                }
            }

            // Store character and its frequency
            list.add(new int[]{s.charAt(i), count});
        }

        /*
         Sort list by frequency in descending order
         pair[1] represents frequency
        */
        list.sort((a, b) -> b[1] - a[1]);

        // Build the result string
        StringBuilder result = new StringBuilder();

        /*
         For each character-frequency pair,
         append the character "frequency" number of times
        */
        for (int[] pair : list) {
            char ch = (char) pair[0];
            int freq = pair[1];

            for (int i = 0; i < freq; i++) {
                result.append(ch);
            }
        }

        return result.toString();
    }

    /*
     =========================================================================
     MAIN METHOD (TESTING)

     Demonstrates brute-force character frequency sorting
     =========================================================================
    */
    public static void main(String[] args) {

        System.out.println(frequencySortBruteForce("tree"));
        System.out.println(frequencySortBruteForce("cccaaa"));
        System.out.println(frequencySortBruteForce("Aabb"));
    }
}
