package tree;

/**
 * Represents a node in a Binary Tree.
 *
 * <p>Each node contains:
 * <ul>
 *   <li>An integer data value</li>
 *   <li>A reference to the left child</li>
 *   <li>A reference to the right child</li>
 * </ul>
 *
 * <p>This class is commonly used in tree traversal algorithms
 * such as inorder, preorder, postorder, and level order traversals.
 */
public class TreeNode {

    /** Value stored in the node */
    public int data;

    /** Reference to the left child node */
    public TreeNode left;

    /** Reference to the right child node */
    public TreeNode right;

    /**
     * Creates a tree node with the given value.
     *
     * @param data the value to be stored in the node
     */
    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
