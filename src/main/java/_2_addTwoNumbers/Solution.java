package _2_addTwoNumbers;

import java.util.ArrayList;
import java.util.List;

public class Solution {
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

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
