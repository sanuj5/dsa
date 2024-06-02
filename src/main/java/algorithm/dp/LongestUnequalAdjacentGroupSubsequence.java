package algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestUnequalAdjacentGroupSubsequence {
    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        if(n == 1){
            return Arrays.asList(words);
        }
        List<String> subsequence = new ArrayList<>();
        int prev = -1;
        String prevWord = "";
        String lastMatch = "";
        for(int i=0;i<groups.length;i++){
            if(groups[i] != prev && hammingDistance(prevWord, words[i])){
                subsequence.add(prevWord);
                lastMatch = words[i];
            }
            else if (!lastMatch.equals("")){
                subsequence.add(lastMatch);
                lastMatch = "";
            }
            prev = groups[i];
            prevWord = words[i];

        }
        if(!lastMatch.equals("")){
            subsequence.add(lastMatch);
        }
        if(subsequence.isEmpty()){
            subsequence.add(words[n-1]);
        }
        return subsequence;
    }

    private boolean hammingDistance(String prevWord, String word) {
        if(prevWord.length() != word.length())
            return false;
        int distance = 0;
        for(int i=0;i<prevWord.length();i++){
            if(prevWord.charAt(i) != word.charAt(i)) {
                if (++distance > 1)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LongestUnequalAdjacentGroupSubsequence luags = new LongestUnequalAdjacentGroupSubsequence();
        String[] words = new String[]{"ccd","bb","ccc"};
        int[] groups = new int[]{1,1,2};
        System.out.println(luags.getWordsInLongestSubsequence(3, words, groups));
    }
}
