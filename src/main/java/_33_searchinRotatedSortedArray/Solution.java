package _33_searchinRotatedSortedArray;

/**
 * 搜索旋转排序数组
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 */
public class Solution {

    public int search(int[] nums, int target) {
        int first = 0;
        int last = nums.length - 1;
        if (nums.length < 1) return -1;
        return search(first, last, nums, target);
    }

    private int search(int first, int last, int[] nums, int target) {
        int mid = (first + last) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (last < first)
            return -1;
        //开始到中间是升序,例如4,5,6,7|,8,0,1,2
        if (nums[mid] >= nums[first]) {
            if (target >= nums[first] && target < nums[mid]) {
                return search(first, mid - 1, nums, target);
            } else {
                return search(mid + 1, last, nums, target);
            }
        }
        //first到mid某个位置升序，之后断开升序，例如4,5,0,1|,2,3
        else {
            if (target <= nums[last] && target > nums[mid]) {
                return search(mid + 1, last, nums, target);
            } else {
                return search(first, mid - 1, nums, target);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {3};
        Solution solution = new Solution();
        System.out.println(solution.search(nums, 3));
    }
}

