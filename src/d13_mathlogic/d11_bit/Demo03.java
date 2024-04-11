package d13_mathlogic.d11_bit;

/**
 * @author tianjiaxuan
 * @create 2023-03-21 16:31
 */
public class Demo03 {

    /**
     * leetcode231. 2 的幂
     * 如果是2的幂,在二进制中的体现就是只有一位为1.
     * 让其与它的-1进行与操作,会把这个唯一的1打散掉,与操作就全是0了.
     */
    public static boolean isPowerOfTwo(int n) {
        return n != 0 && ((long) n & ((long) n - 1)) == 0;
    }

    /**
     * leetcode342. 4的幂
     * 先保证是2的幂的情况下,对3取余等于1.
     */
    private static boolean isPowerOfFour(int n) {
        if (n != 0 && ((long) n & ((long) n - 1)) == 0) {
            return n % 3 == 1;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(-2147483648));

    }
}
