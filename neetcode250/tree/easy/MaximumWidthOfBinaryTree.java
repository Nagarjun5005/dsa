package tree.easy;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Computes the maximum width of a binary tree using level-order traversal.
 *
 * <p><b>Definition Used Here:</b>
 * The width of a binary tree is defined as the maximum number of nodes
 * present at any single level of the tree.
 *
 * <p><b>Important Note:</b>
 * This definition counts only existing nodes and does NOT consider
 * null gaps between nodes. This is different from LeetCode's
 * "Maximum Width of Binary Tree" problem (LC 662),
 * which counts positions including nulls.
 *
 * <p><b>Approach:</b>
 * <ul>
 *   <li>Perform Breadth-First Search (BFS) using a queue.</li>
 *   <li>At each level, record the number of nodes (queue size).</li>
 *   <li>Update the maximum width using the largest level size encountered.</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(N), where N is the number of nodes
 * <br><b>Space Complexity:</b> O(N), due to the queue and level lists
 */
public class MaximumWidthOfBinaryTree {

    public static void main(String[] args) {

        // Constructing the binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int widthOfBinaryTree = widthOfBinaryTree(root);
        System.out.println(widthOfBinaryTree);
    }

    /**
     * Returns the maximum number of nodes present at any level of the binary tree.
     *
     * @param root the root of the binary tree
     * @return maximum width of the tree (node count per level)
     */
    public static int widthOfBinaryTree(TreeNode root) {

        // Queue for level-order traversal
        Queue<TreeNode> queue = new LinkedList<>();

        // Stores nodes level by level (not strictly required for width calculation)
        List<List<Integer>> list = new LinkedList<>();

        int maxWidth = 0;

        // Edge case: empty tree
        if (root == null) {
            return 0;
        }

        queue.offer(root);

        // Perform BFS
        while (!queue.isEmpty()) {

            int levelSize = queue.size();

            // Update maximum width
            maxWidth = Math.max(maxWidth, levelSize);

            List<Integer> sublist = new ArrayList<>();

            // Process current level
            for (int i = 0; i < levelSize; i++) {

                TreeNode current = queue.poll();
                assert current != null;

                sublist.add(current.data);

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            list.add(sublist);
        }

        return maxWidth;
    }
}
