package linkedList.medium;

import neetcode250.linkedList.easy.Node;

/**
 * This class provides an implementation to rotate a singly linked list
 * to the right by 'k' positions.
 *
 * <p>Example:
 * <pre>
 * Input:  1 -> 2 -> 3 -> 4 -> 5,  k = 2
 * Output: 4 -> 5 -> 1 -> 2 -> 3
 * </pre>
 *
 * <p>The algorithm works by:
 * <ol>
 *   <li>Finding the length of the list and the tail node</li>
 *   <li>Forming a circular list by connecting the tail to the head</li>
 *   <li>Calculating the effective rotations (k % length)</li>
 *   <li>Finding the new tail by moving (length - k) steps from head</li>
 *   <li>Breaking the cycle at the correct position to form the new list</li>
 * </ol>
 *
 * <p>This approach works in O(n) time and O(1) space.
 */
public class RotateLinkedList {

    public static void main(String[] args) {

        // Sample linked list: 1 → 2 → 3 → 4 → 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        // Rotate by k = 2
        Node rotated = rotateRight(head, 2);

        // Print rotated list
        printll(rotated);
    }

    /**
     * Rotates a singly linked list to the right by 'k' positions.
     *
     * <p>For example:
     * <pre>
     * 1 → 2 → 3 → 4 → 5, k = 2  becomes  4 → 5 → 1 → 2 → 3
     * </pre>
     *
     * @param head The head node of the linked list
     * @param k    Number of positions to rotate to the right
     * @return     The new head after rotation
     *
     * <p><b>Steps:</b>
     * <ol>
     *   <li>Edge cases: empty list, single node, or k = 0 → return as-is</li>
     *   <li>Find list length and last node (tail)</li>
     *   <li>Connect tail to head to form a circular list</li>
     *   <li>Compute effective rotations using k % length</li>
     *   <li>Find the new tail at position (length - k) from the start</li>
     *   <li>Break the cycle to obtain the rotated list</li>
     * </ol>
     *
     * <p><b>Time Complexity:</b>  O(n)</p>
     * <p><b>Space Complexity:</b> O(1)</p>
     */
    public static Node rotateRight(Node head, int k) {

        // Step 1: Handle edge cases
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Step 2: Find the length and tail node
        int length = 1;
        Node tail = head;

        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Step 3: Make the list circular
        tail.next = head;

        // Step 4: Effective rotation (handle k > length cases)
        k = k % length;
        int stepsToNewTail = length - k;

        // Step 5: Find new tail by moving (length - k) steps
        Node newTail = head;
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        // Step 6: New head is next of newTail
        Node newHead = newTail.next;

        // Step 7: Break the circular link
        newTail.next = null;

        return newHead;
    }

    /**
     * Utility method to print a linked list.
     *
     * @param head The head of the linked list
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
