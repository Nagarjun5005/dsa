package neetcode250.binarySearch.hard;

import java.util.Arrays;

/*
. ----Median of Two Sorted Arrays----
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
The overall run time complexity should be O(log (m+n)).

--Example 1:
Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

--Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 */
public class MedianOfTwoSortedArrays {

    public static double  medianNaive(int[] arr1, int[] arr2){
       int n1=arr1.length;
       int n2=arr2.length;
       int [] temp=new int[n1+n2];

       System.arraycopy(arr1,0,temp,0,n1);
       System.arraycopy(arr2,0,temp,n1,n2);


       Arrays.sort(temp);

        int totalLength=n1+n2;
        if(totalLength%2==1){//odd condition
            return temp[totalLength/2];
        }
        else{//even condition
            int mid1=temp[(totalLength/2)-1];
            int mid2=temp[totalLength/2];
            return (mid1 + mid2) / 2.0;

        }
    }

    public static void main(String[] args) {
        int [] arr1={1,2,3};
        int [] arr2={4,5,6};
        double medianNaive = medianNaive(arr1, arr2);
        System.out.println(medianNaive);
    }

}
