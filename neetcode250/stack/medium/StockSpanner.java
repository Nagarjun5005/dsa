package stack.medium;

import java.util.ArrayList;

/**
 * Problem: 901. Online Stock Span
 *
 * The StockSpanner class calculates the stock span for each incoming
 * stock price.
 *
 * Stock Span Definition:
 * The span of a stock's price on a given day is the maximum number of
 * consecutive days (starting from today and going backwards) for which
 * the price of the stock was less than or equal to today's price.
 *
 * This implementation uses a BRUTE FORCE approach.
 *
 * Key Characteristics:
 * - Prices are processed one day at a time via the next(price) method
 * - For each new price, we scan backwards to count valid consecutive days
 *
 * Time Complexity:
 * - Worst case per call to next(): O(n)
 * - Overall worst case: O(n²)
 *
 * Space Complexity:
 * - O(n) to store all prices
 *
 * This solution is useful for understanding the core logic
 * before optimizing with a stack-based approach.
 */
class StockSpanner {

    /**
     * List to store stock prices day by day.
     * Each index represents a day.
     */
    private ArrayList<Integer> prices;

    /**
     * Constructor
     *
     * Initializes the StockSpanner with an empty list
     * to store incoming stock prices.
     */
    public StockSpanner() {
        prices = new ArrayList<>();
    }

    /**
     * Processes the next stock price and returns its span.
     *
     * Brute Force Logic:
     * 1. Add the current price to the list
     * 2. Initialize span = 1 (today always counts)
     * 3. Traverse backwards through previous prices
     * 4. Increment span while previous prices are
     *    less than or equal to today's price
     * 5. Stop when a higher price is found
     *
     * @param price the stock price for the current day
     * @return the stock span for the current day
     */
    public int next(int price) {

        // Add today's price
        prices.add(price);

        int span = 1; // today always counts
        int i = prices.size() - 1; // index of current day

        // Traverse backwards and count consecutive days
        for (int j = i - 1; j >= 0; j--) {

            // If previous price is less than or equal,
            // it contributes to the span
            if (prices.get(j) <= price) {
                span++;
            } else {
                // Higher price breaks the span
                break;
            }
        }

        return span;
    }

    /**
     * Main method to demonstrate the working of StockSpanner.
     *
     * Simulates multiple calls to next(price),
     * similar to how LeetCode invokes the method.
     */
    public static void main(String[] args) {

        StockSpanner stockSpanner = new StockSpanner();

        int[] prices = {100, 80, 60, 70, 60, 75, 85};

        for (int price : prices) {
            int span = stockSpanner.next(price);
            System.out.println("Price: " + price + " -> Span: " + span);
        }
    }
}
