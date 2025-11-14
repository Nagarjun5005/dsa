package linkedList.medium;

import neetcode250.linkedList.easy.Node;
import java.util.Stack;

/**
 * ---------------------------------------------------------------------
 *  Add Two Numbers II  (LeetCode 445)
 * ---------------------------------------------------------------------
 *
 * Problem:
 *      Two linked lists represent two non-negative integers where
 *      digits are stored in FORWARD ORDER.
 *
 *      Example:
 *          l1 = 7 → 2 → 4 → 3   (7243)
 *          l2 = 5 → 6 → 4       (564)
 *
 *          Output:
 *          7 → 8 → 0 → 7   (7243 + 564 = 7807)
 *
 * Key Difference from Add Two Numbers I:
 *      - Digits are in forward order, so we cannot add from head.
 *      - We must add from the END → use stacks.
 *
 * Approach:
 *      1. Push all digits of both lists into two stacks.
 *      2. Pop from stacks to simulate right-to-left addition.
 *      3. Use carry just like manual addition.
 *      4. Always insert new nodes at the FRONT of the result list.
 *
 * Why Stacks?
 *      Stacks reverse the order of digits so that we can:
 *      - Access least significant digit first
 *      - Perform correct addition
 *
 * Complexity:
 *      Time:  O(n + m)
 *      Space: O(n + m)
 *
 * ---------------------------------------------------------------------
 */
public class AddTwoNumbers2 {

    public static void main(String[] args) {

        // Input linked lists (7243 + 564)
        Node a = new Node(7);
        a.next = new Node(2);
        a.next.next = new Node(4);
        a.next.next.next = new Node(3);    // 7243

        Node b = new Node(5);
        b.next = new Node(6);
        b.next.next = new Node(4);         // 564

        System.out.println("Input List 1:");
        printList(a);
        System.out.println("Input List 2:");
        printList(b);

        Node result = addTwoNumbers(a, b);

        System.out.println("Result:");
        printList(result);
    }

    /**
     * -----------------------------------------------------------------
     * addTwoNumbers()
     * -----------------------------------------------------------------
     *
     * Adds two numbers represented by linked lists where digits are
     * stored in FORWARD ORDER.
     *
     * Steps:
     *      1. Push all digits of both lists into two stacks.
     *      2. Pop values to get digits from the end.
     *      3. Add digits + carry.
     *      4. Create a new node and attach it to the FRONT of result.
     *
     * Explanation of Adding Using Stacks:
     *      l1 = 7 → 2 → 4 → 3
     *      Stack1 = [7, 2, 4, 3]
     *
     *      l2 = 5 → 6 → 4
     *      Stack2 = [5, 6, 4]
     *
     *      Pop and add:
     *          3 + 4 = 7
     *          4 + 6 = 10  (digit 0, carry 1)
     *          2 + 5 + 1 = 8
     *          7 + 0 = 7
     *
     * @param l1 first number in forward-order linked list
     * @param l2 second number in forward-order linked list
     * @return head of resulting linked list (also in forward order)
     * -----------------------------------------------------------------
     */
    public static Node addTwoNumbers(Node l1, Node l2) {

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        // Push all values of list1 into stack1
        while (l1 != null) {
            stack1.push(l1.data);
            l1 = l1.next;
        }

        // Push all values of list2 into stack2
        while (l2 != null) {
            stack2.push(l2.data);
            l2 = l2.next;
        }

        int carry = 0;
        Node head = null;   // Result list will be built from FRONT

        // Process while either stack has values OR carry remains
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {

            int x = stack1.isEmpty() ? 0 : stack1.pop();
            int y = stack2.isEmpty() ? 0 : stack2.pop();

            int sum = x + y + carry;

            carry = sum / 10;        // integer division for new carry
            int digit = sum % 10;    // digit to store in node

            // Create new node and insert at FRONT of result list
            Node newNode = new Node(digit);
            newNode.next = head;     // attach existing list
            head = newNode;          // update head
        }

        return head;
    }

    /**
     * Utility method to print linked list in readable form.
     */
    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) System.out.print(" → ");
            head = head.next;
        }
        System.out.println();
    }
}
