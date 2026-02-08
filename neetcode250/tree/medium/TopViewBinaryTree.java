package tree.medium;

import tree.TreeNode;
import java.util.*;

/**
 * Top View of Binary Tree
 *
 * PROBLEM:
 * --------
 * Given the root of a binary tree, return the top view of the tree.
 *
 * The top view contains the nodes that are visible when the tree
 * is viewed from the top.
 *
 * A node is visible if it is the first node encountered at its
 * horizontal distance (HD) from the root.
 *
 * APPROACH:
 * ---------
 * Level Order Traversal (BFS) with Horizontal Distance
 *
 * - Assign horizontal distance (HD) to each node:
 *     Root → HD = 0
 *     Left child → HD - 1
 *     Right child → HD + 1
 * - Perform BFS so that higher-level nodes are processed first
 * - For each HD, store the first node encountered
 * - Use TreeMap to keep HDs sorted (left → right)
 *
 * WHY BFS?
 * --------
 * BFS guarantees that the first node encountered at a given
 * horizontal distance is the top-most node.
 *
 * DATA STRUCTURES USED:
 * ---------------------
 * - Queue<Pair> for BFS traversal
 * - TreeMap<Integer, Integer> to store first node at each HD
 *
 * TIME COMPLEXITY:
 * ----------------
 * O(n) — each node is processed once
 *
 * SPACE COMPLEXITY:
 * -----------------
 * O(n) — queue and map storage
 */
public class TopViewBinaryTree {

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

        // Sample tree construction
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(5);
        root.left.right.right.right = new TreeNode(6);

        // Print top view of the binary tree
        System.out.println(topView(root));
    }

    /**
     * Returns the top view of the given binary tree.
     *
     * @param root root of the binary tree
     * @return list of node values visible from the top view
     */
    public static List<Integer> topView(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        // Edge case: empty tree
        if (root == null) {
            return result;
        }

        // Stores the first node value for each horizontal distance
        Map<Integer, Integer> map = new TreeMap<>();

        // Queue for BFS traversal
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        // Perform level order traversal
        while (!queue.isEmpty()) {

            Pair current = queue.poll();
            TreeNode node = current.node;
            int hd = current.hd;

            // Store only the first node encountered at this HD
            if (!map.containsKey(hd)) {
                map.put(hd, node.data);
            }

            // Add left child with HD - 1
            if (node.left != null) {
                queue.offer(new Pair(node.left, hd - 1));
            }

            // Add right child with HD + 1
            if (node.right != null) {
                queue.offer(new Pair(node.right, hd + 1));
            }
        }

        // Collect result from leftmost to rightmost HD
        for (int val : map.values()) {
            result.add(val);
        }

        return result;
    }
}
