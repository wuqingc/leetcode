package d00_array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Demo02_2_Smaller {

    /*
     * 315. 计算右侧小于当前元素的个数
     * 交换元素会导致索引变了,所以用一个index数组跟着同时变,方便获取到原数组元素的下标.
     *
     * 两种统计次数的方式:
     *  1. 右半边数组放时, curL以及后面的元素, 全部+1.
     *  2. 左半边数组放时, curR - right, 就是比他小的右侧元素
     * 第二种方式避免循环.
     */
    public List<Integer> countSmaller(int[] nums) {
        if (nums.length < 1){
            return List.of(0);
        }
        counts = new int[nums.length];
        indexs = IntStream.range(0, nums.length).toArray();

        mergeSort(nums, 0, nums.length - 1);
        return Arrays.asList(Arrays.stream(counts).boxed().toArray(Integer[]::new));
    }

    private int[] counts;
    private int[] indexs;

    public void mergeSort(int[] record, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(record, left, mid);
        mergeSort(record, mid + 1, right);
        merge(record, left, mid, mid + 1, right);
    }
    public void merge(int[] record, int left, int leftEnd, int right, int rightEnd) {
        int[] help = new int[rightEnd - left + 1];
        int[] tmpIndexs = new int[rightEnd - left + 1];
        int i = 0, j = 0;
        int curL = left;
        int curR = right;
        while (curL <= leftEnd && curR <= rightEnd) {
            if (record[curL] <= record[curR]) {
                tmpIndexs[j++] = indexs[curL];
                help[i++] = record[curL];
                counts[indexs[curL]] += curR - right;
                curL++;
            } else {
                tmpIndexs[j++] = indexs[curR];
                help[i++] = record[curR++];
            }
        }

        while (curL <= leftEnd) {
            tmpIndexs[j++] = indexs[curL];
            help[i++] = record[curL];
            counts[indexs[curL]] += curR - right;
            curL++;
        }
        while (curR <= rightEnd) {
            tmpIndexs[j++] = indexs[curR];
            help[i++] = record[curR++];
        }
        System.arraycopy(help, 0, record, left, help.length);
        System.arraycopy(tmpIndexs, 0, indexs, left, tmpIndexs.length);
    }
}
