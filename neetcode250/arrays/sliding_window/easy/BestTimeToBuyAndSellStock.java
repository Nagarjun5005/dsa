package arrays.sliding_window.easy;

/**
 * =============================================================
 * Problem: Best Time to Buy and Sell Stock (LeetCode 121)
 * =============================================================
 *
 * You are given an array prices where prices[i] represents the
 * stock price on the i-th day.
 *
 * You want to maximize your profit by choosing:
 *   - one day to buy the stock
 *   - one future day to sell the stock
 *
 * Return the maximum profit. If no profit is possible, return 0.
 *
 * -------------------------------------------------------------
 * Sliding Window Pattern Used:
 *
 * Pattern 6: Sum / Difference Constraint (Min-So-Far Window)
 *
 * This is a specialized form of sliding window where:
 *   - The left boundary (buy day) tracks the minimum value so far
 *   - The right boundary (sell day) moves forward one step at a time
 *
 * -------------------------------------------------------------
 * Key Insight:
 *
 * To maximize profit:
 *   - Always buy at the lowest price seen so far
 *   - Attempt to sell at the current price
 *
 * This removes the need for nested loops.
 *
 * -------------------------------------------------------------
 * Time & Space Complexity:
 *
 * Brute Force:
 *   - Time:  O(n^2)
 *   - Space: O(1)
 *
 * Optimized Sliding Window:
 *   - Time:  O(n)
 *   - Space: O(1)
 *
 * =============================================================
 */
public class BestTimeToBuyAndSellStock {

    /**
     * Driver method to test both implementations.
     */
    public static void main(String[] args) {
        int nums[] = {7, 1, 5, 3, 6, 4};

        int maxProfitBrute = maxProfitBrute(nums);
        System.out.println(maxProfitBrute);

        int maxProfitSlidingWindow = maxProfitSlidingWindow(nums);
        System.out.println(maxProfitSlidingWindow);
    }

    /**
     * -------------------------------------------------------------
     * Brute Force Approach
     * -------------------------------------------------------------
     *
     * Tries all possible pairs of buy day (i) and sell day (j),
     * ensuring i < j, and computes the profit for each pair.
     *
     * This approach is straightforward but inefficient for
     * large input sizes.
     *
     * @param prices array representing stock prices per day
     * @return maximum profit achievable
     */
    public static int maxProfitBrute(int[] prices) {
        int maxProfit = 0;

        // Edge case: no transaction possible
        if (prices == null || prices.length < 2) {
            return 0;
        }

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {

                if (prices[i] < prices[j]) {
                    int profit = prices[j] - prices[i];
                    maxProfit = Math.max(maxProfit, profit);
                }
            }
        }
        return maxProfit;
    }

    /**
     * -------------------------------------------------------------
     * Optimized Approach: Sliding Window (Min-So-Far)
     * -------------------------------------------------------------
     *
     * Maintains:
     *   - minPrice: the lowest price encountered so far (buy point)
     *   - maxProfit: the maximum profit achievable up to current day
     *
     * At each day:
     *   - Update minPrice
     *   - Calculate profit if sold on the current day
     *   - Update maxProfit
     *
     * Sliding Window Interpretation:
     *   - Left pointer: minimum price seen so far
     *   - Right pointer: current day
     *
     * @param prices array representing stock prices per day
     * @return maximum profit achievable
     */
    public static int maxProfitSlidingWindow(int[] prices) {

        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;

        // Edge case: no transaction possible
        if (prices == null || prices.length < 2) {
            return 0;
        }

        for (int price : prices) {

            // Update minimum buying price
            minPrice = Math.min(minPrice, price);

            // Calculate profit if sold today
            int profit = price - minPrice;

            // Update maximum profit
            maxProfit = Math.max(maxProfit, profit);
        }
        return maxProfit;
    }
}
