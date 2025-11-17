package arrays.hashing;

import java.util.HashMap;

public class SubArraySumEqualsK {
    public static void main(String[] args) {
        int arr[] = {1, 1, 2, 3, 3};

        int brute = subArraySumEqualsK(arr, 3);
        System.out.println(brute);

        int hashing = subArraySumEqualsKHashing(arr, 3);
        System.out.println(hashing);
    }

    /*
     * =====================================================
     *  BRUTE FORCE APPROACH
     * =====================================================
     * Time Complexity  : O(n^2)
     * Space Complexity : O(1)
     *
     * IDEA:
     * -----
     * Try every possible subarray (i...j) and calculate its sum.
     * If the sum == k → increment count.
     *
     * This is correct but slow, because:
     * 1. We are checking every possible subarray → O(n^2)
     * 2. We are recomputing sums repeatedly.
     */
    public static int subArraySumEqualsK(int arr[], int k) {
        int n = arr.length;
        int count = 0;

        for (int start = 0; start < n; start++) {
            int sum = 0;
            for (int end = start; end < n; end++) {
                sum += arr[end]; // build sum for subarray (start → end)
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }


    /*
     * =====================================================
     *  OPTIMIZED APPROACH — PREFIX SUM + HASHMAP
     * =====================================================
     * Time Complexity  : O(n)
     * Space Complexity : O(n)
     *
     * CORE CONCEPT:
     * -------------
     * Let:
     *    currSum = prefix sum till current index
     *
     * A subarray (start...i) has sum = k if:
     *
     *    prefix[i] - prefix[start - 1] = k
     *
     * Rearranging:
     *
     *    prefix[start - 1] = currSum - k
     *
     * This means:
     *    → If we have seen a prefix = (currSum - k),
     *    → then the subarray ending at current index has sum k.
     *
     * HASHMAP STORES:
     * ----------------
     *    prefixSum → how many times this prefixSum has occurred so far
     *
     * WHY STORE FREQUENCY?
     * ---------------------
     * Because the same prefix sum may appear multiple times,
     * and EACH occurrence contributes to a valid subarray.
     *
     * IMPORTANT INITIALIZATION:
     * --------------------------
     * map.put(0, 1);
     *
     * This handles the case when:
     *    currSum itself == k
     * meaning the subarray starting from index 0 forms a valid subarray.
     */
    public static int subArraySumEqualsKHashing(int nums[], int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // prefix sum 0 has been seen once — before starting the array
        map.put(0, 1);

        int currSum = 0;
        int count = 0;

        for (int num : nums) {

            // Step 1: update running prefix sum
            currSum += num;

            // Step 2: check if there exists a prefix sum = currSum - k
            // If yes, those many subarrays ending here total up to k
            if (map.containsKey(currSum - k)) {
                count += map.get(currSum - k);
            }

            // Step 3: record the current prefix sum in the map
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }

        return count;
    }
}
