package d06_hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;

/**
 * @author tianjiaxuan
 * @create 2023-03-20 19:54
 * 32位无符号整数的范围是0~4294967295,
 * 现在有40亿个无符号整数,可以使用最多1GB的内存,找出所有出现了两次的数.
 *
 * 1.使用hash分流,分成可以接受的小文件,然后将小文件读入内存中,维护一个hashMap.参考Demo05_1
 * 2.使用升级的位图来进行搜查,两个bit来表示一个数据(准备两个位图).
 *
 * 2^32/8/1024/1024/1024 == > 512MB
 * 需要两个.
 */
public class Demo05_2_BitSet {

    /**
     * 延续Demo04_1的思路,用BitSet数组来进行拼装,每一份的大小为:2^28,一共需要 2^4 ==> 16个
     */
    public static void method(String inputFile) throws IOException {
        double range = ((double) Integer.MAX_VALUE) * 2 + 1;
        int setSize = 1 << 28;
        int arrSize = (int) Math.ceil(range / setSize);

        BitSet[] bitSets1 = new BitSet[arrSize];
        BitSet[] bitSets2 = new BitSet[arrSize];
        for (int i = 0; i < arrSize; i++) {
            bitSets1[i] = new BitSet(setSize);
            bitSets2[i] = new BitSet(setSize);
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
        String record;
        while ((record = bufferedReader.readLine()) != null) {
            long num = Long.parseLong(record);
            int bucketSize = (int) (num / setSize);
            int bitIndex = (int) (num % setSize);
            /*
             * 00 表示出现过0次
             * 01 表示出现过1次
             * 10 表示出现过2次
             * 11 表示大于2次 可以直接忽略了.
             */
            if (!bitSets1[bucketSize].get(bitIndex) && !bitSets2[bucketSize].get(bitIndex)) {
                bitSets2[bucketSize].set(bitIndex);
            } else if (!bitSets1[bucketSize].get(bitIndex) && bitSets2[bucketSize].get(bitIndex)) {
                bitSets1[bucketSize].set(bitIndex);
                bitSets2[bucketSize].set(bitIndex,false);
            } else if (bitSets1[bucketSize].get(bitIndex) && !bitSets2[bucketSize].get(bitIndex)) {
                bitSets2[bucketSize].set(bitIndex);
            }
        }
        bufferedReader.close();
        System.out.println("数据标注完成.");

        for (int i = 0; i < arrSize; i++) {
            for (int j = 0; j < setSize; j++) {
                long num = (long) i * setSize + j;
                if (bitSets1[i].get(j) && !bitSets2[i].get(j)) {
                    System.out.println(num);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String inputFile = "/tjx/demo.txt";
        method(inputFile);
    }
}
