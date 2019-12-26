package _39_combinationSum;

import java.util.*;

/**
 * 组合总和
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class Solution {

    private List<List<Integer>> res = new ArrayList<>();
    private int[] candidates;
    private int len;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return res;
        }
        //排序
        Arrays.sort(candidates);
        this.len = len;
        this.candidates = candidates;
        findCombinationSum(target, 0, new LinkedList<>());
        return res;
    }

    private void findCombinationSum(int residue, int start, Deque<Integer> pre) {
        //residue为0时表示目前的递归调用堆栈符合条件
        if (residue == 0) {
            res.add(new ArrayList<>(pre));
            return;
        }

        //循环数组中的每个元素，并以每个元素进行循环调用(以便调用到所有可能性
        //调用的时候residue减去candidates[i]的值作为条件来使递归结束
        //举个例子的话就递归调用链就犹如树状结构，一支一支地进行调用
        for (int i = start; i < len && residue - candidates[i] >= 0; i++) {
            pre.push(candidates[i]);

            findCombinationSum(residue - candidates[i], i, pre);
            pre.pop();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = {2,3,5};
        int target = 16;
        List<List<Integer>> result = solution.combinationSum(candidates, target);
        System.out.println(result.toString());
    }

}
