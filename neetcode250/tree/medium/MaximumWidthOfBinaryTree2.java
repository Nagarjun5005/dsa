package tree.medium;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
/**
 * Problem:
 * --------
 * Given a binary tree, return the maximum width of the tree.
 *
 * The width of a level is defined as the number of nodes between the
 * leftmost and rightmost non-null nodes at that level, inclusive.
 * Null nodes between end nodes are also counted.
 *
 * Approach:
 * ---------
 * This solution uses Level Order Traversal (BFS) with index tracking.
 *
 * Each node is assigned an index as if the tree were a complete binary tree:
 *  - Root index = 0
 *  - Left child index = 2 * index
 *  - Right child index = 2 * index + 1
 *
 * To avoid integer overflow for deep trees:
 *  - Indexes are normalized at each level by subtracting the minimum
 *    index of that level.
 *
 * For each level:
 *  - Capture the first and last node indexes
 *  - Width = lastIndex - firstIndex + 1
 *  - Update the maximum width
 *
 * Data Structures Used:
 * ---------------------
 * Queue<Pair> :
 *  - Pair stores (TreeNode, index)
 *
 * Time Complexity:
 * ----------------
 * O(N), where N is the number of nodes in the tree.
 * Each node is visited exactly once.
 *
 * Space Complexity:
 * -----------------
 * O(N) in the worst case due to the queue (last level of the tree).
 *
 * Example:
 * --------
 * Tree:
 *        1
 *      /   \
 *     2     3
 *    /       \
 *   4         5
 *
 * Level widths:
 *  - Level 0 → width = 1
 *  - Level 1 → width = 2
 *  - Level 2 → width = 4 (positions counted)
 *
 * Output:
 * -------
 * Maximum Width = 4
 */

public class MaximumWidthOfBinaryTree2 {

    public static void main(String[] args) {

        /*
         * Constructing the binary tree:
         *
         *        1
         *      /   \
         *     2     3
         *    /       \
         *   4         5
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        int widthOfBinaryTree = widthOfBinaryTree(root);
        System.out.println(widthOfBinaryTree); // Output: 4
    }

    /**
     * Calculates the maximum width of a binary tree.
     *
     * @param root root node of the binary tree
     * @return maximum width of the tree
     */
    public static int widthOfBinaryTree(TreeNode root) {

        // Edge case: empty tree
        if (root == null) return 0;

        /*
         * Queue holds pairs of:
         *  - TreeNode
         *  - index representing its position in a complete binary tree
         */
        Queue<Pair> queue = new LinkedList<>();

        // Root is assigned index 0
        queue.offer(new Pair(root, 0L));

        int maxWidth = 0;

        // Standard BFS traversal
        while (!queue.isEmpty()) {

            int size = queue.size();

            // Minimum index at current level (used for normalization)
            long minIndex = queue.peek().index;

            long first = 0, last = 0;

            // Traverse all nodes at the current level
            for (int i = 0; i < size; i++) {

                Pair current = queue.poll();

                // Normalize index to avoid overflow
                assert current != null;
                long index = current.index - minIndex;

                // Capture first and last index of the level
                if (i == 0) first = index;
                if (i == size - 1) last = index;

                // Assign index to left child
                if (current.node.left != null) {
                    queue.offer(new Pair(current.node.left, 2 * index));
                }

                // Assign index to right child
                if (current.node.right != null) {
                    queue.offer(new Pair(current.node.right, 2 * index + 1));
                }
            }

            // Calculate width for current level
            maxWidth = Math.max(maxWidth, (int) (last - first + 1));
        }

        return maxWidth;
    }

    /**
     * Helper class to store a tree node along with its index.
     */
    static class Pair {
        TreeNode node;
        long index;

        Pair(TreeNode node, long index) {
            this.node = node;
            this.index = index;
        }
    }
}
