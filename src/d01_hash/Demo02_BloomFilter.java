package d01_hash;

import java.util.BitSet;

/**
 * @author tianjiaxuan
 * 布隆过滤器基本实现
 *
 * BitSet是Java中用于表示位数组的类，它提供了一组方法来对位数组进行操作。
 * BitSet中每个元素都是一个二进制位，即 0 或 1。一个 BitSet 中可以存储任意长度的位数组，其长度由创建时指定的参数决定。
 *
 * 以下是一些常用的 BitSet 方法：
 * set(int index, boolean value)：将位数组中指定位置的值设为指定的布尔值；
 * get(int index)：获取位数组中指定位置的值；
 * clear()：将位数组中的所有位设为 0；
 *
 * and(BitSet set)：将位数组和指定的位数组进行逻辑与操作，并将结果存储在当前位数组中；
 * or(BitSet set)：将位数组和指定的位数组进行逻辑或操作，并将结果存储在当前位数组中；
 * xor(BitSet set)：将位数组和指定的位数组进行逻辑异或操作，并将结果存储在当前位数组中。
 */
public class Demo02_BloomFilter {

    static class BloomFilter {
        private BitSet bits;
        private int size;
        private int hashCount;

        public BloomFilter(int size, int hashCount) {
            this.bits = new BitSet(size);
            this.size = size;
            this.hashCount = hashCount;
        }

        public void add(String element) {
            /*
             * 找到在位图中的相对位置,描黑
             */
            for (int i = 0; i < hashCount; i++) {
                int hash = (element.hashCode() ^ i) % size;
                bits.set(Math.abs(hash), true);
            }
        }

        public boolean contains(String element) {
            /*
             * 在多个hash函数中,如果有一个为黑,那就直接返回false
             */
            for (int i = 0; i < hashCount; i++) {
                int hash = (element.hashCode() ^ i) % size;
                if (!bits.get(Math.abs(hash))) {
                    return false;
                }
            }
            return true;
        }
    }


    public static void main(String[] args){
        String inputUrl = "www.baidu.com";

        BloomFilter bloomFilter = new BloomFilter(1000,1);

        bloomFilter.add(inputUrl);

        System.out.println(bloomFilter.contains("www.souhu.com"));
        System.out.println(bloomFilter.contains("www.baidu.com"));
    }
}
