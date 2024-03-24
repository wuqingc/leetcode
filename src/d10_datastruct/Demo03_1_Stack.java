package d10_datastruct;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author tianjiaxuan
 * 225. 用队列实现栈
 */
public class Demo03_1_Stack {
    class MyStack {
        private Deque<Integer> queue1;
        private Deque<Integer> queue2;

        public MyStack() {
            queue1 = new ArrayDeque<>();
            queue2 = new ArrayDeque<>();
        }

        public void push(int x) {
            queue1.addLast(x);
        }

        public int pop() {
            while (queue1.size() > 1) {
                queue2.addLast(queue1.pollFirst());
            }
            int v = queue1.pollFirst();

            while (!queue2.isEmpty()) {
                queue1.addLast(queue2.pollFirst());
            }
            return v;
        }

        public int top() {
            int v = 0;
            while (!queue1.isEmpty()) {
                v = queue1.pollFirst();
                queue2.addLast(v);
            }
            while (!queue2.isEmpty()) {
                queue1.addLast(queue2.pollFirst());
            }
            return v;
        }

        public boolean empty() {
            return queue1.isEmpty();
        }
    }
}
