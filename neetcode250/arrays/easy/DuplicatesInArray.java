package neetcode250.arrays.easy;

import java.util.Arrays;
import java.util.HashSet;

public class DuplicatesInArray {


  /*✅ 1. Brute Force (Nested Loops)
    Compare each element with every other element.
    Time Complexity: O(n^2)
    Space Complexity: O(1)
    Simple, but inefficient for large datasets.
      */

    public static boolean containsDuplicate(int []arr){
        int n=arr.length;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(arr[i]==arr[j]){
                    return true;
                }
            }
        }
        return false;
    }

   /* ✅ 2. HashSet / HashMap
    Use a HashSet to track seen elements.
    If an element is already in the set, it's a duplicate.
    HashMap can be used to track frequency.
    Time Complexity: O(n)
    Space Complexity: O(n)
    Efficient and commonly used.
    */

    public static boolean hasDuplicates(int [] arr){
        int n=arr.length;
        HashSet<Integer>set=new HashSet<>();
        for (int j : arr) {
            if (set.contains(j)) {
                return true;
            }
            set.add(j);
        }

        return false;

    }

     //✅ 3. Sorting
    public static boolean hasDuplicatesSorting(int[] arr) {
        // Step 1: Sort the array
        Arrays.sort(arr);

        // Step 2: Check adjacent elements for duplicates
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                return true; // Duplicate found
            }
        }

        return false; // No duplicates found
    }



    public static void main(String[] args) {
        int []arr={1,2,3,1};
        //brute force
        boolean check = containsDuplicate(arr);
        System.out.println(check);


        //hashing
        boolean hashCheck = hasDuplicates(arr);
        System.out.println(hashCheck);

        //sorting
        boolean sortCheck = hasDuplicatesSorting(arr);
        System.out.println(sortCheck);

    }
}
