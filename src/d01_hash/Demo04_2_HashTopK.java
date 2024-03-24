package d01_hash;

import java.io.*;
import java.util.*;

/**
 * @author tianjiaxuan
 * @create 2023-03-20 19:47
 * 某搜索公司一天的用户搜索词汇是海量的(百亿数据量),请设计一种求出每天热门Top100词汇的可行办法.
 *
 * 1.拆分成100个文件,每个文件6GB左右,能接受.
 * 2.针对每个文件找出TOP100并全部存入内存中,64Byte * 100 * 100 => 625MB == > 1G, 能接受.
 * 3.整体的TOP100一定在这100个Top100中.
 * 4.将100*100全部读入大根堆,弹出前100个即可.
 */
public class Demo04_2_HashTopK {
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
    private static void splitFile(String inputFile,String outputDir) throws IOException {
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


    private static PriorityQueue<Map.Entry<String,Integer>> heapConstruct(String outputDir) throws IOException{
        PriorityQueue<Map.Entry<String,Integer>> result = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for (int i = 0; i < FILE_NUM; i++) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(outputDir + "/" + i + ".txt"));
            Map<String,Integer> duplicates = new HashMap<>(100000000);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        if (duplicates.containsKey(line)) {
                            duplicates.put(line,duplicates.get(line) + 1);
                        } else {
                            duplicates.put(line,1);
                        }
                    }
                }
            }
            bufferedReader.close();
            List<Map.Entry<String, Integer>> list = new ArrayList<>(duplicates.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            for (int j = 0; j < Math.min(100,list.size()); j++) {
                result.offer(list.get(j));
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        String inputFile = "/tjx/demo.txt";
        String outputDir = "/tjx/output";
        splitFile(inputFile,outputDir);
        PriorityQueue<Map.Entry<String,Integer>> result = heapConstruct(outputDir);
        int k = 100;
        while (k > 0) {
            System.out.println(result.poll().getKey());
            k--;
        }
    }
}
