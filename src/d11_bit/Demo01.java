package d11_bit;

/**
 * @author tianjiaxuan
 * @create 2023-03-21 16:25
 * 利用异或实现数值的交换.
 *
 */
public class Demo01 {
    /**
     * 这种情况有局限性(异或运算一定要确保交换的两数不相同才可以).
     * 并且优化效率并不高,还是推荐使用中间值(此处只是演示).
     * 异或:相同为0,不同为1.
     *
     * 第一句和第二句:
     * a = a ^ b; b = b ^ a;
     * 相当于 b = b ^ (a ^ b) = a.
     *
     * 第三句:
     * a = a ^ b;
     *   = (a^b) ^ (b ^ (a^b)) = a^b^a = a.
     */
    public static void swap(int[] arr,int i, int j) {
        if (i != j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
        }
    }
}
