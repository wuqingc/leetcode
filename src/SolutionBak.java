/**
 * @author tianjiaxuan
 * @date 2024-04-10 10:01
 */
class SolutionBak {

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, ans = 0;

        while (left < right) {
            ans = Math.max(ans,
                    (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new SolutionBak().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}