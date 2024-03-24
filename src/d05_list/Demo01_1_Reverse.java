package d05_list;

/**
 * @author tianjiaxuan
 * 反转之后: pre为最后节点的位置, cur则是最后节点的后一个位置.
 */
public class Demo01_1_Reverse {

    /*
     * 206. 反转链表
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
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
     * 92. 反转链表 II
     * 指定区间反转.
     *
     * 为什么引入哨兵?
     * 反转之后,传入的head节点可能不是头了,需要找到新的链表头.
     *
     * 区间交换完之后的顺序一定是:
        cur 表示原顺序下的后一个节点
        pre 交换范围的末尾节点
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;

        ListNode p0 = dummyNode;
        for (int i = 1; i < left; i++) {
            p0 = p0.next;
        }

        ListNode pre = p0;
        ListNode cur = p0.next;

        for (int i = left; i <= right; i++){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        p0.next.next = cur;
        p0.next = pre;
        return dummyNode.next;
    }

    /*
     * 24. 两两交换链表中的节点
        p0.next 交换前的第一个节点,现在变成最后一个了.
            所以它的next去接cur
            提前把它存下来, p0会跳到这个位置, 表示下次反转的哨兵位.
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode p0 = dummyNode;

        while (p0.next != null && p0.next.next != null) {
            ListNode pre = p0;
            ListNode cur = p0.next;
            for (int i = 0; i < 2; i++) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            ListNode t = p0.next;
            p0.next.next = cur;
            p0.next = pre;
            p0 = t;
        }
        return dummyNode.next;
    }

    /*
     * 25. K 个一组翻转链表
     * 先算出来要反转几次, 之后就是两两反转链表的模板.
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        int n = 0;
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        while (head != null) {
            head = head.next;
            n++;
        }
        n /= k;

        ListNode p0 = dummyNode;

        for (int i = 0; i < n; i++) {
            ListNode pre = p0;
            ListNode cur = p0.next;
            for (int j = 0; j < k; j++) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            ListNode t = p0.next;
            p0.next.next = cur;
            p0.next = pre;
            p0 = t;
        }
        return dummyNode.next;
    }

}
