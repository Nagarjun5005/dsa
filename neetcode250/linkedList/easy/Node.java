package neetcode250.linkedList.easy;

/**
 * Represents a single node in a singly linked list.
 *
 * Each node stores:
 * - An integer value {@code data}
 * - A reference to the next node {@code next}
 *
 * Example structure:
 * [10] -> [20] -> [30] -> null
 *
 * This class is used across multiple linked list operations
 * such as insertion, deletion, and traversal.
 */
public class Node {

    /** The value stored in this node. */
    public int data;

    /** Reference (pointer) to the next node in the list. */
    public Node next;

    /**
     * Constructs a new node with the given data.
     * The {@code next} pointer is initialized to {@code null}.
     *
     * @param data the value to store in this node
     */
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}
