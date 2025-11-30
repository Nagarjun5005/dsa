package linkedList.medium;

import neetcode250.linkedList.easy.Node;

/**
 * This class implements the operation of rotating a singly linked list
 * to the LEFT by 'k' positions.
 *
 * <p>Example:
 * <pre>
 * Input:  1 → 2 → 3 → 4 → 5, k = 2
 * Output: 3 → 4 → 5 → 1 → 2
 * </pre>
 *
 * <p>The algorithm performs the following steps:
 * <ol>
 *   <li>Find the length of the linked list and the tail node
 *   <li>Connect the tail to the head to form a circular list
 *   <li>Compute effective rotations using k % length
 *   <li>Find the new tail at position (k - 1)
 *   <li>New head is the node next to newTail
 *   <li>Break the circular link to obtain the rotated list
 * </ol>
 *
 * <p>This approach ensures O(n) time complexity and O(1) space.
 */
public class RotateLinkedListLeft {

    public static void main(String[] args) {

        // Sample linked list: 1 → 2 → 3 → 4 → 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        // Rotate left by k = 2
        Node rotated = rotateLeft(head, 2);

        // Print output list
        printll(rotated);
    }

    /**
     * Rotates a singly linked list to the LEFT by k positions.
     *
     * <p>Example:
     * <pre>
     * Input: 1 → 2 → 3 → 4 → 5, k = 2
     * Output: 3 → 4 → 5 → 1 → 2
     * </pre>
     *
     * <p><b>Steps involved:</b></p>
     * <ol>
     *   <li>Handle edge cases (null, single element, k = 0)
     *   <li>Traverse the list to compute its length and get the tail
     *   <li>Make the list circular by connecting tail.next = head
     *   <li>Normalize k using modulo operation (k = k % length)
     *   <li>Find the new tail by moving (k - 1) steps from the head
     *   <li>The new head = newTail.next
     *   <li>Break the circular link by setting newTail.next = null
     * </ol>
     *
     * @param head The head of the singly linked list
     * @param k    The number of positions to rotate left
     * @return     The head of the rotated linked list
     *
     * <p><b>Time Complexity:</b>  O(n) — full traversal once
     * <br><b>Space Complexity:</b> O(1) — constant extra space</p>
     */
    public static Node rotateLeft(Node head, int k) {

        // Step 1: Edge cases
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Step 2: Compute length and find tail
        int length = 1;
        Node tail = head;

        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Step 3: Convert list into a circular linked list
        tail.next = head;

        // Step 4: Effective rotations (avoid rotating more than needed)
        k = k % length;
        if (k == 0) {
            tail.next = null; // break circle and return original head
            return head;
        }

        // Step 5: Find new tail (k - 1 steps from head)
        Node newTail = head;
        for (int i = 1; i < k; i++) {
            newTail = newTail.next;
        }

        // Step 6: Determine new head (next of newTail)
        Node newHead = newTail.next;

        // Step 7: Break the circular link
        newTail.next = null;

        // Step 8: Return rotated list's new head
        return newHead;
    }

    /**
     * Utility method to print a linked list.
     *
     * @param head The head of the list to print
     */
    private static void printll(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " --> ");
            current = current.next;
        }
        System.out.print("null");
    }
}
