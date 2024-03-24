package d02_doublepointer;

import java.util.*;

/**
 * @author tianjiaxuan
 * 滑动窗口
    及时去掉无用数据, 保持双端队列有序.

    1.入队列: 如果队尾元素要比当前元素小, 直接出队列(永远不会有它做最大值的时刻了).
    2.出队列: 当前下标与队首下标比较, 如果过期了就移除队首元素
    3.在滑动窗口成型后, 记录最大值的情况.
 */
public class Demo03_1_Window {
    /*
     * 239. 滑动窗口最大值
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (queue.peekFirst() == i - k) {
                queue.pollFirst();
            }
            if (i >= k - 1) {
                ans.add(nums[queue.peekFirst()]);
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
