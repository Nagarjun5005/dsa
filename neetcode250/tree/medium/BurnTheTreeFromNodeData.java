package tree.medium;

import tree.TreeNode;

import java.util.*;

/**
 * BurnTheTreeFromNodeData
 *
 * This class calculates the minimum time required to burn
 * the entire binary tree starting from a given node value.
 *
 * Problem:
 * - Fire starts from the node having value = start.
 * - Every minute, fire spreads to:
 *      1. Left child
 *      2. Right child
 *      3. Parent
 *
 * Since binary trees do not have parent pointers,
 * we first build a child → parent mapping using BFS.
 *
 * Then we perform BFS starting from the target node
 * and count the number of levels (minutes).
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
class BurnTheTreeFromNodeData {


    /**
     * Driver method for testing.
     *
     * Constructs the sample tree:
     *
     *          3
     *        /   \
     *       5     1
     *      / \   / \
     *     6   2 0   8
     *        / \
     *       7   4
     *
     * Fire starts from node with value 6.
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

        int minimumTikeTakenToBurnTheTreeFromNode = amountOfTime(root, 6);
        System.out.println(minimumTikeTakenToBurnTheTreeFromNode);
    }


    /**
     * Calculates the minimum time required to burn the entire tree
     * starting from the node whose value equals 'start'.
     *
     * Steps:
     * 1. Build parent mapping and locate target node.
     * 2. Perform BFS from target node.
     * 3. Spread fire to left, right, and parent nodes.
     * 4. Each BFS level represents 1 minute.
     *
     * @param root  Root of the binary tree
     * @param start Value of the node from which fire starts
     * @return Total minutes required to burn the entire tree
     */
    public static int amountOfTime(TreeNode root, int start){

        // Map to store child → parent relationships
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        // Locate target node while building parent mapping
        TreeNode target = buildParentMap(root, parentMap, start);

        // Queue for BFS traversal (fire spreading)
        Queue<TreeNode> queue = new LinkedList<>();

        // Set to track visited (already burned) nodes
        Set<TreeNode> visited = new HashSet<>();

        queue.offer(target);
        visited.add(target);

        int time = 0;

        while (!queue.isEmpty()){

            int size = queue.size();

            // Indicates whether fire spread in current minute
            boolean burnt = false;

            for(int i = 0; i < size; i++){

                TreeNode current = queue.poll();

                // Spread fire to left child
                if (current.left != null && !visited.contains(current.left)){
                    burnt = true;
                    queue.offer(current.left);
                    visited.add(current.left);
                }

                // Spread fire to right child
                if (current.right != null && !visited.contains(current.right)){
                    burnt = true;
                    queue.offer(current.right);
                    visited.add(current.right);
                }

                // Spread fire to parent
                TreeNode parent = parentMap.get(current);
                if(parent != null && !visited.contains(parent)){
                    burnt = true;
                    queue.offer(parent);
                    visited.add(parent);
                }
            }

            // If at least one node got burned in this level, increment time
            if (burnt){
                time++;
            }
        }

        return time;
    }


    /**
     * Builds a parent mapping for the tree and
     * simultaneously locates the node whose value equals 'start'.
     *
     * This allows upward traversal during burning.
     *
     * @param root       Root of the binary tree
     * @param parentMap  Map to store child → parent mapping
     * @param start      Target node value
     * @return TreeNode corresponding to the start value
     */
    public static TreeNode buildParentMap(TreeNode root,
                                          Map<TreeNode, TreeNode> parentMap,
                                          int start){

        // Queue for BFS traversal
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        // Root has no parent
        parentMap.put(root, null);

        TreeNode targetNode = null;

        while (!queue.isEmpty()){

            TreeNode current = queue.poll();

            // Locate target node
            if(current.data == start){
                targetNode = current;
            }

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

        return targetNode;
    }
}