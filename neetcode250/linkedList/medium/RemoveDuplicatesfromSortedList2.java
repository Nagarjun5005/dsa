package linkedList.medium;

import neetcode250.linkedList.easy.Node;

/**
 * RemoveDuplicatesfromSortedList2
 * --------------------------------
 * Problem:
 * --------
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 *
 * Example:
 * --------
 * Input:  1 → 2 → 3 → 3 → 4 → 4 → 5
 * Output: 1 → 2 → 5
 *
 * Explanation:
 * -------------
 * - Unlike the simpler version (LeetCode #83), where you only remove *extra* duplicates and
 *   keep one copy of each value, this version removes *all occurrences* of any duplicated value.
 * - So if a value repeats, every node containing that value should be deleted.
 *
 * Approach:
 * ----------
 * 1. Introduce a **dummy node** before the head.
 *    - This helps handle cases where the first few nodes themselves are duplicates.
 *
 * 2. Use two pointers:
 *    - `prev` → points to the last node that is confirmed unique.
 *    - `curr` → used to traverse through the list.
 *
 * 3. As you traverse:
 *    - When you find duplicates (i.e., `curr.data == curr.next.data`):
 *         → Keep moving `curr` forward until you exit the duplicate block.
 *         → Then, connect `prev.next` to `curr.next`, effectively removing all duplicates.
 *    - If no duplicates are found:
 *         → Simply move `prev` forward by one step.
 *
 * 4. Continue until the end of the list.
 *
 * Time Complexity:  O(N)
 *      - Each node is visited once.
 *
 * Space Complexity: O(1)
 *      - No extra data structures are used; operations are done in-place.
 *
 * Edge Cases Handled:
 * --------------------
 * - Empty list (head == null)
 * - All nodes are duplicates (e.g., 1→1→1→1)
 * - Duplicates at head (e.g., 1→1→2→3)
 * - Duplicates at the end (e.g., 1→2→3→3)
 * - No duplicates at all
 *
 * Author: Nagarjun
 * Date: November 2025
 */
public class RemoveDuplicatesfromSortedList2 {

    /**
     * Main method to test the removeDuplicatesfromSortedList2 function.
     * Builds a sample linked list and displays results before and after removal.
     */
    public static void main(String[] args) {
        // Step 1: Create test input list
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(4);
        head.next.next.next.next.next.next = new Node(5);

        // Step 2: Print original list
        System.out.println("Original List:");
        printLinkedList(head);

        // Step 3: Remove duplicates
        System.out.println("After removing duplicates:");
        Node updatedHead = removeDuplicatesfromSortedList2(head);

        // Step 4: Print modified list
        printLinkedList(updatedHead);
    }

    /**
     * Removes all nodes containing duplicate numbers from a sorted linked list.
     *
     * @param head The head of the sorted linked list.
     * @return The head of the modified linked list with all duplicates removed.
     */
    public static Node removeDuplicatesfromSortedList2(Node head) {
        // Step 1: Create a dummy node before head (to handle head deletions)
        Node dummy = new Node(-1);
        dummy.next = head;

        // Step 2: Initialize pointers
        Node prev = dummy; // previous node (one step behind the current sequence)
        Node curr = head;  // current node for traversal

        // Step 3: Traverse the entire list
        while (curr != null && curr.next != null) {

            // Case 1: Duplicates detected
            if (curr.data == curr.next.data) {
                // Move through the entire block of duplicates
                while (curr.next != null && curr.data == curr.next.data) {
                    curr = curr.next;
                }
                // Skip all duplicate nodes
                prev.next = curr.next;

            } else {
                // Case 2: No duplicates — move prev one step ahead
                prev = prev.next;
            }

            // Move curr forward for next iteration
            curr = curr.next;
        }

        // Step 4: Return new head (skipping dummy)
        return dummy.next;
    }

    /**
     * Utility method to print a linked list from head to end.
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
}
