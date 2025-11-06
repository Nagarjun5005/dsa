package linkedList.easy;
import neetcode250.linkedList.easy.Node;
/**
 * ---------------------------------------------------------------
 * Program: Check if a Linked List is Palindrome
 * ---------------------------------------------------------------
 * Description:
 * A linked list is a palindrome if it reads the same forward and backward.
 * Example: 1 → 2 → 3 → 2 → 1  is a palindrome.
 *
 * ---------------------------------------------------------------
 * Approach:
 * 1. Use the slow & fast pointer technique to find the middle of the list.
 *    - slow moves 1 step at a time.
 *    - fast moves 2 steps at a time.
 * 2. Reverse the second half of the linked list starting from the middle.
 * 3. Compare the first half and the reversed second half node by node.
 * 4. If all corresponding nodes match → it’s a palindrome.
 * 5. (Optional) Restore the list to its original form by reversing back.
 *
 * ---------------------------------------------------------------
 * Time Complexity:  O(n)
 * - Each node is visited at most twice (once during traversal, once during comparison).
 *
 * Space Complexity: O(1)
 * - Only a few pointers are used; no extra data structures required.
 *
 * ---------------------------------------------------------------
 * Example Walkthrough:
 * Input:  1 → 2 → 3 → 2 → 1
 * Step 1: Find middle → slow = 3
 * Step 2: Reverse second half → 1 → 2
 * Step 3: Compare both halves → (1,1), (2,2) ✅
 * Result: true (palindrome)
 * ---------------------------------------------------------------
 */

public class PalindromeCheck {

    public static void main(String[] args) {

        // Step 1: Create linked list 1 → 2 → 3 → 2 → 1
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node (1);

        // Step 2: Print original list
        System.out.print("Original Linked List: ");
        printLL(head);

        //step 3 : check if palindrome
        boolean isPalindrome=isPalindrome(head);
        System.out.println("Is Palindrome? "+isPalindrome);
    }

    public static boolean isPalindrome(Node head){
        if(head==null || head.next==null){
            return true;
        }

        // step 1 : Find middle using slow and fast pointers
        Node slow=head;
        Node fast=head;
        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }

        //step 2 : Reverse second half of list
        Node secondHalfHeadFromReverse=reverseList(slow);

        //step 3: Compare first half and reversed second half
        Node firstHalfHead=head;
        Node secondHalfHead=secondHalfHeadFromReverse;
        boolean palindrome=true;

        while(secondHalfHead!=null){
            if(firstHalfHead.data!=secondHalfHead.data){
                palindrome=false;
                break;
            }
            firstHalfHead=firstHalfHead.next;
            secondHalfHead=secondHalfHead.next;
        }

        return palindrome;

    }

    private static Node reverseList(Node head) {
        Node current=head;
        Node previous=null;
        Node next=null;
        while(current!=null){
            next=current.next;
            current.next=previous;
            previous=current;
            current=next;
        }

        return previous;
    }

    /**
     * Prints the linked list nodes in sequence.
     *
     * @param head the head node of the linked list
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
