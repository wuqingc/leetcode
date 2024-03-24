package d05_list;

/**
 * @author tianjiaxuan
 * 快慢指针的位置.
 * 奇数: 慢指针停在中间位置
 * 偶数: 慢指针停在右半区的第一个节点位置
 */
public class Demo02_FastSlow {

    /*
     * 876. 链表的中间结点
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /*
     * 234. 回文链表
     * 1.快慢指针,找到中间节点
     * 2.反转后半部分
     * 3.从头开始遍历(如果是奇数情况,head也有中间那个数的指针,可以找到).
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode pre = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        while (pre != null) {
            if (pre.val != head.val) {
                return false;
            }
            pre = pre.next;
            head = head.next;
        }
        return true;
    }

    /*
     * 141. 环形链表
     * 如果有环,while走不出来,总有一次会相遇的时候.
     * while出来就已经表示没有环了.
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /*
     * 142. 环形链表 II
     * 1.快慢节点相遇时,慢节点一定没走完一圈.
     *  最坏情况: 慢节点刚进圈,快节点刚好在它前面.
     *  按相对距离分析,快节点动,慢节点不动,快指针走环长-1步就能追上慢指针.
     *
     *
        c 相遇点到入口的距离
        b 环中剩下一部分的距离
        a 环外距离
        环长 b + c
        慢指针移动距离 a + b
        快指针移动距离 a + b + k(b + c)

        a - c = (k - 1)(b + c)
        在快慢节点相遇的时候,启动头节点开始走,同时走c步,一定会在环入口相遇.
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                // 只有有环的情况下,才走.
                while (head != null) {
                    if (head == slow) {
                        return head;
                    }
                    head = head.next;
                    slow = slow.next;
                }
            }
        }
        return null;
    }

    /*
     * 143. 重排链表
     * 找到中间位置,逆序
     * 交错排列
     */
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode pre = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }
        ListNode cur = head;
        // pre是最后一个元素的话,就没必要排了
        while (pre.next != null) {
            ListNode n1 = cur.next;
            ListNode n2 = pre.next;
            cur.next = pre;
            pre.next = n1;
            pre = n2;
            cur = n1;
        }
    }
}
