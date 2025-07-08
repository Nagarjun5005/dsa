package neetcode250.arrays.medium;

import java.util.Arrays;

/**
 * Problem: Boats to Save People
 *
 * Given an array `people` where people[i] is the weight of the i-th person,
 * and an integer `limit` representing the maximum weight a boat can carry,
 * return the minimum number of boats to carry every person.
 * Each boat can carry at most two people at the same time,
 * provided the sum of their weights is less than or equal to the limit.
 *
 * Approach:
 * - Sort the array.
 * - Use two pointers: one starting from the lightest person (left),
 *   and one from the heaviest person (right).
 * - Try to pair the heaviest with the lightest person.
 * - If they can pair (sum <= limit), move both pointers.
 * - Else, move only the right pointer (heaviest person goes alone).
 */
public class BoatsToSavePeople {

    /**
     * Calculates the minimum number of boats needed to save all people.
     *
     * @param people Array of weights of people
     * @param limit  Maximum weight a boat can carry
     * @return Minimum number of boats needed
     */
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);  // Sort weights in ascending order
        int res = 0;
        int l = 0;             // Pointer to the lightest person
        int r = people.length - 1; // Pointer to the heaviest person

        while (l <= r) {
            int remain = limit - people[r]; // Remaining weight after taking heaviest person
            r--;                            // Assign heaviest person to a boat
            res++;                          // One boat used

            // If the lightest person can also fit in the boat, pair them
            if (l <= r && remain >= people[l]) {
                l++;  // Pair lightest with heaviest
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] heights = {30, 10, 50, 70, 80};
        int boats = numRescueBoats(heights, 100);
        System.out.println("Minimum number of boats: " + boats);
    }
}
