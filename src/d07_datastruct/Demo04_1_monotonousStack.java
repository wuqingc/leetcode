package d07_datastruct;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author tianjiaxuan
 * 单调栈.
 */
public class Demo04_1_monotonousStack {
    /*
     * 739. 每日温度
     * 1.栈为空, 入栈
     * 2.栈非空, 且栈顶元素小于当前元素,栈顶可以弹出了,记录值.
     * 3.>= 当前元素, 压栈.
     *
     * 如果后面没有更大的,默认就是0,不需要更新.
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty()
                    && temperatures[stack.peekLast()] < temperatures[i]) {
                int cur = stack.pollLast();
                ans[cur] = i - cur;
            }
            stack.addLast(i);
        }
        return ans;
    }
}
