package d02_doublepointer;

import java.util.Arrays;

/**
 * @author tianjiaxuan
 * 同向双指针问题.
 * 需要一些额外的思路转换.
 */
public class Demo01_2_Same {

    /*
     * 1004. 最大连续1的个数 III
     * 用k来扩大窗口范围.
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0;
        int ans = 0;
        while (right < nums.length) {
            while (nums[right] == 0 && k == 0) {
                if (nums[left] == 0) {
                    k++;
                }
                left++;
            }
            if (nums[right++] == 0) {
                k--;
            }
            ans = Math.max(ans, right - left);
        }
        return ans;
    }

    /*
     * 1658. 将 x 减到 0 的最小操作数
     * 思路转换:移除两边 ==> 中间找一个连续的最大子数组
     */
    public int minOperations(int[] nums, int x) {
        int target = Arrays.stream(nums).sum() - x;
        if (target < 0) {
            return -1;
        }
        int sum = 0;
        int left = 0, right = 0;
        int ans = Integer.MIN_VALUE;

        while (right < nums.length) {
            sum += nums[right++];
            while (sum > target && left < right) {
                sum -= nums[left++];
            }
            if (sum == target) {
                ans = Math.max(ans, right - left);
            }
        }
        return ans == Integer.MIN_VALUE ? -1 : nums.length - ans;
    }

    /*
     * 1234. 替换子串得到平衡字符串
     * 子串, 子数组, 都是要求连续.
     *
     * 只要连续子串之外的部分,满足每个字符都 <= avg, 替换该子串就一定能平衡,只不过不一定是最小的.
     * 数组代替hash表,逻辑上比较清爽.
     */
    public int balancedString(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        int[] info = new int['X'];
        for (char c : chs) {
            info[c]++;
        }
        int m = n / 4;
        if (info['Q'] == m && info['W'] == m &&
                info['E'] == m && info['R'] == m) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        int left = 0, right = 0;
        while (right < n) {
            info[chs[right++]]--;
            while (info['Q'] <= m && info['W'] <= m &&
                    info['E'] <= m && info['R'] <= m) {
                ans = Math.min(ans, right - left);
                info[chs[left++]]++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Demo01_2_Same().balancedString("QQWE"));
    }
}
