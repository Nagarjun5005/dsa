package linkedList.medium;

import neetcode250.linkedList.easy.Node;

/**
 * --------------------------------------------------------------------
 *  Add Two Numbers (Linked List)
 * --------------------------------------------------------------------
 *
 *  Problem:
 *      You are given two non-empty linked lists representing two
 *      non-negative integers. The digits are stored in REVERSE ORDER,
 *      and each node contains a single digit. Add the two numbers
 *      and return the result as a linked list.
 *
 *  Example:
 *      l1: 2 → 4 → 3   represents number 342
 *      l2: 5 → 6 → 4   represents number 465
 *
 *      Output: 7 → 0 → 8  (342 + 465 = 807)
 *
 *  Key Points:
 *      ✔ Digits are in reverse order
 *      ✔ Each node stores one digit
 *      ✔ Add digit-by-digit like primary school addition
 *      ✔ Use carry when sum >= 10
 *      ✔ Lists may be of UNEQUAL LENGTH
 *      ✔ Final carry may produce an extra node
 *
 *  Technique:
 *      Use a dummy head node to simplify building the result.
 *
 *  Time Complexity: O(max(n, m))
 *  Space Complexity: O(max(n, m))
 * --------------------------------------------------------------------
 */
public class AddTwoNumbers {

    public static void main(String[] args) {

        // Example 1: Equal Length Lists
        Node l1 = new Node(2);
        l1.next = new Node(4);
        l1.next.next = new Node(3);   // number = 342

        Node l2 = new Node(5);
        l2.next = new Node(6);
        l2.next.next = new Node(4);   // number = 465

        System.out.println("Input List 1:");
        printList(l1);
        System.out.println("Input List 2:");
        printList(l2);

        System.out.println("After adding two numbers:");
        Node added = addTwoNumbers(l1, l2);
        printList(added);


        // Example 2: Unequal Length Lists (9999 + 65 = 10064)
        Node a = new Node(9);
        a.next = new Node(9);
        a.next.next = new Node(9);
        a.next.next.next = new Node(9);

        Node b = new Node(5);
        b.next = new Node(6);

        System.out.println("\nUnequal Length Input List 1:");
        printList(a);
        System.out.println("Unequal Length Input List 2:");
        printList(b);

        Node result2 = addTwoNumbers(a, b);

        System.out.println("Unequal Length Output:");
        printList(result2);
    }

    /**
     * --------------------------------------------------------------------
     * addTwoNumbers()
     * --------------------------------------------------------------------
     *
     * Adds two numbers represented as linked lists where digits are stored
     * in reverse order. The result is returned in the same reverse order.
     *
     * Algorithm:
     *      1. Create a dummy node to attach result digits.
     *      2. Traverse both lists simultaneously.
     *      3. Extract l1 digit (x) and l2 digit (y); use 0 if list ended.
     *      4. Compute:
     *              sum = x + y + carry
     *              digit = sum % 10
     *              carry = sum / 10   (integer division)
     *      5. Append digit to result list.
     *      6. Move l1 and l2 pointers if they exist.
     *      7. Continue until BOTH lists end AND carry is 0.
     *
     * Why while(l1 != null || l2 != null || carry != 0)?
     *      Ensures we process:
     *          ✔ remaining digits of longer list
     *          ✔ final carry digit (like 999 + 1 = 1000)
     *
     * @param l1 first number as linked list
     * @param l2 second number as linked list
     * @return head of resulting linked list after addition
     * --------------------------------------------------------------------
     */
    public static Node addTwoNumbers(Node l1, Node l2) {

        // Dummy node simplifies result building (no need for extra conditions)
        Node dummy = new Node(-1);
        Node current = dummy;

        int carry = 0;

        // Continue until both lists end AND no carry remains
        while (l1 != null || l2 != null || carry != 0) {

            // If one list is shorter, treat missing digit as 0
            int x = (l1 != null) ? l1.data : 0;
            int y = (l2 != null) ? l2.data : 0;

            int sum = x + y + carry;

            // New digit is remainder of division by 10
            int digit = sum % 10;

            // Carry is integer division (not float!)
            // Example: 14 / 10 = 1, NOT 1.4
            carry = sum / 10;

            // Create new node for computed digit
            current.next = new Node(digit);
            current = current.next;

            // Move pointers forward if nodes exist
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // Return the actual head (after dummy)
        return dummy.next;
    }

    /**
     * Utility method to print a linked list.
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
