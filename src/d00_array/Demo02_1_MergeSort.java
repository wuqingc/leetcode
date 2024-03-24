package d00_array;

import java.util.Arrays;

/**
 * @author tianjiaxuan
 * 归并排序.
 * 1. 一分为二: 先把左半边排好,再把右半边排好.
 * 2. merge, merge过程注意下标.
 */
public class Demo02_1_MergeSort {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);

        merge(nums, left, mid, mid + 1, right);
    }

    /*
     * 具体合并过程, 准备一个辅助数组.
     */
    private void merge(int[] nums, int left, int leftEnd, int right, int rightEnd) {
        int[] help = new int[rightEnd - left + 1];
        int i = 0;
        int curL = left, curR = right;

        while (curL <= leftEnd && curR <= rightEnd) {
            if (nums[curL] < nums[curR]) {
                help[i++] = nums[curL++];
            } else {
                help[i++] = nums[curR++];
            }
        }
        while (curL <= leftEnd) {
            help[i++] = nums[curL++];
        }
        while (curR <= rightEnd) {
            help[i++] = nums[curR++];
        }

        for (int c = 0; c < help.length; c++) {
            nums[left + c] = help[c];
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Demo02_1_MergeSort().sortArray(
                new int[]{5, 2, 3, 1}
        )));
    }
}
