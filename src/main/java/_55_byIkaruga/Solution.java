package _55_byIkaruga;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class Solution {

    /**
     * 方法一：循环遍历每个元素(最简单的方法
     * k中存的是当前元素的前面所有元素能到达的最远位置
     * 所以当i > k时，表示前面的元素到达不了当前节点的位置
     * 如果能到达，则比较此节点位置+节点代表的最大长度 和 前面的节点到达的最高位置
     * 哪个大则更新k
     */
    public boolean canJump(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++)
        {
            if (i > k) return false;
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }


    public static void main(String args[]) {
        Solution solution = new Solution();
        int [] a = {3,2,1,0,4};
        System.out.println(solution.canJump(a));
    }



}
