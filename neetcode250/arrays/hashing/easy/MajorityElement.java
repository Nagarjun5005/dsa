package arrays.hashing.easy;

import java.util.HashMap;

/**
 * MAJORITY ELEMENT
 * -----------------
 *
 * Given an integer array nums, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * It is guaranteed that the majority element always exists in the array.
 *
 * Example:
 * --------
 * Input:  nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 *
 * This class demonstrates THREE approaches:
 *
 * 1️⃣ Brute Force Approach
 * 2️⃣ HashMap (Frequency Count) Approach
 * 3️⃣ Optimized Boyer–Moore Voting Algorithm
 *
 *
 * PATTERNS USED:
 * --------------
 * - Brute Force
 * - Hashing (Frequency Map)
 * - Greedy / Voting Algorithm
 *
 *
 * @author Charu
 */
public class MajorityElement {

    public static void main(String[] args) {

        int[] nums = {2, 2, 1, 1, 1, 2, 2};

        // Uncomment to test different approaches

//        int brute = majorityElementBrute(nums);
//        System.out.println(brute);

//        int hashMap = majorityElementHashMap(nums);
//        System.out.println(hashMap);

        int optimized = majorityElementOptimized(nums);
        System.out.println(optimized); // Output: 2
    }

    /**
     * 1️⃣ BRUTE FORCE APPROACH
     * -----------------------
     *
     * Idea:
     * For every element, count how many times it appears in the array.
     * If its frequency exceeds n/2, return it.
     *
     * Algorithm:
     * ----------
     * - Pick each element one by one
     * - Count its occurrences using a nested loop
     * - Return the element if count > n/2 (early exit)
     *
     * Time Complexity:
     * ----------------
     * O(n²) — nested loops
     *
     * Space Complexity:
     * -----------------
     * O(1) — no extra space
     *
     * This approach is correct but inefficient.
     */
    public static int majorityElementBrute(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int count = 1; // current element itself

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }

                // Early exit if majority found
                if (count > nums.length / 2) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    /**
     * 2️⃣ HASHMAP (FREQUENCY COUNT) APPROACH
     * -------------------------------------
     *
     * Idea:
     * Store the frequency of each element using a HashMap.
     * If any element’s frequency exceeds n/2, return it.
     *
     * Algorithm:
     * ----------
     * - Create a HashMap to store (element → frequency)
     * - Traverse the array
     * - Update frequency for each element
     * - If frequency > n/2, return the element immediately
     *
     * Time Complexity:
     * ----------------
     * O(n)
     *
     * Space Complexity:
     * -----------------
     * O(n) — HashMap storage
     *
     * This is faster than brute force but uses extra space.
     */
    public static int majorityElementHashMap(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {

            map.put(num, map.getOrDefault(num, 0) + 1);

            // Early exit when majority condition is met
            if (map.get(num) > nums.length / 2) {
                return num;
            }
        }
        return -1;
    }

    /**
     * 3️⃣ OPTIMIZED APPROACH — BOYER–MOORE VOTING ALGORITHM
     * ----------------------------------------------------
     *
     * Idea:
     * Since the majority element appears more than n/2 times,
     * it cannot be completely canceled out by other elements.
     *
     * Algorithm:
     * ----------
     * - Maintain a candidate and a count
     * - If count == 0, choose the current element as candidate
     * - If current element == candidate → increment count
     * - Else → decrement count
     *
     * At the end, the candidate is the majority element.
     *
     * Time Complexity:
     * ----------------
     * O(n)
     *
     * Space Complexity:
     * -----------------
     * O(1)
     *
     * This is the MOST OPTIMAL solution.
     */
    public static int majorityElementOptimized(int[] nums) {

        int count = 0;
        int candidate = 0;

        for (int num : nums) {

            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }
}
