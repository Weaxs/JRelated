package leetcode._160_intersectionOfTwoLinkedLists;

import leetcode.basic.ListNode;

import java.util.Base64;

/**
 * 相交链表
 *
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *  
 *
 * 示例 2：
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *  
 *
 * 示例 3：
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *
 */
public class Solution {

    /**
     * 双指针法
     *
     * 创建两个指针pA和pB，分别初始化为链表A和B的头结点。然后让它们向后逐结点遍历。
     * 当pA到达链表的尾部时，将它重定位到链表B的头结点(你没看错，就是链表B); 类似的，当pB到达链表的尾部时，将它重定位到链表A的头结点。
     *
     * 如果两个链表存在相交，它们末尾的结点必然相同。因此当pA/pB到达链表结尾时，记录下链表A/B对应的元素。若最后元素不相同，则两个链表不相交。
     *
     * 例:
     *   4,1,8,4,5  5,0,1,8,4,5
     *   5,0,1,8,4,5  4,1,8,4,5
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode p = headA;
        ListNode q = headB;
        boolean connA = true;
        boolean connB = true;
        while (p != null || connB) {
            if (p == null && connB) {
                connB = false;
                p = headB;
            }
            if (q == null && connA) {
                connA = false;
                q = headA;
            }
            if (p == q)
                return q;
            q = q.next;
            p = p.next;
        }

        return null;
    }

    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode ha = headA, hb = headB;
        //巧妙之处在于  如果有交点则在交点出返回交点  如果没有交点  会在最后的null相交
        //2 6 4 null 1 5 null
        //1 5 null 2 6 4 null
        while (ha != hb) {
            ha = ha != null ? ha.next : headB;
            hb = hb != null ? hb.next : headA;
        }
        return ha;
    }


    public static void main(String[] args) {
        ListNode node0 = new ListNode(0);
        ListNode node9 = new ListNode(9);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3);
//        node0.next = node9; node9.next = node1; node1.next = node2; node2.next = node4;
//        node3.next = node1;
        Solution solution = new Solution();
//        ListNode node = solution.getIntersectionNode(node0, node3);
        node2.next = node3;
        ListNode node = solution.getIntersectionNode2(null, node3);
        System.out.println();
    }

}
