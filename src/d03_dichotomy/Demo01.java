package d03_dichotomy;

/**
 * @author tianjiaxuan
 * 二分查找模板(开区间):
 * left + 1 < right
 * left维护一个 < target的区间, right维护一个 >= target的区间.
 * 两个区间的端点相邻时,返回.
 */
public class Demo01 {
    /*
     * 704. 二分查找
     */
    public int search(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right == nums.length || nums[right] != target ? -1 : right;
    }

    /*
     * 35. 搜索插入位置
     */
    public int searchInsert(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    /*
     * 69. x 的平方根
     * 加两个特殊值判断,就能够把逻辑解耦.
     * 这样0~x就是个开区间.
     * 有int溢出情况,所以改一下计算逻辑.
     */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;

        int left = 0, right = x;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (mid <= x / mid) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
