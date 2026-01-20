package stack.hard;

import java.util.Stack;

/*
 =============================================================================
 PROBLEM: 85. Maximal Rectangle (LeetCode)

 Given a 2D binary matrix filled with '0's and '1's, find the largest rectangle
 containing only '1's and return its area.

 Example:
 Input:
 [
   ['1','0','1','0','0'],
   ['1','0','1','1','1'],
   ['1','1','1','1','1'],
   ['1','0','0','1','0']
 ]

 Output: 6

 Explanation:
 The maximal rectangle is:
   1 1 1
   1 1 1
 Area = 6
 =============================================================================
*/

public class MaximalRectangle {

    /*
     =========================================================================
     MAIN METHOD (TESTING)
     =========================================================================
    */
    public static void main(String[] args) {

        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };

        int result = maximalRectangle(matrix);
        System.out.println("Maximal Rectangle Area = " + result); // Expected: 6
    }

    /*
     =========================================================================
     APPROACH OVERVIEW

     CORE IDEA:
     - Treat each row as the base of a histogram.
     - Build histogram heights column-wise.
     - For each row, compute the Largest Rectangle in Histogram.
     - The maximum of all histogram areas is the answer.

     WHY THIS WORKS:
     - A rectangle of 1s in a matrix is equivalent to a rectangle
       in a histogram built from consecutive 1s vertically.

     PATTERNS USED:
     - Histogram Technique
     - Monotonic Stack
     - Previous Smaller Element (PSE)
     - Next Smaller Element (NSE)

     TIME COMPLEXITY:
     - O(rows × cols)

     SPACE COMPLEXITY:
     - O(cols)
     =========================================================================
    */
    public static int maximalRectangle(char[][] matrix) {

        if (matrix == null || matrix.length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Histogram heights for each column
        int[] heights = new int[cols];
        int maxArea = 0;

        // Build histogram row by row
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }

            // Compute largest rectangle for current histogram
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    /*
     =========================================================================
     LARGEST RECTANGLE IN HISTOGRAM (LEETCODE 84)

     IDEA:
     - For each bar, find:
         Previous Smaller Element (PSE)
         Next Smaller Element (NSE)
     - Width = NSE - PSE - 1
     - Area = height × width

     WHY MONOTONIC STACK:
     - Allows finding PSE & NSE in O(n)

     TIME: O(n)
     SPACE: O(n)
     =========================================================================
    */
    private static int largestRectangleArea(int[] heights) {

        int n = heights.length;
        int[] pse = new int[n];
        int[] nse = new int[n];

        Stack<Integer> stack = new Stack<>();

        /*
         ---------------------------------------------------------------
         STEP 1: Previous Smaller Element
         ---------------------------------------------------------------
        */
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            pse[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        /*
         ---------------------------------------------------------------
         STEP 2: Next Smaller Element
         ---------------------------------------------------------------
        */
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            nse[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        /*
         ---------------------------------------------------------------
         STEP 3: Calculate Maximum Area
         ---------------------------------------------------------------
        */
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = nse[i] - pse[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
