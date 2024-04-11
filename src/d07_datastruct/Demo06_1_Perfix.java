package d07_datastruct;

/**
 * @author tianjiaxuan
 * 前缀和.
 */
public class Demo06_1_Perfix {

    /*
     * 2574. 左右元素和的差值
     */
    public int[] leftRightDifference(int[] nums) {
        int[] prefix = new int[nums.length];
        for (int i = 0; i < nums.length - 1; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        int[] suffix = new int[nums.length];
        for (int i = nums.length - 1; i > 0; i--) {
            suffix[i - 1] = suffix[i] + nums[i];
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = Math.abs(prefix[i] - suffix[i]);
        }
        return ans;
    }
}
