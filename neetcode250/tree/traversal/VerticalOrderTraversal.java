package tree.traversal;

import tree.TreeNode;
import java.util.*;

/**
 * Vertical Order Traversal of a Binary Tree
 *
 * PROBLEM:
 * --------
 * Given the root of a binary tree, return its vertical order traversal.
 *
 * In vertical order traversal:
 * - Nodes are grouped by their horizontal distance (HD) from the root
 * - Columns are printed from leftmost to rightmost
 * - Nodes within the same column are printed from top to bottom
 *
 * Horizontal Distance (HD):
 * ------------------------
 * - Root node → HD = 0
 * - Left child → HD = parent HD - 1
 * - Right child → HD = parent HD + 1
 *
 * APPROACH:
 * ---------
 * Breadth First Search (Level Order Traversal)
 *
 * - Use a queue to perform BFS
 * - Store each node along with its horizontal distance
 * - Use a TreeMap to group nodes by HD and maintain sorted order
 * - Each HD maps to a list of node values (vertical column)
 *
 * WHY BFS?
 * --------
 * BFS ensures nodes are processed level by level,
 * which preserves top-to-bottom order inside each vertical column.
 *
 * DATA STRUCTURES USED:
 * ---------------------
 * - Queue<Pair> for BFS traversal
 * - TreeMap<Integer, List<Integer>> to store vertical columns
 *
 * TIME COMPLEXITY:
 * ----------------
 * O(n) — every node is processed once
 *
 * SPACE COMPLEXITY:
 * -----------------
 * O(n) — queue + map storage
 */
public class VerticalOrderTraversal {

    /**
     * Helper class to store a tree node along with its horizontal distance.
     */
    static class Pair {
        TreeNode node; // current tree node
        int hd;        // horizontal distance from root

        Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public static void main(String[] args) {

        // Sample tree construction
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(5);
        root.left.right.right.right = new TreeNode(6);

        // Print vertical order traversal
        System.out.println(verticalOrder(root));
    }

    /**
     * Performs vertical order traversal of the binary tree.
     *
     * @param root root of the binary tree
     * @return list of vertical columns from left to right
     */
    public static List<List<Integer>> verticalOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        // Edge case: empty tree
        if (root == null) {
            return result;
        }

        // Map: Horizontal Distance -> List of node values
        Map<Integer, List<Integer>> map = new TreeMap<>();

        // Queue for BFS traversal
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        // Perform level order traversal
        while (!queue.isEmpty()) {

            Pair current = queue.poll();
            TreeNode node = current.node;
            int hd = current.hd;

            // Add node value to its corresponding vertical column
            map.putIfAbsent(hd, new ArrayList<>());
            map.get(hd).add(node.data);

            // Add left child with HD - 1
            if (node.left != null) {
                queue.offer(new Pair(node.left, hd - 1));
            }

            // Add right child with HD + 1
            if (node.right != null) {
                queue.offer(new Pair(node.right, hd + 1));
            }
        }

        // Collect result from leftmost column to rightmost
        for (List<Integer> column : map.values()) {
            result.add(column);
        }

        return result;
    }
}
