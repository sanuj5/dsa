package algorithm.dp;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] temp = new int[nums.length];
        int length = 0;
        for(int num: nums){
            int index = Arrays.binarySearch(temp, 0, length, num);
            if (index < 0){
                index = -(index+1);
            }
            temp[index] = num;
            if(index == length) length++;
        }
        return length;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(lis.lengthOfLIS(nums));
    }
}
