package arrays.twoPointers.easy;

import java.util.Arrays;

/*
 =============================================================================
 PROBLEM:
 Reverse String (LeetCode 344)

 Given a character array s, reverse the array in-place and return it.

 Example:
 Input:  ['h','e','l','l','o']
 Output: ['o','l','l','e','h']
 =============================================================================
*/

public class ReverseString {

    /*
     =========================================================================
     APPROACH 1: BRUTE FORCE SOLUTION

     IDEA:
     - Create a new character array of the same size
     - Traverse the original array from right to left
     - Copy characters into the new array from left to right
     - Return the new array

     WHY THIS IS BRUTE FORCE:
     - Uses extra space
     - Does not modify the array in-place
     - Straightforward and easy to understand

     TIME COMPLEXITY:
     - O(n) → we traverse the array once

     SPACE COMPLEXITY:
     - O(n) → extra array used
     =========================================================================
    */
    public static char[] reverseStringBrute(char[] s) {

        int n = s.length;
        char[] result = new char[n];

        int index = 0;
        for (int i = n - 1; i >= 0; i--) {
            result[index++] = s[i];
        }

        return result;
    }

    /*
     =========================================================================
     APPROACH 2: TWO POINTERS (OPPOSITE DIRECTION)

     IDEA:
     - Use two pointers:
         i → start of array
         j → end of array
     - Swap characters at i and j
     - Move i forward and j backward
     - Stop when i >= j

     WHY THIS IS OPTIMAL:
     - Reverses the array in-place
     - No extra space required

     TIME COMPLEXITY:
     - O(n)

     SPACE COMPLEXITY:
     - O(1)
     =========================================================================
    */
    public static char[] reverseString(char[] s) {

        int i = 0;
        int j = s.length - 1;

        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }

        return s;
    }

    /*
     =========================================================================
     MAIN METHOD
     =========================================================================
    */
    public static void main(String[] args) {

        char[] data = {'h', 'e', 'l', 'l', 'o'};

        System.out.println("Brute Force Output:");
        System.out.println(Arrays.toString(reverseStringBrute(data)));

        char[] data2 = {'h', 'e', 'l', 'l', 'o'};
        System.out.println("Two Pointer Output:");
        System.out.println(Arrays.toString(reverseString(data2)));
    }
}
