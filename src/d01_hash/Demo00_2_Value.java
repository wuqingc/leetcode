package d01_hash;

import d05_list.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author tianjiaxuan
 * hash记录中间值,list
 */
public class Demo00_2_Value {

    /*
     * 160. 相交链表
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> info = new HashSet<>();
        while (headA != null) {
            info.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (info.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    /*
     * 计算长度.
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;

        while (A != B) {
            A = A == null ? headB : A.next;
            B = B == null ? headA: B.next;
        }

        return A;
    }


    /*
     * 138. 随机链表的复制
     * hash记录源节点跟新节点的映射.
     */
    public Node copyRandomList(Node head) {
        Node dummyNode = new Node(0);
        Node p0 = dummyNode;

        Map<Node, Node> info = new HashMap<>();

        while (head != null) {
            Node cur = new Node(head.val);
            cur.random = head.random;
            p0.next = cur;
            p0 = p0.next;
            info.put(head, cur);
            head = head.next;
        }

        p0 = dummyNode;
        while (p0.next != null) {
            p0.next.random = info.get(p0.next.random);
            p0 = p0.next;
        }
        return dummyNode.next;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
