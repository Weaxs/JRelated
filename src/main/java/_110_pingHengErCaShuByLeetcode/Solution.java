package _110_pingHengErCaShuByLeetcode;

import basic.TreeNode;

/**
 * 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 */
public class Solution {

    /**
     * 自顶向下的递归
     *
     * 定义方法 height，用于计算任意一个节点 的高度：
     * 比较每个节点左右子树的高度。在一棵以 r 为根节点的树
     * T 中，只有每个节点左右子树高度差不大于 1 时，该树才是平衡的。因此可以比较每个节点左右两棵子树的高度差，然后向上递归。
     *
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        //先判断根节点是否平衡 然后 判断左右子树
        return Math.abs(height(root.left) - height(root.right)) < 2
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    private int height(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public boolean isBalanced2(TreeNode root) {
        return isBalancedTreeHelper(root).balanced;
    }

    private TreeInfo isBalancedTreeHelper(TreeNode root) {
        if (root == null)
            return new TreeInfo(0, true);

        //balanced为flase时，height传多少都行。因为优先判断的是balanced
        TreeInfo left = isBalancedTreeHelper(root.left);
        if (!left.balanced)
            return new TreeInfo(0, false);

        TreeInfo right = isBalancedTreeHelper(root.right);
        if (!right.balanced)
            return new TreeInfo(0, false);

        if (Math.abs(left.height - right.height) >= 2)
            return new TreeInfo(0, false);

        return new TreeInfo(Math.max(left.height, right.height) + 1, true);
    }

    class TreeInfo {
        public final int height;
        public final boolean balanced;

        public TreeInfo(int height, boolean balanced) {
            this.balanced = balanced;
            this.height = height;
        }
    }

    public static void main(String[] args) {

    }
}
