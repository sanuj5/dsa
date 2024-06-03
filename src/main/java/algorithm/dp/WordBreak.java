package algorithm.dp;

/**
 * https://neetcode.io/problems/word-break
 * Create array DP for memoization
 * Start from end for each char
 *      For each word
 *          check if current char + word length == word
 *              then dp[i] = dp[current + previous]
 *  return dp[0]
 */
public class WordBreak {
}
