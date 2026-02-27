package tree.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Utility class to check whether a Binary Tree satisfies
 * the Children Sum Property.
 *
 * Children Sum Property:
 * For every non-leaf node,
 *      node.data = leftChild.data + rightChild.data
 *
 * Leaf nodes are always considered valid.
 */
public class ChildrenSumInBinaryTree {

    public static void main(String[] args) {

        /*
                 35
                /  \
              20    15
             /  \   /  \
           15   5  10   5
        */

        Node root = new Node(35);
        root.left = new Node(20);
        root.right = new Node(15);

        root.left.left = new Node(15);
        root.left.right = new Node(5);

        root.right.left = new Node(10);
        root.right.right = new Node(5);

        boolean iterativeResult = childrenSumPropertyIterative(root);
        System.out.println("Iterative Result: " + iterativeResult);

        boolean recursiveResult = childrenSumPropertyRecursive(root);
        System.out.println("Recursive Result: " + recursiveResult);
    }

    /**
     * Checks Children Sum Property using recursion (DFS approach).
     *
     * Approach:
     * 1. Base case: If node is null → return true.
     * 2. If node is a leaf → return true.
     * 3. Check if current node satisfies sum condition.
     * 4. Recursively validate left and right subtrees.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(H) — recursion stack (H = tree height)
     *
     * @param root Root of the binary tree
     * @return true if tree satisfies children sum property, else false
     */
    public static boolean childrenSumPropertyRecursive(Node root) {

        if (root == null)
            return true;

        if (root.left == null && root.right == null)
            return true;

        int left = (root.left != null) ? root.left.data : 0;
        int right = (root.right != null) ? root.right.data : 0;

        if (root.data != left + right)
            return false;

        return childrenSumPropertyRecursive(root.left)
                && childrenSumPropertyRecursive(root.right);
    }

    /**
     * Checks Children Sum Property using iterative BFS (Level Order Traversal).
     *
     * Approach:
     * 1. Use a queue for level-order traversal.
     * 2. For each node:
     *      - If leaf → continue.
     *      - Compute sum of children.
     *      - If sum != parent value → return false.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N) — queue in worst case
     *
     * @param root Root of the binary tree
     * @return true if tree satisfies children sum property, else false
     */
    public static boolean childrenSumPropertyIterative(Node root) {

        if (root == null)
            return true;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            if (current.left == null && current.right == null)
                continue;

            int sum = 0;

            if (current.left != null) {
                sum += current.left.data;
                queue.offer(current.left);
            }

            if (current.right != null) {
                sum += current.right.data;
                queue.offer(current.right);
            }

            if (current.data != sum)
                return false;
        }

        return true;
    }
}
