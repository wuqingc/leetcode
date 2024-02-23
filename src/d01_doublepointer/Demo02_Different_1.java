package d01_doublepointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 相向双指针.
 */
public class Demo02_Different_1 {
    /*
     * 167. 两数之和 II - 输入有序数组
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                return new int[]{left + 1, right + 1};
            }
        }
        return new int[]{};
    }


    /*
     * 15. 三数之和
     *
     * 枚举第一个数, 之后继续求两数之和.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> answer = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }

            if (nums[i] + nums[n - 2] + nums[n - 1] < 0) {
                continue;
            }

            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    answer.add(List.of(nums[i], nums[left], nums[right]));
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                }
            }
        }
        return answer;
    }


    /*
     * 16. 最接近的三数之和
     *
     * 找到target就直接返回, 没找到就记录过程变量.
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int answer = 0;
        int closest = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int c = Math.abs(sum - target);
                if (c < closest) {
                    answer = sum;
                    closest = c;
                }
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return target;
                }
            }
        }
        return answer;

    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,0};
        System.out.println(new Demo02_Different_1().threeSumClosest(nums, -100));
    }
}

