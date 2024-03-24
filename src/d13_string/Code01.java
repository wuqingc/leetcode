package d13_string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tianjiaxuan
 * @create 2023-03-14 16:25
 * leetcode2586. 统计范围内的元音字符串数
 */
public class Code01 {
    public static int vowelStrings(String[] words, int left, int right) {
        Set<Character> hashset = new HashSet<>();
        hashset.add('a');
        hashset.add('e');
        hashset.add('i');
        hashset.add('o');
        hashset.add('u');
        int count = 0;
        for (int i = left; i <= right && i < words.length; i++) {
            if (hashset.contains(words[i].charAt(0))) {
                count++;
            }
        }
        return count;

    }

    public static void main(String[] args) {

    }
}
