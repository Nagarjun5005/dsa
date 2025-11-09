package linkedList.easy;
import neetcode250.linkedList.easy.Node;

/**
 * ---------------------------------------------------------------
 * Program: Merge Two Sorted Linked Lists
 * ---------------------------------------------------------------
 * Description:
 * Given two sorted linked lists, merge them into one sorted linked list
 * and return its head.
 *
 * Example:
 * list1: 1 → 3 → 5
 * list2: 2 → 4 → 6
 * Output: 1 → 2 → 3 → 4 → 5 → 6
 * ---------------------------------------------------------------
 * Approach:
 * 1. Use a dummy node to simplify list construction.
 * 2. Compare the data of both lists node by node.
 * 3. Attach the smaller node to the result and move that list’s pointer.
 * 4. Once one list ends, attach the remaining part of the other list.
 * 5. Return dummy.next as the head of the merged list.
 * ---------------------------------------------------------------
 * Time Complexity:  O(n + m)
 * - Each node from both lists is visited once.
 *
 * Space Complexity: O(1)
 * - Constant extra space (reuses existing nodes).
 * ---------------------------------------------------------------
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        // Step 1: Create first sorted linked list: 1 → 3 → 5
        Node list1 = new Node(1);
        list1.next = new Node(3);
        list1.next.next = new Node(5);

        // Step 2: Create second sorted linked list: 2 → 4 → 6
        Node list2 = new Node(2);
        list2.next = new Node(4);
        list2.next.next = new Node(6);

        System.out.println("List 1:");
        printLL(list1);
        System.out.println("List 2:");
        printLL(list2);

        //step 3: Merge Lists
        Node mergeTwoLists = mergeTwoLists(list1, list2);
        System.out.println("Merged Sorted List :");
        printLL(mergeTwoLists);
    }


    public static Node mergeTwoLists(Node list1,Node list2){
        // Create a dummy node to act as the start of the merged list
        Node dummy=new Node(-1);
        Node tail=dummy;

        //traverse both lists
        while(list1!=null && list2!=null){
            if(list1.data<=list2.data){
                tail.next=list1;
                list1=list1.next;
            }else{
                tail.next=list2;
                list2=list2.next;
            }
            tail=tail.next;
        }

        // Attach remaining nodes
        /*
        you are directly linking the rest of list2 into your merged chain.
        That means the entire chain starting from list2’s current node
        (6 → 7 → 8 → ... → null) is already connected.
         */
        if(list1!=null){
            tail.next=list1;
        }else{
            tail.next=list2;
        }
        // Return the merged list starting from dummy.next
        return dummy.next;
    }

    /**
     * Prints the linked list
     */
    public static void printLL(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) System.out.print(" → ");
            current = current.next;
        }
        System.out.println();
    }

}
