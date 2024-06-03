package algorithm.dp;

/**
 * https://neetcode.io/problems/decode-ways
 * https://www.youtube.com/watch?v=FEkZxCl_-ik
 * Make sure to create memoization array with n+1
 * Initialize index 0 to 1 to handle corner case
 * Initialize index 1 to 0 of current index value 0 else 1
 * At any given index,
 *      If current digit valid --> add 1 to current-1 index result
 *      If current + previous 2 digits valid --> add 1 to current-2 index result
 *  return index[n]
 */
public class DecodeWays {
    public int numDecodings(String s) {
        return 0;
    }
}
