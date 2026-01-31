package tree.medium;

import tree.TreeNode;

/**
 * Checks whether a Binary Tree is height-balanced using an optimized approach.
 *
 * <p><b>Definition:</b>
 * A binary tree is balanced if for every node, the height difference
 * between its left and right subtrees is at most 1.
 *
 * <p><b>Optimized Approach (Bottom-Up DFS):</b>
 * <ul>
 *   <li>The function returns the height of the subtree if it is balanced.</li>
 *   <li>If any subtree is found to be unbalanced, the function returns -1.</li>
 *   <li>The value -1 is propagated upwards immediately, avoiding further work.</li>
 * </ul>
 *
 * <p><b>Key Idea:</b>
 * Height calculation and balance checking are combined into a single recursion,
 * eliminating repeated height calculations.
 *
 * <p><b>Time Complexity:</b> O(N), where N is the number of nodes
 * <br><b>Space Complexity:</b> O(H), where H is the height of the tree
 */
public class CheckForBalancedTreeOptimized {

    public static void main(String[] args) {

        // Constructing the binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int balanced = isBalanced(root);

        if (balanced == -1) {
            System.out.println("It is not balanced");
        } else {
            System.out.println("It is balanced");
        }
    }

    /**
     * Returns the height of the tree if it is balanced.
     *
     * @param root the root node of the binary tree
     * @return height of the subtree if balanced, otherwise -1
     */
    public static int isBalanced(TreeNode root) {

        // Base case: empty tree has height 0 and is balanced
        if (root == null) {
            return 0;
        }

        // Check left subtree
        int lh = isBalanced(root.left);
        if (lh == -1) {
            return -1; // Left subtree is unbalanced
        }

        // Check right subtree
        int rh = isBalanced(root.right);
        if (rh == -1) {
            return -1; // Right subtree is unbalanced
        }

        // Check current node balance
        if (Math.abs(lh - rh) > 1) {
            return -1; // Current node is unbalanced
        }

        // Return height if balanced
        return Math.max(lh, rh) + 1;
    }
}


/*
🟢 STEP 1: main()
isBalanced(1)

🟢 STEP 2: isBalanced(1)
lh = isBalanced(2)


⏸️ isBalanced(1) pauses
▶️ Control goes to isBalanced(2)

🟢 STEP 3: isBalanced(2)
lh = isBalanced(4)


▶️ Call isBalanced(4)

🟢 STEP 4: isBalanced(4)
lh = isBalanced(null) → 0
rh = isBalanced(null) → 0
|0 - 0| ≤ 1 → OK
return 1


✅ isBalanced(4) RETURNS 1

🔙 BACK TO isBalanced(2)
rh = isBalanced(5)


▶️ Call isBalanced(5)

🟢 STEP 5: isBalanced(5)
lh = 0
rh = 0
return 1


✅ isBalanced(5) RETURNS 1

🔙 BACK TO isBalanced(2)
|1 - 1| ≤ 1 → OK
return max(1, 1) + 1 = 2


✅ isBalanced(2) RETURNS 2

🔙 BACK TO isBalanced(1)
rh = isBalanced(3)


▶️ Call isBalanced(3)

🟢 STEP 6: isBalanced(3)
lh = 0
rh = 0
return 1


✅ isBalanced(3) RETURNS 1

🔙 FINAL STEP: isBalanced(1)
|2 - 1| ≤ 1 → OK
return max(2, 1) + 1 = 3


✅ Final return value = 3

🟢 STEP 7: main()
balanced != -1
→ "It is balanced"

🧠 3️⃣ WHY THIS WORKS (THE BIG DIFFERENCE)
🔴 Brute Force

Height computed again and again

Same nodes revisited

Time = O(N²)

🟢 Optimized

Height computed once per node

Early exit using -1

Time = O(N)

🧠 Key sentence (memorize this)

“This solution checks balance bottom-up and propagates failure early, avoiding repeated height calculations.”
 */