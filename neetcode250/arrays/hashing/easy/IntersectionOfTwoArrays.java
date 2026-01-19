package arrays.hashing.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 =============================================================================
 PROBLEM: 349. Intersection of Two Arrays (LeetCode)

 Given two integer arrays nums1 and nums2, return an array of their intersection.
 Each element in the result must be UNIQUE, and the order of the result
 does not matter.

 Example:
 nums1 = [4,9,5]
 nums2 = [9,4,9,8,4]
 Output = [9,4] or [4,9]

 =============================================================================
*/

public class IntersectionOfTwoArrays {

    /*
     =========================================================================
     MAIN METHOD (TESTING)
     =========================================================================
    */
    public static void main(String[] args) {

        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};

        int[] intersection = intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));
    }

    /*
     =========================================================================
     APPROACH: HASH SET LOOKUP

     IDEA:
     - Store all elements of nums1 in a HashSet for O(1) lookup.
     - Traverse nums2 and check if each element exists in the set.
     - Use another HashSet (resultSet) to ensure uniqueness in the result.
     - Convert the result set to an integer array.

     WHY THIS WORKS:
     - HashSet ensures fast lookup.
     - HashSet naturally removes duplicates.
     - Order is not required, so set iteration is acceptable.

     PATTERN:
     - Hashing / Lookup

     TIME COMPLEXITY:
     - O(n + m)
       where:
       n = length of nums1
       m = length of nums2

     SPACE COMPLEXITY:
     - O(n) for storing elements of nums1 in the set
     =========================================================================
    */
    public static int[] intersection(int[] nums1, int[] nums2) {

        // Store unique elements from nums1
        Set<Integer> set = new HashSet<>();

        // Store unique intersection elements
        Set<Integer> resultSet = new HashSet<>();

        // Add all elements of nums1 to the set
        for (int num : nums1) {
            set.add(num);
        }

        // Check elements of nums2 against the set
        for (int num : nums2) {
            if (set.contains(num)) {
                resultSet.add(num); // ensures uniqueness
            }
        }

        // Convert result set to array
        int[] result = new int[resultSet.size()];
        int index = 0;
        for (int num : resultSet) {
            result[index++] = num;
        }

        return result;
    }
}
