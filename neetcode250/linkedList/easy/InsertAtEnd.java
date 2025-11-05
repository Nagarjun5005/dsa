package linkedList.easy;
import neetcode250.linkedList.easy.Node;
/**
 * Demonstrates how to insert a new node at the end of a singly linked list.
 *
 * This example manually creates a linked list and appends a new node at the end.
 *
 * Steps:
 * 1. Create nodes and link them manually to form the list.
 * 2. Call {@link #insertAtEnd(Node, int)} to append a new value.
 * 3. Traverse the list to print all node values.
 *
 * Time Complexity: O(n) — traverses the list once.
 * Space Complexity: O(1) — uses constant extra space.
 */
public class InsertAtEnd {

    public static void main(String[] args) {

        // Create individual nodes
        Node head = new Node(10);
        Node second = new Node(30);
        Node third = new Node(40);
        Node fourth = new Node(50);

        // Link the nodes together to form the initial linked list
        head.next = second;
        second.next = third;
        third.next = fourth;

        // Insert a new node with value 100 at the end of the list
        Node current = insertAtEnd(head, 100);

        // Traverse and print all elements of the updated linked list
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    /**
     * Inserts a new node with the given data at the end of the linked list.
     *
     * @param head the head of the linked list
     * @param x the value to be inserted at the end
     * @return the head of the updated linked list
     */
    public static Node insertAtEnd(Node head, int x) {

        // Create a new node with the provided value
        Node newNode = new Node(x);

        // If the list is empty, the new node becomes the head
        if (head == null) {
            return newNode;
        }

        // Otherwise, traverse to the last node
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        // Append the new node at the end
        current.next = newNode;

        // Return the (unchanged) head reference
        return head;
    }
}

