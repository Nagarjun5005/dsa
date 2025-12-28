package stack.impl;

/**
 * Stack implementation using a singly linked list.
 * Follows LIFO (Last In First Out) principle.
 */
public class StackUsingLinkedList {

    /**
     * Top of the stack (head of linked list).
     */
    private Node top;

    /**
     * Node class representing each element in the stack.
     */
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Pushes an element onto the stack.
     *
     * @param x element to be pushed
     */
    public void push(int x) {
        Node newNode = new Node(x);
        newNode.next = top; // link new node to current top
        top = newNode;      // update top
    }

    /**
     * Removes and returns the top element of the stack.
     *
     * @return popped element, or -1 if stack is empty
     */
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return -1;
        }

        int popped = top.data;
        top = top.next; // move top down
        return popped;
    }

    /**
     * Returns the top element without removing it.
     *
     * @return top element, or -1 if stack is empty
     */
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return top.data;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == null;
    }
}
