package d01_hash;

import java.io.*;
import java.util.BitSet;

/**
 * @author tianjiaxuan
 * @create 2023-03-20 15:17
 *
 * 32位无符号整数的范围是0~4,294,967,295 ==> 42亿
 * 现在有一个正好包含40亿个无符号整数的文件,所以在整个范围中必然存在没出现过的数.
 * 可以使用最多1GB的内存,怎么找到所有未出现过的数？
 */
public class Demo05_1_BitSet {

    /**
     * 1.用位图表示所有的数据
     * 2.遍历位图,如果当前位置没被描黑,就拿出来这个位置的数据
     *
     * 位图的大小估计:
     * 2^32 / 8  = 2^29 Byte
     * 2^29 = 2^9M = 512MB
     * 没有超出限制.
     *
     * Java的BitSet使用int来表示,可以进一步对位图进行拆分16份:
     * 2^32 / 2^4 == > 2^28
     * 用16个2^28长度的BitSet去拼接成一个2^32的二进制图.
     *
     * 1 << 28位,从2^0移动到2^28.
     */
    private static void method01() throws IOException {
        double range = ((double) Integer.MAX_VALUE) * 2 + 1;
        int setSize = 1 << 28;
        int arrSize = (int) Math.ceil(range / setSize);

        BitSet[] bitSets = new BitSet[arrSize];
        for (int i = 0; i < arrSize; i++) {
            bitSets[i] = new BitSet(setSize);
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/tjx/demo.txt"));
        String record;
        while ((record = bufferedReader.readLine()) != null) {
            long num = Long.parseLong(record);
            int bucketSize = (int) (num / setSize);
            int bitIndex = (int) (num % setSize);
            bitSets[bucketSize].set(bitIndex);
        }
        bufferedReader.close();
        System.out.println("数据标注完成.");

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/tjx/res.txt"));
        for (int i = 0; i < arrSize; i++) {
            for (int j = 0; j < setSize; j++) {
                long num = (long) i * setSize + j;
                if (!bitSets[i].get(j)) {
                    bufferedWriter.write(num + "\n");
                    bufferedWriter.flush();
                }
            }
        }
        bufferedWriter.close();
    }


    /**
     * 内存限制为10MB，但是只用找到一个没出现过的数即可
     * 1.给多大空间,算能开多大空间的位图
     * 2.看当前位图有没有满,没满
     * 3.在当前位图找没涂黑的位置,直接返回
     */
    private static void method02() throws IOException{
        double range = ((double) Integer.MAX_VALUE) * 2 + 1;
        int setSize = 1 << 26;
        int arrSize = (int) Math.ceil(range / setSize);

        for (int i = 0; i < arrSize; i++) {
            BitSet bitSet = new BitSet(setSize);
            long left = (long) i * setSize;
            long right = left + setSize;

            BufferedReader bufferedReader = new BufferedReader(new FileReader("/tjx/data.txt"));
            String record;
            while ((record = bufferedReader.readLine()) != null) {
                long num = Long.parseLong(record);
                if (num >= left && num < right) {
                    bitSet.set((int) (num - left));
                }
            }
            bufferedReader.close();
            System.out.println("数据标注完成.");

            if (bitSet.cardinality() < setSize) {
                for (int j = 0; j < setSize; j++) {
                    long num = left + j;
                    if (bitSet.get(j)) {
                        System.out.println(num);
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        method01();
        method02();
    }
}
