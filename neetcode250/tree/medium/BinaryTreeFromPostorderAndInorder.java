package tree.medium;

import tree.TreeNode;
import java.util.*;

/**
 * Constructs a Binary Tree from its Postorder and Inorder traversal arrays.
Problem Statement:
 * Given two integer arrays:
 *
 *     postorder[] → Postorder traversal of a binary tree<
 *     inorder[] → Inorder traversal of the same tree<
 *
 * Construct and return the original binary tree.
 *
 * Traversal Properties:
 *
 *------ Inorder:
 *
 *     Left → Root → Right
 *
 *------Postorder:
 *
 *     Left → Right → Root
 *
 *
 * ------------Core Idea:
 *
 *    The last element in postorder is always the root.
 *    In inorder, elements to the left of root belong to left subtree.
 *    Elements to the right belong to right subtree.
 *    Using subtree sizes, recursively construct left and right subtrees.
 *
 *
 * ----Slicing Logic Explanation:
 *
 * Suppose current subtree ranges are:
 *
 * postorder → [postStart ... postEnd]
 * inorder   → [inStart  ... inEnd]
 *
 *
 * Step 1:
 *
 * root = postorder[postEnd]
 *
 *
 * Step 2:
 *
 * inRoot = index of root in inorder
 *
 *
 * Step 3:
 *
 * numsLeft = inRoot - inStart
 *
 *
 * Postorder structure is:
 *
 * [ LeftSubtree | RightSubtree | Root ]
 *
 *
 * Therefore:
 *
 * Left subtree in postorder:
 *
 * postStart → postStart + numsLeft - 1
 *
 *
 * Right subtree in postorder:

 * postStart + numsLeft → postEnd - 1
 *
 *
 * Time Complexity:O(n)
 *
 *    Each node is processed exactly once.
 *    HashMap enables O(1) lookup for inorder index.
 *
 *
 *Space Complexity: O(n)
 *
 *     O(n) for HashMap storage.
 *     O(n) recursion stack in worst-case (skewed tree).
 * </ul>
 */
public class BinaryTreeFromPostorderAndInorder {

    public static void main(String[] args) {

        int[] postorder = {9, 15, 7, 20, 3};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = buildTree(postorder, inorder);

        // Verify tree construction
        System.out.println(inorderTraversal(root));
    }

    /**
     * Builds the binary tree from postorder and inorder traversal arrays.
     *
     * @param postorder postorder traversal array
     * @param inorder   inorder traversal array
     * @return root node of reconstructed binary tree
     */
    public static TreeNode buildTree(int[] postorder, int[] inorder) {

        Map<Integer, Integer> inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return constructTree(
                postorder, 0, postorder.length - 1,
                inorder, 0, inorder.length - 1,
                inorderMap
        );
    }

    /**
     * Recursive helper method to construct subtree.
     *
     * @param postorder postorder traversal array
     * @param postStart start index of current subtree in postorder
     * @param postEnd   end index of current subtree in postorder
     * @param inorder   inorder traversal array
     * @param inStart   start index of current subtree in inorder
     * @param inEnd     end index of current subtree in inorder
     * @param inorderMap map storing value → index mapping for inorder
     * @return root node of constructed subtree
     */
    private static TreeNode constructTree(
            int[] postorder, int postStart, int postEnd,
            int[] inorder, int inStart, int inEnd,
            Map<Integer, Integer> inorderMap) {

        // Base case: no elements left to construct
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }

        // Step 1: Root is last element of postorder window
        TreeNode root = new TreeNode(postorder[postEnd]);

        // Step 2: Locate root in inorder
        int inRoot = inorderMap.get(root.data);

        // Step 3: Calculate number of nodes in left subtree
        int numsLeft = inRoot - inStart;

        // Step 4: Build left subtree
        root.left = constructTree(
                postorder,
                postStart,
                postStart + numsLeft - 1,
                inorder,
                inStart,
                inRoot - 1,
                inorderMap
        );

        // Step 5: Build right subtree
        root.right = constructTree(
                postorder,
                postStart + numsLeft,
                postEnd - 1,
                inorder,
                inRoot + 1,
                inEnd,
                inorderMap
        );

        return root;
    }

    /**
     * Utility method to verify tree construction using inorder traversal.
     *
     * @param root root node of tree
     * @return inorder traversal as list
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