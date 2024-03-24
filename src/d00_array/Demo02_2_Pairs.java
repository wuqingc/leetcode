package d00_array;

import java.util.List;

public class Demo02_2_Pairs {

    /*
     * LCR 170. 交易逆序对的总数
     * 归并的过程叠加次数就行,
     * 一个右边的数填充: 需要把左边剩余的元素个数都叠加上.最后倒空左边时候就不用叠加了.
     */
    public int reversePairs(int[] record) {
        if (record.length == 0) {
            return count;
        }
        mergeSort(record, 0, record.length - 1);
        return count;
    }

    private int count = 0;

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
        int i = 0;
        int curL = left;
        int curR = right;
        while (curL <= leftEnd && curR <= rightEnd) {
            if (record[curL] <= record[curR]) {
                help[i++] = record[curL++];
            } else {
                count += leftEnd - curL + 1;
                help[i++] = record[curR++];
            }
        }

        while (curL <= leftEnd) {
            help[i++] = record[curL++];
        }
        while (curR <= rightEnd) {
            help[i++] = record[curR++];
        }
        System.arraycopy(help, 0, record, left, help.length);
    }

}
