package binarySearch.easy;

/**
 * File Name: FindCeilInSortedArray.java
 *
 * Problem:
 * Given a sorted array and a number x,
 * find the index of the smallest element greater than or equal to x.
 *
 * Ceil Definition:
 * Ceil of x = smallest element in array such that element >= x
 *
 * If no such element exists, return -1.
 *
 * Approach:
 * Use Binary Search.
 *
 * If arr[mid] >= x:
 *      This can be a potential ceil.
 *      Move left to try finding a smaller valid value.
 *
 * Else:
 *      Move right.
 *
 * Time Complexity  : O(log n)
 * Space Complexity : O(1)
 */

public class FindCeilInSortedArray {

    public static void main(String[] args) {

        int[] arr = {2, 3, 7, 10, 11, 11, 25};

        int x = 9;

        int index = findCeil(arr, x);

        if (index == -1) {
            System.out.println("Ceil does not exist.");
        } else {
            System.out.println("Ceil index : " + index);
            System.out.println("Ceil value : " + arr[index]);
        }
    }


    /**
     * Returns index of ceil of x in sorted array.
     * If no ceil exists, returns -1.
     */
    public static int findCeil(int[] arr, int x) {

        int low = 0;
        int high = arr.length - 1;

        // Default is -1 because ceil may not exist
        int ans = -1;

        while (low <= high) {

            // Safe mid calculation to prevent overflow
            int mid = low + (high - low) / 2;

            // If current element is >= x,
            // it can be a potential ceil
            if (arr[mid] >= x) {
                ans = mid;       // store candidate
                high = mid - 1;  // try to find smaller valid value
            } else {
                low = mid + 1;   // move right
            }
        }

        return ans;
    }
}

