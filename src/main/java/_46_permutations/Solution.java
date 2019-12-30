package _46_permutations;

import java.util.*;

/**
 * 全排列
 *
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 */
public class Solution {

    private List<List<Integer>> res = new ArrayList<>();

    /**
     * 递归调用
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return res;
        }
        findCombination(0, nums, new ArrayList<>());
        return res;
    }

    private void findCombination(int index, int[] nums, List<Integer> combaination) {
        if (index >= nums.length) {
            res.add(new ArrayList<>(combaination));
            return;
        }

        for (int num:nums) {
            //避免出现重复字符
            if (combaination.contains(num))
                continue;
            combaination.add(num);
            findCombination(index + 1, nums, combaination);
            combaination.remove(index);
        }
    }


    /**
     * 官方解答
     *
     * 回溯法
     * 是一种通过探索所有可能的候选解来找出所有的解的算法。如果候选解被确认 不是 一个解的话（或者至少不是 最后一个 解），回溯算法会通过在上一步进行一些变化抛弃该解，即 回溯 并且再次尝试。
     *
     * 这里有一个回溯函数，使用第一个整数的索引作为参数 backtrack(first)。
     *
     * 如果第一个整数有索引 n，意味着当前排列已完成。
     * 遍历索引 first 到索引 n - 1 的所有整数。Iterate over the integers from index first to index n - 1.
     * 在排列中放置第 i 个整数，
     * 即 swap(nums[first], nums[i]).
     * 继续生成从第 i 个整数开始的所有排列: backtrack(first + 1).
     * 现在回溯，即通过 swap(nums[first], nums[i]) 还原.
     *
     */
    public List<List<Integer>> permute2(int[] nums) {
        // init output list
        List<List<Integer>> output = new LinkedList<>();

        // convert nums into list since the output is a list of lists
        ArrayList<Integer> nums_lst = new ArrayList<Integer>();
        for (int num : nums)
            nums_lst.add(num);

        int n = nums.length;
        backtrack(n, nums_lst, output, 0);
        return output;
    }
    private void backtrack(int n,
                           ArrayList<Integer> nums,
                           List<List<Integer>> output,
                           int first) {
        // if all integers are used up
        if (first == n)
            output.add(new ArrayList<Integer>(nums));
        for (int i = first; i < n; i++) {
            // place i-th integer first
            // in the current permutation
            Collections.swap(nums, first, i);
            // use next integers to complete the permutations
            backtrack(n, nums, output, first + 1);
            // backtrack
            Collections.swap(nums, first, i);
        }
    }


    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Solution solution = new Solution();
        System.out.println(solution.permute(nums));
    }

}
