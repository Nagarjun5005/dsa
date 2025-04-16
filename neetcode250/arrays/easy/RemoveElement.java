package neetcode250.arrays.easy;

import java.util.ArrayList;
import java.util.List;

public class RemoveElement {

    /*
----Brute Force Approach
----Time & Space Complexity
Time complexity: O(n)
Space complexity: O(n)
     */
    public static int  removeElementBrute(int [] nums,int val){
        List<Integer>temp=new ArrayList<>();
        for(int n:nums){
            if(n!=val){
                temp.add(n);
            }
        }
        for(int i=0;i<temp.size();i++){
            nums[i]=temp.get(i);
        }

        return temp.size();

    }

    /*
     -----Two Pointer Apprach
    Time complexity: O(n)
    Space complexity: O(1)
     */

    public static int  removeElementOptimal(int [] nums,int val){
    int k=0;
    for(int i=0;i<nums.length;i++){
        if(nums[i]!=val){
            nums[k++]=nums[i];
        }
    }
    return k;

    }

    public static void main(String[] args) {
       int[] nums={1,2,2,3,3};
       int val=3;
        int removeElements = removeElementBrute(nums, val);
        System.out.println("elements remaining-->"+removeElements);


        int removeElementOptimal= removeElementOptimal(nums, val);
        System.out.println("elements remaining-->"+removeElementOptimal);
    }
}
