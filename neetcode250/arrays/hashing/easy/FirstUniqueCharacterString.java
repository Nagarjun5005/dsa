package arrays.hashing.easy;

import java.util.HashMap;
import java.util.Map;

/*
 =============================================================================
 PROBLEM:
 First Unique Character in a String (LeetCode 387)

 Given a string s, return the index of the FIRST character that appears
 exactly once in the string.

 If no such character exists, return -1.
 =============================================================================
*/

public class FirstUniqueCharacterString {

    /*
     =========================================================================
     MAIN METHOD
     Used for local testing of all approaches
     =========================================================================
    */
    public static void main(String[] args) {

        String s = "nagarjun";

        System.out.println("Brute Force Result:");
        System.out.println(firstUniqChar(s));

        System.out.println("HashMap Result:");
        System.out.println(firstUniqCharHashMap(s));

        System.out.println("Array (Optimized) Result:");
        System.out.println(firstUniqCharArray(s));
    }

    /*
     =========================================================================
     APPROACH 1: BRUTE FORCE

     IDEA:
     - For each character at index i, check the entire string
       to see if the same character appears anywhere else.
     - If it does not appear at any other index, it is unique.

     IMPORTANT:
     - We use (i != j) to avoid comparing the character with itself
     - This checks duplicates both BEFORE and AFTER index i

     TIME COMPLEXITY:
     - O(n²)

     SPACE COMPLEXITY:
     - O(1)
     =========================================================================
    */
    public static int firstUniqChar(String s) {

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            boolean isUnique = true;

            for (int j = 0; j < s.length(); j++) {
                if (i != j && ch == s.charAt(j)) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                return i;
            }
        }
        return -1;
    }

    /*
     =========================================================================
     APPROACH 2: OPTIMAL SOLUTION USING HASHMAP

     IDEA:
     - Count frequency of each character using HashMap
     - Traverse the string again to find the first character
       with frequency = 1

     WHY HASHMAP?
     - Works for ANY character set (uppercase, lowercase, symbols, Unicode)
     - Simple and readable

     TIME COMPLEXITY:
     - O(n)

     SPACE COMPLEXITY:
     - O(n)
     =========================================================================
    */
    public static int firstUniqCharHashMap(String s) {

        Map<Character, Integer> map = new HashMap<>();

        // Step 1: Count frequency
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        // Step 2: Find first unique character
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }

    /*
     =========================================================================
     APPROACH 3: OPTIMAL SOLUTION USING ARRAY (MOST EFFICIENT)

     IDEA:
     - Since the problem guarantees lowercase English letters,
       we can use a fixed-size array of length 26.
     - Each index represents a character ('a' to 'z').

     STEPS:
     1. Count frequency using int[26]
     2. Scan string to find first character with count = 1

     WHY THIS IS BEST WHEN APPLICABLE:
     - Fastest
     - Constant extra space
     - No hashing overhead

     TIME COMPLEXITY:
     - O(n)

     SPACE COMPLEXITY:
     - O(1)
     =========================================================================
    */
    public static int firstUniqCharArray(String s) {

        int[] freq = new int[26];

        // Step 1: Count frequency
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // Step 2: Find first unique character
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }
}
