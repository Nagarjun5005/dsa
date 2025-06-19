package neetcode250.arrays.medium;

public class TwoSumSorted {


    //using two pointer approach
    public static int [] twoSumSorted(int []nums,int target){
        int left=0;
        int right=nums.length-1;
        while(left<right){
            int sum=nums[left]+nums[right];
            if(sum==target){
                return new int[]{left+1,right+1};
            }
            else if(sum<target){
                left++;
            }
            else{
                right--;
            }
        }
        return new int[0];
    }


    public static void main(String[] args) {
        int arr[]={0,2,3,4,5,6};
        int target=7;
        int[] ints = twoSumSorted(arr, target);
        System.out.println(ints[0]+","+ints[1]);

    }
}
