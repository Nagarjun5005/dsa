package linkedList.medium;

import neetcode250.linkedList.easy.Node;

/**
 * Reorder a singly linked list into the pattern:
 *
 *      L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → ...
 *
 * WITHOUT changing node values. We can only change pointers.
 *
 * Example:
 * Input:  1 → 2 → 3 → 4 → 5 → 6 → 7
 * Output: 1 → 7 → 2 → 6 → 3 → 5 → 4
 *
 * Steps to solve:
 * 1. Find the middle of the linked list using slow-fast pointers
 * 2. Reverse the second half of the linked list
 * 3. Merge the first half and reversed second half alternately
 *
 * Time Complexity : O(n)
 * Space Complexity: O(1)
 */
public class ReorderLinkedList {

    public static void main(String[] args) {

        // Create example linked list: 1→2→3→4→5→6→7
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);

        System.out.println("Original Linked List:");
        printLl(head);

        System.out.println("\nReordered Linked List:");
        reorderList(head);
        printLl(head);
    }

    /**
     * Reorders the linked list into the required L0 → Ln → L1 → Ln-1 → ... pattern.
     *
     * Steps:
     * 1. Find the middle of the list (slow-fast).
     * 2. Split the list into two halves.
     * 3. Reverse the second half.
     * 4. Merge both halves alternately.
     *
     * @param head The head of the linked list.
     */
    public static void reorderList(Node head) {

        if (head == null || head.next == null) return;

        // ------------------------------------------------------------------
        // STEP 1: Find the middle of the linked list
        // ------------------------------------------------------------------
        Node middle = findMiddle(head);

        // "middle" is the last node of first half
        Node second = middle.next;

        // Split the list into two halves
        middle.next = null;

        // ------------------------------------------------------------------
        // STEP 2: Reverse the second half
        // ------------------------------------------------------------------
        Node reversedSecondHalf = reverseLL(second);

        // ------------------------------------------------------------------
        // STEP 3: Merge first half and reversed second half alternately
        // ------------------------------------------------------------------
        Node first = head;
        second = reversedSecondHalf;

        // alternate merging (zipper merge)
        while (first != null && second != null) {

            // Save next pointers
            Node temp1 = first.next;
            Node temp2 = second.next;

            // Link first → second → next(first)
            first.next = second;
            second.next = temp1;

            // Move pointers forward
            first = temp1;
            second = temp2;
        }
    }

    /**
     * Finds the middle node of the linked list using slow-fast pointer technique.
     *
     * Slow moves 1 step, fast moves 2 steps.
     * When fast reaches the end, slow is at the middle.
     *
     * @param head The head of the linked list.
     * @return The middle node.
     */
    public static Node findMiddle(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;        // Move 1 step
            fast = fast.next.next;   // Move 2 steps
        }

        return slow; // middle node
    }

    /**
     * Reverses a linked list.
     *
     * @param head The head of the list to be reversed.
     * @return The new head of the reversed linked list.
     */
    public static Node reverseLL(Node head) {
        Node previous = null;
        Node current = head;
        Node next;

        while (current != null) {
            next = current.next;     // Store next node
            current.next = previous; // Reverse pointer
            previous = current;      // Move prev forward
            current = next;          // Move current forward
        }

        return previous; // new head of reversed list
    }

    /**
     * Utility function to print the linked list.
     *
     * @param head The head of the linked list.
     */
    public static void printLl(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " → ");
            current = current.next;
        }
        System.out.println("null");
    }
}
