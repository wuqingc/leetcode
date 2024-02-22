package d09_string;

/**
 * @author tianjiaxuan
 * @create 2023-03-13 13:28
 * leetcode5. 最长回文子串
 */
public class Demo_02_1_Manacher {

    /**
     * manacher算法
     *
     * 1.加工好字符串
     * 2.遍历每个字符,需要考虑三种情况:
     *  2.1 当前位置不在最右边界内(以及压线): 那么只能加速它自己
     *  2.2 当前在最右边界内,且对称点的回文范围也在 --> 对称点的回文半径<=right - i,能加速的位置就是i + 对称点的回文半径
     *  2.3 当前在最右边界内,且对称点的回文范围超出左边界 --> 对称点的回文半径>right - i,能加速的位置就是right - i
     *  可以用一个三目运算符表达.
     *
     *  3.确认加速位置后,跳过并继续向两边比较.比较过程中回文半径不断增加.
     *  4.比较完之后更新对称中心,最右边界.
     */
    public static String longestPalindrome(String s) {
        char[] chars = splicing(s);

        int[] radius = new int[chars.length];
        int right = -1;
        int center = -1;
        int indexMax = 0;

        for (int i = 0; i < chars.length; i++) {
            radius[i] = i >= right ? 1 : Math.min(radius[center - (i - center)], right - i);

            while (i + radius[i] < chars.length && i - radius[i] > -1) {
                if (chars[i + radius[i]] == chars[i - radius[i]]) {
                    radius[i]++;
                } else {
                    break;
                }
            }
            /*
             * 比较完之后
             */
            if (i + radius[i] > right) {
                right = i + radius[i];
                center = i;
            }
            if (i > 0) {
                indexMax = radius[i] > radius[indexMax] ? i : indexMax;
            }
        }
        StringBuilder str = new StringBuilder();
        for (int i = indexMax - radius[indexMax] + 1; i < indexMax + radius[indexMax]; i++) {
            if ('#' == chars[i]) {
                continue;
            }
            str.append(chars[i]);
        }
        return str.toString();
    }

    private static char[] splicing(String s) {
        char[] source = s.toCharArray();
        char[] result = new char[source.length * 2 + 1];
        int i = 0;
        for (char ch : source) {
            result[i++] = '#';
            result[i++] = ch;
        }
        result[i] = '#';
        return result;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abb"));
    }
}