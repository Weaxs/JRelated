package _53_maximumSubarray;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 */
public class Solution {

    /**
     * 贪心算法
     * 前几个数的最大值+当前数，如果比当前数还小，那不加，反之加
     * 以输入样例为例：
     * 1.比较1 和 -2 + 1 ,1 大                       累计最大maxSum：1
     * 2.比较 -3 和 -3 + 1， -3 + 1 大               累计最大maxSum：1
     * 3.比较 4 和-3 + 1 + 4, 4 大                  累计最大maxSum：4
     * 4.比较 -1 和 4 + -1, 4 + -1 大               累计最大maxSum：4
     * 5.比较 1 和 4 + -1 + 1, 4 + -1 + 1 大        累计最大maxSum：4
     * 6.比较 2 和 4 + -1 + 1 + 2, 4 + -1 + 1 + 2 大    累计最大maxSum：6
     * 7.比较 -5 和 4 + -1 + 1 + 2 + -5, 4 + -1 + 1 + 2 + -5 大     累计最大maxSum：6
     * 8.比较 4 和 4 + -1 + 1 + 2 + -5 + 4， 4 + -1 + 1 + 2 + -5 + 4大  累计最大maxSum：6
     */
    public int maxSubArray(int[] nums) {
        int currSum = nums[0], maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(nums[i],nums[i] + currSum);
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }

    /**
     * 动态规划
     *
     * 原则是前一个/前几个数的最大值小于零，则不加
     *
     * 以输入样例为例：
     * 1.nums[1]不变
     * 2.nums[2] = 1 + -3 = -2
     * 3.nums[3]不变
     * 4.nums[4] = 4 + -1 = 3
     * 5.nums[5] = 3 + 2 = 5
     * 6.nums[6] = 5 + 1 = 6
     * 7.nums[7] = -5 + 6 = 1
     * 8.nums[8] = 1 + 4 = 5
     */
    private int maxSubArray2(int[] nums) {
        int n = nums.length, maxSum = nums[0];
        for(int i = 1; i < n; ++i) {
            if (nums[i - 1] > 0)
                nums[i] += nums[i - 1];
            maxSum = Math.max(nums[i], maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(solution.maxSubArray2(nums));
    }

}
