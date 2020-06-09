package leetcode._2_addTwoNumbers;

import leetcode.basic.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 两数相加
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    /**
     * 同时遍历l1,l2链表，取值进行相加
     * 并添加一个进位标志carray
     *
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        List<ListNode> resultList = new ArrayList<ListNode>();

        for (;l1 != null || l2 != null;) {
            if (l1 == null) l1 = new ListNode(0);
            if (l2 == null) l2 = new ListNode(0);

            ListNode node = new ListNode(0);
            if (l1.val + l2.val + carry  > 9) {
                node.val = l1.val + l2.val + carry - 10;
                carry = 1;
            } else {
                node.val = l1.val + l2.val + carry;
                carry = 0;
            }
            resultList.add(node);

            l1 = l1.next;
            l2 = l2.next;

        }
        for (int i = 0; i < resultList.size() - 1; i++) {
            resultList.get(i).next = resultList.get(i + 1);
        }
        if (carry == 1)
            resultList.get(resultList.size() - 1).next = new ListNode(1);

        return resultList.get(0);
    }

    public static void main(String[] args) {
        ListNode l1head = new ListNode(0);
        ListNode l1cur = l1head;
        l1cur.next = new ListNode(1);
        l1cur = l1cur.next;
        l1cur.next = new ListNode(2);
        l1cur = l1cur.next;
        l1cur.next = new ListNode(3);

        ListNode l2head = new ListNode(4);
        ListNode l2cur = l1head;
        l2cur.next = new ListNode(3);
        l2cur = l2cur.next;
        l2cur.next = new ListNode(2);
        l2cur = l2cur.next;
        l2cur.next = new ListNode(1);

        Solution solution = new Solution();
        ListNode result = solution.addTwoNumbers(l1head, l2head);
        ListNode result2 = solution.addTwoNumbers2(l1head, l2head);
    }

    /**
     * 官方答案
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

}

