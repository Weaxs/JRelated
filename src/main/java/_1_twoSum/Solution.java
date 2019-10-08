package _1_twoSum;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("没有符合目标值的两个数！");
    }


    public static void main(String args[]) {
        Solution solution = new Solution();
        int[] nums = {2,3,4,5,6,7,2,12,13,12};
        int[] twoNum = solution.twoSum(nums, 99);

    }

}
