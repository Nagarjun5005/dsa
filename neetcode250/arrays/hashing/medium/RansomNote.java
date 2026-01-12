package arrays.hashing.medium;

import java.util.HashMap;

public class RansomNote {

    /*
    ===================================================================
    PROBLEM STATEMENT
    ===================================================================
    Given two strings:
      - ransomNote : the string that needs to be constructed
      - magazine   : the string containing available characters

    Determine whether ransomNote can be constructed using characters
    from magazine.

    RULES:
    - Each character in magazine can be used ONLY ONCE.
    - Order of characters does NOT matter.

    Example:
      ransomNote = "nagarjun"
      magazine   = "nagarjun"
      Output     = true
    ===================================================================
    */

    public static void main(String[] args) {

        String ransomNote = "nagarjun";
        String magazine = "nagarjun";

        // Brute Force approach result
        boolean bruteForceResult = canConstructBruteForce(ransomNote, magazine);

        // Optimized HashMap approach result
        boolean optimizedResult = canConstructOptimized(ransomNote, magazine);

        System.out.println("Brute Force Result     : " + bruteForceResult);
        System.out.println("Optimized Result       : " + optimizedResult);
    }

    /*
    ===================================================================
    METHOD 1: BRUTE FORCE APPROACH
    ===================================================================
    IDEA:
    - For each character in ransomNote:
        - Search for that character in magazine.
        - If found, remove it so it cannot be reused.
        - If not found, return false.

    PARAMETERS:
    - ransomNote : String (target string)
    - magazine   : String (source characters)

    RETURN:
    - true  -> ransomNote can be constructed
    - false -> otherwise

    TIME COMPLEXITY:
    - indexOf()  -> O(m)
    - delete()   -> O(m)
    - Total      -> O(n * m)

    SPACE COMPLEXITY:
    - O(m) due to StringBuilder

    WHY THIS IS BRUTE FORCE:
    - Repeated searching and shifting of characters
    - Very inefficient for large inputs
    ===================================================================
    */

    public static boolean canConstructBruteForce(String ransomNote, String magazine) {

        // Convert magazine to StringBuilder to allow character deletion
        StringBuilder sb = new StringBuilder(magazine);

        // Iterate over each character in ransomNote
        for (int i = 0; i < ransomNote.length(); i++) {
            char ch = ransomNote.charAt(i);

            // Search for character in magazine
            int index = sb.indexOf(String.valueOf(ch));

            // Character not found -> cannot construct ransomNote
            if (index == -1) {
                return false;
            }

            // Remove the used character to prevent reuse
            sb.deleteCharAt(index);
        }

        // All characters matched successfully
        return true;
    }

    /*
    ===================================================================
    METHOD 2: OPTIMIZED HASHMAP APPROACH
    ===================================================================
    IDEA:
    - Count frequency of each character in magazine using HashMap.
    - For each character in ransomNote:
        - If character not present or count is 0 -> return false
        - Otherwise, decrease its count.

    PARAMETERS:
    - ransomNote : String (target string)
    - magazine   : String (source characters)

    RETURN:
    - true  -> ransomNote can be constructed
    - false -> otherwise

    TIME COMPLEXITY:
    - O(n + m)
      where n = length of ransomNote
            m = length of magazine

    SPACE COMPLEXITY:
    - O(1) (at most 26 lowercase characters)

    WHY THIS IS OPTIMAL:
    - Constant time lookup
    - No repeated scanning
    - Industry-standard solution
    ===================================================================
    */

    public static boolean canConstructOptimized(String ransomNote, String magazine) {

        // HashMap to store character frequencies from magazine
        HashMap<Character, Integer> map = new HashMap<>();

        // Step 1: Build frequency map from magazine
        for (int i = 0; i < magazine.length(); i++) {
            char ch = magazine.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Validate ransomNote characters
        for (int i = 0; i < ransomNote.length(); i++) {
            char ch = ransomNote.charAt(i);

            // Character missing or exhausted
            if (!map.containsKey(ch) || map.get(ch) == 0) {
                return false;
            }

            // Consume one occurrence
            map.put(ch, map.get(ch) - 1);
        }

        // All characters successfully matched
        return true;
    }

    /*
    ===================================================================
    COMPARISON SUMMARY
    ===================================================================

    Brute Force:
    - Time Complexity : O(n * m)
    - Space           : O(m)
    - Simple but inefficient
    - Not suitable for large inputs

    Optimized (HashMap):
    - Time Complexity : O(n + m)
    - Space           : O(1)
    - Efficient and scalable


    */
}
