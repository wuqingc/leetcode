package d05_list;

/**
 * @author tianjiaxuan
 * 链表的删除.
 */
public class Demo03_Delete {
    /*
     * 237. 删除链表中的节点
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /*
     * 19. 删除链表的倒数第 N 个结点
     * 链表长度 - n, 从0开始遍历.
     * 刚好停在它的前驱节点, 然后删除.
     *
     * 可能会删除头节点,引入哨兵.
     */
    public ListNode removeNthFromEndBase(ListNode head, int n) {
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            length++;
        }

        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode p0 = dummyNode;
        for (int i = 0; i < length - n; i++) {
            p0 = p0.next;
        }
        p0.next = p0.next.next;
        return dummyNode.next;
    }
    /*
     * fast先走 n 步, 然后跟slow一起走.
     * fast 与 slow始终距离为n.
     * fast指向最后一个节点的时候, slow就是倒数第n个.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode fast = head;
        ListNode slow = dummyNode;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummyNode.next;
    }

    /*
     * 203. 移除链表元素
     * 考虑清楚p0移动的场景.
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode p0 = dummyNode;
        while (p0.next != null) {
            if (p0.next.val == val) {
                p0.next = p0.next.next;
            } else {
                p0 = p0.next;
            }
        }
        return dummyNode.next;
    }

    /*
     * 83. 删除排序链表中的重复元素
     * 既然要求保留一个, 那就保留第一个, 这样避免了头节点的删除.
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    /*
     * 82. 删除排序链表中的重复元素 II
     * 删完之后cur要动一下,才能跳过最后一个重复元素.
     */
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode p0 = dummyNode;
        while (p0.next != null && p0.next.next != null) {
            ListNode cur = p0.next;
            int val = cur.val;

            if (cur.next.val == val) {
                while (cur.next != null && cur.next.val == val) {
                    cur = cur.next;
                }
                cur = cur.next;
                p0.next = cur;
            } else {
                p0 = p0.next;
            }
        }
        return dummyNode.next;
    }
}
