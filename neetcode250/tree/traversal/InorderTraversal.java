package tree.traversal;

import tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Performs Inorder Traversal (Left → Root → Right) on a Binary Tree.
 *
 * <p>In inorder traversal:
 * <ul>
 *   <li>The left subtree is visited first</li>
 *   <li>Then the root node is processed</li>
 *   <li>Finally, the right subtree is visited</li>
 * </ul>
 *
 * <p>This implementation uses recursion and a helper method to
 * maintain a shared result list across recursive calls.
 *
 * <p>Time Complexity: O(N) where N is the number of nodes in the tree
 * <br>Space Complexity: O(H) where H is the height of the tree (recursion stack)
 */
public class InorderTraversal {

    public static void main(String[] args) {
        // Creating tree nodes
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Inorder traversal
        List<Integer> result = inorderTraversal(root);
        System.out.println(result);
    }

    /**
     * Initiates inorder traversal of a binary tree.
     *
     * @param root the root node of the binary tree
     * @return a list containing the inorder traversal sequence
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderHelper(root, list);
        return list;
    }

    /**
     * Helper method to recursively traverse the tree in inorder fashion.
     *
     * @param root the current node being processed
     * @param list the shared list used to store traversal results
     */
    private static void inorderHelper(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inorderHelper(root.left, list);
        list.add(root.data);
        inorderHelper(root.right, list);
    }
}
