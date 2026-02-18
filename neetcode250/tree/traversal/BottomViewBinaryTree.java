package tree.traversal;

import tree.TreeNode;

import java.util.*;

/**
 * BottomViewBinaryTree
 *
 * ==========================================================
 * PROBLEM STATEMENT
 * ==========================================================
 * Given the root of a binary tree, return the bottom view
 * of the binary tree.
 *
 * The bottom view contains the nodes that are visible when
 * the tree is viewed from the bottom.
 *
 * A node is included in the bottom view if it is the
 * lowest (deepest) node at its horizontal distance (HD)
 * from the root.
 *
 *
 * ==========================================================
 * CONCEPT: HORIZONTAL DISTANCE (HD)
 * ==========================================================
 * Each node is assigned a horizontal distance from root:
 *
 *      Root → HD = 0
 *      Left child → HD - 1
 *      Right child → HD + 1
 *
 * Example:
 *
 *              1(0)
 *            /       \
 *        2(-1)       3(+1)
 *        /    \          \
 *    4(-2)  5(0)        7(+2)
 *
 *
 * ==========================================================
 * APPROACH: BFS (LEVEL ORDER TRAVERSAL)
 * ==========================================================
 * Why BFS?
 * --------
 * - BFS processes nodes level by level.
 * - Lower level nodes appear later in traversal.
 * - For bottom view, we keep overwriting values at the same HD.
 * - The last node stored for each HD will be the bottom-most node.
 *
 *
 * ==========================================================
 * ALGORITHM STEPS
 * ==========================================================
 * 1. If root is null → return empty list.
 * 2. Create a TreeMap<Integer, Integer> to store:
 *        HD → latest node value
 * 3. Perform BFS using a queue.
 * 4. For each node:
 *        - Put (HD, node value) in map (overwrite allowed).
 *        - Add left child with HD - 1.
 *        - Add right child with HD + 1.
 * 5. Extract values from TreeMap in sorted order of HD.
 *
 *
 * ==========================================================
 * DATA STRUCTURES USED
 * ==========================================================
 * - Queue<Pair> → for BFS traversal
 * - TreeMap<Integer, Integer> → keeps HD sorted automatically
 *
 *
 * ==========================================================
 * TIME COMPLEXITY
 * ==========================================================
 * O(n log n)
 * - Each node is processed once → O(n)
 * - Each insertion in TreeMap → O(log n)
 *
 *
 * ==========================================================
 * SPACE COMPLEXITY
 * ==========================================================
 * O(n)
 * - Queue stores nodes
 * - Map stores HD entries
 *
 *
 * ==========================================================
 * DIFFERENCE: TOP VIEW vs BOTTOM VIEW
 * ==========================================================
 *
 * TOP VIEW:
 *     Store first node encountered at each HD
 *     → if (!map.containsKey(hd)) put(...)
 *
 * BOTTOM VIEW:
 *     Always overwrite at each HD
 *     → map.put(hd, value)
 *
 */
public class BottomViewBinaryTree {

    /**
     * Helper class to store a tree node along with its horizontal distance.
     */
    public static class Pair {

        TreeNode node; // current tree node
        int hd;        // horizontal distance from root

        public Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public static void main(String[] args) {

        /*
         * Sample Tree:
         *
         *                 1
         *              /     \
         *            2         3
         *          /   \      /   \
         *        4      5    6     7
         *              / \
         *             8   9
         */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        root.left.right.left = new TreeNode(8);
        root.left.right.right = new TreeNode(9);

        System.out.println("Bottom View: " + bottomView(root));
    }


    /**
     * Computes the bottom view of a binary tree.
     *
     * @param root Root node of the binary tree
     * @return List of node values visible from bottom view
     */
    public static List<Integer> bottomView(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        // Edge case: empty tree
        if (root == null) {
            return result;
        }

        // Map to store horizontal distance → latest node value
        Map<Integer, Integer> map = new TreeMap<>();

        // Queue for BFS traversal
        Queue<Pair> queue = new LinkedList<>();

        // Start with root at HD = 0
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {

            Pair current = queue.poll();
            TreeNode node = current.node;
            int hd = current.hd;

            // For bottom view, always overwrite the value at HD
            map.put(hd, node.data);

            // Add left child with HD - 1
            if (node.left != null) {
                queue.offer(new Pair(node.left, hd - 1));
            }

            // Add right child with HD + 1
            if (node.right != null) {
                queue.offer(new Pair(node.right, hd + 1));
            }
        }

        // Collect values from leftmost HD to rightmost HD
        for (int value : map.values()) {
            result.add(value);
        }

        return result;
    }
}
