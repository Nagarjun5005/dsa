package binarySearch.easy;

public class ImplementUpperBound {

    public static void main(String[] args) {
        int[] arr = {2, 3, 7, 10, 11, 11, 25};

        // Upper bound of 11 → first element > 11
        int upperBound = upperBound(arr, 11);

        System.out.println("Upper Bound ---> " + upperBound);
    }

    /**
     * Returns the index of the first element in the sorted array
     * that is strictly greater than the target.
     *
     * If no such element exists, returns arr.length.
     *
     * Example:
     * arr = [2,3,7,10,11,11,25]
     * target = 11
     * output = 6 (index of 25)
     *
     * Difference from Lower Bound:
     * Lower Bound  -> first element >= target
     * Upper Bound  -> first element > target
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int upperBound(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;

        // Default answer is arr.length
        // This handles case when all elements are <= target
        int ans = arr.length;

        while (low <= high) {

            // Prevent integer overflow
            int mid = low + (high - low) / 2;

            // If current element is strictly greater than target,
            // it can be a potential answer
            if (arr[mid] > target) {
                ans = mid;          // store potential answer
                high = mid - 1;     // search left for smaller index
            } else {
                // If current element is <= target,
                // move to right half
                low = mid + 1;
            }
        }

        return ans;
    }
}

