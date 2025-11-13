package arrays.arrays.easy;

import java.util.Arrays;
import java.util.HashMap;

public class AnagramStrings {


    /*
   1. Sorting
   Time: O(n log n)
   Space: O(n) (if strings are immutable)
     */

    public static boolean isAnagramSorting(String a,String b){
        //step1 : convert string to char array
        char[] arr1 =a.toCharArray();
        char [] arr2=b.toCharArray();

        // Step 2: Sort the arrays
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        //step 3 :now compare the array if they are equal
        return Arrays.equals(arr1, arr2);

    }

    /*
    2: Hash Table
    Time	       O(n)
    Space	       O(k) → O(1) for lowercase a–z
     */

    public static boolean isAnagramHashing(String s ,String n){

        if(s.length()!=n.length()){
            return false;
        }
        HashMap<Character,Integer>map=new HashMap<>();

        for (char x : s.toCharArray()) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        for (char y : n.toCharArray()) {
            if (!map.containsKey(y)) return false;
            map.put(y, map.get(y) - 1);
            if (map.get(y) < 0) return false;
        }

//        for(char y:n.toCharArray()){
//            if (!map.containsKey(y)) return false;
//            map.put(y, map.getOrDefault(y,0)-1);
//        }


//
//        for(int val:map.values()){
//            if(val!=0){
//                return false;
//            }
//        }
        return true;
    }





    public static void main(String[] args) {
        String a="racecar";
        String b="carrace";
        //sorting
        System.out.println( isAnagramSorting(a,b));

        //hashing
        System.out.println( isAnagramHashing(a,b));


    }
}



