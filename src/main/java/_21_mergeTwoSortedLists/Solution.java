package _21_mergeTwoSortedLists;


import basic.ListNode;

/**
 * 合并两个有序链表
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 */
public class Solution {

    //迭代法
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        ListNode res = l3;
        for(;l1 != null && l2 != null;) {
            if (l1.val < l2.val) {
                l3.next = l1;
                l3 = l3.next;
                l3.val = l1.val;
                l1 = l1.next;
            } else {
                l3.next = l2;
                l3 = l3.next;
                l3.val = l2.val;
                l2 = l2.next;
            }
        }
        l3.next = (l1 == null ? l2 : l1);

        return res.next;
    }

    public static void main(String args[]) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(4);
        ListNode b4 = new ListNode(5);
        a1.next=a2;a2.next=a3;a3.next=b4;

        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(3);
        ListNode b3 = new ListNode(4);

        b1.next=b2;b2.next=b3;

        Solution solution = new Solution();
        ListNode res = solution.mergeTwoLists(a1, b1);
        System.out.println("");
    }

    //递归法
    private ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }


}

