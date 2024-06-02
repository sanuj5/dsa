package algorithm.string;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("baaabaaa"));
    }

    public static String longestPalindrome(String s) {
        int max =0;
        String maxString = new String();
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                if(isPalindorme(s.substring(i,j+1)) && j-i+1> max){
                    maxString = s.substring(i,j+1);
                    max = j-i;
                }
            }
        }
        return maxString;
    }
    private static boolean isPalindorme(String s){
        for(int i=0;i<s.length()/2;i++){
            if(s.charAt(i) != s.charAt(s.length()-i-1)){
                return false;
            }
        }
        return  true;
    }
}
