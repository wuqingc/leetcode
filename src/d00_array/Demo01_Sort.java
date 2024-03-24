package d00_array;

/**
 * @author tianjiaxuan
 * O(n^2) 的三种排序.
 */
public class Demo01_Sort {
    public int[] sortArray(int[] nums) {
        bubbleSort(nums);
        return nums;
    }

    /*
     * 冒泡排序: 两数交换,大数放后面.
     * i 表示每个数都需要判断一下
     * j 表示每次都是从第一个开始判断, 但由于后面的数都是最大的了,不需要遍历到最后.
     */
    private void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j] < nums[j - 1]) {
                    int t = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = t;
                }
            }
        }
    }

    /*
     * 选择排序: 每次都选择最小的放在最前面
     */
    private void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            int t = nums[min];
            nums[min] = nums[i];
            nums[i] = t;
        }
    }

    /*
     * 插入排序: 当前数组维持一个有序的子集,每个元素都往前交换到合适的位置.
     */
    private void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    int t = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = t;
                }
            }
        }
    }
}
