package linkedList.medium;

/**
 * A custom implementation of a singly linked list supporting:
 * - Get value at index
 * - Add at head
 * - Add at tail
 * - Add at specific index
 * - Delete at specific index
 *
 * Maintains head, tail, and size references for O(1) tail insertions.
 */
class MyLinkedList {

    /**
     * Inner Node class representing each element in the linked list.
     */
    private static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int current_size = 0;

    /**
     * Constructor initializes an empty linked list.
     */
    public MyLinkedList() {
    }

    /**
     * Retrieves the value at a given index.
     *
     * @param index position in the linked list (0-indexed)
     * @return value at index, or -1 if invalid
     */
    public int get(int index) {
        if (index < 0 || index >= current_size)
            return -1;

        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.val;
    }

    /**
     * Returns the Node at a given index.
     * Used internally by other methods.
     */
    private Node getNodeAtIndex(int index) {
        if (index < 0 || index >= current_size)
            return null;

        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * Adds a new node at the start of the list.
     *
     * @param val value to insert
     */
    public void addAtHead(int val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        if (current_size == 0)
            tail = head;
        current_size++;
    }

    /**
     * Adds a new node at the end of the list.
     *
     * @param val value to insert
     */
    public void addAtTail(int val) {
        if (current_size == 0)
            addAtHead(val);
        else {
            tail.next = new Node(val);
            tail = tail.next;
            current_size++;
        }
    }

    /**
     * Inserts a new node at the specified index.
     *
     * @param index index where to insert (0-indexed)
     * @param val   value of the new node
     */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > current_size)
            return;
        else if (index == 0)
            addAtHead(val);
        else if (index == current_size)
            addAtTail(val);
        else {
            Node newNode = new Node(val);
            Node prevNode = getNodeAtIndex(index - 1);
            newNode.next = prevNode.next;
            prevNode.next = newNode;
            current_size++;
        }
    }

    /**
     * Deletes the node at the specified index.
     *
     * @param index position of node to delete
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= current_size)
            return;
        else if (current_size == 1) {
            head = null;
            tail = null;
        } else if (index == 0) {
            head = head.next;
        } else if (index == current_size - 1) {
            Node previousNode = getNodeAtIndex(index - 1);
            previousNode.next = null;
            tail = previousNode;
        } else {
            Node previousNode = getNodeAtIndex(index - 1);
            previousNode.next = previousNode.next.next;
        }
        current_size--;
    }

    /**
     * Prints the entire linked list in a readable format.
     */
    public void printList() {
        Node temp = head;
        System.out.print("Linked List: ");
        while (temp != null) {
            System.out.print(temp.val);
            if (temp.next != null) System.out.print(" -> ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * Returns the current size of the linked list.
     */
    public int size() {
        return current_size;
    }

    /**
     * Demonstrates all linked list operations.
     */
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        System.out.println("🔹 Adding elements at head:");
        list.addAtHead(10);
        list.addAtHead(20);
        list.printList(); // 20 -> 10

        System.out.println("\n🔹 Adding element at tail:");
        list.addAtTail(30);
        list.printList(); // 20 -> 10 -> 30

        System.out.println("\n🔹 Adding element at index 1:");
        list.addAtIndex(1, 15);
        list.printList(); // 20 -> 15 -> 10 -> 30

        System.out.println("\n🔹 Getting element at index 2:");
        System.out.println("Value at index 2: " + list.get(2)); // 10

        System.out.println("\n🔹 Deleting element at index 1:");
        list.deleteAtIndex(1);
        list.printList(); // 20 -> 10 -> 30

        System.out.println("\n🔹 Deleting head and tail:");
        list.deleteAtIndex(0); // removes 20
        list.deleteAtIndex(list.size() - 1); // removes 30
        list.printList(); // 10

        System.out.println("\n✅ Final Size of Linked List: " + list.size());
    }
}
