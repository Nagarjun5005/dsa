package arrays.hashing.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 =============================================================================
 PROBLEM: 350. Intersection of Two Arrays II (LeetCode)

 Given two integer arrays nums1 and nums2, return an array of their intersection.
 Each element in the result should appear as many times as it shows in both
 arrays (minimum frequency).

 The order of the result does not matter.

 Example:
 nums1 = [1,2,2,1]
 nums2 = [2,2,1,1]
 Output = [2,2,1,1] or any valid order
 =============================================================================
*/

public class IntersectionOfTwoArraysWithDuplicates {

    /*
     =========================================================================
     MAIN METHOD (TESTING)
     =========================================================================
    */
    public static void main(String[] args) {

        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2, 1, 1};

        int[] intersected = intersect(nums1, nums2);
        System.out.println(Arrays.toString(intersected));
    }

    /*
     =========================================================================
     APPROACH: HASHMAP (FREQUENCY COUNTING)

     IDEA:
     - Count the frequency of each element in nums1 using a HashMap.
     - Traverse nums2:
         - If the current element exists in the map AND its frequency > 0,
           add it to the result.
         - Decrease the frequency in the map to avoid over-counting.

     WHY THIS WORKS:
     - Each element is added at most the minimum number of times it appears
       in both arrays.
     - HashMap allows O(1) average lookup and update time.

     PATTERN:
     - Hashing / Frequency Counting

     TIME COMPLEXITY:
     - O(n + m)
       where:
       n = length of nums1
       m = length of nums2

     SPACE COMPLEXITY:
     - O(n) for the frequency map
     - O(k) for the result list (k = size of intersection)
     =========================================================================
    */
    public static int[] intersect(int[] nums1, int[] nums2) {

        // Step 1: Build frequency map for nums1
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums1) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Collect intersection elements using nums2
        List<Integer> resultList = new ArrayList<>();
        for (int num : nums2) {
            if (freqMap.containsKey(num) && freqMap.get(num) > 0) {
                resultList.add(num);
                freqMap.put(num, freqMap.get(num) - 1);
            }
        }

        // Step 3: Convert result list to array
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }
}
