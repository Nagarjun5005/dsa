package neetcode250.arrays.easy;

/**
 * Problem: Best Time to Buy and Sell Stock
 * ----------------------------------------
 * Given an array 'prices' where prices[i] is the price of a given stock on day 'i',
 * find the maximum profit you can achieve from a single buy and a single sell.
 * You must buy before you sell. If no profit is possible, return 0.
 *
 * Approaches:
 * 1. Brute Force - O(N^2)
 * 2. Sliding Window (Optimal) - O(N)
 *
 * NeetCode 250 - Arrays - Easy
 */
public class BestTimeBuySellStock {

    /**
     * Brute Force Approach: O(N^2) Time Complexity
     * --------------------------------------------
     * This method tries every possible pair of buy and sell days
     * and calculates the profit for each pair.
     * It keeps track of the maximum profit found.
     *
     * @param prices Array of stock prices on each day.
     * @return Maximum profit achievable by buying and selling once.
     */
    public static int buySellStock(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }

    /**
     * Optimal Sliding Window Approach: O(N) Time Complexity
     * -----------------------------------------------------
     * This method efficiently finds the maximum profit using two pointers (buy and sell).
     * The 'l' pointer tracks the minimum buying price found so far.
     * The 'r' pointer scans each day to calculate potential profit.
     *
     * If the current sell price is lower than the buy price, move the buy pointer forward.
     * Otherwise, calculate the profit and update the maximum profit if it is greater.
     *
     * This approach solves the problem in a single pass with constant space.
     *
     * @param prices Array of stock prices on each day.
     * @return Maximum profit achievable by buying and selling once.
     */
    public static int buySellStockBetter(int[] prices) {
        int l = 0; // Buy day
        int r = 1; // Sell day
        int maxProfit = 0;

        while (r < prices.length) {
            if (prices[r] > prices[l]) {
                int profit = prices[r] - prices[l];
                maxProfit = Math.max(profit, maxProfit);
            } else {
                l = r; // Move buy day to the current day
            }
            r++;
        }

        return maxProfit;
    }

    /**
     * Main method to test both the brute force and optimal solutions.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

        System.out.println("Brute Force Profit: " + buySellStock(prices));
        System.out.println("Optimal Profit: " + buySellStockBetter(prices));
    }
}
