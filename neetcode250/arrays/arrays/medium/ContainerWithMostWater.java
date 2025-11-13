package arrays.arrays.medium;

/**
 * Problem: Container With Most Water
 *
 * This class contains both brute-force and optimal solutions to find
 * the maximum area of water that can be contained between two vertical lines.
 */
public class ContainerWithMostWater {

    // Variable to track maximum area in brute-force solution
    int maxArea = 0;

    /**
     * Brute-force solution to find the maximum area.
     * Time Complexity: O(n^2)
     *
     * @param height Array representing heights of vertical lines
     * @return Maximum area of water that can be contained
     */
    public int maxArea(int[] height) {
        // Iterate through all pairs of lines
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                // Calculate area using the minimum of two heights and the distance between them
                int area = Math.min(height[i], height[j]) * (j - i);
                // Update maxArea if a larger area is found
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    // Variable to track maximum area in optimal solution
    int maxAreaOptimal = 0;

    /**
     * Optimal two-pointer solution to find the maximum area.
     * Time Complexity: O(n)
     *
     * @param height Array representing heights of vertical lines
     * @return Maximum area of water that can be contained
     */
    public int maxAreaOptimal(int[] height) {
        int left = 0; // Start pointer
        int right = height.length - 1; // End pointer

        // Move pointers towards each other until they meet
        while (left < right) {
            // Calculate the current area
            int area = Math.min(height[left], height[right]) * (right - left);
            // Update maxAreaOptimal if a larger area is found
            maxAreaOptimal = Math.max(maxAreaOptimal, area);

            // Move the pointer pointing to the shorter line
            if (height[left] < height[right]) {
                left++;
            } else if (height[left] > height[right]) {
                right--;
            } else {
                // If both heights are equal, move either pointer (here, moving right)
                right--;
            }
        }
        return maxAreaOptimal;
    }

    /**
     * Main method to test both brute-force and optimal solutions.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        int[] height = {1, 7, 2, 5, 4, 7, 3, 6};

        // Create an instance of the ContainerWithMostWater class
        ContainerWithMostWater container = new ContainerWithMostWater();

        // Test brute-force solution
        int maxArea1 = container.maxArea(height);
        System.out.println("Brute Force Max Area: " + maxArea1);

        // Test optimal two-pointer solution
        int optimal = container.maxAreaOptimal(height);
        System.out.println("Optimal Max Area: " + optimal);
    }
}
