package linkedList.medium;

import neetcode250.linkedList.easy.Node;

/**
 * MergeInBetweenLinkedList
 * ------------------------
 * Problem:
 * Given two singly linked lists list1 and list2 and two indices A and B,
 * remove the segment in list1 from index A to index B (inclusive),
 * and insert the entire list2 in that position.
 *
 * Example:
 * --------
 * list1 = [0, 1, 2, 3, 4, 5]
 * list2 = [1000000, 1000001, 1000002]
 * A = 3, B = 4
 *
 * Output:
 * 0 -> 1 -> 2 -> 1000000 -> 1000001 -> 1000002 -> 5
 *
 * Explanation:
 * Remove nodes at index 3 and 4 (values 3 and 4) from list1,
 * then splice list2 between nodes 2 and 5.
 *
 * Approach:
 * ---------
 * 1. Traverse list1 to reach node at index A-1.
 *    - This node is the left connection point (prevA).
 *
 * 2. Traverse list1 again to reach node at index B.
 *    - This node is the right boundary (afterB).
 *
 * 3. Connect prevA.next = list2 (attach beginning of list2).
 *
 * 4. Traverse list2 to its tail.
 *
 * 5. Connect the tail of list2 to afterB.next (node after index B).
 *
 * This correctly merges list2 between A and B (inclusive).
 *
 * Time Complexity: O(N + M)
 *    - N = length of list1
 *    - M = length of list2
 *
 * Space Complexity: O(1)
 *    - operations done in-place using pointers
 */
public class MergeInBetweenLinkedList {

    public static void main(String[] args) {
        // list1 : 0 -> 1 -> 2 -> 3 -> 4 -> 5
        Node list1 = new Node(0);
        list1.next = new Node(1);
        list1.next.next = new Node(2);
        list1.next.next.next = new Node(3);
        list1.next.next.next.next = new Node(4);
        list1.next.next.next.next.next = new Node(5);

        // list2 : 1000000 -> 1000001 -> 1000002
        Node list2 = new Node(1000000);
        list2.next = new Node(1000001);
        list2.next.next = new Node(1000002);

        int a = 3;  // Remove segment starting from index A
        int b = 4;  // Ending at index B (inclusive)

        System.out.println("Original List1:");
        printList(list1);

        System.out.println("List2:");
        printList(list2);

        Node merged = mergeInBetween(list1, a, b, list2);

        System.out.println("After merge:");
        printList(merged);
    }

    /**
     * Merges list2 into list1 between indices a and b inclusive.
     *
     * @param list1 The main list into which we merge list2
     * @param a     Starting index for removal in list1
     * @param b     Ending index for removal in list1
     * @param list2 The list to be inserted between indices a and b
     * @return The updated head of list1
     */
    public static Node mergeInBetween(Node list1, int a, int b, Node list2) {

        // Step 1: Traverse until node at index (a - 1)
        Node prevA = list1;
        int i = 0;
        while (i < a - 1) {
            prevA = prevA.next;
            i++;
        }

        // Step 2: Traverse until node at index b
        Node afterB = list1;
        int j = 0;
        while (j < b) {
            afterB = afterB.next;
            j++;
        }

        // Step 3: Connect prevA to head of list2
        prevA.next = list2;

        // Step 4: Traverse to the tail node of list2
        Node tail = list2;
        while (tail.next != null) {
            tail = tail.next;
        }

        // Step 5: Connect tail of list2 to the node after B
        tail.next = afterB.next;

        // Return updated list1
        return list1;
    }

    /**
     * Utility method to print a linked list.
     *
     * @param head Starting node of the list
     */
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
