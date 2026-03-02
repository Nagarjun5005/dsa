package tree.medium;


import tree.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * Construct Binary Tree from Preorder and Postorder Traversals.
 *
 * Problem Statement:
 * Given two integer arrays representing the preorder and postorder
 * traversal of a binary tree, construct and return the original tree.
 *
 * Important Assumption:
 * A unique binary tree can be constructed only if the tree is a
 * Full Binary Tree (every node has either 0 or 2 children).
 *
 * Traversal Definitions:
 *
 * Preorder:
 * Root → Left → Right
 *
 * Postorder:
 * Left → Right → Root
 *
 * Algorithm Overview:
 * 1. The first element in preorder is always the root.
 * 2. The second element in preorder is the root of the left subtree.
 * 3. Locate this left subtree root in postorder.
 * 4. The index in postorder determines the size of the left subtree.
 * 5. Recursively construct left and right subtrees.
 *
 * Slicing Logic:
 *
 * Preorder Structure:
 * [ Root | LeftSubtree | RightSubtree ]
 *
 * Postorder Structure:
 * [ LeftSubtree | RightSubtree | Root ]
 *
 * If left subtree size is L:
 *
 * Left subtree in preorder:
 * (preStart + 1) to (preStart + L)
 *
 * Right subtree in preorder:
 * (preStart + L + 1) to preEnd
 *
 * Left subtree in postorder:
 * postStart to indexOf(leftRoot)
 *
 * Right subtree in postorder:
 * indexOf(leftRoot) + 1 to postEnd - 1
 *
 * Time Complexity:
 * O(n) — Each node is processed exactly once.
 *
 * Space Complexity:
 * O(n) — Recursion stack + HashMap storage.
 */
public class BinaryTreeFromPreorderAndPostorder {

    /**
     * Builds the binary tree from preorder and postorder arrays.
     *
     * @param preorder  preorder traversal array
     * @param postorder postorder traversal array
     * @return root node of reconstructed tree
     */
    public static TreeNode buildTree(int[] preorder, int[] postorder) {

        Map<Integer, Integer> postIndexMap = new HashMap<>();

        for (int i = 0; i < postorder.length; i++) {
            postIndexMap.put(postorder[i], i);
        }

        return construct(
                preorder, 0, preorder.length - 1,
                postorder, 0, postorder.length - 1,
                postIndexMap
        );
    }

    /**
     * Recursive helper method to construct subtree.
     *
     * @param preorder     preorder traversal array
     * @param preStart     start index in preorder
     * @param preEnd       end index in preorder
     * @param postorder    postorder traversal array
     * @param postStart    start index in postorder
     * @param postEnd      end index in postorder
     * @param postIndexMap value-to-index map for postorder
     * @return root node of constructed subtree
     */
    private static TreeNode construct(
            int[] preorder, int preStart, int preEnd,
            int[] postorder, int postStart, int postEnd,
            Map<Integer, Integer> postIndexMap) {

        if (preStart > preEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);

        if (preStart == preEnd) {
            return root;
        }

        int leftRootVal = preorder[preStart + 1];

        int index = postIndexMap.get(leftRootVal);
        int leftSize = index - postStart + 1;

        root.left = construct(
                preorder,
                preStart + 1,
                preStart + leftSize,
                postorder,
                postStart,
                index,
                postIndexMap
        );

        root.right = construct(
                preorder,
                preStart + leftSize + 1,
                preEnd,
                postorder,
                index + 1,
                postEnd - 1,
                postIndexMap
        );

        return root;
    }
}