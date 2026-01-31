package tree.easy;

import tree.TreeNode;

/**
 * Calculates the maximum depth (height) of a Binary Tree.
 *
 * <p><b>Definition:</b>
 * <ul>
 *   <li>The depth of a node is the number of nodes from the root to that node.</li>
 *   <li>The height (max depth) of a binary tree is the number of nodes
 *       on the longest path from the root node to a leaf node.</li>
 * </ul>
 *
 * <p><b>Approach (Recursive - DFS):</b>
 * <ol>
 *   <li>If the current node is null, return 0 (base case).</li>
 *   <li>Recursively compute the depth of the left subtree.</li>
 *   <li>Recursively compute the depth of the right subtree.</li>
 *   <li>Return 1 + maximum of left and right depths.</li>
 * </ol>
 *
 * <p><b>Important:</b>
 * The left subtree is completely evaluated before the right subtree
 * because Java executes method calls sequentially.
 *
 * <p><b>Time Complexity:</b> O(N) — every node is visited once
 * <br><b>Space Complexity:</b> O(H) — recursion stack, where H is tree height
 */
public class HeightDepthOfBinaryTree {

    public static void main(String[] args) {

        // Constructing the binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Calculating maximum depth
        int maxDepth = maxDepth(root);
        System.out.println("Max Depth ----> " + maxDepth);
    }

    /**
     * Returns the maximum depth of a binary tree.
     *
     * @param root the root node of the binary tree
     * @return the maximum depth (height) of the tree
     */
    public static int maxDepth(TreeNode root) {

        // Base case: empty subtree has depth 0
        if (root == null) {
            return 0;
        }

        // Recursively compute depth of left subtree
        int left = maxDepth(root.left);

        // Recursively compute depth of right subtree
        int right = maxDepth(root.right);

        // Current node depth = 1 + maximum of left and right subtree depths
        return 1 + Math.max(left, right);
    }
}
