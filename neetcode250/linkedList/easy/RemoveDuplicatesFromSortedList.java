package linkedList.easy;

import neetcode250.linkedList.easy.Node;

/**
 * RemoveDuplicatesFromSortedList
 * ------------------------------
 * Problem:
 * --------
 * Given the head of a sorted linked list, remove all duplicates such that
 * each element appears only once. The list should remain sorted after modification.
 *
 * Example:
 * --------
 * Input:  1 → 1 → 2 → 2 → 3 → 4
 * Output: 1 → 2 → 3 → 4
 *
 * Explanation:
 * Since the list is sorted, all duplicate elements will appear consecutively.
 * Therefore, we can easily detect duplicates by comparing a node with its next node.
 *
 * Approach:
 * ----------
 * - Traverse the list using a `current` pointer.
 * - Compare the current node’s value with the next node’s value.
 * - If they are equal → skip the next node by updating the link.
 * - If not equal → move the current pointer ahead.
 *
 * Time Complexity:  O(N)
 *     → We traverse each node once.
 * Space Complexity: O(1)
 *     → The operation is done in-place using constant extra space.
 *
 * Author: Nagarjun
 * Date: November 2025
 */
public class RemoveDuplicatesFromSortedList {

    /**
     * Main method for testing the removeDuplicatesFromSortedList function.
     * Creates a sample sorted linked list and prints it before and after removing duplicates.
     */
    public static void main(String[] args) {
        // Step 1: Create sample nodes for the sorted list
        Node head = new Node(1);
        Node first = new Node(1);
        Node second = new Node(2);
        Node third = new Node(2);
        Node fourth = new Node(3);
        Node fifth = new Node(4);

        // Step 2: Link the nodes together
        head.next = first;
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;

        // Step 3: Display the original list
        System.out.println("Original Linked List --->");
        printLinkedList(head);

        // Step 4: Remove duplicates and get the updated list
        System.out.println("\nAfter removing duplicates from the list:");
        Node updatedHead = removeDuplicatesFromSortedList(head);

        // Step 5: Display the result
        printLinkedList(updatedHead);
    }

    /**
     * Removes duplicate nodes from a sorted linked list.
     *
     * Logic:
     * ------
     * Since the list is sorted, any duplicates will be adjacent.
     * We traverse the list with a pointer `current`:
     *   - If current node and next node have the same value,
     *     we skip the next node by linking `current.next` to `current.next.next`.
     *   - Otherwise, move to the next node.
     *
     * @param head The head node of the sorted linked list
     * @return The head of the modified linked list after removing duplicates
     */
    public static Node removeDuplicatesFromSortedList(Node head) {
        // Edge case: Empty or single-node list
        if (head == null || head.next == null) {
            return head;
        }

        // Pointer to traverse the list
        Node current = head;

        // Traverse until the second-last node
        while (current != null && current.next != null) {
            if (current.data == current.next.data) {
                // Duplicate detected — skip the next node
                current.next = current.next.next;
            } else {
                // Move ahead only when values are different
                current = current.next;
            }
        }

        return head;
    }

    /**
     * Prints the linked list starting from the given head node.
     *
     * @param head The starting node of the linked list
     */
    public static void printLinkedList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " --> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
