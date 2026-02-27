package tree.medium;

import java.util.*;

/**
 * Utility class to find all nodes at distance K from a given target node
 * in a binary tree.
 *
 * Approach:
 * 1. Build a parent map to treat the tree like an undirected graph.
 * 2. Perform BFS starting from target node.
 * 3. When distance reaches K, return all nodes at that level.
 *
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class AllNodesDistanceKFromTarget {

    public static void main(String[] args) {

        /*
                    3
                   / \
                  5   1
                 / \ / \
                6  2 0  8
                  / \
                 7   4
        */

        Node root = new Node(3);
        root.left = new Node(5);
        root.right = new Node(1);

        root.left.left = new Node(6);
        root.left.right = new Node(2);

        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);

        root.right.left = new Node(0);
        root.right.right = new Node(8);

        List<Integer> list = distanceK(root, root.left, 2);
        System.out.println(list); // Expected Output: [7,4,1]
    }

    /**
     * Returns list of node values that are K distance away from target.
     *
     * @param root   Root of binary tree
     * @param target Target node
     * @param k      Required distance
     * @return List of node values at distance K
     */
    public static List<Integer> distanceK(Node root, Node target, int k) {

        // Step 1: Build parent map
        Map<Node, Node> parentMap = buildParentMap(root);

        // Step 2: BFS from target
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        queue.offer(target);
        visited.add(target);

        int distance = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // If we reached required distance
            if (distance == k) {
                List<Integer> result = new ArrayList<>();
                for (Node node : queue) {
                    result.add(node.data);
                }
                return result;
            }

            for (int i = 0; i < size; i++) {

                Node current = queue.poll();

                // Explore left child
                if (current.left != null && !visited.contains(current.left)) {
                    visited.add(current.left);
                    queue.offer(current.left);
                }

                // Explore right child
                if (current.right != null && !visited.contains(current.right)) {
                    visited.add(current.right);
                    queue.offer(current.right);
                }

                // Explore parent
                Node parent = parentMap.get(current);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    queue.offer(parent);
                }
            }

            distance++;
        }

        return new ArrayList<>();
    }

    /**
     * Builds a map storing parent of each node.
     *
     * @param root Root of tree
     * @return Map of child -> parent
     */
    private static Map<Node, Node> buildParentMap(Node root) {

        Map<Node, Node> parentMap = new HashMap<>();

        if (root == null) return parentMap;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        parentMap.put(root, null);  // Root has no parent

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            if (current.left != null) {
                parentMap.put(current.left, current);
                queue.offer(current.left);
            }

            if (current.right != null) {
                parentMap.put(current.right, current);
                queue.offer(current.right);
            }
        }

        return parentMap;
    }
}

/**
 * Basic binary tree node.
 */
class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
    }
}