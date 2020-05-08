package _104_maximumDepthOfBinaryTree;

import basic.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 */
public class Solution {

    //队列
    public int maxDepth2(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        if (root != null)
            queue.add(root);
        int maxDepth = 0;
        for (;!queue.isEmpty();) {
            int size = queue.size();
            for (int i = 0;i < size; i++) {
                TreeNode tmp = queue.poll();
                if (tmp.left != null)
                    queue.add(tmp.left);
                if (tmp.right != null)
                    queue.add(tmp.right);
            }
            maxDepth++;
        }
        return maxDepth;
    }

    //递归
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left_height = maxDepth(root.left);
            int right_height = maxDepth(root.right);
            return java.lang.Math.max(left_height, right_height) + 1;
        }

    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode t1 = new TreeNode(1);
        TreeNode t21 = new TreeNode(2);
        TreeNode t22 = new TreeNode(2);
        TreeNode t31 = new TreeNode(3);
        TreeNode t32 = new TreeNode(3);
        t1.left = t21;
        t1.right = t22;
        t21.right = t31;
        t22.left = t32;
        System.out.println(solution.maxDepth(t1));
    }


}
