package d13_mathlogic.d11_bit;

/**
 * @author tianjiaxuan
 * @create 2023-03-21 16:24
 * 给定两个有符号32位整数a和b,返回a和b中较大的.
 */
public class Demo02 {

    /**
     * 负数返回1,正数返回0;
     */
    private static int symbol(int num) {
        return (num >> 31) & 1;
    }
    /*
     * 1变0,0变1.
     */
    private static int flip(int num) {
        return num ^ 1;
    }
    /**
     * if-else 可以被替换成互斥的条件,然后用 + 相连.
     * 判断 a-b 的符号位,如果是正数就返回a,负数就返回b.
     *
     * 这种情况可能会溢出.
     */
    private static int bigNum(int num1,int num2) {
        int result = num1 - num2;
        int syc = symbol(result);
        return num2 * syc + num1 * flip(syc);
    }

    /**
     * 防止溢出的情况:
     * 1.a b 符号相同,差值为正数,返回a
     * 2.a b 符号不相同,a是正数,返回a
     * 3.返回b的情况就是a的相反.
     */
    private static int bigNum2(int num1,int num2) {
        int result = num1 - num2;

        // 正数为1 负数为0
        int syc = flip(symbol(result));
        int sy1 = flip(symbol(num1));
        int sy2 = flip(symbol(num2));

        // 不同为1
        int diff12 = sy1 ^ sy2;
        // 相同为1
        int same12 = flip(diff12);

        // ab符号相同且c为正数 ab符号不同且a为正数
        int returnA = same12 * syc + diff12 * sy1;

        return num2 * flip(returnA) + num1 * returnA;
    }

    public static void main(String[] args) {
        int num1 = 100000;
        int num2 = 200000;
        System.out.println(bigNum(num1,num2));

        num1 = 2147483647;
        num2 = -2147480000;
        System.out.println(bigNum2(num1, num2));
    }
}
