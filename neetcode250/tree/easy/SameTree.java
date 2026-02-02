package tree.easy;

import tree.TreeNode;
/**
 * Same Tree
 *
 * Problem:
 * --------
 * Given two binary trees, determine if they are the same.
 *
 * Two binary trees are considered the same if:
 *  - They have the same structure
 *  - All corresponding nodes have the same values
 *
 * Approach:
 * ---------
 * This solution uses Depth First Search (DFS) with recursion.
 *
 * At each recursive call, we compare:
 *  1. If both nodes are null → trees are identical at this branch
 *  2. If one node is null and the other is not → trees differ
 *  3. If node values differ → trees differ
 *  4. Recursively compare left and right subtrees
 *
 * The traversal order is preorder-style:
 *  Node → Left → Right
 *
 * Algorithm:
 * ----------
 * isSameTree(p, q):
 *  - If p == null AND q == null → return true
 *  - If p == null OR q == null → return false
 *  - If p.data != q.data → return false
 *  - Return isSameTree(p.left, q.left)
 *    AND isSameTree(p.right, q.right)
 *
 * Time Complexity:
 * ----------------
 * O(N), where N is the number of nodes in the tree.
 * Each node is visited once.
 *
 * Space Complexity:
 * -----------------
 * O(H), where H is the height of the tree.
 * This space is used by the recursion call stack.
 *
 * Example:
 * --------
 * Tree 1 and Tree 2:
 *
 *        1
 *      /   \
 *     2     3
 *    /       \
 *   4         5
 *
 * Output:
 * -------
 * true
 *
 * Pattern:
 * --------
 * DFS + Tree Structural Comparison
 */

public class SameTree {
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.right.right = new TreeNode(5);

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);


        boolean sameTree = isSameTree(root1, root2);
        System.out.println(sameTree);

    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {

        // Case 1: both are null
        if (p == null && q == null) {
            return true;
        }

        // Case 2: one is null, the other is not
        if (p == null || q == null) {
            return false;
        }

        // Case 3: values differ
        if (p.data != q.data) {
            return false;
        }

        // Case 4: recursively compare left and right subtrees
        return isSameTree(p.left, q.left) &&
                isSameTree(p.right, q.right);
    }

}
