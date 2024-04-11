package d13_mathlogic.d11_bit;

/**
 * @author tianjiaxuan
 * @create 2023-03-21 16:31
 */
public class Demo04 {

    /**
     * leetcode371. 两整数之和
     *
     * 无进位相加 ^
     * 进位信息 & >> 1
     * 进位信息为0时,无进位相加的值就是两个数的和
     */
    public static int getSum(int a, int b) {
        int sum = a ^ b;
        int carry = (a & b) << 1;
        while (carry != 0) {
            int temp = sum ^ carry;
            carry = (sum & carry) << 1;
            sum = temp;
        }
        return sum;
    }
    public static int getSub(int a, int b) {
        return getSum(a,negNum(b));
    }

    /**
     * 实现二进制乘法.
     * 跟十进制的竖式计算相同.
     */
    public static int getMulti(int a, int b) {
        int sum = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                sum += a;
            }
            a <<= 1;
            b >>>= 1;
        }
        return sum;
    }

    /**
     * 取反操作
     */
    public static int negNum(int num) {
        return getSum(~num,1);
    }
    public static boolean isNeg(int num) {
        return num < 0;
    }

    /**
     * leetcode29. 两数相除
     * 被除数向前移动,不断地够除数,到达刚好小于的地步就相减.
     * 移了几位,在几位上补1(或操作进行累加补1).
     */
    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        long res = 0;
        long x = dividend;
        long y = divisor;
        x = x < 0 ? -x : x;
        y = y < 0 ? -y : y;
        for (int i = 31; i >= 0; i--) {
            /*
             * x往右移动,这样防止溢出(替代了y向左移动).
             */
            if ((x >>> i) >= y) {
                res |= 1L << i;
                x -= (y << i);
            }
        }
        return (int) (dividend<0 ^ divisor<0 ? -res : res);
    }


    public static void main(String[] args) {
        System.out.println(getSum(2,3));
        System.out.println(getSub(2,3));
        System.out.println(getMulti(2,3));
        System.out.println(divide(-1010369383,-2147483648));
    }
}
