package arrays.hashing.medium;


import java.util.*;

/*
 =============================================================================
 PROBLEM:
 Sort Characters By Frequency

 Given a string s, sort the characters in decreasing order of their
 frequency. Characters with higher frequency appear before characters
 with lower frequency. If two characters have the same frequency, their
 relative order does not matter.

 Example:
 Input:  "tree"
 Output: "eetr" or "eert"
 =============================================================================
*/

public class SortCharactersByFrequency {

    /*
     =========================================================================
     MAIN METHOD

     Used to test both brute-force and optimized (bucket sort) approaches.
     =========================================================================
    */
    public static void main(String[] args) {
        String s = "nagarjun";

        // Brute force result
        System.out.println(frequencySortBrute(s));

        // Optimized bucket sort result
        System.out.println(frequencySort(s));
    }

    /*
     =========================================================================
     APPROACH 1: BRUTE FORCE (NESTED LOOPS + SORTING)

     IDEA:
     - Traverse the string character by character
     - For each character, count how many times it appears using another loop
     - Use a boolean[] visited array to avoid counting the same character again
     - Store each unique character with its frequency
     - Sort characters by frequency in descending order
     - Build the result string by repeating each character according to
       its frequency

     DATA STRUCTURES USED:
     - boolean[] visited → tracks already-counted characters
     - List<char[]> → stores {character, frequency}

     TIME COMPLEXITY:
     - Frequency counting using nested loops: O(n²)
     - Sorting unique characters: O(u log u), u = number of unique characters
     - Overall: O(n²)

     SPACE COMPLEXITY:
     - visited array + list: O(n)
     =========================================================================
    */
    public static String frequencySortBrute(String s) {

        // visited[i] = true means character at index i is already counted
        boolean[] visited = new boolean[s.length()];

        /*
         Each char[] pair:
         pair[0] → character
         pair[1] → frequency (stored as char but used as int)
        */
        List<char[]> list = new ArrayList<>();

        // Outer loop: select a character
        for (int i = 0; i < s.length(); i++) {

            // Skip if already processed
            if (visited[i]) {
                continue;
            }

            int count = 0;

            // Inner loop: count occurrences of s.charAt(i)
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    count++;
                    visited[j] = true;
                }
            }

            // Store character and its frequency
            list.add(new char[]{s.charAt(i), (char) count});
        }

        /*
         Sort list by frequency in descending order
         pair[1] represents frequency
        */
        list.sort((a, b) -> b[1] - a[1]);

        // Build the result string
        StringBuilder result = new StringBuilder();

        // Append each character frequency number of times
        for (char[] pair : list) {
            char ch = pair[0];
            int freq = pair[1];

            for (int i = 0; i < freq; i++) {
                result.append(ch);
            }
        }

        return result.toString();
    }

    /*
     =========================================================================
     APPROACH 2: OPTIMAL (HASHMAP + BUCKET SORT)

     IDEA:
     - Count frequency of each character using HashMap
     - Create a bucket array where:
         index = frequency
         value = list of characters with that frequency
     - Traverse the bucket array from highest frequency to lowest
     - Build the result string by repeating characters based on frequency

     WHY BUCKET SORT?
     - Maximum frequency of any character is bounded by s.length()
     - Avoids sorting all characters globally
     - Achieves linear time complexity

     DATA STRUCTURES USED:
     - HashMap<Character, Integer> → character → frequency
     - List<Character>[] → frequency-indexed buckets

     TIME COMPLEXITY:
     - Frequency counting: O(n)
     - Bucket placement: O(n)
     - Result construction: O(n)
     - Overall: O(n)

     SPACE COMPLEXITY:
     - HashMap + bucket array: O(n)
     =========================================================================
    */
    public static String frequencySort(String s) {

        // Step 1: Frequency map
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            count.put(s.charAt(i), count.getOrDefault(s.charAt(i), 0) + 1);
        }

        /*
         Step 2: Bucket array
         freq[i] contains characters that appear exactly i times
        */
        List<Character>[] freq = new List[s.length() + 1];

        // Initialize buckets
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        // Step 3: Place characters into buckets
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            freq[entry.getValue()].add(entry.getKey());
        }

        // Step 4: Build result string from highest frequency to lowest
        StringBuilder result = new StringBuilder();

        for (int i = freq.length - 1; i > 0; i--) {
            for (Character c : freq[i]) {
                for (int j = 0; j < i; j++) {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}

