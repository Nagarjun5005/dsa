package tree.medium;

import tree.TreeNode;
import java.util.*;

/**
 * LowestCommonAncestor
 *
 * ------------------------------------------------------------
 * Problem:
 * Given a Binary Tree and two nodes p and q,
 * find their Lowest Common Ancestor (LCA).
 *
 * The Lowest Common Ancestor is defined as the lowest node
 * in the tree that has both p and q as descendants
 * (a node can be a descendant of itself).
 *
 * ------------------------------------------------------------
 * Example:
 *
 *              1
 *            /   \
 *           2     3
 *         /  \   /  \
 *        4    5 6    7
 *
 * LCA(4, 7) = 1
 * LCA(4, 5) = 2
 *
 * ------------------------------------------------------------
 * This class implements:
 *
 * 1️⃣ Recursive Solution (Postorder DFS)
 * 2️⃣ Iterative Solution (BFS + Parent Map)
 *
 * ------------------------------------------------------------
 * Time Complexity: O(N)
 * Space Complexity:
 *    Recursive → O(H)  (recursion stack)
 *    Iterative → O(N)  (parent map + queue)
 */
public class LowestCommonAncestor {

    public static void main(String[] args) {

        // Construct sample binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Recursive LCA
        TreeNode recursiveResult =
                lowestCommonAncestorRecursive(root, root.left.left, root.right.right);

        System.out.println("Recursive LCA: " + recursiveResult.data);

        // Iterative LCA
        TreeNode iterativeResult =
                lcaIterative(root, root.left.left, root.right.right);

        System.out.println("Iterative LCA: " + iterativeResult.data);
    }

    // ==========================================================
    // 1️⃣ RECURSIVE SOLUTION (Postorder DFS)
    // ==========================================================

    /**
     * Recursive approach using Postorder Traversal.
     *
     * Core Logic:
     * 1. If root is null → return null.
     * 2. If root == p OR root == q → return root.
     * 3. Recursively search left and right subtree.
     * 4. If both left and right are non-null → root is LCA.
     * 5. If only one side is non-null → propagate that upward.
     */
    public static TreeNode lowestCommonAncestorRecursive(
            TreeNode root, TreeNode p, TreeNode q) {

        // Base Case
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestorRecursive(root.left, p, q);
        TreeNode right = lowestCommonAncestorRecursive(root.right, p, q);

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;  // Both sides returned non-null → LCA found
        }
    }

    // ==========================================================
    // 2️⃣ ITERATIVE SOLUTION (BFS + Parent Map)
    // ==========================================================

    /**
     * Iterative approach using:
     * - BFS traversal
     * - Parent Map to track child → parent relationships
     *
     * Steps:
     * 1. Perform BFS to build parent map.
     * 2. Store all ancestors of node p in a Set.
     * 3. Move upward from q until we find a node in p’s ancestor set.
     *
     * That node is the LCA.
     */
    public static TreeNode lcaIterative(TreeNode root,
                                        TreeNode p,
                                        TreeNode q) {

        if (root == null)
            return null;

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        parentMap.put(root, null);

        // Build parent map until both nodes are found
        while (!parentMap.containsKey(p) || !parentMap.containsKey(q)) {

            TreeNode current = queue.poll();

            if (current.left != null) {
                parentMap.put(current.left, current);
                queue.offer(current.left);
            }

            if (current.right != null) {
                parentMap.put(current.right, current);
                queue.offer(current.right);
            }
        }

        // Store all ancestors of p
        Set<TreeNode> ancestors = new HashSet<>();

        while (p != null) {
            ancestors.add(p);
            p = parentMap.get(p);
        }

        // Traverse ancestors of q
        while (!ancestors.contains(q)) {
            q = parentMap.get(q);
        }

        return q;
    }
}