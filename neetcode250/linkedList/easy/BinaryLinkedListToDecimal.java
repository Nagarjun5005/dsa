package linkedList.easy;
import neetcode250.linkedList.easy.Node;

/**
 * Problem: Convert a binary number represented as a singly linked list
 * (where each node contains either 0 or 1) into its decimal equivalent.
 *
 * Example:
 * Input: 0 -> 1 -> 0 -> 0
 * Output: 4
 *
 * Logic:
 * Traverse the linked list from head to end, and for each node:
 * Multiply the current result by 2 (shifting bits left),
 * then add the current node's binary value.
 */
public class BinaryLinkedListToDecimal {

    public static void main(String[] args) {
        // Step 1: Create individual nodes
        Node head = new Node(0);
        Node second = new Node(1);
        Node third = new Node(0);
        Node fourth = new Node(0);

        // Step 2: Link nodes to form the linked list
        head.next = second;
        second.next = third;
        third.next = fourth;

        // Step 3: Convert binary linked list to decimal
        int decimalValue = binaryLinkedListToDecimal(head);
        System.out.println("Decimal value of linked list: " + decimalValue);

        // Step 4: Print linked list (for verification)
        System.out.print("Binary Linked List: ");
        printLL(head);
    }

    /**
     * Converts a binary number in linked list form to its decimal equivalent.
     * @param head Head node of the linked list
     * @return Decimal integer representation
     */
    public static int binaryLinkedListToDecimal(Node head) {
        int result = 0;
        Node current = head;

        while (current != null) {
            // Multiply by 2 to shift left, then add the current node's value
            result = (result * 2) + current.data;
            current = current.next;
        }

        return result;
    }

    /**
     * Utility method to print linked list
     * @param head Head node of the list
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
