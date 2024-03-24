package d14_greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author tianjiaxuan
 * leetcode502
 */
public class Demo04_IPO {
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        if (profits == null || capital == null){
            return 0;
        }

        PriorityQueue<Integer[]> minHeap = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < profits.length; i++) {
            minHeap.offer(new Integer[]{capital[i],profits[i]});
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int count = 0;
        while (count < k) {
            while (!minHeap.isEmpty() && minHeap.peek()[0] <= w) {
                Integer[] temp = minHeap.poll();
                maxHeap.offer(temp[1]);
            }
            /*
             * 存在没钱做项目的情况,提前返回.
             */
            if (maxHeap.isEmpty()) {
                return w;
            }
            w += maxHeap.poll();
            count++;
        }
        return w;
    }

    public static void main(String[] args){

        System.out.println(findMaximizedCapital(5,0,new int[]{1},new int[]{0}));

    }
}
