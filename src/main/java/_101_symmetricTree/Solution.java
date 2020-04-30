package _101_symmetricTree;

import basic.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 对称二叉树
 *
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *  
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *  
 *
 * 进阶：
 *
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 *
 */
public class Solution {

    //递归
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode treeNode1, TreeNode treeNode2) {
        if (treeNode1 == null && treeNode2 == null)
            return true;
        if (treeNode1 == null || treeNode2 == null)
            return false;

        return treeNode1.val == treeNode2.val
                && isMirror(treeNode1.left, treeNode2.right)
                && isMirror(treeNode1.right, treeNode2.left);
    }

    //迭代队列
    public boolean isSymmetric2(TreeNode root) {
        Deque<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t21 = new TreeNode(2);
        TreeNode t22 = new TreeNode(2);
        TreeNode t31 = new TreeNode(3);
        TreeNode t32 = new TreeNode(3);
        t1.left = t21;
        t1.right = t22;
        t21.right = t31;
        t22.left = t32;
        Solution solution = new Solution();
        System.out.println(solution.isSymmetric2(t1));

    }

}
