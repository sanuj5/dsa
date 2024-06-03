package algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://neetcode.io/problems/two-integer-sum
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            Integer temp = map.get(target - nums[i]);
            if(temp != null){
                return new int[]{temp,i};
            }
            map.put(nums[i],i);
        }
        return null;
    }

    public static void main(String[] args) {
        TwoSum ts = new TwoSum();
        int[] nums = new int[]{3,3};
        int[] result = ts.twoSum(nums,6);
        if(result != null){
            for(int num: result){
                System.out.println(num);
            }
        }
    }
}
