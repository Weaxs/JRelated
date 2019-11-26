package _24_swapNodesInPairs;

import basic.ListNode;

/**
 * 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *  
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class Solution {

    /**
     * 递归法
     *
     * 由于递归是用从后往前交换的，后面交换号的节点已返回值的形式获得，故不需要存pre节点
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode next = head.next;
        //swapPairs(head.next.next)有可能返回null，此时如果不存next，那么head.next.next会报错
        head.next = swapPairs(head.next.next);
        next.next = head;
        return next;

    }

    /**
     * 正向从前往后交换法
     */
    public ListNode swapPairs2(ListNode head) {
        ListNode result = new ListNode(0);
        result.next = head;
        ListNode pre = result;
        for (;head != null && head.next != null;) {
            ListNode next = head.next;
            pre.next = next;
            head.next = next.next;
            next.next = head;
            //head已经next了一下，故此处也等于pre = pre.next.next
            pre = head;
            //交换完毕后的head已经next了一下  所以此处只需要再next一次即可
            head = head.next;
        }

        return result.next;
    }

    public static void main(String []args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(4);
        ListNode a4 = new ListNode(5);
        a1.next=a2;a2.next=a3;a3.next=a4;

        Solution solution = new Solution();
        ListNode result = solution.swapPairs2(a1);
        System.out.println("");
    }

}
