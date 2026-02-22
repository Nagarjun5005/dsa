package binarySearch.medium;

/**
 * FindNthRootOfInteger demonstrates two approaches
 * to compute the Nth root of an integer M.
 *
 * <p>
 * Problem:
 * Given two integers N and M, find the integer X such that:
 *
 *      X^N = M
 *
 * If exact root exists → return X
 * Otherwise → return -1
 *
 * </p>
 *
 * Approaches:
 * 1. Brute Force (Linear Search)
 * 2. Binary Search (Optimized)
 *
 * Time Complexity:
 * - Brute Force: O(M × N)
 * - Binary Search: O(N × log M)
 */
public class FindNthRootOfInteger {

    public static void main(String[] args) {

        int nthRoot = nthRoot(2, 64);
        System.out.println("Binary Search Result: " + nthRoot);

        System.out.println("Brute Force Result: " + nthRootBrute(2, 64));
    }

    /**
     * Brute force approach to find nth root.
     *
     * <p>
     * Iterates from 1 to M and checks for each value i
     * whether i^N equals M.
     * </p>
     *
     * @param n the exponent
     * @param m the number whose nth root is required
     * @return exact root if exists, otherwise -1
     *
     * Time Complexity: O(M × N)
     */
    public static int nthRootBrute(int n, int m) {

        for (int i = 1; i < m; i++) {

            int value = 1;

            for (int j = 0; j < n; j++) {
                value = value * i;

                if (value > m) {
                    break; // stop early if exceeded
                }
            }

            if (value == m) {
                return i;
            }

            if (value > m) {
                return i - 1;  // floor value
            }
        }

        return -1;
    }

    /**
     * Optimized Binary Search approach to find nth root.
     *
     * <p>
     * Uses binary search on the answer space [1, M].
     * Since X^N is monotonically increasing,
     * we can eliminate half of the search space each iteration.
     * </p>
     *
     * @param n the exponent
     * @param m the number whose nth root is required
     * @return exact root if exists, otherwise -1
     *
     * Time Complexity: O(N × log M)
     */
    public static int nthRoot(int n, int m) {

        // Edge case handling
        if (m == 0) return 0;
        if (m == 1) return 1;

        int low = 1;
        int high = m;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            int value = power(mid, n, m);

            if (value == m) {
                return mid;
            } else if (value > m) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    /**
     * Computes base^exponent but stops early
     * if result exceeds limit.
     *
     * <p>
     * Early stopping prevents unnecessary computation
     * and avoids overflow risks.
     * </p>
     *
     * @param base     the base number
     * @param exponent the exponent
     * @param limit    threshold value (M)
     * @return computed power (may exceed limit)
     */
    public static int power(int base, int exponent, int limit) {

        long result = 1;  // use long to reduce overflow risk

        for (int i = 0; i < exponent; i++) {

            result *= base;

            if (result > limit) {
                return (int) result;
            }
        }

        return (int) result;
    }
}