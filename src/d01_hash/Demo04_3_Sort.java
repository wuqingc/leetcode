package d01_hash;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author tianjiaxuan
 * @create 2023-03-22 09:36
 * 有10GB的int类型数据文件,5GB内存可用.
 * 如何完成对输出一个有序的新文件?
 */
public class Demo04_3_Sort {

    /**
     * 将堆改为TreeMap,记录次数,如果重复数据较多的情况下.
     * 内存相对来说还是比较大的情况下采用(较少的文件读取次数):
     * 一个int ==> 4Byte.
     * 5GB内存 == > 5 * 2^30 / 4 ==> 2^30个int
     *
     * int类型数据的范围 -2^31 ~ 2^31-1
     * 分份数,每份2^30大小.
     * 维护一个小根堆,只记录当前范围的数.
     */
    private static void method01(String inputFile,String outputFile) throws IOException {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));

        long left = 1 << 31;
        int per = 1 << 30;
        long right = left + per;

        int count = 0;
        int needCount = 4;

        while (count < needCount) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int number = Integer.parseInt(line);
                if (number >= left && number < right) {
                    minHeap.offer(number);
                }
            }
            bufferedReader.close();
            System.out.println("读取数据完成.");
            System.out.println(left + "\t" + right);

            while (!minHeap.isEmpty()) {
                bufferedWriter.write(minHeap.poll() + "\n");
                bufferedWriter.flush();
            }
            left = right;
            right = right + per;
            count++;
        }
        bufferedWriter.close();
    }


    public static void main(String[] args) throws IOException {
        String inputFile = "/tjx/demo.txt";
        String outputFile = "/tjx/res.txt";
        method01(inputFile,outputFile);

    }
}
