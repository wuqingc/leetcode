package d10_datastruct;

import java.util.PriorityQueue;

/**
 * @author tianjiaxuan
 * 295. 数据流的中位数
 */
public class Demo02_1_Heap {

    /*
     * 两个堆:
     * 1.小根堆,保存较大的一半元素. maxHeap
     * 2.大根堆,保存较小的一半元素. minHeap
     *
     * 添加元素的过程:
     * 1.maxHeap为空,或者num > maxHeap堆顶元素, 进maxHeap
     * 2.其他情况进minHeap
     * 3.maxHeap数量太多,就弹出堆顶到minHeap,反之也一样.
     */
    class MedianFinder {
        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            maxHeap = new PriorityQueue<>();
            minHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        }

        public void addNum(int num) {
            if (maxHeap.isEmpty() || num > maxHeap.peek()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
            int diff = maxHeap.size() - minHeap.size();
            if (diff > 1) {
                minHeap.add(maxHeap.poll());
            }
            if (diff < -1) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            int s1 = maxHeap.size();
            int s2 = minHeap.size();
            if (s1 == s2) {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
            return s1 > s2 ? maxHeap.peek() : minHeap.peek();
        }
    }

}

