package _40_combinationSumII;

import java.util.*;

/**
 * 组合总和 II
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 */
public class Solution {

    private List<List<Integer>> res = new ArrayList<>();
    private int[] candidates;
    private int len;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return res;
        }
        //排序
        Arrays.sort(candidates);
        this.len = len;
        this.candidates = candidates;
        findCombinationSum2(target, 0, new LinkedList<>());
        return res;
    }

    private void findCombinationSum2(int residue, int start, Deque<Integer> pre) {
        //residue为0时表示目前的递归调用堆栈符合条件
        if (residue == 0) {
            res.add(new ArrayList<>(pre));
            return;
        }

        //循环数组中的每个元素，并以每个元素进行循环调用(以便调用到所有可能性
        //调用的时候residue减去candidates[i]的值作为条件来使递归结束
        //举个例子的话就递归调用链就犹如树状结构，一支一支地进行调用
        for (int i = start; i < len && residue - candidates[i] >= 0; i++) {

            //{2,5,2,1,2}  5  排序后 {1,2,2,2,5}
            //此条件语句无法解决三个相同连在一起的去重
//            if (i != 0 && candidates[i - 1] == candidates[i] && (pre.peek() == null || pre.peek() != candidates[i - 1]))
//                continue;
            //[3,1,3,5,1,1] 8 排序后{1,1,1,3,3,5}
            //此条件语句会使 {3,5}{3,5}重复，第一个{3,5}后，第二3时 堆栈在最外层，故start = 0，i = 4，此时会把第二个{3,5}加进去
//            if(i > start && candidates[start] == candidates[i])

            //只要前一个数和当前数相同就跳过，以{1,1,1,3,3,5}为例
            //若此时pre栈是{1},加第二个1可以加上，加第三个1加不上，因为加第二个1时 i= start，调用第三个1时i > start
            if(i > start && candidates[i-1] == candidates[i])
                continue;

            pre.push(candidates[i]);

            //在题39基础上，把i换成i+1，以此避免会取同一个相同索引的数字
            findCombinationSum2(residue - candidates[i], i + 1, pre);
            pre.pop();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = {3,1,3,5,1,1};
        int target = 8;
        List<List<Integer>> result = solution.combinationSum2(candidates, target);
        System.out.println(result.toString());
    }

}
