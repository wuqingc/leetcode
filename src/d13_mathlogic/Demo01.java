package d13_mathlogic;

import d01_hash.Demo00_1_Value;

import java.util.*;

/**
 * @author tianjiaxuan
 * 逻辑题,关键在于读懂题意.
 */
public class Demo01 {


    /*
     * 822. 翻转卡片游戏
     * 排除掉正反相同的元素,剩下正反所有元素的最小值就是答案了.
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

