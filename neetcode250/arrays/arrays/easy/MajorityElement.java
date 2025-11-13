package arrays.arrays.easy;

import java.util.HashMap;

public class MajorityElement {


    /* Brute Force Approach
    Time complexity: O(n^2)
    Space complexity: O(1)
    */
    public static int majorityElement(int []nums){
        int n= nums.length;
        for (int num : nums) {
            int count = 0;
            for (int i : nums) {
                if (num == i) {
                    count++;
                }
            }
            if (count > n / 2) {
                return num;
            }
        }
        return -1;
    }

    /*----Hashing method
    Time complexity:  O(n)
    Space complexity: O(n)
     */

    public static int majorityElementHashing(int []nums){
        HashMap<Integer,Integer>map=new HashMap<>();
        int res=0;
        int maxCount=0;
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
            if(map.get(num)>maxCount){
                res=num;
                maxCount=map.get(num);
            }
        }
        return res;

    }/*
    Time complexity: O(n)
    Space complexity: O(1)
    */

    public static int majorityElementMoores(int []nums){
        int count=0;
        int el=0;
        for(int num:nums){
            if(count==0){
                el=num;
                count=1;
            } else if (num==el) {
                count++;
            }else {
                count--;
            }
        }
        return el;

    }


    public static void main(String[] args) {
        int[] nums={2,2,1,1,2,2};
        int majorityElement = majorityElement(nums);
        System.out.println("majority element-->"+majorityElement);


        int majorityElementHashing = majorityElementHashing(nums);
        System.out.println("majority element--->"+majorityElementHashing);

        int majorityElementMoores = majorityElementMoores(nums);
        System.out.println("majority element--->"+majorityElementMoores);
    }
}
