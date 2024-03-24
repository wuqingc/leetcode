package d02_doublepointer;

/**
 * @author tianjiaxuan
 * 图形类问题.
 */
public class Demo02_3_Different {
    /*
     * 11. 盛最多水的容器
     *
     * 什么情况下才能拿到更大的面积呢?
     * 宽度已经减小了,高度必须增加.所以较小的高度往中间找找看,找到比它更大的就更新值.
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = Integer.MIN_VALUE;

        while (left < right) {
            int t = Math.min(height[left], height[right]) * (right - left);
            ans = Math.max(ans, t);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }

    /*
     * 42. 接雨水
     * 维护一个左侧最大值, 右侧最大值.
     * 如果左边的小, 就把左边当前元素能接的水累加(不可能接更多了) ,然后继续向左移动.
     */
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int preMax = 0, sufMax = 0;
        int ans = 0;

        while (left < right) {
            preMax = Math.max(preMax, height[left]);
            sufMax = Math.max(sufMax, height[right]);

            if (preMax < sufMax) {
                ans += preMax - height[left++];
            } else {
                ans += sufMax - height[right--];
            }
        }
        return ans;

    }
}
