package d01_hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author tianjiaxuan
 * @create 2023-03-20 19:55
 *
 * 32位无符号整数的范围是0~4294967295,现在有40亿个无符号整数.
 * 可以使用最多10MB的内存,怎么找到这40亿个整数的中位数？
 *
 * 2^13Byte / 4Byte = 2^11 == 2048个int
 *
 * 1.准备2048个桶,每个桶有自己的范围,计数,找到刚好20亿的位置就停在这个桶.
 * 2.针对于这个桶继续拆分2048份,可拆分的数已经小于2048为止
 * 3.将这批数填入并排序
 * 4.输出指定位置的数
 */
public class Demo06_1_Bucket {
    private static long method(String inputFile,long length) throws IOException {
        long median = length / 2;
        int left = 0;
        double right = (double) Integer.MAX_VALUE * 2 + 1;
        int sum = 0;
        int arraySize = 2048;

        while (left + arraySize < right) {
            int[] buckets = new int[arraySize];
            int bucketSize = (int) ((right - left) / arraySize);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                long num = Long.parseLong(line);
                if (num >= left && num <= right) {
                    buckets[(int) ((num - left)/bucketSize)]++;
                }
            }
            bufferedReader.close();

            for (int i = 0; i < arraySize; i++) {
                sum += buckets[i];
                if (sum > median) {
                    sum = sum - buckets[i];
                    left = left + i * bucketSize;
                    right = left + bucketSize;
                    break;
                }
            }
        }

        List<Long> sort = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            long num = Long.parseLong(line);
            if (num >= left && num <= right) {
                sort.add(num);
            }
        }
        bufferedReader.close();
        Collections.sort(sort);
        int i = -1;
        while (sum < median) {
            i++;
            sum++;
        }
        if (length % 2 == 0) {
            return (long) ((sort.get(i) + sort.get(i + 1)) / 2.0);
        } else {
            return sort.get(i);
        }
    }

    public static void main(String[] args) throws IOException {
        String inputFile = "/tjx/demo.txt";
        System.out.println(method(inputFile, 518638));
    }
}
