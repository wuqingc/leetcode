package d10_dp;

import java.util.Arrays;

/**
 * @author tianjiaxuan
 * @date 2024-03-25 10:08
 *
 * 输入顺序来考虑会更简单.
 */
public class Demo00_1_problems {

    /*
     * 494. 目标和
     * sum - 2x = target
     * x = (sum - target) / 2
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        this.target = sum - target;
        if (this.target % 2 != 0) {
            return 0;
        }
        this.target = (sum - target) / 2;

        dfs(nums, 0, 0);
        return ans;
    }
    private int target;
    private int ans = 0;
    private void dfs(int[] nums, int i, int cur) {
        if (i == nums.length) {
            if (cur == target) {
                ans += 1;
            }
            return;
        }
        dfs(nums, i + 1, cur);

        dfs(nums, i + 1, cur + nums[i]);
    }

}
