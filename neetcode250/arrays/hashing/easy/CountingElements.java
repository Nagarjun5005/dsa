package arrays.hashing.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 =============================================================================
 PROBLEM:
 Counting Elements (LeetCode 1426)

 Given an integer array arr, count how many elements x exist such that:
     x + 1 is also present in the array.

 IMPORTANT:
 - Each occurrence is counted separately.
 - Order of elements does not matter.

 -----------------------------------------------------------------------------
 EXAMPLE:
 Input:  arr = [1, 2, 3]

 Valid elements:
 1 -> 2 exists
 2 -> 3 exists
 3 -> 4 does not exist

 Output: 2
 =============================================================================
*/

public class CountingElements {

    /*
     =========================================================================
     MAIN METHOD
     Used for local testing of all approaches
     =========================================================================
    */
    public static void main(String[] args) {

        int[] arr = {1, 2, 3};

        System.out.println("Brute Force Result:");
        System.out.println(countElements(arr));

        System.out.println("HashMap Result:");
        System.out.println(countElementsHashMap(arr));

        System.out.println("HashSet Result:");
        System.out.println(countElementsHashSet(arr));
    }

    /*
     =========================================================================
     APPROACH 1: BRUTE FORCE

     IDEA:
     - For each element arr[i], scan the entire array to check
       whether arr[i] + 1 exists.
     - As soon as one match is found, count the element and stop scanning.

     WHY THIS WORKS:
     - The problem only asks whether (x + 1) exists, not how many times.
     - Each element occurrence is counted separately.

     TIME COMPLEXITY:
     - O(n²)
       (Two nested loops over the array)

     SPACE COMPLEXITY:
     - O(1)
       (No extra data structures used)
     =========================================================================
    */
    public static int countElements(int[] arr) {

        int n = arr.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i] + 1 == arr[j]) {
                    count++;
                    break;  // stop after first match
                }
            }
        }
        return count;
    }

    /*
     =========================================================================
     APPROACH 2: HASHMAP (FREQUENCY TABLE)

     IDEA:
     - First, store the frequency of each element in a HashMap.
     - Then, for each element x, check if (x + 1) exists as a key.

     WHY HASHMAP:
     - Provides O(1) average-time existence checks.
     - Useful when frequency information might be needed for variations
       of the problem.

     TIME COMPLEXITY:
     - O(n)
       (One pass to build the map + one pass to count)

     SPACE COMPLEXITY:
     - O(n)
       (HashMap storing all elements)
     =========================================================================
    */
    public static int countElementsHashMap(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;

        // Step 1: Build frequency map
        for (int el : arr) {
            map.put(el, map.getOrDefault(el, 0) + 1);
        }

        // Step 2: Count elements where (el + 1) exists
        for (int el : arr) {
            if (map.containsKey(el + 1)) {
                count++;
            }
        }

        return count;
    }

    /*
     =========================================================================
     APPROACH 3: HASHSET (OPTIMAL & SIMPLE)

     IDEA:
     - Store all elements in a HashSet.
     - For each element x, directly check if (x + 1) exists in the set.

     WHY HASHSET IS IDEAL HERE:
     - We only need existence checks, not frequencies.
     - Cleaner and slightly faster than HashMap.

     TIME COMPLEXITY:
     - O(n)
       (One pass to build the set + one pass to count)

     SPACE COMPLEXITY:
     - O(n)
       (HashSet storing all unique elements)
     =========================================================================
    */
    public static int countElementsHashSet(int[] arr) {

        Set<Integer> set = new HashSet<>();
        int count = 0;

        // Step 1: Add all elements to the set
        for (int el : arr) {
            set.add(el);
        }

        // Step 2: Count elements where (el + 1) exists
        for (int el : arr) {
            if (set.contains(el + 1)) {
                count++;
            }
        }

        return count;
    }
}
