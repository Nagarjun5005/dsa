package tree.medium;

import tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Constructs a Binary Tree from Preorder and Postorder traversals.
 *
 * Problem Statement:
 * Given two integer arrays representing the preorder and postorder traversal
 * of a binary tree, reconstruct and return the binary tree.
 *
 * Important:
 * A unique tree can be reconstructed only when the tree is a Full Binary Tree
 * (every node has either 0 or 2 children). Without this constraint,
 * multiple trees may satisfy the same traversals.
 *
 * Traversal Definitions:
 *
 * Preorder:
 * Root → Left → Right
 *
 * Postorder:
 * Left → Right → Root
 *
 * Core Logic:
 * 1. The first element in preorder is always the root.
 * 2. The next element in preorder represents the left subtree root.
 * 3. Locate this left subtree root inside postorder.
 * 4. The position of that node in postorder determines the size
 *    of the left subtree.
 * 5. Using subtree size, recursively construct left and right subtrees.
 *
 * Slicing Concept:
 *
 * Preorder structure:
 * [ Root | LeftSubtree | RightSubtree ]
 *
 * Postorder structure:
 * [ LeftSubtree | RightSubtree | Root ]
 *
 * If left subtree contains L nodes:
 *
 * Preorder:
 *   Left subtree  -> preStart + 1  to preStart + L
 *   Right subtree -> preStart + L + 1 to preEnd
 *
 * Postorder:
 *   Left subtree  -> postStart to indexOf(leftRoot)
 *   Right subtree -> indexOf(leftRoot) + 1 to postEnd - 1
 *
 * Time Complexity:
 * O(n)
 * Each node is processed exactly once.
 *
 * Space Complexity:
 * O(n)
 * Recursion stack + HashMap for index lookup.
 */
public class BinaryTreeFromPreorderAndPostorder {

    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] postorder = {4, 5, 2, 6, 7, 3, 1};

        TreeNode treeNode = buildTree(preorder, postorder);

        List<Integer> list = preorderTraversal(treeNode);
        System.out.println(list);
    }

    /**
     * Builds the binary tree using preorder and postorder arrays.
     *
     * @param preorder  preorder traversal array
     * @param postorder postorder traversal array
     * @return root of reconstructed binary tree
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
     * Recursive helper method to construct a subtree.
     *
     * @param preorder     preorder traversal array
     * @param preStart     start index in preorder window
     * @param preEnd       end index in preorder window
     * @param postorder    postorder traversal array
     * @param postStart    start index in postorder window
     * @param postEnd      end index in postorder window
     * @param postIndexMap map for quick lookup of value index in postorder
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

    /**
     * Utility method to verify constructed tree using preorder traversal.
     *
     * @param root root node of tree
     * @return list containing preorder traversal
     */
    public static List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private static void preorderHelper(TreeNode root, List<Integer> list) {

        if (root == null) {
            return;
        }

        list.add(root.data);
        preorderHelper(root.left, list);
        preorderHelper(root.right, list);
    }
}