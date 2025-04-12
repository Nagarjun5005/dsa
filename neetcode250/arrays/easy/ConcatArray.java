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

    /*Using System.copy
    syntax : System.arraycopy(sourceArray, sourcePosition, destinationArray, destinationPosition, length);
    Parameters
    sourceArray: The array from which elements are copied.
    sourcePosition: The starting index in the source array.
    destinationArray: The array to which elements are copied.
    destinationPosition: The starting index in the destination array
    length: The number of elements to be copied


     */
 public static int[] concat(int[] nums){
     int n=nums.length;
     int []result=new int[2*n];
     System.arraycopy(nums,0,result,0,nums.length);
     System.arraycopy(nums,0,result,nums.length,nums.length);

     return result;
 }
    public static void main(String[] args) {
        int[] arr={1,2,3};
        int[] concatenation = getConcatenation(arr);
        System.out.println(Arrays.toString(concatenation));
        int[] concat = concat(arr);
        System.out.println(Arrays.toString(concat));
    }
}
