package d09_string;
/**
 * @author tianjiaxuan
 * KMP算法实现
 */
public class Demo_01_KMP {
    /**
     * 目标:查询str1是否包含str2:
     * 如果包含,返回具体下标;如果不包含,返回-1.
     *
     * 双指针暴力搜索是O(m*n),归并的外排是有序的,字符串本身无序,每个位置都得比较.
     *
     * 1.考虑极端情况:如果str2比str1长,肯定不可能.
     * 2.求str2的next数组.
     * 3.加速字符串匹配过程.
     *
     */
    public static int KMP(String str1,String str2) {
        if (str2 == null || str1 == null || str1.length() < 1 || str1.length() < str2.length()) {
            return -1;
        }
        int[] next = getNextArray(str2.toCharArray());
        int i = 0, j = 0;
        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else if (next[j] == -1) {
                i++;
            } else {
                j = next[j];
            }
        }
        /*
         * 只有当匹配成功的时候,j才能走完str2.
         * 否则总是一个向前找的过程.
         */
        return j == str2.length() ? i - j : -1;
    }
    /**
     * 数学归纳法求next数组
     */
    private static int[] getNextArray(char[] str2) {
        int[] next = new int[str2.length];
        if (str2.length == 1){
            next[0] = -1;
            return next;
        }
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int ch = next[i - 1];
        while (i < str2.length) {
            /*
             * 比较两个字符是否相等:
             *  如果相等,当前字符的最长匹配长度的上一个字符的匹配长度 + 1
             *  不相等时,查看ch能否向前移动
             *      如果有,向前移动ch
             *      没有,当前字符的最长匹配长度为0
             */
            if (str2[ch] == str2[i - 1]) {
                next[i++] = ++ch;
            } else if (ch > 0) {
                ch = next[ch];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }


    public static void main(String[] args) {

        String str1 = "sadbutsad";
        String str2 = "sad";
        System.out.println(KMP(str1,str2));
    }
}