package d00_array;

import java.util.Random;

/**
 * @author tianjiaxuan
 * 快速排序:
 * 数组中选定一个数target, 大于target放右边, 小于target放左边.
 * 递归, 到只剩一个数为止.
 *
 */
public class Demo03_2_QuickSort {
    // 静态加载提升效率
    private static final Random RANDOM = new Random();

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int rand = RANDOM.nextInt(right - left + 1) + left;
        int[] part = partition(nums, left, right, nums[rand]);
        quickSort(nums, left, part[0]);
        quickSort(nums, part[1], right);
    }

    private int[] partition(int[] nums, int left, int right, int target) {
        int cur = left;
        left--;
        right++;
        while (cur < right) {
            if (nums[cur] < target) {
                swap(nums,cur, left + 1);
                left++;
                cur = left + 1;
            } else if (nums[cur] > target) {
                swap(nums,cur, right - 1);
                right--;
            } else {
                cur++;
            }
        }
        return new int[]{left, right};
    }

    private void swap(int[] nums, int l1, int l2) {
        int t = nums[l1];
        nums[l1] = nums[l2];
        nums[l2] = t;
    }
}
