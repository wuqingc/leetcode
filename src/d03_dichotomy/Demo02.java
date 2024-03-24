package d03_dichotomy;

/**
 * @author tianjiaxuan
 * 不是裸的二分,需要转换一下思路.
 *
 * Arrays.binarySearch(arr, key); 如果数组中有多个key,并不能精确定位到第一个下标.
 *
 * 如果二分一定能够找到答案,比如找数组中的最小值,一定是存在的.
 * 那么就可以让right直接指向数组末尾元素,避免校验下表越界的情况.
 */
public class Demo02 {
    private int binarySearch(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    /*
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * right会停在 >= target的第一个位置, 找两次就行.
     */
    public int[] searchRange(int[] nums, int target) {
        int left = binarySearch(nums, target);
        if (left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        int right = binarySearch(nums, target + 1);
        return new int[]{left, right - 1};
    }

    /*
     * 153. 寻找旋转排序数组中的最小值
     * right指针是最小值:
     *  如果mid > right,表示有旋转,left收缩到mid
     *  如果mid <= right,right收缩.
     */
    public int findMin(int[] nums) {
        int left = -1, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return nums[right];
    }
    /*
     * 154. 寻找旋转排序数组中的最小值 II
     * 加了重复值.
     * 有相等的情况,直接把这个值去掉,就算它是最小值,当前区间也还会有一份.
     */
    public int findMin2(int[] nums) {
        int left = -1, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[right]) {
                left = mid;
            } else if (nums[mid] < nums[right]){
                right = mid;
            } else {
                right--;
            }
        }
        return nums[right];
    }

    /*
     * 33. 搜索旋转排序数组
     * 1.一次二分找到最小值, 再两次二分分别找两个片段.
     * 2.分类讨论,right会收缩的时机:
     *  2.1 mid > nums[right], target > nums[right](它俩同处前半段),
     *      且 nums[mid] >= target
     *  2.2 mid < nums[right], target > nums[right](mid在后半段,target在前半段,直接收缩)
     *  2.3 mid < nums[right], mid < nums[right],(他俩同处后半段)
     *      且 nums[mid] >= target
     */
    public int search(int[] nums, int target) {
        int left = -1, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (isBlue(nums, target, mid, right)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }
    private boolean isBlue(int[] nums, int target, int mid, int right) {
        if (nums[mid] > nums[right]) {
            return target > nums[right] && nums[mid] >= target;
        } else {
            if (target > nums[right]) {
                return true;
            }
            if (target < nums[right] && nums[mid] >= target) {
                return true;
            }
        }
        return false;
    }

    /*
     * 162. 寻找峰值
     *
     */
    public int findPeakElement(int[] nums) {
        int left = -1, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        System.out.println(new Demo02().findPeakElement(new int[]{1, 2, 3, 1}));
    }
}
