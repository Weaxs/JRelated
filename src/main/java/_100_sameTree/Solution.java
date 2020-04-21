package _100_sameTree;


import basic.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 相同的树
 *
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 示例 1:
 *
 * 输入:       1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *
 * 输出: true
 * 示例 2:
 *
 * 输入:      1          1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 *
 * 输出: false
 * 示例 3:
 *
 * 输入:       1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 *
 * 输出: false
 *
 */
public class Solution {

    //1.递归法
    //时间复杂度 : O(N)，其中 N 是树的结点数，因为每个结点都访问一次。
    //空间复杂度 : 最优情况（完全平衡二叉树）时为 O(log(N))，最坏情况下（完全不平衡二叉树）时为 O(N)，用于维护递归栈。
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;

        if (p == null || q == null)
            return false;

        if (p.val != q.val)
            return false;

        return (isSameTree(p.left, q.left)) && (isSameTree(p.right, q.right));
    }

    //2.循环使用队列
    //时间复杂度 : O(N)，其中 N 是树的结点数，因为每个结点都访问一次。
    //空间复杂度 : 最优情况（完全平衡二叉树）时为 O(\log(N))，最坏情况下（完全不平衡二叉树）时为 O(N)，用于维护双向队列
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (!check(p, q))
            return false;

        Deque<TreeNode> pQueue = new LinkedList<>();
        Deque<TreeNode> qQueue = new LinkedList<>();
        pQueue.add(p);
        qQueue.add(q);
        for (;!pQueue.isEmpty();) {
             p = pQueue.poll();
             q = qQueue.poll();

            if (p != null) {
                if (!check(p.right, q.right))
                    return false;
                if (p.right != null) {
                    pQueue.add(p.right);
                    qQueue.add(q.right);
                }

                if (!check(p.left, q.left))
                    return false;
                if (p.left != null) {
                    pQueue.add(p.left);
                    qQueue.add(q.left);

                }
            }

        }
        return true;

    }

    private boolean check(TreeNode p , TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.val != q.val)
            return false;

        return true;
    }

    //3.序列化，缺点是两个数都需要遍历，效率低
    public boolean isSameTree3(TreeNode p, TreeNode q) {

        String s1 = trace(p);
        String s2 = trace(q);
        return s1.equals(s2);
    }

    private static String trace(TreeNode root){
        if(root == null)
            return "#";
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        sb.append(trace(root.left));
        sb.append(trace(root.right));
        return sb.toString();
    }

    public static void main(String args[]) {
        TreeNode p1 = new TreeNode(1);
        TreeNode p2 = new TreeNode(2);
        p1.right = p2;
        TreeNode q1 = new TreeNode(1);
        TreeNode q2 = new TreeNode(2);
        q1.right = q2;
        Solution solution = new Solution();
        System.out.println(solution.isSameTree2(null, null));

    }
}
