package linkedList.medium;
import neetcode250.linkedList.easy.Node;

/**
 * Problem:
 * --------
 * You are given a linked list where:
 *   - The list always starts with 0.
 *   - The list always ends with 0.
 *   - Values between two zeros form a "segment".
 *
 * Goal:
 * -----
 * For each segment between two zeros:
 *      → compute the sum of all the values
 *      → create a new node with that sum
 *
 * Example:
 * Input:  0 → 3 → 1 → 0 → 4 → 5 → 2 → 0
 * Output: 4 → 11
 *
 * Explanation:
 * Segment 1: 3 + 1 = 4
 * Segment 2: 4 + 5 + 2 = 11
 *
 */

public class MergeNodesBetweenZeros {

    public static void main(String[] args) {

        /**
         * Creating the input linked list manually (no loops):
         *
         * 0 → 3 → 1 → 0 → 4 → 5 → 2 → 0
         */
        Node head = new Node(0);                        // node1
        head.next = new Node(3);                        // node2
        head.next.next = new Node(1);                   // node3
        head.next.next.next = new Node(0);              // node4
        head.next.next.next.next = new Node(4);         // node5
        head.next.next.next.next.next = new Node(5);    // node6
        head.next.next.next.next.next.next = new Node(2); // node7
        head.next.next.next.next.next.next.next = new Node(0); // node8

        System.out.println("Original List --->");
        printList(head);

        System.out.println("\nAfter merging nodes between zeros:");
        Node mergedNodes = mergeNodes(head);

        printList(mergedNodes);
    }

    /**
     * mergeNodes():
     * -------------
     * Traverses the linked list and merges the values between zeros.
     *
     * Logic:
     * 1. Create a dummy node to build the resulting list easily.
     * 2. Use 'tail' pointer to append new sum nodes into the result list.
     * 3. Skip the very first zero using head.next.
     * 4. Keep accumulating values until you hit a zero.
     * 5. When you hit a zero and sum > 0, create a new node with sum.
     * 6. Reset sum and continue.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(k) where k = number of segments
     */
    public static Node mergeNodes(Node head) {

        Node dummy = new Node(0); // Start of the result list
        Node tail = dummy;        // Used to append new nodes
        Node curr = head.next;    // Skip the first zero (always present)

        int sum = 0;              // Holds the running segment sum

        while (curr != null) {

            // Case 1: Not a zero → keep adding values to current segment
            if (curr.data != 0) {
                sum += curr.data;
            }

            // Case 2: Zero found → segment ended
            else {
                if (sum > 0) {
                    // Create a new node with the segment sum
                    tail.next = new Node(sum);

                    // Move tail pointer forward
                    tail = tail.next;

                    // Reset sum for next segment
                    sum = 0;
                }
            }

            // Move the curr pointer to next node
            curr = curr.next;
        }

        // Return the list without the dummy node
        return dummy.next;
    }

    /**
     * printList():
     * ------------
     * Prints the linked list in a readable format:
     * Example: 4 -> 11
     */
    private static void printList(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.data);
            if (cur.next != null) System.out.print(" -> ");
            cur = cur.next;
        }
        System.out.println();
    }
}
