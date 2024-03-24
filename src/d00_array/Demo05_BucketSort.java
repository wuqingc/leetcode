package d00_array;

/**
 * @author tianjiaxuan
 * 桶排序,数据量几千几万的时候可以用一把.
 *
 * 把最大元素和最小元素的值找到, 准备这么多的桶.
 * bucket[i]: i大小的元素出现过bucket[i]次, 然后分别往出倒即可.
 */
public class Demo05_BucketSort {
    /*
     * leetcode1122. 数组的相对排序
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        int[] bucket = new int[1001];
        for (int x : arr1) {
            bucket[x]++;
        }
        int i = 0;
        for (int x : arr2) {
            while (bucket[x]-- > 0) {
                arr1[i++] = x;
            }
        }
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr1[i++] = j;
            }
        }
        return arr1;
    }

}
