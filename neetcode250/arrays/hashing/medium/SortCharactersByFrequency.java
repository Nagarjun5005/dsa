package arrays.hashing.medium;

import java.util.*;

public class SortCharactersByFrequency {
    public static void main(String[] args) {
              String s ="nagarjun";
        System.out.println(frequencySortBrute(s));
        System.out.println(frequencySortBrute(s));
    }

    public static String frequencySortBrute(String s) {
        boolean [] visted=new boolean[s.length()];
        List<char[]> list = new ArrayList<>();
       for(int i=0;i<s.length();i++){
           if(visted[i]){
               continue;
           }
           int count=0;
           for(int j=0;j<s.length();j++){
               if(s.charAt(i)==s.charAt(j)){
                   count++;
                   visted[j]=true;
               }
           }
           list.add(new char[]{s.charAt(i), (char) count});

       }
        list.sort((a,b)->b[1]-a[1]);

        StringBuilder result=new StringBuilder();

        for (char [] pair:list){
            char ch=pair[0];
            int freq=pair[1];
            for(int i=0;i<freq;i++){
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static String frequencySort(String s) {
        Map<Character, Integer> count=new HashMap<>();
        List<Character>[] freq=new List[s.length()+1];

        //initialize the buckets
        for(int i=0;i<freq.length;i++){
            freq[i]=new ArrayList<>();
        }

        //count the frequencies
        for(int i =0;i<s.length();i++){
            count.put(s.charAt(i),count.getOrDefault(s.charAt(i),0)+1);
        }

        //lets form the bucket
        for(Map.Entry<Character, Integer> entry:count.entrySet()){
            freq[entry.getValue()].add(entry.getKey());
        }
        StringBuilder result=new StringBuilder();
        for(int i=freq.length-1;i>0 ;i--){
          for (Character c : freq[i]) {
              for(int j=0;j<i;j++){
                  result.append(c);
              }
            }

        }
        return result.toString();

    }
}
