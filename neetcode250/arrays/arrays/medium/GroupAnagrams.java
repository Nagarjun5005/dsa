package arrays.arrays.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {


    /* Using Sorting And Hashing
      Time complexity : O(m∗nlogn)
      Space Complexity : 0(m*n)
     */

    public static  List<List<String>> groupAnagramsBrute(String[] strs) {
         //create a hashmap ---key =string word ,value =list[words]
        HashMap<String,List<String>>map=new HashMap<>();

        for(String word:strs){
            //convert to char array
            char[] chars=word.toCharArray();
            //sort it
            Arrays.sort(chars);
            //convert it back to string
            String sortedWord=new String(chars);

            if(!map.containsKey(sortedWord)){
                map.put(sortedWord,new ArrayList<>());
            }
            map.get(sortedWord).add(word);
        }
        return new ArrayList<>(map.values());
    }

    /*Using hashing only
       Time complexity : O(m∗n)
       space complexity : 0(m*n)
     */
    public static  List<List<String>> groupAnagramsOptimal(String[] strs) {
        HashMap<String,List<String>>map=new HashMap<>();
        for(String s: strs){
            int []count=new int[26];
            for(char c:s.toCharArray()){
                count[c-'a']++;
            }
            String key=Arrays.toString(count);
            map.putIfAbsent(key,new ArrayList<>());
            map.get(key).add(s);

        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {

        //brute
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = groupAnagramsBrute(input);
        System.out.println(result);

        //optimal
        List<List<String>> lists = groupAnagramsOptimal(input);
        System.out.println(lists);
    }
}



/*

// Brute alternative
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedS = new String(charArray);
            res.putIfAbsent(sortedS, new ArrayList<>());
            res.get(sortedS).add(s);
        }
        return new ArrayList<>(res.values());
    }
}



Sure! Let's walk through one iteration of this groupAnagrams method using an example input. Suppose the input is:


String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
We'll walk through how the code handles the first element, "eat".

Initial state:
res is an empty HashMap: {}

Iteration 1: s = "eat"
Initialize a count array of 26 zeros:


int[] count = new int[26];
// count = [0, 0, 0, ..., 0]  (length 26)
Populate count array with character frequencies of "eat":

'e' → count[4]++ → now count[4] = 1

'a' → count[0]++ → now count[0] = 1

't' → count[19]++ → now count[19] = 1

Final count array:


count = [1, 0, 0, 0, 1, 0, ..., 0, 1, 0, ..., 0]
        // 'a' at index 0 = 1, 'e' at index 4 = 1, 't' at index 19 = 1
Convert count to a string key:


String key = Arrays.toString(count);
// key = "[1, 0, 0, 0, 1, 0, 0, ..., 0, 1, 0, ..., 0]"
Use the key to group anagrams:

Since the key doesn't exist yet in res, create a new list:


res.putIfAbsent(key, new ArrayList<>());
res.get(key).add("eat");
Now, the map res looks like:


{
  "[1, 0, 0, 0, 1, 0, ..., 0, 1, 0, ..., 0]": ["eat"]
}

 */
