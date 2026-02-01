package tree.easy;

import tree.TreeNode;

/**
 * Provides both Brute-Force and Optimized approaches
 * to calculate the Diameter of a Binary Tree.
 *
 * <p><b>Important:</b>
 * <ul>
 *   <li>Brute-force version calculates diameter in terms of NODES.</li>
 *   <li>Optimized version (LeetCode-style) calculates diameter in terms of EDGES.</li>
 * </ul>
 */
public class DiameterOfBinaryTree {

    /* =========================================================
       =============== BRUTE FORCE APPROACH ====================
       ========================================================= */

    /**
     * Brute-force approach to calculate diameter.
     *
     * <p><b>Diameter Definition:</b>
     * Number of nodes on the longest path between any two nodes.
     *
     * <p><b>Time Complexity:</b> O(N²)
     * <br><b>Space Complexity:</b> O(H)
     */
    public static int diameterBruteForce(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        int diameterThroughRoot = leftHeight + rightHeight + 1;

        int leftDiameter = diameterBruteForce(root.left);
        int rightDiameter = diameterBruteForce(root.right);

        return Math.max(
                diameterThroughRoot,
                Math.max(leftDiameter, rightDiameter)
        );
    }

    /**
     * Helper method to calculate height of a subtree.
     */
    private static int height(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return 1 + Math.max(
                height(root.left),
                height(root.right)
        );
    }

    /* =========================================================
       =============== OPTIMIZED APPROACH ======================
       ========================================================= */

    /** Stores the maximum diameter (edges-based) */
    private int diameterOptimized = 0;

    /**
     * Optimized approach to calculate diameter.
     *
     * <p><b>Diameter Definition (LeetCode):</b>
     * Number of edges on the longest path.
     *
     * <p><b>Time Complexity:</b> O(N)
     * <br><b>Space Complexity:</b> O(H)
     */
    public int diameterOptimized(TreeNode root) {

        diameterOptimized = 0; // reset for reuse
        heightOptimized(root);
        return diameterOptimized;
    }

    /**
     * Computes height and updates diameter simultaneously.
     */
    private int heightOptimized(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int left = heightOptimized(node.left);
        int right = heightOptimized(node.right);

        // Update diameter (edges count)
        diameterOptimized = Math.max(diameterOptimized, left + right);

        return 1 + Math.max(left, right);
    }

    /* =========================================================
       ======================= TEST =============================
       ========================================================= */

    public static void main(String[] args) {

        // Constructing sample tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Brute force (nodes)
        int bruteDiameter = diameterBruteForce(root);
        System.out.println("Brute Force Diameter (nodes): " + bruteDiameter);

        // Optimized (edges)
        DiameterOfBinaryTree obj = new DiameterOfBinaryTree();
        int optimizedDiameter = obj.diameterOptimized(root);
        System.out.println("Optimized Diameter (edges): " + optimizedDiameter);
    }
}
