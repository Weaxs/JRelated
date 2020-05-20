package _112_pathSum;

import basic.Pair;
import basic.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 路径总和
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 */
public class Solution {

    //深度遍历
    public boolean hasPathSum(TreeNode root, int sum) {
        return pathSum(root, 0, sum);
    }

    private boolean pathSum(TreeNode currNode, int currSum, int sum) {

        if (currNode == null)
            return false;

        currSum += currNode.val;
        //叶子节点
        if (currNode.left == null && currNode.right == null)
            return currSum==sum;

        boolean ans = false;
        if (currNode.left != null)
            ans = ans || pathSum(currNode.left, currSum, sum);
        if (currNode.right != null)
            ans = ans || pathSum(currNode.right, currSum, sum);

        return ans;
    }
    //深度遍历(堆栈)
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null)
            return false;
        Deque<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        stack.push(new Pair<TreeNode, Integer>(root, root.val));

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> currNode = stack.poll();
            //叶子节点
            if (currNode.getKey().left == null && currNode.getKey().right == null)
                if (currNode.getValue() == sum)
                    return true;

            if (currNode.getKey().left != null)
                stack.push(new Pair<TreeNode, Integer>(currNode.getKey().left, currNode.getKey().left.val + currNode.getValue()));
            if (currNode.getKey().right != null)
                stack.push(new Pair<TreeNode, Integer>(currNode.getKey().right, currNode.getKey().right.val + currNode.getValue()));

        }

        return false;
    }

    //官方递归(通过对sum减
    public boolean hasPathSum3(TreeNode root, int sum) {
        if (root == null)
            return false;

        sum -= root.val;
        if ((root.left == null) && (root.right == null))
            return (sum == 0);
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    //官方迭代(通过对sum减
    public boolean hasPathSum4(TreeNode root, int sum) {
        if (root == null)
            return false;

        LinkedList<TreeNode> node_stack = new LinkedList<>();
        LinkedList<Integer> sum_stack = new LinkedList<>();
        node_stack.add(root);
        sum_stack.add(sum - root.val);

        TreeNode node;
        int curr_sum;
        while ( !node_stack.isEmpty() ) {
            node = node_stack.pollLast();
            curr_sum = sum_stack.pollLast();
            if ((node.right == null) && (node.left == null) && (curr_sum == 0))
                return true;

            if (node.right != null) {
                node_stack.add(node.right);
                sum_stack.add(curr_sum - node.right.val);
            }
            if (node.left != null) {
                node_stack.add(node.left);
                sum_stack.add(curr_sum - node.left.val);
            }
        }
        return false;
    }

    public static void main(String args[]) {
        TreeNode t1 = new TreeNode(5);
        TreeNode t21 = new TreeNode(4);
        TreeNode t22 = new TreeNode(8);
        TreeNode t311 = new TreeNode(11);
        TreeNode t321 = new TreeNode(13);
        TreeNode t322 = new TreeNode(4);
        TreeNode t4111 = new TreeNode(7);
        TreeNode t4112 = new TreeNode(2);
        TreeNode t4232 = new TreeNode(1);
        TreeNode t5 = new TreeNode(10);
//        t4232.left = t5;
//        t1.left = t21;t1.right = t22;t21.left = t311;t22.left = t321;t22.right = t322;t311.left = t4111;t311.right = t4112;t322.right = t4232;

        Solution solution = new Solution();
        System.out.println(solution.hasPathSum2(null, 5));

    }

}
