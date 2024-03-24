package d03_dichotomy;

import java.util.Arrays;

/**
 * @author tianjiaxuan
 * 枚举答案, 二分查找.
 */
public class Demo04 {
    /*
     * 275. H 指数 II
     * 用二分的方式枚举答案, 看答案是不是满足, 然后缩减区间.
     * 只要当前位置的被引用次数 >= 当前位置到末尾的文章数就行.
     * right此时的位置是当前下标, n - right就是实际的论文数量.
     */
    public int hIndex(int[] citations) {
        int left = -1, right = citations.length;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            int d = citations.length - mid;
            int v = citations[mid];
            if (d > v) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return citations.length - right;
    }

    /*
     * 1011. 在 D 天内送达包裹的能力
     * 退出循环之后,还有一天的货物,所以需要跟一比较.
     */
    public int shipWithinDays(int[] weights, int days) {
        int right = Arrays.stream(weights).sum();
        int left = 0;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (check(weights, weights[mid], days)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }
    private boolean check(int[] weights, int limit, int days) {
        int cur = 0;
        int i = 0;
        while (i < weights.length && days >= 1) {
            cur += weights[i];
            if (cur > limit) {
                cur = 0;
                days--;
            } else {
                i++;
            }
        }
        return days >= 1;
    }

    public static void main(String[] args) {
        System.out.println(new Demo04()
                .check(new int[]{1,2,3,1,1}, 2, 4));
    }
}
