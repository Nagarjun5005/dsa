package tree.traversal;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeftView
 *
 * ==========================================================
 * PROBLEM STATEMENT
 * ==========================================================
 * Given the root of a binary tree, return the Left View
 * of the binary tree.
 *
 * The left view consists of nodes visible when the tree
 * is viewed from the left side.
 *
 * For each level of the tree, only the leftmost node
 * is visible.
 *
 *
 * ==========================================================
 * APPROACH 1: ITERATIVE (BFS - LEVEL ORDER TRAVERSAL)
 * ==========================================================
 *
 * IDEA:
 * - Perform level order traversal using a queue.
 * - For each level:
 *      → Add the FIRST node (i == 0)
 *
 * WHY IT WORKS:
 * - Level order ensures nodes are processed level by level.
 * - The first node encountered in each level
 *   is the leftmost node.
 *
 *
 * TIME COMPLEXITY:
 * O(n) — every node is processed once
 *
 * SPACE COMPLEXITY:
 * O(n) — queue may store nodes at a level
 *
 *
 * ==========================================================
 * APPROACH 2: RECURSIVE (DFS - LEFT FIRST)
 * ==========================================================
 *
 * IDEA:
 * - Perform DFS traversal.
 * - Visit nodes in this order:
 *      Node → Left → Right
 *
 * - Maintain current level.
 * - If level == result.size():
 *      → This is the first node at this level
 *      → Add it to result.
 *
 * WHY IT WORKS:
 * - Since we visit left subtree first,
 *   the first node encountered at each level
 *   is the leftmost node.
 *
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * SPACE COMPLEXITY:
 * O(h) — recursion stack (h = height of tree)
 *
 *
 * ==========================================================
 * IMPORTANT INSIGHT
 * ==========================================================
 * If we reverse DFS order to:
 *      Node → Right → Left
 *
 * We get RIGHT VIEW instead.
 */
public class LeftView {

    public static void main(String[] args) {

        /*
         * Sample Tree:
         *
         *             1
         *            / \
         *           2   3
         *            \
         *             4
         *              \
         *               5
         *                \
         *                 6
         */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(5);
        root.left.right.right.right = new TreeNode(6);

        System.out.println("Left View (Iterative): " + leftViewIterative(root));

        ArrayList<Integer> result = leftViewRecursive(root);
        System.out.println("Left View (Recursive): " + result);
    }

    /**
     * ======================================================
     * ITERATIVE APPROACH (BFS)
     * ======================================================
     *
     * Uses level order traversal.
     * Adds first node of each level to result.
     */
    private static ArrayList<Integer> leftViewIterative(TreeNode root) {

        ArrayList<Integer> result = new ArrayList<>();

        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                TreeNode current = queue.poll();

                // If first node at this level → leftmost
                if (i == 0) {
                    result.add(current.data);
                }

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }

        return result;
    }


    /**
     * ======================================================
     * RECURSIVE APPROACH (DFS - LEFT FIRST)
     * ======================================================
     *
     * Calls helper with level tracking.
     */
    private static ArrayList<Integer> leftViewRecursive(TreeNode root) {

        ArrayList<Integer> result = new ArrayList<>();
        leftViewHelper(root, 0, result);
        return result;
    }


    /**
     * Helper method for DFS traversal.
     *
     * @param root   current tree node
     * @param level  current depth level
     * @param result list storing left view
     */
    private static void leftViewHelper(TreeNode root, int level, ArrayList<Integer> result) {

        if (root == null) return;

        /*
         * If visiting this level for the first time,
         * then this node is the leftmost node.
         */
        if (level == result.size()) {
            result.add(root.data);
        }

        // Visit left subtree first
        leftViewHelper(root.left, level + 1, result);

        // Then visit right subtree
        leftViewHelper(root.right, level + 1, result);
    }
}
