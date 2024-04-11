package d12_greedy;

import java.util.PriorityQueue;

/**
 * 一块金条切成两半,是需要花费和长度数值一样的铜板的.
 * 比如长度为20的金条,不管切成长度多大的两半,都要花费20个铜板.
 * 一群人想整分整块金条，怎么分最省铜板？
 * 例如,给定数组{10,20,30},代表一共三个人,整块金条长度为10+20+30=60.
 * 金条要分成10,20,30三个部分.
 * 1.先把长度60的金条分成10和50,花费60;再把长度50的金条分成20和30,花费50,一共花费110铜板.
 * 2.先把长度60的金条分成30和30,花费60;再把长度30金条分成10和20,花费30.一共花费90铜板.
 *
 * 输入一个数组,返回分割的最小代价.
 * 贪心策略:
 * 每次都选最小的两个切割部分组合,切割代价就最小.
 * @author tianjiaxuan
 */
public class Demo03_HuffmanTree {
    /**
     * 为什么分金条是个huffman树?
     * 用逆向思维来考虑,把{10,20,30}三个节点组成一个完整的树,怎么样代价最小?
     * 那么这个最小代价怎么算呢? 10*2 + 20*2 + 30*1
     * 如果参与了组建过程,就可以在过程中求出解.
     *
     * leetcode1547.切棍子的最小成本,不是一个huffman,只是表明了切的位置,并不是要拼接成一整个木棍.
     */
    private static int lessMoney(int[] arr){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++){
            minHeap.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (minHeap.size() > 1){
            /*
             * 小根堆弹出两个数,相加之后就是花费的代价.
             * 只要小根堆的长度 > 1,就进行循环.
             */
            cur = minHeap.poll() + minHeap.poll();
            sum += cur;
            minHeap.add(cur);
        }
        return sum;
    }

    public static void main(String[] args){
        /*
         * 表示要把一个长度为60的金条分成三部分:
         * 10 20 30
         *
         * 求其最小代价.
         */
        int[] arr = {10,20,30};
        System.out.println(lessMoney(arr));
    }
}
