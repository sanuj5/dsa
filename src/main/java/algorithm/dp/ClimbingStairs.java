package algorithm.dp;

/**
 * https://neetcode.io/problems/climbing-stairs
 * DP to use previous result from end
 * For n-3rd stair, it will take result(n-2) + result(n-1) combinations to reach to n-1
 * For n-4rd stair, it will take result(n-3) + result(n-2) combinations to reach to n-1
 * This is classical fibonnaci series where just add previous two numbers to current
 * return current number
 */
public class ClimbingStairs {
}
