package tree.medium;

import tree.TreeNode;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Performs Level Order Traversal (Breadth-First Search) on a Binary Tree.
 *
 * <p>Level order traversal visits nodes level by level from left to right.
 * It uses a queue to ensure nodes are processed in the order they appear
 * in the tree.
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>If the root is null, return an empty result list.</li>
 *   <li>Insert the root node into the queue.</li>
 *   <li>While the queue is not empty:
 *     <ul>
 *       <li>Store the current queue size (number of nodes in the level).</li>
 *       <li>Process exactly that many nodes.</li>
 *       <li>Add each node’s value to a level list.</li>
 *       <li>Insert the node’s children into the queue.</li>
 *     </ul>
 *   </li>
 *   <li>Add the level list to the final result.</li>
 * </ol>
 *
 * <p><b>Why ArrayDeque?</b>
 * <ul>
 *   <li>Does not allow null elements, preventing runtime exceptions.</li>
 *   <li>Provides O(1) insertion and removal operations.</li>
 *   <li>Preferred queue implementation in modern Java.</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b> O(N), where N is the number of nodes
 * <br><b>Space Complexity:</b> O(N) in the worst case (queue storage)
 */
public class LevelOrderTraversal {

    public static void main(String[] args) {

        // Constructing the binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        List<List<Integer>> lists = levelOrder(root);
        System.out.println(lists);
    }

    /**
     * Returns the level order traversal of a binary tree.
     *
     * @param root the root node of the binary tree
     * @return a list of lists containing node values level by level
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();

        // Base case: empty tree
        if (root == null) {
            return result;
        }

        queue.offer(root);

        // BFS traversal
        while (!queue.isEmpty()) {

            int levelSize = queue.size();
            List<Integer> sublist = new LinkedList<>();

            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {

                TreeNode current = queue.poll();
                if (current==null) continue;;
                sublist.add(current.data);

                // Enqueue left child
                if (current.left != null) {
                    queue.offer(current.left);
                }

                // Enqueue right child
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            // Add completed level to result
            result.add(sublist);
        }

        return result;
    }
}
