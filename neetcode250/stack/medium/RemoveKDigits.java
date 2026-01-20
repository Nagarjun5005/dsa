package stack.medium;

import java.util.Stack;

/*
 =============================================================================
 PROBLEM: 402. Remove K Digits (LeetCode)

 Given a string num representing a non-negative integer, and an integer k,
 remove k digits from the number so that the resulting number is the smallest
 possible.

 NOTE:
 - The result should not contain leading zeros.
 - If all digits are removed, return "0".

 Example:
 Input:  num = "1432219", k = 3
 Output: "1219"
 =============================================================================
*/

class RemoveKDigits {


    /*
     =========================================================================
     OPTIMAL GREEDY SOLUTION (MONOTONIC STACK)

     CORE IDEA:
     - To make the number smallest, we want smaller digits as early as possible.
     - If a digit is larger than the next digit, removing it makes the number
       smaller.
     - We use a monotonic increasing stack to enforce this greedily.

     STACK INVARIANT:
     - Digits in the stack are always in increasing order (bottom → top).

     WHEN TO POP:
     - While the current digit is smaller than the stack's top AND
       we still have digits to remove (k > 0).

     WHY THIS WORKS:
     - Larger digits before smaller digits hurt the number.
     - Greedily removing them leads to a globally minimal result.

     PATTERN:
     - Monotonic Increasing Stack
     - Greedy

     TIME COMPLEXITY:
     - O(n)

     SPACE COMPLEXITY:
     - O(n)
     =========================================================================
    */
    public static String removeKdigits(String num, int k) {

        Stack<Character> stack = new Stack<>();

        /*
         ---------------------------------------------------------------
         STEP 1: Build a monotonic increasing stack
         ---------------------------------------------------------------
        */
        for (char digit : num.toCharArray()) {

            // Remove larger digits before smaller ones
            while (!stack.isEmpty() && k > 0 && stack.peek() > digit) {
                stack.pop();
                k--;
            }

            stack.push(digit);
        }

        /*
         ---------------------------------------------------------------
         STEP 2: If k is still remaining, remove digits from the end

         This happens when the number is already increasing (e.g., "12345").
         Removing digits from the end removes the largest digits.
         ---------------------------------------------------------------
        */
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        /*
         ---------------------------------------------------------------
         STEP 3: Build the final result from the stack

         IMPORTANT:
         - Iterating over Stack goes from bottom → top
         - This preserves the correct left-to-right order
         ---------------------------------------------------------------
        */
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }

        /*
         ---------------------------------------------------------------
         STEP 4: Remove leading zeros

         Example:
         "00123" → "123"
         ---------------------------------------------------------------
        */
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        // Edge case: if all digits are removed
        if (sb.length() == 0) {
            return "0";
        }

        return sb.toString();
    }

    /*
     =========================================================================
     MAIN METHOD (TESTING)
     =========================================================================
    */
    public static void main(String[] args) {

        String num = "1432219";
        int k = 3;

        String result = removeKdigits(num, k);
        System.out.println(result); // Expected Output: "1219"
    }
}
