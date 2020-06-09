package leetcode._23_mergekSortedLists;

import leetcode.basic.ListNode;

/**
 * 合并K个排序链表
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 */
public class Solution {

    /*归并*/
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        return startMergeKLists(lists, 0, lists.length - 1);
    }
    private ListNode startMergeKLists(ListNode[] lists, int left, int right){
        if(left == right) return lists[left];
        int mid = (left + right) / 2;
        ListNode l1 = startMergeKLists(lists, left, mid);
        ListNode l2 = startMergeKLists(lists, mid + 1, right);
        return mergeLists(l1, l2);
    }
    private ListNode mergeLists(ListNode l1, ListNode l2){
        ListNode l3 = new ListNode(-1);
        ListNode temp = l3;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                temp.next = l1;
                l1 = l1.next;
            }else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = l1 != null ? l1 : l2;
        return l3.next;
    }


    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        int left = 0; int right = lists.length - 1;
        while(right > 0) {
            while(left < right) {
                lists[left] = merge(lists[left], lists[right]);
                left++;
                right--;
            }
            left = 0;
        }
        return lists[0];
    }

    private ListNode merge(ListNode node1, ListNode node2) {
        ListNode head = new ListNode(0);
        ListNode pre = head;
        while(node1 != null && node2 != null) {
            if(node1.val < node2.val){
                pre.next = node1;
                node1 = node1.next;
                pre = pre.next;
            } else {
                pre.next = node2;
                node2 = node2.next;
                pre = pre.next;
            }
        }
        pre.next = node1 == null ? node2 : node1;
        return head.next;
    }


    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];

        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(5);
        a1.next=a2;a2.next=a3;

        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(3);
        ListNode b3 = new ListNode(4);
        b1.next=b2;b2.next=b3;

        ListNode c1 = new ListNode(2);
        ListNode c2 = new ListNode(6);
        c1.next=c2;

        lists[0] = a1;
        lists[1] = b1;
        lists[2] = c1;
        Solution solution = new Solution();
        ListNode result = solution.mergeKLists2(lists);
        System.out.println("");
    }


}

