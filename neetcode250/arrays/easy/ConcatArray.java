package neetcode250.arrays.easy;

import java.util.Arrays;

public class ConcatArray {
    public static int[] getConcatenation(int[] nums) {
        int n=nums.length;
        int result[]=new int [2*n];

        for(int i=0;i<n;i++){
            result[i]=nums[i];
            result[i+n]=nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr={1,2,3};
        int[] concatenation = getConcatenation(arr);
        System.out.println(Arrays.toString(concatenation));
    }
}
