package leetcode._141_linkedListCycle;

import leetcode.basic.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表
 *
 * 给定一个链表，判断链表中是否有环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *
 */
public class Solution {

    /**
     * 使用set进行判重
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> nodeSet = new HashSet<>();
        while (head.next != null) {
            if (nodeSet.contains(head)) {
                return true;
            } else {
                nodeSet.add(head);
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 官方: 双指针
     * 设置快慢指针
     * 慢指针走一步，快指针走两步
     * 如果是环，快指针会追上慢指针
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode slow = head;
        ListNode fast = head.next;
        for (;slow != fast;slow = slow.next, fast = fast.next.next) {
            if (fast == null || fast.next == null)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next=listNode2;listNode2.next=listNode3;
        listNode3.next=listNode2;

        System.out.println(solution.hasCycle(listNode1));

    }

}
