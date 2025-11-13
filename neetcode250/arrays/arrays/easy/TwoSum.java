package arrays.arrays.easy;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    public static  int [] twoSumBrute(int arr[],int sum){
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]+arr[j]==sum){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }

    public static int [] twoSumBetter(int arr[],int target){
        HashMap<Integer,Integer>map=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            int compliment=target-arr[i];
            if(map.containsKey(compliment)){
                return new int []{map.get(compliment),i};
            }
            map.put(arr[i],i);
        }
        return new int[0];
    }
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5};
        int[] twoSum = twoSumBrute(arr,7);
        System.out.println("two sum--->"+ Arrays.toString(twoSum));

        int [] twoSumHash=twoSumBetter(arr,7);
        System.out.println("hasing---->"+Arrays.toString(twoSumHash));



    }
}
