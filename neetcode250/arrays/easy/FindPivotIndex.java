package neetcode250.arrays.easy;

/**
 * The {@code FindPivotIndex} class provides methods to find the pivot index in an array.
 * The pivot index is the index where the sum of the numbers to the left is equal to the sum of the numbers to the right.
 *
 * There are two approaches implemented:
 * <ul>
 *     <li>Brute force approach with nested loops.</li>
 *     <li>Optimized approach using prefix sum (pre-sum technique).</li>
 * </ul>
 *
 * Example:
 * For the array [1, 7, 3, 6, 5, 6], the pivot index is 3 because:
 * <pre>
 *     Left sum = 1 + 7 + 3 = 11
 *     Right sum = 5 + 6 = 11
 * </pre>
 *
 * Time Complexities:
 * <ul>
 *     <li>Brute Force: O(n²)</li>
 *     <li>Prefix Sum: O(n)</li>
 * </ul>
 */
public class FindPivotIndex {

    /**
     * Finds the pivot index using a brute force approach.
     * For each element, it calculates the sum of elements to the left and to the right.
     *
     * @param arr The input array of integers.
     * @return The pivot index if found, otherwise -1.
     */
    public static int pivotIndex(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int leftSum = 0;
            int rightSum = 0;

            // Calculate sum of elements to the left of index i
            for (int j = 0; j < i; j++) {
                leftSum += arr[j];
            }

            // Calculate sum of elements to the right of index i
            for (int j = i + 1; j < arr.length; j++) {
                rightSum += arr[j];
            }

            // Check if left sum equals right sum
            if (leftSum == rightSum) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the pivot index using the prefix sum (optimized) approach.
     * This reduces the time complexity to O(n) by using total sum and a running left sum.
     *
     * @param arr The input array of integers.
     * @return The pivot index if found, otherwise -1.
     */
    public static int findPivotPreSum(int arr[]) {
        // Calculate the total sum of the array
        int totalSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totalSum += arr[i];
        }

        int leftSum = 0; // Running sum of elements to the left
        for (int i = 0; i < arr.length; i++) {
            int rightSum = totalSum - arr[i] - leftSum;

            // Check if left sum equals right sum
            if (leftSum == rightSum) {
                return i;
            }

            // Update the running sum
            leftSum += arr[i];
        }
        return -1;
    }

    /**
     * Main method to test both the brute force and prefix sum approaches.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int arr[] = {1, 7, 3, 6, 5, 6};

        int pivotIndex = pivotIndex(arr);
        System.out.println("Pivot Index using Brute Force: " + pivotIndex);

        int pivotPreSum = findPivotPreSum(arr);
        System.out.println("Pivot Index using Prefix Sum: " + pivotPreSum);
    }
}
