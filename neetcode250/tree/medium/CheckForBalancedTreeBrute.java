package tree.medium;

import tree.TreeNode;

/**
 * Checks whether a Binary Tree is height-balanced using a brute-force approach.
 *
 * <p><b>Definition:</b>
 * A binary tree is considered balanced if for every node:
 * <ul>
 *   <li>The height difference between its left and right subtrees is at most 1</li>
 *   <li>Both left and right subtrees are themselves balanced</li>
 * </ul>
 *
 * <p><b>Approach (Brute Force):</b>
 * <ol>
 *   <li>For each node, compute the height of the left subtree.</li>
 *   <li>Compute the height of the right subtree.</li>
 *   <li>Check if the height difference is within the allowed range.</li>
 *   <li>Recursively verify balance for left and right subtrees.</li>
 * </ol>
 *
 * <p><b>Important Execution Rule (Rule Zero):</b><br>
 * Java executes function calls depth-first and line by line.
 * A function call must completely return before the next line executes.
 *
 * <p><b>Time Complexity:</b> O(N²) in the worst case (skewed tree),
 * due to repeated height calculations.<br>
 * <b>Space Complexity:</b> O(H), where H is the height of the tree
 * (recursion stack).
 */
public class CheckForBalancedTreeBrute {

    public static void main(String[] args) {

        // Constructing the binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        boolean balanced = isBalanced(root);
        System.out.println(balanced);
    }

    /**
     * Determines whether a binary tree is balanced.
     *
     * @param root the root of the binary tree
     * @return true if the tree is balanced, false otherwise
     */
    public static boolean isBalanced(TreeNode root) {

        // Base case: an empty tree is balanced
        if (root == null) {
            return true;
        }

        // Compute heights of left and right subtrees
        int lh = height(root.left);
        int rh = height(root.right);

        /*
         * A node is balanced if:
         * 1. The height difference is at most 1
         * 2. The left subtree is balanced
         * 3. The right subtree is balanced
         */
        return (Math.abs(lh - rh) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right));
    }

    /**
     * Computes the height of a binary tree.
     *
     * <p>Height is defined as the number of nodes
     * on the longest path from the current node to a leaf.
     *
     * @param root the root of the subtree
     * @return the height of the subtree
     */
    public static int height(TreeNode root) {

        // Base case: empty subtree has height 0
        if (root == null) {
            return 0;
        }

        // Recursively compute heights
        int lh = height(root.left);
        int rh = height(root.right);

        // Height of current node
        return 1 + Math.max(lh, rh);
    }
}



/*
RULE ZERO (THIS MAKES EVERYTHING CLEAR)

Java always executes function calls depth-first, line by line.
A function must completely return before the next line executes.

🔵 STEP-BY-STEP CALL FLOW (NO SKIPS)
🟢 STEP 1: main()
isBalanced(1)

🟢 STEP 2: isBalanced(1)
Line 1:
int lh = height(1.left);  // height(2)


⏸️ isBalanced(1) is paused
▶️ Control moves to height(2)

🟢 STEP 3: height(2)
Line 1:
int lh = height(2.left); // height(4)


⏸️ height(2) paused
▶️ Control moves to height(4)

🟢 STEP 4: height(4)
height(4.left)  → height(null) → 0
height(4.right) → height(null) → 0
return 1


✅ height(4) RETURNS 1

🔙 BACK TO height(2)

Now:

int rh = height(2.right); // height(5)


▶️ Call height(5)

🟢 STEP 5: height(5)
height(5.left)  → 0
height(5.right) → 0
return 1


✅ height(5) RETURNS 1

🔙 BACK TO height(2)

Now:

return 1 + max(1, 1) = 2


✅ height(2) RETURNS 2

🔙 BACK TO isBalanced(1)

Now next line executes:

int rh = height(1.right); // height(3)


▶️ Call height(3)

🟢 STEP 6: height(3)
height(null) → 0
height(null) → 0
return 1


✅ height(3) RETURNS 1

🔙 BACK TO isBalanced(1)

Now evaluate:

Math.abs(2 - 1) <= 1  // TRUE


But we are NOT DONE YET ❗
Because Java must evaluate ALL conditions in:

&& isBalanced(root.left)
&& isBalanced(root.right)

🟢 STEP 7: isBalanced(2)

▶️ Call isBalanced(2)

Inside isBalanced(2):
lh = height(4)
rh = height(5)


⚠️ YES — heights are recalculated AGAIN (brute force!)

Calls again:

height(4) → returns 1

height(5) → returns 1

|1 - 1| <= 1 → true


Now:

isBalanced(4)
isBalanced(5)

🟢 STEP 8: isBalanced(4)
lh = height(null) → 0
rh = height(null) → 0
return true

🟢 STEP 9: isBalanced(5)
lh = height(null) → 0
rh = height(null) → 0
return true

🔙 BACK TO isBalanced(2)
return true

🟢 STEP 10: isBalanced(3)

▶️ Call isBalanced(3)

lh = height(null) → 0
rh = height(null) → 0
return true

🔙 FINAL RETURN TO isBalanced(1)

Now Java has:

true && true && true


✅ isBalanced(1) RETURNS true

🟢 STEP 11: main()
System.out.println(true);

🧠 CALL STACK VISUAL (THIS IS THE CORE)
Calls go DOWN ⬇️
isBalanced(1)
  ├── height(2)
  │     ├── height(4)
  │     └── height(5)
  ├── height(3)
  ├── isBalanced(2)
  │     ├── height(4)
  │     ├── height(5)
  │     ├── isBalanced(4)
  │     └── isBalanced(5)
  └── isBalanced(3)

Returns come UP ⬆️
 */