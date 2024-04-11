package d07_datastruct;

import java.util.*;

/**
 * @author tianjiaxuan
 *
 * 同向双指针 + 有序表
 */
public class Demo01_1_SortedList {

    /*
     * leetcode1438. 绝对差不超过限制的最长连续子数组
     */
    public int longestSubarray(int[] nums, int limit) {
        var treeMap = new TreeMap<Integer, Integer>();
        int ans = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            treeMap.put(nums[right], treeMap.getOrDefault(nums[right], 0) + 1);
            while (treeMap.lastKey() - treeMap.firstKey() > limit) {
                treeMap.put(nums[left], treeMap.get(nums[left]) - 1);
                if (treeMap.get(nums[left]) == 0) {
                    treeMap.remove(nums[left]);
                }
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }

    /*
     * 2817. 限制条件下元素之间的最小绝对差
     */
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        var sl = new TreeSet<Integer>();
        sl.add(Integer.MAX_VALUE);
        sl.add(Integer.MIN_VALUE / 2);

        int ans = Integer.MAX_VALUE;

        for (int i = x; i < nums.size(); i++) {
            sl.add(nums.get(i - x));
            int y = nums.get(i);
            ans = Math.min(ans, Math.min(sl.ceiling(y) - y, y - sl.floor(y)));
        }
        return ans;
    }
}
