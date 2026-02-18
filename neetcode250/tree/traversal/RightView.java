package tree.traversal;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * RightView
 *
 * ==========================================================
 * PROBLEM STATEMENT
 * ==========================================================
 * Given the root of a binary tree, return the Right View
 * of the binary tree.
 *
 * The right view consists of nodes visible when the tree
 * is viewed from the right side.
 *
 * For each level of the tree, only the rightmost node
 * is visible.
 *
 *
 * ==========================================================
 * APPROACH 1: ITERATIVE (BFS - LEVEL ORDER TRAVERSAL)
 * ==========================================================
 *
 * IDEA:
 * - Perform level order traversal using a queue.
 * - For each level:
 *      → Add the LAST node (i == size - 1)
 *
 * WHY IT WORKS:
 * - Level order ensures nodes are processed level by level.
 * - The last node encountered in each level
 *   is the rightmost node.
 *
 *
 * TIME COMPLEXITY:
 * O(n) — every node is processed once
 *
 * SPACE COMPLEXITY:
 * O(n) — queue may hold nodes of a level
 *
 *
 * ==========================================================
 * APPROACH 2: RECURSIVE (DFS - RIGHT FIRST)
 * ==========================================================
 *
 * IDEA:
 * - Perform DFS traversal.
 * - Visit nodes in this order:
 *      Node → Right → Left
 *
 * - Maintain current level.
 * - If level == result.size():
 *      → This is the first node at this level
 *      → Add it to result.
 *
 * WHY IT WORKS:
 * - Since we visit right subtree first,
 *   the first node encountered at each level
 *   is the rightmost node.
 *
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * SPACE COMPLEXITY:
 * O(h) — recursion stack (h = height of tree)
 *
 *
 * ==========================================================
 * IMPORTANT INSIGHT
 * ==========================================================
 * If we reverse DFS order to:
 *      Node → Left → Right
 *
 * We get LEFT VIEW instead.
 */
public class RightView {

    public static void main(String[] args) {

        /*
         * Sample Tree:
         *
         *             1
         *            / \
         *           2   3
         *            \
         *             4
         *              \
         *               5
         *                \
         *                 6
         */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.right = new TreeNode(5);
        root.left.right.right.right = new TreeNode(6);

        System.out.println("Right View (Iterative): " + rightViewIterative(root));
        System.out.println("Right View (Recursive): " + rightViewRecursive(root));
    }

    /**
     * ======================================================
     * ITERATIVE APPROACH (BFS)
     * ======================================================
     *
     * Uses level order traversal.
     * Adds last node of each level to result.
     */
    private static ArrayList<Integer> rightViewIterative(TreeNode root) {

        ArrayList<Integer> result = new ArrayList<>();

        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                TreeNode current = queue.poll();

                // If last node in this level → rightmost
                if (i == size - 1) {
                    result.add(current.data);
                }

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }

        return result;
    }


    /**
     * ======================================================
     * RECURSIVE APPROACH (DFS - RIGHT FIRST)
     * ======================================================
     *
     * Calls helper with level tracking.
     */
    public static List<Integer> rightViewRecursive(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        rightViewHelper(root, 0, result);
        return result;
    }


    /**
     * Helper method for DFS traversal.
     *
     * @param node   current tree node
     * @param level  current depth level
     * @param result list storing right view
     */
    private static void rightViewHelper(TreeNode node, int level, List<Integer> result) {

        if (node == null) return;

        /*
         * If visiting this level for the first time,
         * then this node is the rightmost node.
         */
        if (level == result.size()) {
            result.add(node.data);
        }

        // Visit right subtree first
        rightViewHelper(node.right, level + 1, result);

        // Then visit left subtree
        rightViewHelper(node.left, level + 1, result);
    }
}


/*
Perfect 🔥 let’s do a deep dry run of the Recursive Right View (DFS) so it becomes crystal clear.

🌳 Example Tree

We’ll use this:

        1
       / \
      2   3
       \    \
        5    7


Expected Right View:

1 3 7


🧠 Key Logic
If level == result.size()
→ This is the FIRST node we are visiting at this level
→ Add it


And we go:

Right → Left


So rightmost node is visited first.

🔥 Dry Run Step-by-Step

Initial Call:

rightViewHelper(1, 0, [])

🔹 Step 1: Node = 1, Level = 0
result = []
level = 0


Check:

level == result.size()
0 == 0 → TRUE


Add 1.

result = [1]


Now go right:

rightViewHelper(3, 1, [1])

🔹 Step 2: Node = 3, Level = 1
result = [1]
level = 1


Check:

1 == 1 → TRUE


Add 3.

result = [1, 3]


Now go right:

rightViewHelper(7, 2, [1,3])

🔹 Step 3: Node = 7, Level = 2
result = [1,3]
level = 2


Check:

2 == 2 → TRUE


Add 7.

result = [1,3,7]


Now go right:

rightViewHelper(null, 3)


Returns.

Now go left:

rightViewHelper(null, 3)


Returns.

Back to node 3.

🔹 Step 4: Now process left of 3

Left of 3 is null.

Return.

Back to node 1.

🔹 Step 5: Now process left of 1
rightViewHelper(2, 1, [1,3,7])

Node = 2, Level = 1

Check:

1 == result.size()?
1 == 3 → FALSE


So DO NOT add.

Why?

Because we already visited level 1 from right side (node 3).

Now go right:

rightViewHelper(5, 2, [1,3,7])

Node = 5, Level = 2

Check:

2 == 3 → FALSE


Do not add.

Go right → null
Go left → null

Return.

Back to 2.

Process left of 2 → null.

Return.

✅ Final Result
[1, 3, 7]


Correct ✅
 */