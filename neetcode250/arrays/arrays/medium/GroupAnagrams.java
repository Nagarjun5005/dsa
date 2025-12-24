package arrays.arrays.medium;

import java.util.*;

/**
 * The {@code GroupAnagrams} class provides three different approaches to group
 * words that are anagrams of each other.
 *
 * An anagram is defined as a word formed by rearranging the letters of another word.
 * Example:
 * "eat", "tea", and "ate" are anagrams because they contain the same characters.
 *
 * This class demonstrates:
 * 1. Brute-force method using sorting + hashing
 * 2. Improved method using sorting + putIfAbsent
 * 3. Optimal method using character frequency (hashing only)
 *
 * All methods return a list of groups, where each group contains strings that
 * are anagrams of each other.
 */
public class GroupAnagrams {

    /**
     * Brute-force method using sorting and HashMap.
     *
     * Strategy:
     * ---------
     * - Convert each word into a char array
     * - Sort the characters
     * - Convert back to string → this sorted string becomes the 'key'
     * - Insert the original word into the map based on this key
     *
     * Example:
     *   "eat" → "aet"
     *   "tea" → "aet"
     *   "ate" → "aet"
     *
     * All these words are grouped together.
     *
     * Time Complexity:  O(m * n log n)
     *    m = number of strings
     *    n = average length of each string
     *
     * Space Complexity: O(m * n)
     *
     * @param strs array of input strings
     * @return list of grouped anagrams
     */
    public static List<List<String>> groupAnagramsBrute(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars); // sorting the characters
            String sortedWord = new String(chars);

            // Add word to its group
            map.putIfAbsent(sortedWord, new ArrayList<>());
            map.get(sortedWord).add(word);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * Cleaner version of brute approach using putIfAbsent.
     *
     * Time Complexity:  O(m * n log n)
     * Space Complexity: O(m * n)
     *
     * @param strs input strings
     * @return grouped anagrams
     */
    public static List<List<String>> groupAnagramsBetter(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sortedWord = new String(charArray);

            map.putIfAbsent(sortedWord, new ArrayList<>());
            map.get(sortedWord).add(word);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * Optimal approach using character frequency as key.
     *
     * Why this works:
     * ---------------
     * All anagrams have the same count of each character.
     * Example:
     *   "eat" → a:1, e:1, t:1
     *   "tea" → a:1, e:1, t:1
     *
     * Instead of sorting, we create an array of size 26 that stores frequency
     * of each character.
     *
     * Example key:
     *   [1,0,0,0,...,1,...1] → unique signature for anagrams
     *
     * Time Complexity:  O(m * n)
     * Space Complexity: O(m * n)
     *
     * @param strs input strings
     * @return list of grouped anagrams
     */
    public static List<List<String>> groupAnagramsOptimal(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            int[] count = new int[26];

            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            // Convert frequency array into a string key
            String key = Arrays.toString(count);

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * Driver method for testing the three approaches.
     */
    public static void main(String[] args) {

        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};

        // Brute-force
        System.out.println("Brute Force Output:");
        System.out.println(groupAnagramsBrute(input));

        // Better approach
        System.out.println("\nBetter Approach Output:");
        System.out.println(groupAnagramsBetter(input));

        // Optimal approach
        System.out.println("\nOptimal Approach Output:");
        System.out.println(groupAnagramsOptimal(input));
    }
}


/*
ChatGPT said:

Of course, Charu — let’s walk through the optimal approach for Group Anagrams step by step, using a full iteration walkthrough.

This is the character frequency hashing method, which runs in:

Time:  O(m * n)
Space: O(m * n)


Where:

m = number of words

n = average length of each word

⭐ Key Idea

Instead of sorting each string, we build a frequency array of size 26:

count[c - 'a']++


This array becomes the unique signature (key) of all anagrams.

Example:

"eat" →  [1,0,0,0,...,1,...,1,0]
"tea" →  same
"ate" →  same


Then we convert this array to a string using:

String key = Arrays.toString(count);


This ensures all anagrams share EXACTLY the same key in HashMap.

⭐ INPUT

Let's use your example:

["eat", "tea", "tan", "ate", "nat", "bat"]

🔥 FULL ITERATION WALKTHROUGH
Iteration 1: word = "eat"

Initialize count array:

[0,0,0,0,0,0, ... 0]   (26 zeroes)


Process letters:

'e' → count[4]++

'a' → count[0]++

't' → count[19]++

Final count:

[a:1, e:1, t:1] →
[1,0,0,0,1,0,0,...,1,0,0]


Key becomes:

"[1, 0, 0, 0, 1, 0, 0, 0, 0, ..., 1, 0]"


HashMap:

key → ["eat"]

Iteration 2: word = "tea"

Count letters:

't' → count[19]++

'e' → count[4]++

'a' → count[0]++

Same count array as "eat".

Key is the SAME.

HashMap becomes:

key → ["eat", "tea"]

Iteration 3: word = "tan"

Count:

't' → count[19]++

'a' → count[0]++

'n' → count[13]++

Key for "tan":

[a:1, n:1, t:1]


HashMap:

key1 → ["eat","tea"]
key2 → ["tan"]

Iteration 4: word = "ate"

Count:

'a' → count[0]++

't' → count[19]++

'e' → count[4]++

This matches the same signature as "eat" and "tea".

So append to the same group:

key1 → ["eat", "tea", "ate"]
key2 → ["tan"]

Iteration 5: word = "nat"

Count:

'n', 'a', 't'

Signature same as "tan".

Map:

key1 → ["eat", "tea", "ate"]
key2 → ["tan", "nat"]

Iteration 6: word = "bat"

Count:

'b' → count[1]++

'a' → count[0]++

't' → count[19]++

Signature:

[a:1, b:1, t:1]


New key.

Map:

key1 → ["eat", "tea", "ate"]
key2 → ["tan", "nat"]
key3 → ["bat"]

⭐ FINAL RESULT
[
  ["eat","tea","ate"],
  ["tan","nat"],
  ["bat"]
]
 */
