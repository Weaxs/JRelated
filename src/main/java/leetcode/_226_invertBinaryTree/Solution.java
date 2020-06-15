package leetcode._226_invertBinaryTree;

import leetcode.basic.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 反转二叉树/翻转二叉树
 *
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 */
public class Solution {

    //官方递归
    // 时间复杂度O(n)，空间复杂度O(n)
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    //官方 队列
    public TreeNode invertTree2(TreeNode root) {
        if (root == null)
            return null;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode tmp = current.left;
            current.left = current.right;
            current.right = tmp;
            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);
        }
        return root;
    }

}
