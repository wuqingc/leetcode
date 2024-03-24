package d05_list;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author tianjiaxuan
 * 借助反转链表来解答.
 */
public class Demo01_2_Reverse {

    /*
     * 2. 两数相加
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode();
        ListNode p0 = dummyNode;
        int carry = 0;

        while (l1 != null || l2 != null || carry > 0) {
            int value = carry;
            if (l1 != null) {
                value += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                value += l2.val;
                l2 = l2.next;
            }
            carry = value / 10;
            p0.next = new ListNode(value % 10);
            p0 = p0.next;
        }

        return dummyNode.next;
    }

    /*
     * 445. 两数相加 II
     * 反转之后,跟上题一样.
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        return reverse(addTwoNumbers(l1,l2));
    }
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /*
     * 2816. 翻倍以链表形式表示的数字
     * 其实就是加两次.
     * 但因为数值固定(最多就是多一个最高位节点),也可以不反转,直接算.
     *
     * 后面的几位最多进一,所以head=4,一定不可能有新节点(8+1)
     */
    public ListNode doubleIt(ListNode head) {
        if (head.val >= 5) {
            head = new ListNode(0, head);
        }
        ListNode cur = head;
        while (cur != null) {
            cur.val = cur.val * 2 % 10;
            if (cur.next != null && cur.next.val >= 5) {
                cur.val += 1;
            }
            cur = cur.next;
        }
        return head;
    }

}
