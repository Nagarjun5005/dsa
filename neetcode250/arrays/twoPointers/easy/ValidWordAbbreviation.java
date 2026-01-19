package arrays.twoPointers.easy;

/*
 =============================================================================
 PROBLEM: Valid Word Abbreviation (LeetCode 408)

 A string can be abbreviated by replacing non-adjacent, non-empty substrings
 with their lengths (without leading zeros).

 You are given:
 - word : the original string
 - abbr : the abbreviation

 Return true if abbr is a valid abbreviation of word, else false.

 -----------------------------------------------------------------------------
 EXAMPLES:
 word = "apple", abbr = "a3e"      → true
 word = "international", abbr = "i9l" → false
 word = "abbreviation", abbr = "abbreviation" → true

 -----------------------------------------------------------------------------
 KEY RULES:
 1. Letters in abbr must match exactly with word.
 2. Digits in abbr represent how many characters to skip in word.
 3. Numbers cannot have leading zeros.
 4. Abbreviation must consume the entire word.
 =============================================================================
*/

public class ValidWordAbbreviation {

    /*
     =========================================================================
     APPROACH: TWO POINTERS (MERGE / COMPARE PATTERN)

     We use two pointers:
     - i → pointer for `word`
     - j → pointer for `abbr`

     We move both pointers forward based on the character at abbr[j].

     CASE 1: abbr[j] is a LETTER
       - It must match word[i]
       - If it matches → move both pointers
       - If it doesn't → invalid abbreviation

     CASE 2: abbr[j] is a DIGIT
       - Parse the full number (can be multiple digits)
       - This number tells how many characters to skip in `word`
       - Add that number to pointer i

     INVALID CASES:
       - abbr contains a number starting with '0'
       - pointer i goes out of bounds
       - leftover characters in word or abbr

     TIME COMPLEXITY:
     - O(n + m), where n = word length, m = abbr length

     SPACE COMPLEXITY:
     - O(1), no extra data structures used
     =========================================================================
    */
    public static boolean validWordAbbreviation(String word, String abbr) {

        int n = word.length();
        int m = abbr.length();

        // Pointer for word and abbreviation
        int i = 0, j = 0;

        // Traverse both strings
        while (i < n && j < m) {

            // Leading zero is NOT allowed (e.g., "i012n")
            if (abbr.charAt(j) == '0') {
                return false;
            }

            // Case 1: Current character in abbr is a letter
            if (Character.isLetter(abbr.charAt(j))) {

                // Letter must match exactly with word
                if (i < n && word.charAt(i) == abbr.charAt(j)) {
                    i++; // move in word
                    j++; // move in abbr
                } else {
                    return false;
                }
            }
            // Case 2: Current character in abbr is a digit
            else {

                int subLen = 0;

                // Parse the full number (may have multiple digits)
                while (j < m && Character.isDigit(abbr.charAt(j))) {
                    int check=abbr.charAt(j);
                    subLen = subLen * 10 + (abbr.charAt(j) - '0');
                    j++;
                }

                // Skip subLen characters in word
                i += subLen;
            }
        }

        // Both pointers must reach the end for a valid abbreviation
        return i == n && j == m;
    }

    /*
     =========================================================================
     MAIN METHOD (TESTING)
     =========================================================================
    */
    public static void main(String[] args) {

        String word = "implementation";
        String abbr = "i12n";

        boolean result = validWordAbbreviation(word, abbr);
        System.out.println(result); // true
    }
}
