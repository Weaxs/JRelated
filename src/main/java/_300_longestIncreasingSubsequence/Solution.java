package _300_longestIncreasingSubsequence;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 *
 * 子序列不要求连续(子串要求连续)，但是它们必须保持在原始数组中的相对顺序不变
 *
 */
public class Solution {

    //1、动态规划 时间复杂度 O(N^2)  空间复杂度O(N)
    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        if (nums.length < 2)
            return length;

        int[]dp = new int[length];
        Arrays.fill(dp, 1);
        for (int i = 1;i < length;i++) {
            for (int j = 0; j < i;j++) {
                //当前面的数比当前数小时，比较dp[i]和dp[j] + 1 (dp[j] + 1不一定大于dp[i] ? )
                //例如：10 9 1 5 3 4 101 18 19 2 6
                //dp[]：1 1 1 2 2 3  4  4  5  2 4
                //当nums[i] = 6 nums[j] = 2时，dp[10] = 4   dp[9]+1= 3  此时dp[i]大于dp[j] + 1
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        //需要循环找最大的，因为最后一位并不一定最大
        //例如：10 9 2 5 3 7 101 18 4
        //dp[]：1 1 1 2 2 3  4  4  3    组合出的最长上升子序列是[2,3,4,18]  dp[]中的数可以表示替换最长子序列的哪一位置
        //这种用3替换5或者用18替换101意味着，替换后，更容易找到比3大/比18大的数来做子序列的末尾
        int res = 0;
        for (int i = 0; i < length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;

    }

    //2.相比普通动态规划，内部for循环的次数明显减少，  空间复杂度O(N)
    private int lengthOfLIS2(int[] nums) {
        int length = nums.length;
        if (nums.length < 2)
            return length;

        //用于存储这个上升子序列
        List<Integer> result = new ArrayList<>();
        result.add(nums[0]);
        for (int i = 1;i < length;i++) {
            //如果nums[i]比目前子序列的第一位还小，则直接替换
            if (nums[i] < result.get(0)) {
                result.set(0, nums[i]);
                break;
            }
            for (int j = result.size() - 1;j >= 0;j--) {
                //nums[i]比子序列第一位大，即意味着 替换子序列中的元素/新增到子序列尾部
                if (nums[i] > result.get(j)) {
                    //如果比子序列的最后一位大，则直接添加到尾部
                    if (j == result.size() - 1) {
                        result.add(nums[i]);
                    } else {
                        //否则循环到比子序列节点大的最大的位置，然后替换上一个位置
                        result.set(j + 1, nums[i]);
                    }
                    break;
                }
            }
        }

        return result.size();
    }

    //3.动态规划+二分查找  时间复杂度 O(NlogN) 空间复杂度O(N)
    private int lengthOfLIS3(int[] nums) {
        int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            //答题思路等同解法二，但内部for使用二分查找
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if(res == j) res++;
        }
        return res;
    }

    //4、贪心算法 + 二分
    private int lengthOfLIS4(int[] nums) {
        int n = nums.length;
        if (nums.length < 2)
            return n;

        //用于存储这个上升子序列
        int len = 1;
        List<Integer> result = new ArrayList<>();
        result.add(nums[0]);
        for (int i = 1;i < n; i++) {
            if (nums[i] > result.get(len - 1)) {
                result.add(nums[i]);
                len++;
            } else {
                //二分查找
                int l = 0, r = len; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l < r) {
                    int mid = (l + r)/2;
                    //去找后一半
                    if (result.get(mid) < nums[i]) {
//                        pos = mid;
                         l = mid + 1;
                    } else {
                        r = mid;
                    }
                }
                result.set(l,nums[i]);

            }
        }

        return len;

    }



    public static void main(String args[]) {
        Solution solution = new Solution();
        int[] nums = {10, 9, 1, 5, 3, 4, 101, 18, 19, 2, 6};
        System.out.println(solution.lengthOfLIS4(nums));
    }

}
