package _47_permutationsII;

import java.util.*;

public class Solution {

    private List<List<Integer>> res = new ArrayList<>();
    private boolean[] used;

    /**
     * 递归调用
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        //需要排序！！因为判断的是used[i]和used[i-1]，如果数组为3 1 3就会出错，故必须排序
        Arrays.sort(nums);
        used = new boolean[len];
        findCombination(0, nums, new ArrayList<>());
        return res;
    }

    /**
     *
     *
     */
    private void findCombination(int index, int[] nums, List<Integer> combaination) {
        if (index >= nums.length) {
            res.add(new ArrayList<>(combaination));
            return;
        }

        for (int i = 0; i < nums.length;i++) {
            //
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]))
                continue;
            used[i] = true;
            combaination.add(nums[i]);
            findCombination(index + 1, nums, combaination);
            combaination.remove(index);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1};
        Solution solution = new Solution();
        System.out.println(solution.permuteUnique(nums));
    }

}
