package algorithm.dp;

/**
 * https://leetcode.com/problems/house-robber/description/ Iterate over array Maximum at n would be
 * the max of n-2 and n-3 Return max(n-1, n-2)
 */
public class HouseRobbers {
  public int rob(int[] nums) {
    int rob1 = 0, rob2 = 0;

    for (int n : nums) {
      int temp = Math.max(n + rob1, rob2);
      rob1 = rob2;
      rob2 = temp;
    }
    return rob2;
  }

  public static void main(String[] args) {
    HouseRobbers hr = new HouseRobbers();
    int[] nums = new int[] {2, 7, 9, 3, 1};
    System.out.println(hr.rob(nums));
  }
}
