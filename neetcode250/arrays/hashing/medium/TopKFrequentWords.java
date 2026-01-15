package arrays.hashing.medium;

import java.util.*;

/*
 =============================================================================
 PROBLEM:
 Top K Frequent Words

 Given an array of strings "words" and an integer k, return the k most frequent
 words.

 Rules:
 - Words with higher frequency come first
 - If two words have the same frequency, the word with lower
   lexicographical (alphabetical) order comes first

 Example:
 Input:
   words = ["i","love","leetcode","i","love","coding"]
   k = 2

 Output:
   ["i","love"]
 =============================================================================
*/

class TopKFrequentWords
{

    /*
     =========================================================================
     APPROACH 1: BRUTE FORCE (NESTED LOOPS + SORTING)

     IDEA:
     - Traverse the array
     - For each word, count how many times it appears using another loop
     - Use a boolean[] visited array to avoid recounting the same word
     - Store each unique word with its frequency
     - Sort based on:
         1) frequency (descending)
         2) lexicographical order (ascending) if frequencies are equal
     - Pick first k words

     DATA STRUCTURES:
     - boolean[] visited → to avoid double counting
     - List<WordFreq> → stores (word, frequency)

     TIME COMPLEXITY:
     - Counting using nested loops: O(n²)
     - Sorting unique words: O(u log u), u = number of unique words
     - Overall: O(n²)

     SPACE COMPLEXITY:
     - visited array + list: O(n)
     =========================================================================
    */
    public List<String> topKFrequentBruteForce(String[] words, int k) {

        boolean[] visited = new boolean[words.length];
        List<WordFreq> list = new ArrayList<>();

        // Count frequencies using nested loops
        for (int i = 0; i < words.length; i++) {

            if (visited[i]) {
                continue;
            }

            int count = 0;
            for (int j = 0; j < words.length; j++) {
                if (words[i].equals(words[j])) {
                    count++;
                    visited[j] = true;
                }
            }

            list.add(new WordFreq(words[i], count));
        }

        /*
         Sort rules:
         - Higher frequency first
         - If same frequency, alphabetical order
        */
        list.sort((a, b) -> {
            if (a.freq != b.freq) {
                return b.freq - a.freq;
            }
            return a.word.compareTo(b.word);
        });

        // Collect top k words
        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(list.get(i).word);
        }

        return result;
    }

    /*
     Helper class to store word-frequency pair
    */
    static class WordFreq {
        String word;
        int freq;

        WordFreq(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }
    }

    /*
     =========================================================================
     APPROACH 2: BUCKET SORT (OPTIMAL FREQUENCY-BASED SOLUTION)

     IDEA:
     - Count frequency of each word using HashMap
     - Create a bucket array where:
         index = frequency
         value = list of words with that frequency
     - Sort words inside each bucket lexicographically
     - Traverse buckets from highest frequency to lowest
     - Collect first k words

     WHY BUCKET SORT?
     - Maximum frequency is bounded by words.length
     - Avoids sorting all words globally

     TIME COMPLEXITY:
     - Frequency counting: O(n)
     - Bucket fill: O(n)
     - Sorting buckets: O(n log n) worst-case
     - Overall: O(n log n)

     SPACE COMPLEXITY:
     - HashMap + bucket array: O(n)
     =========================================================================
    */
    public List<String> topKFrequentBucketSort(String[] words, int k) {

        // Step 1: Frequency map
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        /*
         Step 2: Bucket array
         freq[i] = list of words appearing exactly i times
        */
        List<String>[] freq = new List[words.length + 1];
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        // Step 3: Place words into buckets
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            freq[entry.getValue()].add(entry.getKey());
        }

        // Step 4: Sort each bucket lexicographically
        for (int i = 0; i < freq.length; i++) {
            Collections.sort(freq[i]);
        }

        // Step 5: Collect top k frequent words
        List<String> result = new ArrayList<>();

        for (int i = freq.length - 1; i > 0 && result.size() < k; i--) {
            for (String word : freq[i]) {
                result.add(word);
                if (result.size() == k) {
                    return result;
                }
            }
        }

        return result;
    }

    /*
     =========================================================================
     MAIN METHOD (TESTING BOTH APPROACHES)
     =========================================================================
    */
    public static void main(String[] args) {

        TopKFrequentWords solution = new TopKFrequentWords();

        String[] words = {
                "i", "love", "leetcode", "i", "love", "coding"
        };
        int k = 2;

        System.out.println("Brute Force Result:");
        System.out.println(solution.topKFrequentBruteForce(words, k));

        System.out.println("\nBucket Sort Result:");
        System.out.println(solution.topKFrequentBucketSort(words, k));
    }
}

