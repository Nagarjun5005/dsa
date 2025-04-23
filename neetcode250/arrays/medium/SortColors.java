package neetcode250.arrays.medium;

import java.util.Arrays;

/*
Dutch National Flag Algorithm
 */

public class SortColors {
    /*
    Time complexity: O(nlogn)
    Space complexity :0(1) or 0(n)---->based on sorting method
     */
    public static void sortColortsBrute(int nums[]){
         Arrays.sort(nums);
    }
    /*
     Time Complexity :O(n) + O(n) = O(n)
     Space Complexity : You use a fixed array of size 3: int[] count = new int[3]
                        So Space = O(1) — constant space.

     */
    public static int [] sortColorsBetter(int nums[]){
        int [] count=new int[3];
        for (int num : nums) {
//            count[num]=count[num]+1;
            count[num]++;
        }
        int index=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<count[i];j++){
                nums[index]=i;
                index++;
            }
        }

        return nums;

    }


    /*
    Three Pointers approach ----in place sorting
    Dutch National Flag Algorithm
    Time: O(n) — one pass
    Space: O(1) — in-place
     */
    public static int [] sortColorsOptimal(int nums[]){
        int left=0;
        int right=nums.length-1;
        int i=0;
        while(i<=right){
            if(nums[i]==0){
                swap(nums,i,left);
                left++;
            } else if (nums[i]==2) {
                swap(nums,i,right);
                right--;
                i--;
            }
            i++;
        }

        return  nums;

    }

    private static void swap(int[] nums, int i, int j) {
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    public static void main(String[] args) {
        int nums[]={2,0,2,1,1,0};
        int[] sorted= sortColorsBetter(nums);
        System.out.println(Arrays.toString(sorted));

        int[] sortedOptimal = sortColorsOptimal(nums);
        System.out.println(Arrays.toString(sortedOptimal));
    }
}
