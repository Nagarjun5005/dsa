package linkedList.medium;

import neetcode250.linkedList.easy.Node;

/**
 * RemoveNthFromEnd
 * ----------------
 * Problem:
 * --------
 * Given the head of a singly linked list, remove the nth node from the end of the list and return its head.
 *
 * Example:
 * Input:  10 -> 20 -> 30 -> 40 -> 50, n = 2
 * Output: 10 -> 20 -> 30 -> 50
 *
 * Approach:
 * ----------
 * This uses the **Two Pointer (Fast & Slow)** technique with a **Dummy Node** to handle edge cases.
 *
 * Key Idea:
 * - Create a dummy node before head to simplify deletion (even when the first node must be deleted).
 * - Move the `fast` pointer (n + 1) steps ahead to create a gap of n nodes between `fast` and `slow`.
 * - Move both pointers together until `fast` reaches the end.
 * - `slow` will now be just before the node to delete, so set `slow.next = slow.next.next`.
 *
 * Time Complexity: O(L), where L is the length of the linked list.
 * Space Complexity: O(1) — only pointers are used.
 *
 * @author
 *   Nagarjun (you 😊)
 * @date
 *   November 2025
 */
public class RemoveNthFromEnd {

    /**
     * Main method to test the removeNthFromEnd function.
     * Demonstrates the linked list before and after removal.
     */
    public static void main(String[] args) {
        // Step 1: Create nodes
        Node head = new Node(10);
        Node first = new Node(20);
        Node second = new Node(30);
        Node third = new Node(40);
        Node fourth = new Node(50);

        // Step 2: Link nodes to form a Linked List
        head.next = first;
        first.next = second;
        second.next = third;
        third.next = fourth;

        // Step 3: Print original list
        System.out.println("Original Linked List:");
        printLinkedList(head);

        // Step 4: Remove nth node from end (here n = 2)
        System.out.println("\nAfter removing the 2nd node from end:");
        head = removeNthFromEnd(head, 2);

        // Step 5: Print modified list
        printLinkedList(head);
    }

    /**
     * Prints the elements of the linked list sequentially.
     *
     * @param head the starting node of the linked list
     */
    private static void printLinkedList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ---> ");
            current = current.next;
        }
        System.out.println("null");
    }

    /**
     * Removes the nth node from the end of a singly linked list.
     *
     * @param head the head of the linked list
     * @param n the position of the node from the end (1-based)
     * @return the head of the modified linked list after deletion
     *
     * Steps:
     * 1. Create a dummy node before head to handle edge cases (e.g., removing the first node).
     * 2. Initialize both `fast` and `slow` pointers at the dummy node.
     * 3. Move `fast` pointer n+1 steps ahead to create a gap of n nodes.
     * 4. Move both `fast` and `slow` simultaneously until `fast` reaches the end.
     * 5. Now, `slow` will be just before the target node → perform deletion.
     */
    public static Node removeNthFromEnd(Node head, int n) {
        // Step 1: Create dummy node before head
        Node dummy = new Node(-1);
        dummy.next = head;

        // Step 2: Initialize two pointers
        Node slow = dummy;
        Node fast = dummy;

        // Step 3: Move fast pointer n+1 steps ahead to create the gap
        for (int i = 0; i <= n; i++) {
            // Edge case: if list length < n, return head unchanged
            if (fast == null) return head;
            fast = fast.next;
        }

        // Step 4: Move both pointers until fast reaches the end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Step 5: Delete the target node
        slow.next = slow.next.next;

        // Step 6: Return new head (dummy.next because head might be deleted)
        return dummy.next;
    }
}
