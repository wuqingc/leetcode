package d09_string;

/**
 * @author tianjiaxuan
 * leetcode214. 最短回文串
 *
 * 前后拼接都可以,在准备字符串环节反转一下就可以.
 */
public class Demo_02_3_Manacher_ShortestEnd {

    public static String shortestPalindrome(String s) {
        char[] chars = splicing(s);

        int[] radius = new int[chars.length];
        int right = -1;
        int center = -1;
        int maxContainsEnd = -1;

        for (int i = 0; i < chars.length; i++) {
            radius[i] = i >= right ? 1 : Math.min(radius[2 * center - i], right - i);

            while (i + radius[i] < chars.length && i - radius[i] > -1) {
                if (chars[i + radius[i]] == chars[i - radius[i]]) {
                    radius[i]++;
                } else {
                    break;
                }
            }
            /*
             * 比较完之后更新
             */
            if (i + radius[i] > right) {
                right = i + radius[i];
                center = i;
            }
            if (right == chars.length) {
                maxContainsEnd = radius[i];
                break;
            }
        }

        /*
         * 根据对称中心拿右侧点.
         */
        char[] res = new char[s.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = chars[i * 2 + 1];
        }
        return s + String.valueOf(res);
    }

    private static char[] splicing(String s) {
        char[] source = s.toCharArray();
        char[] result = new char[source.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            result[i] = i % 2  == 0 ? '#' : source[index++];
        }
//        for (int i = result.length - 1; i >= 0; i--) {
//            result[i] = i % 2  == 0 ? '#' : source[index++];
//        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(shortestPalindrome("abcd"));
    }

}
