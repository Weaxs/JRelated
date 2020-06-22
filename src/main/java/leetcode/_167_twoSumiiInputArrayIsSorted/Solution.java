package leetcode._167_twoSumiiInputArrayIsSorted;

/**
 *  两数之和 II - 输入有序数组
 *
 *  给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 *
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 *
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 *
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2
 *
 */
public class Solution {

    //双指针法
    //时间复杂度：O(n)  每个元素最多被访问一次，共有 n 个元素
    //空间复杂度：O(1)  只是用了两个指针
    public int[] twoSum(int[] numbers, int target) {

        for (int p = 0, q = numbers.length - 1;p < q;) {
            int sum = numbers[p] + numbers[q];
            if (sum < target) {
                p++;
            } else if (sum > target) {
                q--;
            } else {
                return new int[]{p + 1, q + 1};
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] numbers = {2, 7, 11, 15};
        int[] ans = solution.twoSum(numbers, 9);
        System.out.println();


    }

}
