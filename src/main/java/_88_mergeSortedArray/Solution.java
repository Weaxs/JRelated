package _88_mergeSortedArray;

import java.util.Arrays;

/**
 * 合并两个有序数组
 *
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 *  
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 *
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 */
public class Solution {

    //方法一：使用封装好的类
    //时间复杂度 : O((n + m)\log(n + m))O((n+m)log(n+m))
    //空间复杂度 : O(1)O(1)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }

    //方法二：定义三个指针
    //时间复杂度 : O(n + m)O(n+m)
    //空间复杂度 : O(1)O(1)
    /**
     * p1为nums1的最后一个数的指针
     * p2为nums2的最后一个数的指针
     * p为合成后的nums1的最后一个指针
     *
     * 以此从后往前添加数(避免了加在前面之后需要将后面的数字往后移动的操作)
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;

        for (;p1 >= 0 && p2 >= 0;) {
            //如果nums2大，则把nums2放到最后，并对p2指针和p指针--
            //如果nums1大，则把nums1放到最后，并对p1指针和p指针--
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }

        //把nums2剩下的节点加到从0开始到p2,nums1的不需要这一步
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    public static void main(String args[]) {
        Solution solution = new Solution();

    }

}
