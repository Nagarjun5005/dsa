package tree.traversal;

import tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * BoundaryTraversal
 *
 * This class implements Boundary Traversal of a Binary Tree.
 *
 * Boundary Traversal Definition:
 * ------------------------------
 * Boundary traversal of a binary tree includes:
 * 1. Root node (if it is not a leaf)
 * 2. Left boundary nodes (excluding leaf nodes)
 * 3. All leaf nodes (from left to right)
 * 4. Right boundary nodes (excluding leaf nodes, added in reverse order)
 *
 * Traversal Order:
 * ----------------
 * Root → Left Boundary → Leaves → Right Boundary (bottom-up)
 *
 * Example:
 * --------
 *          1
 *        /   \
 *       2     3
 *      / \   / \
 *     4   5 6   7
 *
 * Output:
 * --------
 * [1, 2, 4, 5, 6, 7, 3]
 *
 * Key Points:
 * -----------
 * - Leaf nodes must NOT be duplicated
 * - Left boundary prefers left child, falls back to right child
 * - Right boundary prefers right child, falls back to left child
 *
 * Time Complexity:
 * ----------------
 * O(n), where n is the number of nodes in the tree
 *
 * Space Complexity:
 * -----------------
 * O(n) for storing the result
 * O(h) for recursion stack while collecting leaves
 */
public class BoundaryTraversal {

    public static void main(String[] args) {

        // Constructing sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        ArrayList<Integer> result = boundaryTraversal(root);
        System.out.println(result);
    }

    /**
     * Performs boundary traversal of the given binary tree.
     *
     * @param root Root node of the binary tree
     * @return List containing boundary traversal in anti-clockwise order
     */
    public static ArrayList<Integer> boundaryTraversal(TreeNode root) {

        ArrayList<Integer> result = new ArrayList<>();

        // Edge case: empty tree
        if (root == null) {
            return result;
        }

        // Add root only if it is not a leaf node
        if (!isLeaf(root)) {
            result.add(root.data);
        }

        // Add left boundary (excluding leaf nodes)
        addLeftBoundary(root, result);

        // Add all leaf nodes
        addLeaves(root, result);

        // Add right boundary (excluding leaf nodes, bottom-up)
        addRightBoundary(root, result);

        return result;
    }

    /**
     * Adds the left boundary of the tree to the result.
     * Leaf nodes are excluded to avoid duplication.
     */
    private static void addLeftBoundary(TreeNode root, ArrayList<Integer> result) {

        TreeNode curr = root.left;

        while (curr != null) {

            if (!isLeaf(curr)) {
                result.add(curr.data);
            }

            // Prefer left child; fallback to right child
            if (curr.left != null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    /**
     * Adds all leaf nodes of the tree using DFS traversal.
     */
    private static void addLeaves(TreeNode root, ArrayList<Integer> result) {

        // If current node is a leaf, add and return
        if (isLeaf(root)) {
            result.add(root.data);
            return;
        }

        if (root.left != null) {
            addLeaves(root.left, result);
        }

        if (root.right != null) {
            addLeaves(root.right, result);
        }
    }

    /**
     * Adds the right boundary of the tree to the result.
     * Nodes are first collected top-down and then added
     * to the result in reverse order (bottom-up).
     */
    private static void addRightBoundary(TreeNode root, ArrayList<Integer> result) {

        List<Integer> temp = new ArrayList<>();
        TreeNode curr = root.right;

        while (curr != null) {

            if (!isLeaf(curr)) {
                temp.add(curr.data);
            }

            // Prefer right child; fallback to left child
            if (curr.right != null) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        // Add right boundary in reverse order
        for (int i = temp.size() - 1; i >= 0; i--) {
            result.add(temp.get(i));
        }
    }

    /**
     * Utility method to check if a node is a leaf node.
     *
     * @param node Tree node
     * @return true if node has no children, false otherwise
     */
    private static boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
