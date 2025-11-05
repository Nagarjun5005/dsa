package linkedList.easy;

import neetcode250.linkedList.easy.Node;

/**
 * A utility class that demonstrates different deletion operations
 * on a singly linked list:
 *  - delete at start
 *  - delete at end
 *  - delete at a given index
 *
 * Each method returns the updated head of the linked list.
 */
public class LinkedListDeletion {

    public static void main(String[] args) {

        // Create individual nodes
        Node head = new Node(10);
        Node second = new Node(30);
        Node third = new Node(40);
        Node fourth = new Node(50);

        // Link nodes together to form the linked list
        head.next = second;
        second.next = third;
        third.next = fourth;

        System.out.println("Before Deletion------>");
        printLL(head);

        // Delete node at index 2 (0-based)
        head = deleteAtIndex(head, 2);

        System.out.println("After Deletion------->");
        printLL(head);
    }

    /**
     * Deletes the first node (head) of the linked list.
     *
     * @param head the head node of the linked list
     * @return the new head after deletion
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public static Node deleteAtStart(Node head) {
        if (head == null) {
            return null;
        }
        return head.next; // move head to the next node
    }

    /**
     * Deletes the last node of the linked list.
     *
     * @param head the head node of the linked list
     * @return the head after deletion
     *
     * Time Complexity: O(n)
     *   → because we traverse till the second last node
     * Space Complexity: O(1)
     */
    public static Node deleteAtEnd(Node head) {
        // Empty list
        if (head == null) {
            return null;
        }

        // Only one node
        if (head.next == null) {
            return null;
        }

        Node current = head;
        // Traverse till second-last node
        while (current.next.next != null) {
            current = current.next;
        }

        // Remove last node
        current.next = null;
        return head;
    }

    /**
     * Deletes a node at the specified index (0-based).
     *
     * @param head  the head node of the linked list
     * @param index position of the node to delete
     * @return the head after deletion
     *
     * Time Complexity: O(n)
     *   → because we may need to traverse up to the (index - 1)th node
     * Space Complexity: O(1)
     */
    public static Node deleteAtIndex(Node head, int index) {
        if (head == null) {
            return null; // empty list
        }

        // Deleting the first node
        if (index == 0) {
            return head.next;
        }

        Node current = head;
        int count = 0;

        // Traverse till one node before the target node
        while (current != null && count < index - 1) {
            current = current.next;
            count++;
        }

        // If index is out of bounds
        if (current == null || current.next == null) {
            System.out.println("Index out of range");
            return head;
        }

        // Bypass the target node
        current.next = current.next.next;
        return head;
    }

    /**
     * Prints all nodes in the linked list.
     *
     * @param head the head node of the linked list
     * @return the head node (unchanged)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static Node printLL(Node head) {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        return head;
    }
}
