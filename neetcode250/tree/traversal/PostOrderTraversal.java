package tree.traversal;

import tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Performs Postorder Traversal (Left → Right → Root) on a Binary Tree.
 *
 * <p>In postorder traversal:
 * <ul>
 *   <li>The left subtree is visited first</li>
 *   <li>Then the right subtree is visited</li>
 *   <li>Finally, the root node is processed</li>
 * </ul>
 *
 * <p>This traversal is commonly used in:
 * <ul>
 *   <li>Deleting trees</li>
 *   <li>Evaluating expression trees</li>
 *   <li>Bottom-up processing</li>
 * </ul>
 *
 * <p>Time Complexity: O(N)
 * <br>Space Complexity: O(H), where H is the height of the tree
 */
public class PostOrderTraversal {

    public static void main(String[] args) {
        // Creating tree nodes
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Postorder traversal
        List<Integer> result = postorderTraversal(root);
        System.out.println(result);
    }

    /**
     * Initiates postorder traversal of a binary tree.
     *
     * @param root the root node of the binary tree
     * @return a list containing the postorder traversal sequence
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    /**
     * Helper method to recursively traverse the tree in postorder fashion.
     *
     * @param root the current node being processed
     * @param list the shared list used to store traversal results
     */
    private static void postOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.data);
    }
}
