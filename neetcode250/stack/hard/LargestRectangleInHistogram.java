package stack.hard;

import java.util.Arrays;
import java.util.Stack;

/*
 =============================================================================
 PROBLEM:
 Largest Rectangle in Histogram (LeetCode 84)

 Given an array of integers `heights` representing the histogram's bar heights
 where the width of each bar is 1, find the area of the largest rectangle
 that can be formed in the histogram.

 -----------------------------------------------------------------------------
 EXAMPLE:
 heights = [2, 1, 5, 6, 2, 3]

 Output: 10

 Explanation:
 The largest rectangle is formed using heights[2] and heights[3]
 with height = 5 and width = 2 → area = 10
 =============================================================================
*/

public class LargestRectangleInHistogram {

    /*
     =========================================================================
     MAIN METHOD
     Used for local testing
     =========================================================================
    */
    public static void main(String[] args) {

        int[] heights = {2, 1, 5, 6, 2, 3};

        int[] nextSmallerElement = findNextSmallerElement(heights);
        System.out.println("NSE: " + Arrays.toString(nextSmallerElement));

        int[] previousSmallerElement = findPreviousSmallerElement(heights);
        System.out.println("PSE: " + Arrays.toString(previousSmallerElement));

        int largestRectangleInHistogram = largestRectangleInHistogramBrute(heights);
        System.out.println("Largest Rectangle Area: " + largestRectangleInHistogram);
    }

    /*
     =========================================================================
     APPROACH: MONOTONIC STACK (OPTIMAL SOLUTION)

     CORE IDEA:
     For each bar i, assume heights[i] is the smallest bar in a rectangle.
     Find how far this rectangle can extend:
       - to the LEFT  → Previous Smaller Element (PSE)
       - to the RIGHT → Next Smaller Element (NSE)

     Once the boundaries are known:
       width = NSE[i] - PSE[i] - 1
       area  = heights[i] * width

     The maximum area among all bars is the answer.

     TIME COMPLEXITY:
     - O(n)

     SPACE COMPLEXITY:
     - O(n)
     =========================================================================
    */
    public static int largestRectangleInHistogramBrute(int[] heights) {

        int largest = 0;

        // Find boundaries for each bar
        int[] nse = findNextSmallerElement(heights);
        int[] pse = findPreviousSmallerElement(heights);

        // Compute area for each bar
        for (int i = 0; i < heights.length; i++) {
            int width = nse[i] - pse[i] - 1;
            int area = heights[i] * width;
            largest = Math.max(largest, area);
        }

        return largest;
    }

    /*
     =========================================================================
     PREVIOUS SMALLER ELEMENT (PSE)

     For each index i, PSE[i] stores the index of the nearest bar to the LEFT
     that has a height strictly smaller than heights[i].

     If no such bar exists:
       PSE[i] = -1

     STACK STRATEGY:
     - Monotonic INCREASING stack (stores indices)
     - Traverse from LEFT to RIGHT

     TIME COMPLEXITY:
     - O(n)

     SPACE COMPLEXITY:
     - O(n)
     =========================================================================
    */
    private static int[] findPreviousSmallerElement(int[] heights) {

        int n = heights.length;
        int[] pse = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Traverse from left to right
        for (int i = 0; i < n; i++) {

            // Remove elements that are >= current height
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            // If stack is empty, no smaller element on the left
            pse[i] = stack.isEmpty() ? -1 : stack.peek();

            // Push current index
            stack.push(i);
        }

        return pse;
    }

    /*
     =========================================================================
     NEXT SMALLER ELEMENT (NSE)

     For each index i, NSE[i] stores the index of the nearest bar to the RIGHT
     that has a height strictly smaller than heights[i].

     If no such bar exists:
       NSE[i] = n  (array length)

     STACK STRATEGY:
     - Monotonic INCREASING stack (stores indices)
     - Traverse from RIGHT to LEFT

     TIME COMPLEXITY:
     - O(n)

     SPACE COMPLEXITY:
     - O(n)
     =========================================================================
    */
    private static int[] findNextSmallerElement(int[] heights) {

        int n = heights.length;
        int[] nse = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {

            // Remove elements that are >= current height
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            // If stack is empty, no smaller element on the right
            nse[i] = stack.isEmpty() ? n : stack.peek();

            // Push current index
            stack.push(i);
        }

        return nse;
    }
}
