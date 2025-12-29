package stack.easy;

import java.util.Stack;

/**
 * ValidParenthesis checks whether a given string
 * containing parentheses is balanced.
 *
 * <p>
 * The string may contain the following characters:
 * <ul>
 *   <li>( )</li>
 *   <li>{ }</li>
 *   <li>[ ]</li>
 * </ul>
 * </p>
 *
 * <p>
 * The algorithm uses a Stack to validate the order and
 * type of parentheses based on the LIFO (Last In First Out)
 * principle.
 * </p>
 *
 * <p>
 * Rules for valid parentheses:
 * <ul>
 *   <li>Every opening bracket must have a corresponding closing bracket</li>
 *   <li>Brackets must be closed in the correct order</li>
 *   <li>No closing bracket should appear before its opening bracket</li>
 * </ul>
 * </p>
 *
 * <p>
 * Time Complexity: O(n), where n is the length of the string<br>
 * Space Complexity: O(n) in the worst case (all opening brackets)
 * </p>
 */
public class ValidParenthesis {

    /**
     * Entry point of the program.
     * Tests the validity of a sample parentheses string.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        String s = "{[()()]}";

        boolean valid = isValid(s);

        System.out.println("Balanced Parenthesis--?-- " + valid);
    }

    /**
     * Checks whether the given parentheses string is valid.
     *
     * <p>
     * The method iterates through each character in the string:
     * <ul>
     *   <li>If the character is an opening bracket, it is pushed onto the stack</li>
     *   <li>If the character is a closing bracket, the top of the stack is popped
     *       and checked for a matching opening bracket</li>
     * </ul>
     * </p>
     *
     * <p>
     * If at any point the stack is empty when a closing bracket is encountered,
     * or the brackets do not match, the string is invalid.
     * </p>
     *
     * @param s the input string containing parentheses
     * @return true if the parentheses are balanced, false otherwise
     */
    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            // Push opening brackets onto the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // Handle closing brackets
            else {

                // If stack is empty, no matching opening bracket exists
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                // Check for matching bracket pairs
                if ((ch == ')' && top != '(') ||
                        (ch == ']' && top != '[') ||
                        (ch == '}' && top != '{')) {
                    return false;
                }
            }
        }

        // Stack must be empty for the parentheses to be valid
        return stack.isEmpty();
    }
}
