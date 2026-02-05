package tree.traversal;

import tree.TreeNode;
import java.util.*;

/**
 * ZIG_Zag_Traversal
 *
 * This class demonstrates Zigzag (Spiral) Level Order Traversal of a Binary Tree.
 *
 * Zigzag traversal means:
 * - Level 0 → Left to Right
 * - Level 1 → Right to Left
 * - Level 2 → Left to Right
 *   and so on...
 *
 * Approach:
 * ----------
 * - Use Breadth First Search (BFS) with a Queue to traverse level by level.
 * - Maintain a boolean flag `leftToRight` to decide insertion order.
 * - For each level:
 *      - If leftToRight is true → insert values at the end of the list
 *      - If leftToRight is false → insert values at the beginning of the list
 * - Toggle the flag after each level.
 *
 * Data Structures Used:
 * ---------------------
 * - Queue<TreeNode> → for level-order traversal
 * - LinkedList<Integer> → allows insertion at both beginning and end in O(1)
 *
 * Time Complexity:
 * ----------------
 * O(n) where n is the number of nodes in the tree.
 *
 * Space Complexity:
 * -----------------
 * O(n) for the queue and result list.
 */
public class ZIG_Zag_Traversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        List<List<Integer>> lists = zigzagLevelOrder(root);
        System.out.println(lists);
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> sublist = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                if (leftToRight) {
                    sublist.addLast(current.data);
                } else {
                    sublist.addFirst(current.data);
                }

                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }

            result.add(sublist);
            leftToRight = !leftToRight; // toggle direction
        }

        return result;
    }
}
