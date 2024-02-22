package d06_hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author tianjiaxuan
 *
 * 一致性哈希具体实现.
 * 将服务器的hash值排序存入数组中.
 * 当有请求时,二分查找存入机器的数组,找到数组中第一个 >= 当前hashCode的位置(顺时针).
 */
public class Demo03_ConsistentHash {

    static class ConsistentHash<T> {
        /*
         * 有序表章节计算.
         */
        private final SortedMap<Integer, T> ring = new TreeMap<>();
        private final HashFunction hashFunction;

        public ConsistentHash(HashFunction hashFunction) {
            this.hashFunction = hashFunction;
        }

        public void add(T node) {
            ring.put(hashFunction.hash(node.toString()), node);
        }

        public void remove(T node) {
            ring.remove(hashFunction.hash(node.toString()));
        }

        public T get(Object key) {
            if (ring.isEmpty()) {
                return null;
            }
            int hash = hashFunction.hash(key.toString());
            if (!ring.containsKey(hash)) {
                SortedMap<Integer, T> tailMap = ring.tailMap(hash);
                hash = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
            }
            return ring.get(hash);
        }

        public interface HashFunction {
            int hash(String key);
        }

        public static class MD5HashFunction implements HashFunction {
            private MessageDigest md5;

            public MD5HashFunction() {
                try {
                    md5 = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException("MD5 Hash Function not found");
                }
            }

            public int hash(String key) {
                md5.reset();
                md5.update(key.getBytes());
                byte[] bKey = md5.digest();
                int res = ((int) (bKey[3] & 0xFF) << 24)
                        | ((int) (bKey[2] & 0xFF) << 16)
                        | ((int) (bKey[1] & 0xFF) << 8)
                        | ((int) (bKey[0] & 0xFF));
                return res;
            }
        }
    }



    public static void main(String[] args) {
        long ring = (long) Math.pow(2,32);

        List<String> serverMacs = new ArrayList<>();
        serverMacs.add("172.18.101.158");
        serverMacs.add("142.251.10.90");
        serverMacs.add("199.232.68.133");
        serverMacs.add("192.168.31.135");

        String request = "具体请求";

        /*
         * 对2^32进行取模运算,组成一个0~2^32的环.
         */
        long[] serverList = new long[serverMacs.size()];
        int i = 0;
        for (String serverMac : serverMacs) {
            serverList[i++] = serverMac.hashCode() % ring;
        }
        Arrays.sort(serverList);
        long findServer = request.hashCode() % ring;
        int left = 0;
        int right = serverList.length;

        /*
         * 二分查找
         * 顺时针离当前位置最近的机器.
         */
        while (left < right) {
            int mid = left + (right - left) >> 1;
            if (serverList[mid] == findServer) {
                break;
            }
            if (findServer > serverList[mid]) {
                left = mid + 1;
            } else if (findServer < serverList[mid]) {
                right = mid;
            }
        }

        System.out.println(findServer);
        for (long temp : serverList) {
            System.out.print(temp + "\t");
        }
        System.out.println();

        System.out.println("定位到机器:\t" + left);
    }

}
