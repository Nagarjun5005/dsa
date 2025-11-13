package arrays.twoPointers.easy;
/*
In data structures and algorithms (DSA), the two‑pointers (or “two indices”) technique is a pattern where you maintain
two indices (or references) into a data structure—most often an array or a linked list—and move them according to some
rules to achieve an efficient solution.
 */

import java.util.Arrays;

public class ReverseString {

    public static char [] reverseString(char[] s){
        //['h','e','l','l','o'];----->
           int i=0;
           int j=s.length-1;
           while(i<j){
               char temp=s[i];
               s[i]=s[j];
               s[j]=temp;
               i++;
               j--;
           }

        return s;
    }

    public static void main(String[] args) {
        char[] data={'h','e','l','l','o'};
        char[] data1 = reverseString(data);
        System.out.println(data1);
    }

}
