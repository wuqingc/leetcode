package d00_array;

public class Demo03_1_Colors {

    /*
     * 75. 颜色分类
     * left维护小于区域, right维护大于区域.
     * cur 一直保持在left前面.
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        int target = 1;
        int left = -1, right = n, cur = 0;
        while (cur < right) {
            if (nums[cur] < target) {
                swap(nums, cur, left + 1);
                left++;
                cur = left + 1;
            } else if (nums[cur] > target) {
                swap(nums, cur, right - 1);
                right--;
            } else {
                cur++;
            }
        }
    }

    private void swap(int[] nums, int l1, int l2) {
        int t = nums[l1];
        nums[l1] = nums[l2];
        nums[l2] = t;
    }

    public static void main(String[] args) {
        new Demo03_1_Colors().sortColors(new int[]{2,0,1});
    }


}
