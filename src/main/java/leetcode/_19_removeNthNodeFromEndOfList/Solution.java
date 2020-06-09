package leetcode._19_removeNthNodeFromEndOfList;


import leetcode.basic.ListNode;

/**
 * 删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 */
public class Solution {

    /**
     * 存一个目标位置和当前位置
     * 并记录目标位置的前链节点
     * 在当前位置即将遍历完毕时，将此时的目标节点清空掉
     *
     * 注意：如果删除的是头节点，则直接放回head.next，如果仅有一个节点，则返回null
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head;
//        leetcode.basic.ListNode post = head.next;
        ListNode current = head;
        for (int targetPos = 1,currentPos = 1;current != null;currentPos++,current = current.next) {
            if (currentPos - targetPos == n - 1) {
                if (current.next != null) {
                    if (targetPos > 1) pre = pre.next;
//                    post = post.next;
                    targetPos++;
                } else {
                    if (pre == null) return null;//仅有一个节点的情况
//                    if (targetPos == 1) return post;//删除的是头结点的情况
                    if (targetPos == 1) return head.next;//删除的是头结点的情况
                    pre.next = pre.next.next;
                    break;
                }
            }
        }
        return head;
    }

    /**
     * 先将一个节点遍历到n+1的位置
     * 之后和另一个节点一起进行遍历
     */
    private ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        //0
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);

        a.next = b;
        b.next = c;c.next = d;d.next = e;
        a = solution.removeNthFromEnd2(a, 4);
        System.out.println("");
    }

}

