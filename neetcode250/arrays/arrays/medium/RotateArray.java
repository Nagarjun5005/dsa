package arrays.arrays.medium;

import java.util.Arrays;

/**
 * This class provides two methods to rotate an array to the right by 'k' steps.
 *
 * 1. Brute-force approach: Rotates the array by shifting elements one by one.
 * 2. Optimal approach: Uses array reversal to achieve rotation in O(n) time.
 */
public class RotateArray {

    /**
     * Rotates the given array to the right by 'k' steps using the brute-force method.
     * Time Complexity: O(k * n)
     * Space Complexity: O(1)
     *
     * @param arr The input array to rotate.
     * @param k The number of positions to rotate the array.
     * @return The rotated array.
     */
    public static int[] rotateArrayBrute(int arr[], int k) {
        int n = arr.length;

        // To handle cases where k is greater than n (the array length).
        k = k % n;

        // Rotate the array by shifting one element at a time, k times.
        for (int i = 0; i < k; i++) {
            int last = arr[n - 1];

            // Shift all elements to the right by one position.
            for (int j = n - 1; j > 0; j--) {
                arr[j] = arr[j - 1];
            }

            // Place the last element at the first index.
            arr[0] = last;
        }

        return arr;
    }

    /**
     * Rotates the given array to the right by 'k' steps using the optimal reversal method.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param arr The input array to rotate.
     * @param k The number of positions to rotate the array.
     * @return The rotated array.
     */
    public static int[] rotateArrayOptimal(int[] arr, int k) {
        int n = arr.length;

        // To handle cases where k is greater than n.
        k = k % n;

        // Step 1: Reverse the entire array.
        reverseArray(arr, 0, n - 1);

        // Step 2: Reverse the first k elements.
        reverseArray(arr, 0, k - 1);

        // Step 3: Reverse the remaining elements.
        reverseArray(arr, k, n - 1);

        return arr;
    }

    /**
     * Reverses the elements of the array from index 'start' to index 'end'.
     *
     * @param arr The input array.
     * @param start The starting index of the subarray to reverse.
     * @param end The ending index of the subarray to reverse.
     */
    public static void reverseArray(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * Main method to test both the brute-force and optimal array rotation methods.
     * Prints the result of each rotation.
     */
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        // Test Brute-Force RotationAdmin
        int[] arr1 = rotateArrayBrute(arr, k);
        System.out.println("Brute Force Rotated Array: " + Arrays.toString(arr1));

        // Test Optimal Rotation
        int arr2[] = {1, 2, 3, 4, 5, 6, 7};
        int[] arr3 = rotateArrayOptimal(arr2, k);
        System.out.println("Optimal Rotated Array: " + Arrays.toString(arr3));
    }
}
