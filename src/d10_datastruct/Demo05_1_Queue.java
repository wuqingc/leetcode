package d10_datastruct;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author tianjiaxuan
 * 232. 用栈实现队列
 */
public class Demo05_1_Queue {
    class MyQueue {
        private Deque<Integer> stack1;
        private Deque<Integer> stack2;

        public MyQueue() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
        }

        public void push(int x) {
            stack1.addLast(x);
        }

        public int pop() {
            while (stack1.size() > 1) {
                stack2.addLast(stack1.pollLast());
            }
            int v = stack1.pollLast();
            while (!stack2.isEmpty()) {
                stack1.addLast(stack2.pollLast());
            }
            return v;
        }

        public int peek() {
            while (stack1.size() > 1) {
                stack2.addLast(stack1.pollLast());
            }
            int v = stack1.peekLast();
            while (!stack2.isEmpty()) {
                stack1.addLast(stack2.pollLast());
            }
            return v;
        }

        public boolean empty() {
            return stack1.isEmpty();
        }
    }
}
