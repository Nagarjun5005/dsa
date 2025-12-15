package binarySearch.medium;

/**
 * The {@code FindPeakElement} class provides two methods to find any peak element
 * in an integer array.
 *
 * A peak element is an element that is strictly greater than its neighbors.
 * For an array element at index i:
 * - It is a peak if: arr[i] > arr[i - 1] AND arr[i] > arr[i + 1]
 * - Corner elements have only one neighbor:
 *      - arr[0] is a peak if arr[0] > arr[1]
 *      - arr[n-1] is a peak if arr[n-1] > arr[n-2]
 *
 * This class contains:
 * 1. A brute-force O(n) method
 * 2. A binary search O(log n) optimized method
 *
 * Both methods return the index of any valid peak.
 */
public class FindPeakElement {

    public static void main(String[] args) {
        int arr[] = {1, 2, 1, 3, 5, 6, 4};

        int peakElement = findPeakElement(arr);
        System.out.println("Peak Element (Brute Force) ---> " + peakElement);

        int peakElementBinarySearch = findPeakElementBinarySearch(arr);
        System.out.println("Peak Element (Binary Search) ---> " + peakElementBinarySearch);
    }

    /**
     * Brute-force method to find a peak element in the array.
     *
     * <p>This method checks the following cases:
     * <ul>
     *     <li>Whether the first element is a peak</li>
     *     <li>Whether any middle element is a peak</li>
     *     <li>Whether the last element is a peak</li>
     * </ul>
     *
     * <p>Time Complexity: O(n)</p>
     * <p>Space Complexity: O(1)</p>
     *
     * @param arr input array
     * @return index of any peak element, or -1 if none found
     */
    public static int findPeakElement(int[] arr) {

        int n = arr.length;

        // Case 1: If only one element OR first element is a peak
        if (n == 1 || arr[0] > arr[1]) {
            return 0;
        }

        // Case 2: Check middle elements
        for (int i = 1; i < n - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                return i;
            }
        }

        // Case 3: Check last element
        if (arr[n - 1] > arr[n - 2]) {
            return n - 1;
        }

        return -1;
    }

    /**
     * Optimized method to find a peak element using Binary Search.
     *
     * <p>Logic used:
     * <ul>
     *     <li>If arr[mid] is greater than both neighbors → mid is a peak.</li>
     *     <li>If arr[mid] < arr[mid + 1], we are on a rising slope:
     *         a peak MUST exist on the right side → move left = mid + 1.</li>
     *     <li>Else, we are on a falling slope:
     *         a peak is on the left side → move right = mid - 1.</li>
     * </ul>
     *
     * <p>This logic works because in any rising or falling slope,
     * a peak is guaranteed to exist in the direction of movement.</p>
     *
     * <p>Time Complexity: O(log n)</p>
     * <p>Space Complexity: O(1)</p>
     *
     * @param arr input array
     * @return index of any peak element
     */
    public static int findPeakElementBinarySearch(int[] arr) {
        int n = arr.length;

        // Boundary checks
        if (n == 1) return 0;
        if (arr[0] > arr[1]) return 0;
        if (arr[n - 1] > arr[n - 2]) return n - 1;

        int left = 1;
        int right = n - 2;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Case 1: mid is a peak
            if (arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) {
                return mid;
            }

            // Case 2: Rising slope → go right
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            }
            // Case 3: Falling slope → go left
            else {
                right = mid - 1;
            }
        }

        // In theory, this point is never reached because the problem guarantees a peak exists.
        return -1;
    }
}
