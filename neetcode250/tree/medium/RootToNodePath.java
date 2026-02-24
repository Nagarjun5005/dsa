package tree.medium;

import tree.TreeNode;
import java.util.ArrayList;

/**
 * RootToNodePath demonstrates how to find the path
 * from root to a given target node in a Binary Tree.
 *
 * -------------------------------------------------------
 * Problem:
 * Given a binary tree and a target value,
 * return the path from root to that target node.
 *
 * Example:
 *
 *              1
 *            /   \
 *           2     3
 *         /  \   /  \
 *        4    5 6    7
 *
 * Target = 7
 * Output = [1, 3, 7]
 *
 * -------------------------------------------------------
 * Approach:
 * This problem is solved using:
 *
 *      DFS (Depth First Search) + Backtracking
 *
 * Core Idea:
 * 1. Traverse the tree recursively.
 * 2. Add current node to path.
 * 3. If current node equals target → return true.
 * 4. Recursively search in left and right subtree.
 * 5. If neither subtree contains target,
 *    remove current node from path (backtrack).
 *
 * Time Complexity: O(N)
 * - In worst case, we visit all nodes.
 *
 * Space Complexity: O(H)
 * - H = height of tree (recursion stack).
 */
public class RootToNodePath {

    public static void main(String[] args) {

        // Constructing sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.left.right.left = new TreeNode(8);
        root.left.right.right = new TreeNode(9);

        // Finding path to target node
        ArrayList<Integer> result = solve(root, 7);

        System.out.println(result);
    }

    /**
     * Recursive helper method to find path.
     *
     * @param root  Current node
     * @param list  Stores path from root to current node
     * @param find  Target value to search
     *
     * @return true if target exists in subtree
     */
    private static boolean getPath(TreeNode root,
                                   ArrayList<Integer> list,
                                   int find) {

        // Base Case: If node is null
        if (root == null) {
            return false;
        }

        // Add current node to path
        list.add(root.data);

        // If current node is target, stop recursion
        if (root.data == find) {
            return true;
        }

        // Recursively search left OR right subtree
        if (getPath(root.left, list, find)
                || getPath(root.right, list, find)) {
            return true;
        }

        // Backtracking step:
        // If target not found in this path,
        // remove current node from path
        list.remove(list.size() - 1);

        return false;
    }

    /**
     * Public method to initialize path list
     * and start recursion.
     *
     * @param root Root of binary tree
     * @param find Target value
     * @return List containing root-to-target path
     */
    public static ArrayList<Integer> solve(TreeNode root, int find) {

        ArrayList<Integer> list = new ArrayList<>();

        if (root == null) {
            return list;
        }

        getPath(root, list, find);

        return list;
    }
}