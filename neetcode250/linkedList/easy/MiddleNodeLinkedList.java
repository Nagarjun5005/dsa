package linkedList.easy;
import neetcode250.linkedList.easy.Node;

/**
 * Finds the middle node of a singly linked list using
 * the two-pointer (slow and fast) approach.
 *
 * <p>For odd-length lists, it returns the exact middle node.
 * For even-length lists, it returns the second middle node.</p>
 *
 * Example:
 * Input: 1 → 2 → 3 → 4 → 5
 * Output: 3 → 4 → 5
 *
 * Input: 1 → 2 → 3 → 4
 * Output: 3 → 4
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MiddleNodeLinkedList {

    public static void main(String[] args) {
        Node head = new Node(1);
        Node first = new Node(2);
        Node second = new Node(3);
        Node third = new Node(4);
        Node fourth = new Node(5);

        // Linking nodes to form the linked list
        head.next = first;
        first.next = second;
        second.next = third;
        third.next = fourth;

        System.out.println("Actual Linked List ---->");
        printLl(head);
        System.out.println();

        System.out.println("Middle Linked List ----> (Pointing as head)");
        Node middleNode = findMiddleNode(head);
        printLl(middleNode);
    }

    /**
     * Finds the middle node of the given linked list.
     *
     * Uses two pointers:
     * - slow: moves one step at a time
     * - fast: moves two steps at a time
     *
     * When the fast pointer reaches the end,
     * the slow pointer will be at the middle.
     *
     * @param head the head node of the linked list
     * @return the middle node
     */
    public static Node findMiddleNode(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;        // Move by 1
            fast = fast.next.next;   // Move by 2
        }

        return slow;
    }

    /**
     * Prints the linked list starting from the given node.
     *
     * @param head the starting node
     */
    public static void printLl(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print(" → ");
            current = current.next;
        }
        System.out.println();
    }
}
