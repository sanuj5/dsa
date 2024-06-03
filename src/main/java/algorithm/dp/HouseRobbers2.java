package algorithm.dp;

import java.util.Arrays;

/**
 * https://neetcode.io/problems/house-robber-ii Iterate over array Maximum at n would be the max of
 * n-2 and n-3 Return max(n-1, n-2)
 */
public class HouseRobbers2 {
  public int rob(int[] nums) {
    return Math.max(nums[0], Math.max(
            helper(Arrays.copyOfRange(nums, 1, nums.length)),
            helper(Arrays.copyOfRange(nums, 0, nums.length - 1)))
    );
  }

  private int helper(int[] nums) {
    int rob1 = 0, rob2 = 0;

    for (int n : nums) {
      int newRob = Math.max(rob1 + n, rob2);
      rob1 = rob2;
      rob2 = newRob;
    }
    return rob2;
  }

  public static void main(String[] args) {
    HouseRobbers2 hr = new HouseRobbers2();
    int[] nums = new int[] {3,7,4,8,1,9,5,2,6,10};
    System.out.println(hr.rob(nums));
  }
}
