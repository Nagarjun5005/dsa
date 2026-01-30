package tree.traversal;

import tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Performs Preorder Traversal (Root → Left → Right) on a Binary Tree.
 *
 * <p>In preorder traversal:
 * <ul>
 *   <li>The root node is processed first</li>
 *   <li>Then the left subtree is traversed</li>
 *   <li>Finally, the right subtree is traversed</li>
 * </ul>
 *
 * <p>Preorder traversal is useful for:
 * <ul>
 *   <li>Creating a copy of the tree</li>
 *   <li>Serializing/deserializing trees</li>
 *   <li>Prefix expression evaluation</li>
 * </ul>
 *
 * <p>Time Complexity: O(N)
 * <br>Space Complexity: O(H), where H is the height of the tree
 */
public class PreOrderTraversal {

    public static void main(String[] args) {
        // Creating tree nodes
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Preorder traversal
        List<Integer> result = preorderTraversal(root);
        System.out.println(result);
    }

    /**
     * Initiates preorder traversal of a binary tree.
     *
     * @param root the root node of the binary tree
     * @return a list containing the preorder traversal sequence
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }

    /**
     * Helper method to recursively traverse the tree in preorder fashion.
     *
     * @param root the current node being processed
     * @param list the shared list used to store traversal results
     */
    private static void preorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        list.add(root.data);
        preorder(root.left, list);
        preorder(root.right, list);
    }
}
