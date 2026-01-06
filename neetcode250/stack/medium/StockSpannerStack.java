package stack.medium;

import java.util.Stack;

/**
 * Problem: 901. Online Stock Span
 *
 * This class provides an OPTIMAL solution using a stack.
 *
 * Stock Span Definition:
 * The span of a stock's price on a given day is the maximum number of
 * consecutive days (starting from today and going backwards) for which
 * the price was less than or equal to today's price.
 *
 * This implementation improves the brute-force solution by using
 * a Monotonic Decreasing Stack.
 *
 * Time Complexity:
 * - Amortized O(1) per call to next()
 * - Overall O(n)
 *
 * Space Complexity:
 * - O(n) for stack storage
 */
public class StockSpannerStack {

    /**
     * Stack stores pairs of:
     * [price, span]
     *
     * Meaning:
     * - price : stock price on that day
     * - span  : computed span for that price
     *
     * The stack is maintained in decreasing order of prices.
     */
    private Stack<int[]> stack;

    /**
     * Constructor
     *
     * Initializes an empty stack.
     */
    public StockSpannerStack() {
        this.stack = new Stack<>();
    }

    /**
     * Processes the next stock price and returns its span.
     *
     * Stack Logic:
     * 1. Start with span = 1 (today always counts)
     * 2. While stack top price <= current price:
     *    - Pop the element
     *    - Add its span to current span
     * 3. Push current price with its span
     *
     * This allows us to "jump" multiple days at once
     * instead of counting one by one.
     *
     * @param price stock price for the current day
     * @return stock span for the current day
     */
    public int next(int price) {

        int span = 1; // today always counts

        // Merge spans of previous smaller or equal prices
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1];
        }

        // Push current price and its span
        stack.push(new int[]{price, span});

        return span;
    }

    /**
     * Main method to demonstrate the working of the
     * stack-based Stock Spanner.
     */
    public static void main(String[] args) {

        StockSpannerStack stockSpanner = new StockSpannerStack();

        int[] prices = {100, 80, 60, 70, 60, 75, 85};

        for (int price : prices) {
            int span = stockSpanner.next(price);
            System.out.println("Price: " + price + " -> Span: " + span);
        }
    }
}
