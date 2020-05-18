package _111_minimumDepthofBinaryTree;

import basic.Pair;
import basic.TreeNode;

import java.util.Deque;
import java.util.LinkedList;


/**
 * 二叉树的最小深度
 *
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 *
 */
public class Solution {

    //广度优先遍历
    public int minDepth(TreeNode root) {
        int minDepth = 0;
        if (root == null)
            return minDepth;

        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = false;
        for (;!queue.isEmpty();) {
            int length = queue.size();
            minDepth++;
            for (int i = 0;i < length;i++) {
                TreeNode currNode = queue.poll();

                if (currNode.left == null && currNode.right == null) {
                    flag = true;
                    break;
                }

                if (currNode.right != null)
                    queue.add(currNode.right);
                if (currNode.left != null)
                    queue.add(currNode.left);
            }
            if (flag)
                break;
        }

        return minDepth;
    }

    //递归法(官方)
    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }


        if ((root.left == null) && (root.right == null)) {
            return 1;
        }

        //分别取左右子树的最小深度
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            //这两行都可以
//            min_depth = Math.min(minDepth2(root.left), min_depth);
            min_depth = minDepth2(root.left);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth2(root.right), min_depth);
            //这行不行因为有可能左子树最小深度被刷掉
//            min_depth = minDepth2(root.right);
        }

        return min_depth + 1;
    }

    //深度遍历(官方) 一层for循环  栈
    //每个节点绑定一个深度的参数，以此确保深度遍历的时候，深度的计算没有问题
    public int minDepth3(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        else {
            stack.add(new Pair<>(root, 1));
        }

        int min_depth = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.pollLast();
            root = current.getKey();
            int current_depth = current.getValue();
            if ((root.left == null) && (root.right == null)) {
                min_depth = Math.min(min_depth, current_depth);
            }
            if (root.left != null) {
                stack.add(new Pair<>(root.left, current_depth + 1));
            }
            if (root.right != null) {
                stack.add(new Pair<>(root.right, current_depth + 1));
            }
        }
        return min_depth;
    }

    //广度层序遍历(官方) 一层for循环  队列
    public int minDepth4(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        else {
            stack.add(new Pair<>(root, 1));
        }

        int current_depth = 0;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.poll();
            root = current.getKey();
            current_depth = current.getValue();
            if ((root.left == null) && (root.right == null)) {
                break;
            }
            if (root.left != null) {
                stack.add(new Pair<>(root.left, current_depth + 1));
            }
            if (root.right != null) {
                stack.add(new Pair<>(root.right, current_depth + 1));
            }
        }
        return current_depth;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode t1 = new TreeNode(3);
        TreeNode t21 = new TreeNode(9);
        TreeNode t22 = new TreeNode(20);
        TreeNode t31 = new TreeNode(15);
        TreeNode t32 = new TreeNode(7);
        t1.left = t21;
        t1.right = t22;
        t21.right = t31;
        t21.left = t32;

        System.out.println(solution.minDepth3(t1));

    }



}
