package _108_convertSortedArrayToBinarySearchTree;

import basic.TreeNode;

import java.util.Random;

/**
 * 将有序数组转换为二叉搜索树
 *
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 */
public class Solution {

    //中序遍历
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(0, nums.length - 1, nums);
    }

    private TreeNode helper(int left,int right, int[] nums) {
        if (left > right)
            return null;

        //选左边
        int p = (left + right) / 2;

        //(left + right) % 2 == 1表示数组数为偶数个(中间的元素有两个)
//        //选右边
//        if ((left + right) % 2 == 1) p += ++p;
        //中间左右随机一个作为根节点
        Random rand = new Random();
        if ((left + right) % 2 == 1) p += rand.nextInt(2);

        TreeNode root = new TreeNode(nums[p]);
        root.left = helper(left, p - 1, nums);
        root.right = helper(p + 1, right, nums);
        return root;
    }

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        Solution solution = new Solution();
        solution.sortedArrayToBST(nums);
        System.out.println();
    }

}
