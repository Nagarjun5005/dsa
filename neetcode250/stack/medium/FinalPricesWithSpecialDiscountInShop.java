package stack.medium;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem: Final Prices With a Special Discount in a Shop
 *
 * Given an array of prices, for each item i:
 * - Find the first item j > i such that prices[j] <= prices[i]
 * - That value becomes the discount
 * - Final price = prices[i] - discount
 *
 * If no such item exists to the right, no discount is applied.
 *
 * This class demonstrates:
 * 1. Brute force approach (O(n²))
 * 2. Optimal stack-based approach using Monotonic Stack (O(n))
 *
 * Core Pattern:
 * - Next Smaller or Equal Element (to the right)
 */
public class FinalPricesWithSpecialDiscountInShop {

    public static void main(String[] args) {

        int[] prices = {8, 4, 6, 2, 3};

        int[] brute = finalPrices(prices);
        System.out.println("Final prices (Brute): " + Arrays.toString(brute));

        int[] stack = finalPricesStack(prices);
        System.out.println("Final prices (Stack): " + Arrays.toString(stack));
    }

    /**
     * Brute Force Approach
     *
     * For each item, scan elements to its right and find the
     * first price that is strictly smaller.
     *
     * Once found:
     * - Apply it as a discount
     * - Stop scanning further
     *
     * Time Complexity: O(n²)
     * Space Complexity: O(n) for result array
     *
     * @param prices input array of prices
     * @return array of final prices after discount
     */
    public static int[] finalPrices(int[] prices) {

        int n = prices.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {

            int discount = 0; // default: no discount

            // Scan to the right of current item
            for (int j = i + 1; j < n; j++) {
                if (prices[j] < prices[i]) {
                    discount = prices[j];
                    break; // first valid discount found
                }
            }

            result[i] = prices[i] - discount;
        }

        return result;
    }

    /**
     * Stack-Based (Optimal) Approach
     *
     * Uses a Monotonic Increasing Stack.
     *
     * Stack stores indices of items that are waiting for a discount.
     *
     * Logic:
     * - Traverse prices from left to right
     * - While current price is less than or equal to the price
     *   at the index on top of the stack:
     *      - Pop that index
     *      - Apply current price as discount
     * - Push current index onto stack
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param prices input array of prices
     * @return array of final prices after discount
     */
    public static int[] finalPricesStack(int[] prices) {

        int n = prices.length;

        // Clone prices so we can modify final values directly
        int[] result = prices.clone();

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            /*
             * If current price is smaller or equal,
             * it becomes the discount for all items
             * on the stack that are more expensive.
             */
            while (!stack.isEmpty() && prices[i] <= prices[stack.peek()]) {
                int index = stack.pop();
                result[index] -= prices[i];
            }

            /*
             * Current index waits for its discount
             */
            stack.push(i);
        }

        return result;
    }
}
