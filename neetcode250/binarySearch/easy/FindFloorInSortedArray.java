package binarySearch.easy;

/**
 * File Name: FindFloorInSortedArray.java
 *
 * Problem:
 * Given a sorted array and a number x,
 * find the index of the greatest element less than or equal to x.
 *
 * Floor Definition:
 * Floor of x = largest element in array such that element <= x
 *
 * If no such element exists, return -1.
 *
 * Approach:
 * Use Binary Search.
 *
 * If arr[mid] <= x:
 *      This can be a potential floor.
 *      Move right to try finding a larger valid value.
 *
 * Else:
 *      Move left.
 *
 * Time Complexity  : O(log n)
 * Space Complexity : O(1)
 */

public class FindFloorInSortedArray {

    public static void main(String[] args) {

        int[] arr = {2, 3, 7, 10, 11, 11, 25};

        int x = 9;

        int index = findFloor(arr, x);

        if (index == -1) {
            System.out.println("Floor does not exist.");
        } else {
            System.out.println("Floor index : " + index);
            System.out.println("Floor value : " + arr[index]);
        }
    }


    /**
     * Returns index of floor of x in sorted array.
     * If no floor exists, returns -1.
     */
    public static int findFloor(int[] arr, int x) {

        int low = 0;
        int high = arr.length - 1;

        // Default is -1 because floor may not exist
        int ans = -1;

        while (low <= high) {

            // Safe mid calculation to avoid overflow
            int mid = low + (high - low) / 2;

            // If current element is <= x,
            // it can be a potential floor
            if (arr[mid] <= x) {
                ans = mid;        // store candidate
                low = mid + 1;    // try to find larger valid value
            } else {
                high = mid - 1;   // move left
            }
        }

        return ans;
    }
}
