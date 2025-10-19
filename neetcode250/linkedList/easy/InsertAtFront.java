package neetcode250.linkedList.easy;

/**
 * Demonstrates how to insert a new node at the front (beginning)
 * of a singly linked list.
 *
 * This example manually creates a linked list and adds a new node at the front.
 *
 * Steps:
 * 1. Create nodes and link them manually to form the list.
 * 2. Call {@link #insertAtFront(Node, int)} to add a node at the beginning.
 * 3. Traverse the updated list and print all node values.
 *
 * Time Complexity: O(1) — insertion happens in constant time.
 * Space Complexity: O(1) — only one extra node is created.
 */
public class InsertAtFront {

    public static void main(String[] args) {

        // Create individual nodes
        Node head = new Node(10);
        Node second = new Node(30);
        Node third = new Node(40);
        Node fourth = new Node(50);

        // Link nodes together to form the linked list
        head.next = second;
        second.next = third;
        third.next = fourth;

        // Insert new node with value 1 at the front
        Node current = insertAtFront(head, 1);

        // Traverse and print all nodes
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    /**
     * Inserts a new node with the given value at the front of the linked list.
     *
     * @param head the head of the linked list
     * @param x the value to insert at the front
     * @return the new head of the linked list
     */
    public static Node insertAtFront(Node head, int x) {

        // Step 1: Create a new node with given data
        Node newNode = new Node(x);

        // Step 2: If the list is empty, the new node becomes the head
        if (head == null) {
            head = newNode;
        } else {
            // Step 3: Otherwise, link new node to current head
            newNode.next = head;
            head = newNode;
        }

        // Step 4: Return the new head reference
        return head;
    }
}
