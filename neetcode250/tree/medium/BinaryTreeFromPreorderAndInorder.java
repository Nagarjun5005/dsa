package tree.medium;

import tree.TreeNode;
import java.util.*;

/*
 * ================================================================
 *  Problem: Construct Binary Tree from Preorder and Inorder Traversal
 * ================================================================
 *
 *  Problem Statement:
 *  ------------------
 *  Given two arrays:
 *      1. preorder[]  -> Preorder traversal of a binary tree
 *      2. inorder[]   -> Inorder traversal of the same tree
 *
 *  Construct and return the original binary tree.
 *
 *  Constraints:
 *  - All node values are unique.
 *  - preorder.length == inorder.length
 *  - Tree must be reconstructed exactly.
 *
 *
 *  Example:
 *  --------
 *  preorder = [3, 9, 20, 15, 7]
 *  inorder  = [9, 3, 15, 20, 7]
 *
 *  Constructed Tree:
 *
 *          3
 *         / \
 *        9   20
 *           /  \
 *          15   7
 *
 *
 *  Key Observations:
 *  -----------------
 *  1. Preorder traversal order:
 *         Root -> Left -> Right
 *
 *     -> First element of preorder window is always the root.
 *
 *  2. Inorder traversal order:
 *         Left -> Root -> Right
 *
 *     -> Elements left of root form left subtree.
 *     -> Elements right of root form right subtree.
 *
 *
 *  Core Idea:
 *  ----------
 *  - Use preorder to pick root.
 *  - Use inorder to determine subtree sizes.
 *  - Recursively build left and right subtrees.
 *
 *
 *  Slicing Logic (Most Important Part):
 *  -------------------------------------
 *
 *  Suppose:
 *      preorder window = [preStart ... preEnd]
 *      inorder window  = [inStart  ... inEnd]
 *
 *  Step 1:
 *      root = preorder[preStart]
 *
 *  Step 2:
 *      Find root index in inorder -> inRoot
 *
 *  Step 3:
 *      Number of nodes in left subtree:
 *
 *          numsLeft = inRoot - inStart
 *
 *      (Because inorder left of root belongs to left subtree)
 *
 *  Step 4:
 *      Preorder structure is:
 *
 *          [ Root | Entire Left Subtree | Entire Right Subtree ]
 *
 *      So:
 *
 *      Left subtree preorder range:
 *          start = preStart + 1
 *          end   = preStart + numsLeft
 *
 *      Right subtree preorder range:
 *          start = preStart + numsLeft + 1
 *          end   = preEnd
 *
 *
 *  Time Complexity:
 *  ----------------
 *      O(n)
 *      - Each node processed once.
 *      - HashMap provides O(1) lookup for inorder index.
 *
 *
 *  Space Complexity:
 *  -----------------
 *      O(n)
 *      - HashMap storage.
 *      - Recursion stack (worst case skewed tree).
 *
 * ================================================================
 */

public class BinaryTreeFromPreorderAndInorder {

    public static void main(String[] args) {

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = buildTree(preorder, inorder);

        // Verify tree construction
        System.out.println(inorderTraversal(root));
    }

    /*
     * Public method to initiate tree construction.
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        // Map to store value -> index in inorder for quick lookup
        Map<Integer, Integer> inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return constructTree(
                preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1,
                inorderMap
        );
    }

    /*
     * Recursive method to construct tree.
     *
     * preStart, preEnd define current subtree window in preorder
     * inStart, inEnd define current subtree window in inorder
     */
    private static TreeNode constructTree(
            int[] preorder, int preStart, int preEnd,
            int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inorderMap) {

        /*
         * Base Condition:
         * If window becomes invalid, no subtree exists.
         */
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        /*
         * Step 1:
         * Root is always first element of preorder window.
         */
        TreeNode root = new TreeNode(preorder[preStart]);

        /*
         * Step 2:
         * Find root position in inorder.
         */
        int inRoot = inorderMap.get(root.data);

        /*
         * Step 3:
         * Compute number of nodes in left subtree.
         */
        int numsLeft = inRoot - inStart;

        /*
         * Step 4:
         * Recursively build left subtree.
         *
         * Preorder:
         *   Skip root (preStart)
         *   Take next numsLeft elements
         *
         * Inorder:
         *   Elements left of root
         */
        root.left = constructTree(
                preorder,
                preStart + 1,
                preStart + numsLeft,
                inorder,
                inStart,
                inRoot - 1,
                inorderMap
        );

        /*
         * Step 5:
         * Recursively build right subtree.
         *
         * Preorder:
         *   Skip root + left subtree
         *
         * Inorder:
         *   Elements right of root
         */
        root.right = constructTree(
                preorder,
                preStart + numsLeft + 1,
                preEnd,
                inorder,
                inRoot + 1,
                inEnd,
                inorderMap
        );

        return root;
    }

    /*
     * Utility method to verify constructed tree using inorder traversal.
     */
    public static List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private static void inorderHelper(TreeNode root, List<Integer> list) {

        if (root == null) {
            return;
        }

        inorderHelper(root.left, list);
        list.add(root.data);
        inorderHelper(root.right, list);
    }
}