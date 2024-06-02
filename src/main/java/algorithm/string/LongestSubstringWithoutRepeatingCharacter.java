package algorithm.string;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacter {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcdd"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int[] charSeen = new int[256];
        int count = 0;
        int max = 0;
        int prev = -1;
        Arrays.fill(charSeen,-1);
        for (int i = 0; i < s.length(); i++) {
            prev = charSeen[s.charAt(i)];
            if (prev != -1 && prev >= i - count) {
                 max = Math.max(max,count);
                 count = i - prev;
            } else {
                count++;
            }
            charSeen[s.charAt(i)] = i;
        }
        max = Math.max(max,count);
        return max;
    }
}
