package _34_findFirstAndLastPositionOfElementInSortedArray;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 */
public class Solution {

    private int[] searchRange(int[] nums, int target) {
        int start = 0;
        int end = 0;
        //因为是顺序的，所以nums[i]>target时就跳出循环
        for (int i = 0;i < nums.length && nums[i] <= target; i++,end++) {
            if (nums[i] != target)
                start++;
        }
        int[] result = {start, end-1};
        int[] noSearch = {-1, -1};
        if (start > end - 1)
            return noSearch;
        else
            return result;

    }

    public static void main(String[] args) {
        int nums[] = {5,7,7,8,8,10};
        Solution solution = new Solution();
        int[] result = solution.searchRange(nums,13);
        System.out.println();
    }

}
