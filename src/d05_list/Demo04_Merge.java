package d05_list;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @author tianjiaxuan
 * 合并链表
 */
public class Demo04_Merge {
    /*
     * 21. 合并两个有序链表
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode();
        ListNode p0 = dummyNode;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                p0.next = list1;
                list1 = list1.next;
            } else {
                p0.next = list2;
                list2 = list2.next;
            }
            p0 = p0.next;
        }
        if (list1 != null) {
            p0.next = list1;
        }
        if (list2 != null) {
            p0.next = list2;
        }
        return dummyNode.next;
    }

    /*
     * leetcode23. 合并 K 个升序链表
     * 小根堆.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummyNode = new ListNode();
        ListNode p0 = dummyNode;

        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        heap.addAll(Arrays.stream(lists).filter(Objects::nonNull).toList());

        while (!heap.isEmpty()) {
            ListNode listNode = heap.poll();
            if (listNode.next != null) {
                heap.add(listNode.next);
            }
            p0.next = listNode;
            p0 = p0.next;
        }
        return dummyNode.next;
    }
}
