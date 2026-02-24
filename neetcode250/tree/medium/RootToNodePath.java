package tree.medium;

import tree.TreeNode;
import java.util.*;

/**
 * RootToNodePath
 *
 * ------------------------------------------------------------
 * Problem:
 * Given a Binary Tree and a target value,
 * return the path from root to that target node.
 *
 * Since a tree has a unique path from root to any node,
 * once we find the target, we can reconstruct that path.
 *
 * ------------------------------------------------------------
 * This class contains two approaches:
 *
 * 1️⃣ Brute Force (Recursive DFS + Backtracking)
 * 2️⃣ BFS (Queue + Parent Map)
 *
 * ------------------------------------------------------------
 * Time Complexity: O(N)
 * Space Complexity:
 *    Recursive → O(H)
 *    BFS → O(N)
 */
public class RootToNodePath {

    // ==========================================================
    // 1️⃣ BRUTE FORCE (Recursive DFS + Backtracking)
    // ==========================================================

    /**
     * Public method to find root → target path using recursion.
     *
     * @param root   Root of binary tree
     * @param target Target value
     * @return List containing path from root to target
     */
    public static ArrayList<Integer> rootToNodeRecursive(TreeNode root, int target) {

        ArrayList<Integer> path = new ArrayList<>();
        if (root == null)
            return path;

        findPath(root, target, path);
        return path;
    }

    /**
     * Recursive helper method.
     *
     * Core Logic:
     * 1. Add current node to path.
     * 2. If node == target → return true.
     * 3. Recurse left and right.
     * 4. If target not found → remove node (backtrack).
     *
     * @return true if target found in subtree
     */
    private static boolean findPath(TreeNode root,
                                    int target,
                                    ArrayList<Integer> path) {

        if (root == null)
            return false;

        // Step 1: Add current node
        path.add(root.data);

        // Step 2: Check target
        if (root.data == target)
            return true;

        // Step 3: Search left or right
        if (findPath(root.left, target, path) ||
                findPath(root.right, target, path))
            return true;

        // Step 4: Backtrack if not found
        path.remove(path.size() - 1);

        return false;
    }

    // ==========================================================
    // 2️⃣ BFS VERSION (Queue + Parent Map)
    // ==========================================================

    /**
     * BFS approach using Queue.
     *
     * Instead of recursion, we:
     * - Traverse tree level by level.
     * - Store parent pointers.
     * - Reconstruct path once target is found.
     *
     * @param root   Root of tree
     * @param target Target value
     * @return Path from root to target
     */
    public static ArrayList<Integer> rootToNodeBFS(TreeNode root, int target) {

        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        queue.offer(root);
        parentMap.put(root, null);

        TreeNode targetNode = null;

        // Level-order traversal
        while (!queue.isEmpty()) {

            TreeNode current = queue.poll();

            if (current.data == target) {
                targetNode = current;
                break;
            }

            if (current.left != null) {
                queue.offer(current.left);
                parentMap.put(current.left, current);
            }

            if (current.right != null) {
                queue.offer(current.right);
                parentMap.put(current.right, current);
            }
        }

        if (targetNode == null)
            return result;

        // Reconstruct path from target → root
        while (targetNode != null) {
            result.add(targetNode.data);
            targetNode = parentMap.get(targetNode);
        }

        // Reverse to get root → target
        Collections.reverse(result);

        return result;
    }
}