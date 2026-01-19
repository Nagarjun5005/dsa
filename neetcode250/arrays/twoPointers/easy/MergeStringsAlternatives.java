package arrays.twoPointers.easy;

/*
 =============================================================================
 PROBLEM: Merge Strings Alternately (LeetCode 1768)

 Given two strings word1 and word2, merge them by adding characters
 in alternating order, starting with word1.
 If one string is longer than the other, append the remaining characters
 to the end of the merged string.

 Example:
 word1 = "abc"
 word2 = "pqr"
 Output = "apbqcr"

 Example:
 word1 = "ab"
 word2 = "pqrs"
 Output = "apbqrs"
 =============================================================================
*/

public class MergeStringsAlternatives {

    /*
     =========================================================================
     APPROACH: TWO POINTERS (MERGE / COMPARE PATTERN)

     IDEA:
     - Use two pointers:
         i → pointer for word1
         j → pointer for word2
     - Append characters alternately from each string.
     - When one string is exhausted, append the remaining characters
       from the other string.

     WHY THIS IS A MERGE / COMPARE PROBLEM:
     - We traverse two sequences in parallel.
     - Both pointers move forward independently.
     - No backward or inward movement is required.

     TIME COMPLEXITY:
     - O(n + m), where:
         n = length of word1
         m = length of word2

     SPACE COMPLEXITY:
     - O(n + m) for the StringBuilder result.
     =========================================================================
    */
    public static String mergeAlternately(String word1, String word2) {

        StringBuilder merged = new StringBuilder();

        int i = 0; // pointer for word1
        int j = 0; // pointer for word2

        // Merge characters alternately while both strings have characters
        while (i < word1.length() && j < word2.length()) {
            merged.append(word1.charAt(i));
            merged.append(word2.charAt(j));
            i++;
            j++;
        }

        // Append remaining characters from word1 (if any)
        while (i < word1.length()) {
            merged.append(word1.charAt(i));
            i++;
        }

        // Append remaining characters from word2 (if any)
        while (j < word2.length()) {
            merged.append(word2.charAt(j));
            j++;
        }

        return merged.toString();
    }

    /*
     =========================================================================
     MAIN METHOD (TESTING)
     =========================================================================
    */
    public static void main(String[] args) {

        String name1 = "nagarjunnn";
        String name2 = "surabhi";

        String mergedAlternately = mergeAlternately(name1, name2);
        System.out.println(mergedAlternately);
    }
}
