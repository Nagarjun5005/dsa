package neetcode250.twoPointers.easy;

public class ValidPalindrome {


    public static boolean isPalindrome(String s ){
        //madam---madam is a palindrome
        StringBuilder reverse= new StringBuilder();
        for(int i=s.length()-1;i>=0;i--){
             reverse.append(s.charAt(i));
        }
        return reverse.toString().equals(s);
    }

    public static boolean isPalindromeTwoPointer(String s ){
       int left=0;
       int right=s.length()-1;
       while (left<right){
           if(s.charAt(left)!=s.charAt(right)){
               return false;
           }
           left++;
           right--;
       }
       return true;
    }

    public static boolean isPalindromeLeetCode(String s){
        int left=0;
        int right=s.length()-1;
        while(left<right){
            while(left<right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while(left<right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
                left++;
                right--;

        }
        return true;
    }


    public static void main(String[] args) {
       String palindrome="madam";
        boolean palindrome1 = isPalindrome(palindrome);
        System.out.println(palindrome1);

        boolean palindromeTwoPointer = isPalindromeTwoPointer(palindrome);
        System.out.println(palindromeTwoPointer);

        boolean palindrome2=isPalindromeLeetCode(palindrome);
        System.out.println(palindrome2);
    }

}
