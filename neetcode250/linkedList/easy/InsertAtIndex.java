package linkedList.easy;
import neetcode250.linkedList.easy.Node;
/**
 * Demonstrates how to insert a node at a specific index (position)
 * in a singly linked list.
 *
 * Steps:
 * 1. Create an example linked list manually.
 * 2. Insert a new node at the given index using {@link #insertNodeAtPosition(Node, int, int)}.
 * 3. Print the updated linked list.
 *
 * Edge Cases Handled:
 * - Insertion at the start (index 0)
 * - Insertion at any valid middle position
 * - Throws an exception for invalid (out-of-bounds) index
 *
 * Time Complexity: O(N) — traversal up to the given index.
 * Space Complexity: O(1) — constant extra space used.
 */
public class InsertAtIndex {

    public static void main(String[] args) {
        // Step 1: Create individual nodes
        Node head = new Node(10);
        Node second = new Node(20);
        Node third = new Node(40);
        Node fourth = new Node(50);

        // Step 2: Link nodes together to form a linked list
        head.next = second;
        second.next = third;
        third.next = fourth;

        System.out.println("Original List:");
        printList(head);

        // Step 3: Insert new node (30) at index 2
        head = insertNodeAtPosition(head, 30, 2);
        System.out.println("After inserting 30 at index 2:");
        printList(head);

        // Step 4: Insert node at the front (index 0)
        head = insertNodeAtPosition(head, 50, 0);
        System.out.println("After inserting 50 at index 0:");
        printList(head);

        // Uncomment to test invalid index case
        // head = insertNodeAtPosition(head, 200, 10);
    }

    /**
     * Inserts a new node with given data at a specified index in the linked list.
     *
     * @param head the head of the linked list
     * @param data the value to insert
     * @param position the index (0-based) where new node should be inserted
     * @return updated head of the linked list
     * @throws IndexOutOfBoundsException if the position is invalid
     */
    public static Node insertNodeAtPosition(Node head, int data, int position) {
        // Step 1: Create a new node
        Node newNode = new Node(data);

        // Step 2: Handle insertion at the front (index 0)
        if (position == 0) {
            newNode.next = head;
            head = newNode;
            return head;
        }

        // Step 3: Traverse to the given position
        Node previous = null;
        Node current = head;
        int count = 0;

        while (current != null && count < position) {
            previous = current;
            current = current.next;
            count++;
        }

        // Step 4: Validate position
        if (count != position) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + position);
        }

        // Step 5: Insert node between previous and current
        previous.next = newNode;
        newNode.next = current;

        return head;
    }

    /**
     * Utility method to print all elements in the linked list.
     *
     * @param head the head node of the linked list
     */
    public static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}

