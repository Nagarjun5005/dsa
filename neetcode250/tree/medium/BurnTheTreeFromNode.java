package tree.medium;

import tree.TreeNode;

import java.util.*;

/**
 * BurnTheTreeFromNode
 *
 * This class calculates the minimum time required to burn
 * the entire binary tree starting from a given target node.
 *
 * Problem Description:
 * - Fire starts at the target node.
 * - Every minute, fire spreads to:
 *      1. Left child
 *      2. Right child
 *      3. Parent
 *
 * Since a binary tree does not have parent pointers,
 * we first construct a parent map to simulate
 * bidirectional traversal (like an undirected graph).
 *
 * Approach:
 * 1. Build parent mapping using BFS.
 * 2. Perform BFS starting from the target node.
 * 3. Each BFS level represents 1 minute.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class BurnTheTreeFromNode {


    /**
     * Driver method to test the burn tree logic.
     *
     * Constructs the sample binary tree:
     *
     *          3
     *        /   \
     *       5     1
     *      / \   / \
     *     6   2 0   8
     *        / \
     *       7   4
     *
     * Fire starts from node 6.
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);

        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);

        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        int minimumTikeTakenToBurnTheTreeFromNode =
                minimumTikeTakenToBurnTheTreeFromNode(root, root.left.left);

        System.out.println(minimumTikeTakenToBurnTheTreeFromNode);
    }


    /**
     * Computes the minimum time required to burn the entire tree
     * starting from the given target node.
     *
     * Steps:
     * 1. Build parent map (child → parent mapping).
     * 2. Perform BFS from the target node.
     * 3. Spread fire to left child, right child, and parent.
     * 4. Each BFS level corresponds to 1 minute.
     *
     * @param root   Root of the binary tree
     * @param target Node from where burning starts
     * @return Number of minutes required to burn entire tree
     */
    public static int minimumTikeTakenToBurnTheTreeFromNode(TreeNode root, TreeNode target){

        Map<TreeNode,TreeNode> parentMap = buildParentMap(root);

        // Queue used for BFS traversal (simulating fire spread)
        Queue<TreeNode> queue = new LinkedList<>();

        // Set to track visited (already burned) nodes
        Set<TreeNode> visited = new HashSet<>();

        // Start BFS from target node
        queue.offer(target);
        visited.add(target);

        int time = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Indicates whether fire spread in current minute
            boolean burnt = false;

            for(int i = 0; i < size; i++) {

                TreeNode current = queue.poll();

                // Spread to left child
                if (current.left != null && !visited.contains(current.left)){
                    burnt = true;
                    queue.offer(current.left);
                    visited.add(current.left);
                }

                // Spread to right child
                if (current.right != null && !visited.contains(current.right)){
                    burnt = true;
                    queue.offer(current.right);
                    visited.add(current.right);
                }

                // Spread to parent
                TreeNode parent = parentMap.get(current);
                if(parent != null && !visited.contains(parent)){
                    burnt = true;
                    queue.offer(parent);
                    visited.add(parent);
                }
            }

            // If at least one node caught fire, increment time
            if (burnt){
                time++;
            }
        }

        return time;
    }


    /**
     * Builds a mapping of each node to its parent.
     *
     * Since binary tree nodes do not have parent references,
     * this method performs a BFS traversal to record
     * parent-child relationships.
     *
     * This enables upward traversal during the burning process.
     *
     * @param root Root of the binary tree
     * @return Map containing child → parent mapping
     */
    public static Map<TreeNode,TreeNode> buildParentMap(TreeNode root){

        Map<TreeNode,TreeNode> parentMap = new HashMap<>();

        // Queue for BFS traversal
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        // Root has no parent
        parentMap.put(root, null);

        while (!queue.isEmpty()){

            TreeNode current = queue.poll();

            // Map left child to parent
            if(current.left != null){
                parentMap.put(current.left, current);
                queue.offer(current.left);
            }

            // Map right child to parent
            if(current.right != null){
                parentMap.put(current.right, current);
                queue.offer(current.right);
            }
        }

        return parentMap;
    }
}