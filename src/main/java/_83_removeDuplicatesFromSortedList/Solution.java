package _83_removeDuplicatesFromSortedList;

import basic.ListNode;

/**
 * 删除排序链表中的重复元素
 *
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 */
public class Solution {

    public ListNode deleteDuplicates(ListNode head) {

        if (head == null)
            return null;
        ListNode pre = head;
        ListNode curr = head.next;

        for (;curr != null;) {
            if (curr.val == pre.val) {//需要删除
                pre.next =curr.next;
                curr.next = null;
                curr = pre.next;
            } else {//不用删除
                curr = curr.next;
                pre = pre.next;
            }
        }

        return head;
    }

    //官方
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                ListNode node = current.next;
                current.next = node.next;
                node.next = null;//清除野指针
//                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }


}
