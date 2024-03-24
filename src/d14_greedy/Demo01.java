package d14_greedy;

import java.util.Arrays;

/**
 * @author tianjiaxuan
 * 贪心.
 */
public class Demo01 {

    /*
     * 2576. 求出最多标记下标
     * 最大化标记.
     * 直接从中点开始贪心,避免最小/最大的数值对互相消耗.
     */
    public int maxNumOfMarkedIndices(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        int left = 0, right = (nums.length + 1) / 2;
        while (right < nums.length) {
            if (2 * nums[left] <= nums[right]) {
                ans += 1;
                left++;
            }
            right++;
        }
        return ans * 2;
    }
}
