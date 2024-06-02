package algorithm.dp;

/**
 * https://leetcode.com/problems/house-robber/description/
 */
public class HouseRobbers {
    public int rob(int[] nums) {
        if(nums.length == 1)
            return nums[0];
        if(nums.length == 2)
            return Math.max(nums[0],nums[1]);
        int l1 = Math.max(nums[0], nums[1]);
        int l2 = nums[0] + nums[2];
        for(int i=3 ; i<nums.length;i++){
            l2 = Math.max(l1,l2);
            l1 += nums[i];
            int temp = l1;
            l1 = l2;
            l2 = temp;
        }
        return Math.max(l1,l2);
    }

    public static void main(String[] args) {
        HouseRobbers hr = new HouseRobbers();
        int[] nums = new int[]{2,7,9,3,1};
        System.out.println(hr.rob(nums));
    }
}
