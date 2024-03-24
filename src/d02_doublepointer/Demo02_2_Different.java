package d02_doublepointer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author tianjiaxuan
 * 对向双指针: 区间叠加
 */
public class Demo02_2_Different {
    /*
     * 611. 有效三角形的个数
     * 倒着枚举: 为了保证left + right有个共同的单调性.
     * num[left] + nums[right] > nums[i]
     * 所以在left~right区间内,任意数 + right 都 > nums[i]
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);

        int ans = 0;
        for (int i = nums.length - 1; i > 1; i--) {
            int left = 0;
            int right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    ans += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return ans;
    }

    /*
     * 2824. 统计和小于目标的下标对数目
     * 当前位置 < target
     * 表示left + 这个区间的任意数(都比当前right小) 都 < target
     */
    public int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);
        int ans = 0;
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            int t = nums.get(left) + nums.get(right);
            if (t >= target) {
                right--;
            } else {
                ans += right - left;
                left++;
            }
        }
        return ans;
    }
}
