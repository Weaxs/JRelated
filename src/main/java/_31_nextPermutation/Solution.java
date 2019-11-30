package _31_nextPermutation;

/**
 * 下一个排列
 *
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 */
public class Solution {

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        //找到从右往走的，第一个左边小于右边的数
        for (;i >= 0 && nums[i] >= nums[i + 1];i--);
        //i<0则表示次数组从左往右是递减的
        if (i >= 0) {
            int j = nums.length - 1;
            //找到从右往左的，最小的大于i的数，并将i与其交换
            for (;j >= i && nums[j] <= nums[i];j--) ;
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        //反转i之后的数
        for (int start = i+1,end = nums.length - 1; start < end; start++,end--) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,9};
        Solution solution = new Solution();
        solution.nextPermutation(nums);
        System.out.println();
    }

}
