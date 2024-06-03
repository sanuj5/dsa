package algorithm.array;

/**
 * https://neetcode.io/problems/is-anagram
 * Approach 1
 * Iterate n times where n is length of both strings
 * If s1[i] != s2[n-i-1] --> return false
 * return true
 *
 * Approach 2
 * Define array of size 26 marking for each character
 * Iterate n times where n is length of both strings
 * for each character in string 1, add count
 * for each character in string 1, subtract count
 * Iterate over array and if count !=0 return false
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        return false;
    }
}
