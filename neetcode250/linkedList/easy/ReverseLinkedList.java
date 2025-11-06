package linkedList.easy;

import neetcode250.linkedList.easy.Node;

/**
 * ---------------------------------------------------------------
 * Program: Reverse a Singly Linked List (Iterative Method)
 * ---------------------------------------------------------------
 * Approach:
 * Use three pointers: previous, current, next
 *
 * Steps:
 * 1. Initialize previous = null, current = head
 * 2. While current != null:
 *      - Store next node
 *      - Reverse current node's pointer
 *      - Move previous and current one step forward
 * 3. Previous becomes the new head
 *
 * Time Complexity: O(n) — traverses list once
 * Space Complexity: O(1) — uses constant extra space
 * ---------------------------------------------------------------
 */

public class ReverseLinkedList {

    public static void main(String[] args) {

        // Creating linked list 1->2->3->4->5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.println("Original Linked List:");
        printLL(head);

        // Update head to the new reversed head
        head = reverseLinkedList(head);

        System.out.println("\nReversed Linked List:");
        printLL(head);
    }

    /**
     * Reverses the linked list and returns the new head
     */
    public static Node reverseLinkedList(Node head) {
        Node current = head;
        Node previous = null;
        Node next = null;

        while (current != null) {
            next = current.next;     // save next
            current.next = previous; // reverse pointer
            previous = current;      // move previous
            current = next;          // move current
        }
        return previous; // new head
    }

    /**
     * Prints the linked list
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
