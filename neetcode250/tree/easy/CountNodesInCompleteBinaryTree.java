package tree.easy;

import tree.TreeNode;

/**
 * Problem:
 * Count the number of nodes in a Complete Binary Tree.
 *
 * Optimized Approach:
 * - If leftmost height == rightmost height → Tree is Perfect.
 *   Nodes = 2^h - 1
 * - Otherwise recursively count left and right subtrees.
 *
 * Time Complexity: O(log^2 n)
 * Space Complexity: O(log n)  (recursion stack)
 */
public class CountNodesInCompleteBinaryTree {

    public static void main(String[] args) {

        // Constructing a sample complete binary tree
        //          1
        //       /     \
        //      2       3
        //    /   \    /
        //   4     5  6

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);

        int countNodes = countNodes(root);
        System.out.println(countNodes);  // Output: 6
    }

    /**
     * Counts total nodes in a complete binary tree.
     *
     * @param root root of tree
     * @return total number of nodes
     */
    public static int countNodes(TreeNode root) {

        // Base case: empty tree
        if (root == null) {
            return 0;
        }

        // Calculate leftmost and rightmost heights
        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);

        /*
         * If both heights are equal,
         * it means the tree is a Perfect Binary Tree.
         *
         * Total nodes in perfect tree:
         * 2^h - 1
         *
         * (1 << h) is same as 2^h
         */
        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1;
        }

        /*
         * If not perfect:
         * Recursively count nodes in left and right subtree.
         *
         * 1 (current node)
         * + left subtree nodes
         * + right subtree nodes
         */
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * Computes height by moving only to left.
     * Used to detect if tree is perfect.
     */
    private static int getLeftHeight(TreeNode node) {
        int height = 0;

        while (node != null) {
            height++;
            node = node.left;
        }

        return height;
    }

    /**
     * Computes height by moving only to right.
     * Used to detect if tree is perfect.
     */
    private static int getRightHeight(TreeNode node) {
        int height = 0;

        while (node != null) {
            height++;
            node = node.right;
        }

        return height;
    }
}