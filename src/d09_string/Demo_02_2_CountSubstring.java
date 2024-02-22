package d09_string;

/**
 * @author tianjiaxuan
 * @create 2023-03-15 10:45
 * leetcode647. 回文子串
 */
public class Demo_02_2_CountSubstring {

    public static int countSubstrings(String s) {
        char[] chars = splicing(s);

        int[] radius = new int[chars.length];
        int right = -1;
        int center = -1;
        int count = s.length();

        for (int i = 0; i < chars.length; i++) {
            radius[i] = i >= right ? 1 : Math.min(radius[center - (i - center)], right - i);

            while (i + radius[i] < chars.length && i - radius[i] > -1) {
                if (chars[i + radius[i]] == chars[i - radius[i]]) {
                    radius[i]++;
                } else {
                    break;
                }
            }

            int test = radius[i];
            while (test - 2 > 0) {
                count++;
                test -= 2;
            }
            /*
             * 比较完之后
             */
            if (i + radius[i] > right) {
                right = i + radius[i];
                center = i;
            }
        }
        return count;
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
        System.out.println(countSubstrings("aaaaa"));
    }
}
