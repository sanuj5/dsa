package algorithm.array;

import java.util.Arrays;

/*
https://leetcode.com/problems/bag-of-tokens/
 */
public class BagOfTokens {
    public int bagOfTokensScore(int[] tokens, int power) {
        int i = 0;
        int j = tokens.length-1;
        int score = 0;
        Arrays.sort(tokens);
        while(i<=j){
            if(tokens[i] <= power){
                score++;
                power -= tokens[i];
                i++;
                continue;
            }
            if(score > 0 && i<j){
                power += tokens[j];
                score--;
            }
            j--;
        }
        return score;
    }

    public static void main(String[] args) {
        BagOfTokens bot = new BagOfTokens();
        int[] tokens = new int[]{100};
        System.out.println(bot.bagOfTokensScore(tokens, 50));
    }
}
