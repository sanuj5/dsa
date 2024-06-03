package algorithm.dp;

/**
 * https://neetcode.io/problems/coin-change
 * For n in int 0 to amount+1 create array DP
 *      for each coin i
 *          min = coins[i] + DP[coins[n-i]]
 * return DP[n-1]
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        return 0;
    }
}
