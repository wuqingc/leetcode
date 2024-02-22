package d06_hash;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author tianjiaxuan
 * @create 2023-03-20 19:17
 * 有一个包含100亿个URL的大文件,假设每个URL占用64Byte.
 * 请找出其中所有重复的URL.
 *
 * 1.能接受失误率吗? 如果能,直接使用布隆过滤器来统计,发现进入过集合,输出.
 * 2.不能接受,采用hash分流的方式,相同的URL一定会分入相同的文件中,再针对于这批小文件读入内存统计.
 *
 * 我们首先对大文件进行hash分流,将其分成100个小文件,每个小文件大小为:
 * 100000000*64/1024/1024/1024 ==> 6GB 可以接受的程度.
 * 然后对每个小文件采用set存储,如果发现当前line已经添加过了,就可以直接输出.
 */
public class Demo04_1_Hash {

    /**
     * 将URL分成的小文件数
     */
    private static final int FILE_NUM = 100;

    /**
     * 分流所采用的hash函数.
     */
    private static long hash(String str) {
        return str.hashCode() & Integer.MAX_VALUE;
    }

    private static void splitFile(String inputFile,String outputDir) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter[] bufferedWriters = new BufferedWriter[FILE_NUM];
        for (int i = 0; i < FILE_NUM; i++) {
            bufferedWriters[i] = new BufferedWriter(new FileWriter(outputDir + "/" + i + ".txt"));
        }

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            long hashCode = hash(line);
            int fileIndex = (int) (hashCode % FILE_NUM);
            bufferedWriters[fileIndex].write(line);
            bufferedWriters[fileIndex].newLine();
            bufferedWriters[fileIndex].flush();
        }
        bufferedReader.close();
        for (int i = 0; i < FILE_NUM; i++) {
            bufferedWriters[i].close();
        }
    }

    private static void findDuplicate(String outputDir) throws IOException{
        for (int i = 0; i < FILE_NUM; i++) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(outputDir + "/" + i + ".txt"));
            Set<String> duplicates = new HashSet<>(100000000);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!duplicates.add(line)) {
                    System.out.println("Duplicate URL: " + line);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String inputFile = "/tjx/demo.txt";
        String outputDir = "/tjx/output";
        splitFile(inputFile,outputDir);
        findDuplicate(outputDir);
    }
}