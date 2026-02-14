package binarySearch.easy;

public class ImplementLowerBound {

    public static void main(String[] args) {
        int[] arr = {2, 3, 7, 10, 11, 11, 25};

        // Lower bound of 9 → first element >= 9
        int lowerBound = lowerBound(arr, 9);

        System.out.println("Lower Bound ---> " + lowerBound);
    }

    /**
     * Returns the index of the first element in the sorted array
     * that is greater than or equal to the target.
     *
     * If no such element exists, returns arr.length.
     *
     * Example:
     * arr = [2,3,7,10,11]
     * target = 9
     * output = 3 (index of 10)
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int lowerBound(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;

        // Default answer is arr.length
        // This handles case when all elements are smaller than target
        int ans = arr.length;

        while (low <= high) {

            // Prevent overflow
            int mid = low + (high - low) / 2;

            // If current element is >= target,
            // it can be a potential answer
            if (arr[mid] >= target) {
                ans = mid;          // store potential answer
                high = mid - 1;     // search on left side for smaller index
            } else {
                // If current element is smaller than target,
                // move to right half
                low = mid + 1;
            }
        }

        return ans;
    }
}
