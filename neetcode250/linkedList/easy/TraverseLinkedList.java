package linkedList.easy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import neetcode250.linkedList.easy.Node;

/**
 * Demonstrates different ways to traverse a singly linked list.
 *
 * This example creates a simple linked list manually and shows:
 * 1. How to print elements directly during traversal.
 * 2. How to collect values into an array using an intermediate list.
 *
 * Time Complexity: O(N) — each node is visited once.
 * Space Complexity: O(N) — additional list used for storing elements.
 */
public class TraverseLinkedList {

    public static void main(String[] args) {

        // Step 1: Create individual nodes
        Node head = new Node(10);
        Node second = new Node(30);
        Node third = new Node(40);
        Node fourth = new Node(50);

        // Step 2: Link nodes to form the linked list
        head.next = second;
        second.next = third;
        third.next = fourth;

        // Step 3: Traverse and print nodes directly
        System.out.println("Traversing the linked list:");
        traverse(head);

        // Step 4: Traverse and convert to array, then print
        System.out.println("Traversed elements as array: " + Arrays.toString(traverse1(head)));
    }

    /**
     * Traverses the linked list and prints each node's data.
     *
     * @param head the head of the linked list
     */
    public static void traverse(Node head) {
        Node current = head;
        while (current != null) {
            System.out.println(current.data); // Print current node data
            current = current.next;           // Move to next node
        }
    }

    /**
     * Traverses the linked list and returns an integer array
     * containing all the node data values.
     *
     * @param head the head of the linked list
     * @return an array containing all node values in traversal order
     */
    public static int[] traverse1(Node head) {
        Node current = head;
        List<Integer> list = new ArrayList<>();

        // Step 1: Collect data into ArrayList
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }

        // Step 2: Convert ArrayList to int array
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }
}

