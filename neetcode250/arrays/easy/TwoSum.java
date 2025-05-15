package neetcode250.arrays.easy;

import java.util.Arrays;

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

//    public static int [] twoSumBetter(int arr[],int target){
//
//    }
    public static void main(String[] args) {
        int arr[]={1,2,3,4,5};
        int[] twoSum = twoSumBrute(arr,7);
        System.out.println("two sum--->"+ Arrays.toString(twoSum));



    }
}
