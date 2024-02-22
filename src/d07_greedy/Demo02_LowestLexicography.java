package d07_greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个字符串类型的数组strs,找到一种拼接方式.
 * 使得把所有字符串拼起来之后形成的字符串具有最低的字典序.
 * @author tianjiaxuan
 *
 * 制定一个贪心策略,置于比较器中,将字符串数组进行排序后拼接即可.
 *
 * 比较策略:
 * str1 + str2 <= str2 + str1
 * 使str1置前.
 *
 * 字典序:字母在字典中的顺序,扩展为两个字符串的大小关系.
 * 长度相同时,直接比较值即可;
 * 长度不同时,将短的字符串高位对齐,不足为补0(ASCII码).
 * 例:"abc" < "b"  ==> "abc" < "b00"
 */
public class Demo02_LowestLexicography {
    static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    /**
     * 给定一个字符串类型的数组strs.
     * 找到一种拼接方式,使得把所有字符串拼起来之后形成的字符串具有最低的字典序.
     */
    private static String lowestString(String[] strings) {
        Arrays.sort(strings,new StringComparator());
        StringBuilder result = new StringBuilder();
        for (String str : strings) {
            result.append(str);
        }
        return result.toString();
    }


    public static void main(String[] args) {
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        System.out.println(lowestString(strs1));

        String[] strs2 = { "ba", "b" };
        System.out.println(lowestString(strs2));

    }
}
