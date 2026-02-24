package binarySearch.medium;

/**
 * KokoEatingBananas solves the classic Binary Search on Answer problem.
 *
 * Problem Statement:
 * Given an array of banana piles and an integer h (hours),
 * Koko can eat at most k bananas per hour.
 *
 * In one hour:
 * - She chooses one pile.
 * - Eats up to k bananas from it.
 * - If pile has fewer than k bananas, she finishes it.
 *
 * Goal:
 * Find the minimum integer k such that all bananas
 * can be eaten within h hours.
 *
 * -------------------------------------------------------
 * Observations:
 * 1. As eating speed k increases, total hours required decreases.
 * 2. The function totalHours(k) is monotonic decreasing.
 * 3. This allows us to apply Binary Search on the answer space.
 *
 * -------------------------------------------------------
 * Approaches:
 * 1. Brute Force → Try all speeds from 1 to maxPile.
 *    Time Complexity: O(maxPile × n)
 *
 * 2. Binary Search → Search in range [1, maxPile].
 *    Time Complexity: O(n × log(maxPile))
 */
public class KokoEatingBananas {

    public static void main(String[] args) {

        int piles[] = {3, 6, 7, 11};
        int h = 8;

        int minEatingSpeedBruteForce = minEatingSpeedBruteForce(piles, h);
        System.out.println("Brute Force Result: " + minEatingSpeedBruteForce);

        int minEatingSpeedBinarySearch = minEatingSpeedBinarySearch(piles, h);
        System.out.println("Binary Search Result: " + minEatingSpeedBinarySearch);
    }

    /**
     * Brute Force Approach:
     *
     * Try every possible eating speed from 1 to max pile size.
     * For each speed, calculate total hours required.
     * Return the first speed that satisfies totalHours <= h.
     *
     * Time Complexity: O(maxPile × n)
     * Space Complexity: O(1)
     */
    public static int minEatingSpeedBruteForce(int[] piles, int h) {

        int maxPile = 0;
        for (int pile : piles) {
            maxPile = Math.max(pile, maxPile);
        }

        for (int k = 1; k <= maxPile; k++) {

            int totalHours = 0;

            for (int pile : piles) {
                // Ceiling division: ceil(pile / k)
                totalHours += (pile + k - 1) / k;
            }

            if (totalHours <= h) {
                return k;
            }
        }

        return maxPile;
    }

    /**
     * Binary Search Approach:
     *
     * Since totalHours(k) is monotonic decreasing,
     * we can apply binary search on k.
     *
     * Search Space:
     * low = 1
     * high = max(piles)
     *
     * If totalHours(mid) <= h:
     *      mid is valid → try smaller speed (move left)
     * Else:
     *      speed too slow → increase speed (move right)
     *
     * Time Complexity: O(n × log(maxPile))
     * Space Complexity: O(1)
     */
    public static int minEatingSpeedBinarySearch(int[] piles, int h) {

        int maxPile = 0;
        for (int pile : piles) {
            maxPile = Math.max(pile, maxPile);
        }

        int low = 1;
        int high = maxPile;
        int ans = high;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            long totalHours = 0;

            for (int pile : piles) {
                totalHours += (pile + mid - 1) / mid;
            }

            if (totalHours <= h) {
                ans = mid;        // store valid answer
                high = mid - 1;   // try smaller
            } else {
                low = mid + 1;    // increase speed
            }
        }

        return ans;
    }
}