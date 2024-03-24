package d02_doublepointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tianjiaxuan
 * 对向双指针.
 */
public class Demo02_1_Different {
    /*
     * 167. 两数之和 II - 输入有序数组
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int s = numbers[left] + numbers[right];
            if (s > target) {
                right--;
            } else if (s < target) {
                left++;
            } else {
                return new int[]{left+1, right + 1};
            }
        }
        return null;
    }

    /*
     * 2491. 划分技能点相等的团队
     */
    public long dividePlayers(int[] skill) {
        Arrays.sort(skill);
        int left = 0, right = skill.length - 1;
        long ans = (long) skill[left] * skill[right];
        int pre = skill[left++] + skill[right--];


        while (left < right) {
            if (pre != skill[left] + skill[right]) {
                return -1;
            } else {
                ans += (long) skill[left++] * skill[right--];
            }
        }
        return ans;
    }


    /*
     * 15. 三数之和
     *
     1.排序后枚举第一个数,然后剩余部分就是两数之和相加等于第一个数的取反
     2.去重:
        如果枚举的当前数跟上一个相同,就跳过(第一个数不用比较).
        左右指针移动后,继续判断当前位置值与之前是否一样,如果一样就继续移动
     3.剪枝:
        如果当前数 + 它的后两个数已经大于0了,这个数没有解,直接break.它后面也不可能有解了
        如果当前数 + 数组最后两个数还是小于0,这个数没有解,应该continue,它后面还可能有解.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }
            if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] < 0) {
                continue;
            }

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int s = nums[i] + nums[left] + nums[right];
                if (s < 0) {
                    left++;
                } else if (s > 0) {
                    right--;
                } else {
                    ans.add(List.of(nums[i], nums[left], nums[right]));
                    left++;
                    while (nums[left - 1] == nums[left] && left < right) {
                        left++;
                    }
                    right--;
                    while (nums[right + 1] == nums[right] && left < right) {
                        right--;
                    }
                }
            }

        }
        return ans;
    }


    /*
     * 16. 最接近的三数之和
     *
     * 找到target就直接返回, 没找到就记录过程变量.
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0;
        int closest = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int s = nums[i] + nums[left] + nums[right];
                int sub = Math.abs(target - s);
                if (sub < closest) {
                    closest = sub;
                    ans = s;
                }
                if (s < target) {
                    left++;
                } else if (s > target) {
                    right--;
                } else {
                    return target;
                }
            }

        }
        return ans;
    }

    /*
     * 18. 四数之和
     * 注意:有溢出的case,要转成long.
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target) {
                continue;
            }

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) {
                    continue;
                }

                int left = j + 1, right = nums.length - 1;
                while (left < right) {
                    long s = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (s < target) {
                        left++;
                    } else if (s > target) {
                        right--;
                    } else {
                        ans.add(List.of(nums[i], nums[j],nums[left], nums[right]));
                        left++;
                        while (nums[left - 1] == nums[left] && left < right) {
                            left++;
                        }
                        right--;
                        while (nums[right + 1] == nums[right] && left < right) {
                            right--;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        new Demo02_1_Different().fourSum(new int[]{0,0,0,1000000000,1000000000,1000000000,1000000000}, 1000000000);
    }
}

