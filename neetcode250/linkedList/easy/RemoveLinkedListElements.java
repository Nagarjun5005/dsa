package linkedList.easy;

import neetcode250.linkedList.easy.Node;

/**
 * ---------------------------------------------------------------
 * Program: Remove All Nodes with Given Value from Linked List
 * ---------------------------------------------------------------
 * Description:
 * Given the head of a linked list and an integer value (val),
 * remove all nodes that have Node.data == val, and return the new head.
 *
 * Example:
 * Input:  1 → 2 → 6 → 4 → 6
 * val = 6
 * Output: 1 → 2 → 4
 *
 * ---------------------------------------------------------------
 * Approach:
 * 1. Create a dummy node and point it to head.
 *    - This helps handle cases where the head node itself needs removal.
 *
 * 2. Use two pointers:
 *    - prev → tracks the previous valid node
 *    - current → traverses the linked list
 *
 * 3. For each node:
 *    - If current.data == val → skip the node by linking prev.next = current.next
 *    - Else → move prev forward (keep the node)
 *
 * 4. Move current forward in every iteration.
 *
 * 5. Return dummy.next (which represents the new head after deletions).
 *
 * ---------------------------------------------------------------
 * Time Complexity:  O(n)
 * - Each node is visited once during traversal.
 *
 * Space Complexity: O(1)
 * - Only a few pointers are used.
 * ---------------------------------------------------------------
 */

public class RemoveLinkedListElements {

    public static void main(String[] args) {

        // Step 1: Create linked list 1 → 2 → 6 → 4 → 6
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(6);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(6);

        System.out.println("Original Linked List:");
        printLL(head);

        // Step 2: Remove all nodes with value = 6
        head = removeElements(head, 6);  // ✅ must update head

        System.out.println("Linked List After Removing Value 6:");
        printLL(head);
    }

    /**
     * Removes all nodes from the linked list that have a value equal to val.
     *
     * @param head the head node of the linked list
     * @param val  the value to remove
     * @return     the new head of the modified linked list
     */
    public static Node removeElements(Node head, int val) {

        // Step 1: Create dummy node to handle head deletions easily
        Node dummy = new Node(-1);
        dummy.next = head;

        // Step 2: Initialize pointers
        Node prev = dummy;
        Node current = head;

        // Step 3: Traverse the linked list
        while (current != null) {
            if (current.data == val) {
                // Skip the node by linking prev to current.next
                prev.next = current.next;
            } else {
                // Keep node and move prev forward
                prev = current;
            }

            // Move current forward regardless
            current = current.next;
        }

        // Step 4: Return the new head
        return dummy.next;
    }

    /**
     * Utility Method - Prints the linked list
     *
     * @param head the head node of the list
     */
    public static void printLL(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }
}
