package linkedList.easy;

import neetcode250.linkedList.easy.Node;
import java.util.HashSet;

/**
 * This class demonstrates two different approaches to find the intersection node
 * between two singly linked lists:
 * 1. Brute force using HashSet (O(m + n) time, O(m) space)
 * 2. Optimal two-pointer approach (O(m + n) time, O(1) space)
 *
 * The intersection means both lists share the exact same node reference in memory,
 * not just the same data value.
 */
public class IntersectionOfTwoLinkedList {

    public static void main(String[] args) {

        // Shared part: 8 → 4 → 5
        Node intersect = new Node(8);
        intersect.next = new Node(4);
        intersect.next.next = new Node(5);

        // List A: 4 → 1 → (8 → 4 → 5)
        Node headA = new Node(4);
        headA.next = new Node(1);
        headA.next.next = intersect;

        // List B: 5 → 6 → 1 → (8 → 4 → 5)
        Node headB = new Node(5);
        headB.next = new Node(6);
        headB.next.next = new Node(1);
        headB.next.next.next = intersect;

        // Display both lists
        System.out.print("Original Linked List A: ");
        printLL(headA);

        System.out.print("Original Linked List B: ");
        printLL(headB);

        // Find and display intersection node using both approaches
        System.out.println("\nIntersection Node (Brute Force):");
        Node intersectionNodeBrute = getIntersectionNodeBrute(headA, headB);
        printLL(intersectionNodeBrute);

        System.out.println("\nIntersection Node (Optimal - Two Pointer):");
        Node intersectionNodeOptimal = getIntersectionNodeOptimal(headA, headB);
        printLL(intersectionNodeOptimal);
    }

    /**
     * Finds the intersection node of two linked lists using a HashSet.
     *
     * Algorithm:
     * 1. Traverse List A and store all nodes in a HashSet.
     * 2. Traverse List B and check for the first node that exists in the set.
     *
     * @param headA Head of the first linked list
     * @param headB Head of the second linked list
     * @return The intersection node if found, otherwise null
     */
    public static Node getIntersectionNodeBrute(Node headA, Node headB) {
        Node current = headA;
        HashSet<Node> set = new HashSet<>();

        // Step 1: Add all nodes of List A into HashSet
        while (current != null) {
            set.add(current);
            current = current.next;
        }

        // Step 2: Traverse List B and check if node exists in HashSet
        Node temp = headB;
        while (temp != null) {
            if (set.contains(temp)) {
                return temp; // Intersection found
            }
            temp = temp.next;
        }

        // Step 3: No intersection found
        return null;
    }

    /**
     * Finds the intersection node using the optimal two-pointer approach.
     *
     * Algorithm:
     * 1. Use two pointers (p1 and p2) starting from headA and headB.
     * 2. Move both pointers one step at a time.
     * 3. When a pointer reaches the end, redirect it to the other list’s head.
     * 4. They will either meet at the intersection node or both reach null.
     *
     * @param headA Head of the first linked list
     * @param headB Head of the second linked list
     * @return The intersection node if found, otherwise null
     */
    public static Node getIntersectionNodeOptimal(Node headA, Node headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Node p1 = headA;
        Node p2 = headB;

        // Traverse both lists
        while (p1 != p2) {

            // Move p1 forward or redirect to headB
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }

            // Move p2 forward or redirect to headA
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }

        // Either intersection node or null
        return p1;
    }

    /**
     * Utility method to print the linked list nodes in sequence.
     *
     * @param head The head node of the linked list
     */
    public static void printLL(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print(" → ");
            current = current.next;
        }
        System.out.println();
    }
}
