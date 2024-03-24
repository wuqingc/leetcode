package d01_hash;

import java.util.*;

/**
 * @author tianjiaxuan
 * 使用hash表来存中间值,加速过程.
 */
public class Demo00_1_Value {
    /*
     * 1. 两数之和
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> info = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            if (info.containsKey(sub)) {
                return new int[]{info.get(sub), i};
            }
            info.put(nums[i], i);
        }
        return null;
    }

    /*
     * 2815. 数组中的最大数对和
     * 数位只有0-10,可以直接用数组来存
     */
    public int maxSum(int[] nums) {
        int ans = -1;
        int[] maxVal = new int[10];
        Arrays.fill(maxVal, Integer.MIN_VALUE);

        for (int x : nums) {
            int digit = 0;
            for (int v = x; v > 0; v /= 10) {
                digit = Math.max(v % 10, digit);
            }
            ans = Math.max(ans, x + maxVal[digit]);
            maxVal[digit] = Math.max(maxVal[digit], x);
        }
        return ans;
    }

    /*
     * 822. 翻转卡片游戏
     */
    public int flipgame(int[] fronts, int[] backs) {
        int n = fronts.length;
        Set<Integer> info = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (fronts[i] == backs[i]) {
               info.add(fronts[i]);
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (!info.contains(fronts[i])) {
                ans = Math.min(ans, fronts[i]);
            }
            if (!info.contains(backs[i])) {
                ans = Math.min(ans, backs[i]);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        System.out.println(new Demo00_1_Value().maxSum(new int[]{51, 71, 17, 24, 42}));
    }
}
