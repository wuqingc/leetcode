package d06_hash;

import java.util.*;

/**
 * @author tianjiaxuan
 * leetcode380. O(1) 时间插入、删除和获取随机元素
 */
public class Demo01_RandomPool {
    private static class RandomizedSet {
        private Map<Integer,Integer> sourceData = new HashMap<>();
        private List<Integer> indexData = new ArrayList<>();

        public RandomizedSet() {

        }

        public boolean insert(int val) {
            if (this.sourceData.containsKey(val)) {
                return false;
            }
            this.sourceData.put(val,this.sourceData.size());
            indexData.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!this.sourceData.containsKey(val)) {
                return false;
            }
            /*
             * 1.map获取当前元素的下标,然后map删除元素.
             * 2.将index的最后一个元素放到被map删除的位置,移除最后一个元素.
             *
             * 3.在index中,最后一个元素的下标改变到被删除的那里了,所以map存的下标也应该被重新put().
             *   put()的条件: 删除的下标小于最后一个.
             */
            int delIndex = sourceData.get(val);
            this.sourceData.remove(val);

            int lastIndex = this.indexData.size() - 1;
            this.indexData.set(delIndex,this.indexData.get(lastIndex));
            this.indexData.remove(lastIndex);

            if (delIndex < lastIndex) {
                this.sourceData.put(this.indexData.get(delIndex),delIndex);
            }
            return true;
        }

        public int getRandom() {
            int rand = (int) (Math.random() * this.indexData.size());
            return this.indexData.get(rand);
        }
    }

    public static void main(String[] args) {
        //["insert","insert","getRandom","getRandom","insert","remove","getRandom","getRandom","insert","remove"]
        //[[3],[3],[],[],[1],[3],[],[],[0],[0]]
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(3));
        System.out.println(randomizedSet.insert(3));
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.getRandom());

        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.remove(3));
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.getRandom());

        System.out.println(randomizedSet.insert(0));
        System.out.println(randomizedSet.remove(0));
    }

}
