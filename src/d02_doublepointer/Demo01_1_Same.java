package d02_doublepointer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tianjiaxuan
 * 同向双指针问题.
 *
 * 右端点无脑扩,左端点看情况收缩.
 * 问题的特征: 必须连续.
 * 思考问题,尽量先把特殊位置的解返回出来,然后考虑通用解.
 * 通过O(1)步骤获取了多少的信息量来推算算法的时间复杂度.
 *
 */
public class Demo01_1_Same {
    /*
     * 209. 长度最小的子数组
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        while (right < nums.length) {
            sum += nums[right++];
            while (sum >= target) {
                ans = Math.min(ans, right - left);
                sum -= nums[left++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /*
     * 713. 乘积小于 K 的子数组
     * 跟上一题相比: 要在循环外记录答案.因为如果sum < k, 继续收缩左端点还是会变小.
     * L-R 子数组的数量
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0;
        int sum = 1;
        int left = 0, right = 0;

        while (right < nums.length) {
            sum *= nums[right++];
            while (sum >= k && left < right) {
                sum /= nums[left++];
            }
            ans += right - left;
        }
        return ans;
    }

    /*
     * 3. 无重复字符的最长子串
     * 使用hash来记录窗口中的字符是否重复.
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> info = new HashSet<>();
        int left = 0, right = 0;
        int ans = 0;
        while (right < s.length()) {
            char cur = s.charAt(right++);
            while (info.contains(cur)) {
                info.remove(s.charAt(left++));
            }
            info.add(cur);
            ans = Math.max(ans, right - left);
        }
        return ans;
    }

    public static void main(String[] args) {
        new Demo01_1_Same().numSubarrayProductLessThanK(new int[]{10,5,2,6},100);
    }

}
