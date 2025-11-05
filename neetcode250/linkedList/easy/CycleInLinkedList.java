package linkedList.easy;
import neetcode250.linkedList.easy.Node;

/**
 * ---------------------------------------------------------------
 * Program: Detect Cycle in a Linked List
 * ---------------------------------------------------------------
 * Description:
 * This program checks whether a given singly linked list contains a cycle (loop).
 * A cycle exists when a node’s "next" pointer refers to a previous node,
 * forming a loop instead of terminating at null.
 *
 * ---------------------------------------------------------------
 * Algorithm Used: Floyd’s Cycle Detection (Tortoise and Hare)
 * ---------------------------------------------------------------
 * Approach:
 * 1. Use two pointers, slow and fast.
 * 2. Move "slow" one step at a time and "fast" two steps at a time.
 * 3. If there is a cycle, both pointers will eventually meet inside the loop.
 * 4. If "fast" or "fast.next" becomes null, then the linked list has no cycle.
 *
 * ---------------------------------------------------------------
 * Example:
 * Input List: 1 → 2 → 3 → 4 → 1 (cycle back to head)
 *
 * Step-by-Step:
 * slow = 1, fast = 1
 * slow = 2, fast = 3
 * slow = 3, fast = 1
 * slow = 4, fast = 3
 * slow = 1, fast = 1 → They meet → Cycle detected ✅
 *
 * ---------------------------------------------------------------
 * Time Complexity:  O(n)
 * - Each pointer traverses the list at most once.
 *
 * Space Complexity: O(1)
 * - Only two pointers (slow, fast) are used, requiring constant space.
 *
 * ---------------------------------------------------------------
 * Author: Nagarjun N (example)
 * ---------------------------------------------------------------
 */

public class CycleInLinkedList {

    public static void main(String[] args) {
        // Step 1: Create nodes
        Node head = new Node(1);
        Node first = new Node(2);
        Node second = new Node(3);
        Node third = new Node(4);

        // Step 2: Link nodes to form a cycle
        head.next = first;
        first.next = second;
        second.next = third;
        third.next = head; // Creates a cycle back to head

        // Step 3: Detect cycle
        boolean detectCycleInLl = detectCycleInLl(head);

        // Step 4: Output result
        System.out.println("Cycle detected: " + detectCycleInLl);
    }

    /**
     * Detects if a given singly linked list has a cycle.
     *
     * @param head the head node of the linked list
     * @return true if there is a cycle, false otherwise
     */
    public static boolean detectCycleInLl(Node head) {
        Node slow = head;
        Node fast = head;

        // Traverse the linked list with two pointers
        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move slow by one step
            fast = fast.next.next;      // Move fast by two steps

            // If slow and fast meet, there is a cycle
            if (slow == fast) {
                return true;
            }
        }

        // If we reach here, no cycle exists
        return false;
    }
}
