package d00_array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianjiaxuan
 * 堆排序
 * <p>
 * 数组表示完全二叉树
 * 当前节点的父亲 (i - 1) // 2
 * 当前节点的左右儿子 2i + 1    2i + 2
 *
 * 1.建立小根堆, 把最小的放在堆顶.
 * 2.插入, 新来的元素补在末尾.
 *      从堆尾开始校验:
 *      不停的跟父节点进行比较, 如果比父节点小就往上换.
 * 3.弹出,末尾元素与0位置元素交换, 弹出末尾元素.
 *      从堆顶开始校验:
 *      cur = 0, 选它左右孩子中较小的, 与 cur 比较, 比 cur 小就交换.
 *      发生交换后, cur换到这个子树的下标, 继续向下换(没发生变化的那棵树就不管了).
 */
public class Demo04_HeapSort {
    public static void main(String[] args) {
        new Demo04_HeapSort().sortArray(new int[]{5,1,1,2,0,0});
    }

    public int[] sortArray(int[] nums) {
        Heap<Integer> integerHeap = new Heap<>();
        for (int i = 0; i < nums.length; i++) {
            integerHeap.append(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = integerHeap.pop();
        }
        return nums;
    }
    class Heap<T extends Comparable> {
        private List<T> data;

        Heap() {
            data = new ArrayList<>();
        }

        public void append(T element) {
            data.add(element);
            int cur = data.size() - 1;
            while (cur != 0
                    && data.get(cur).compareTo(data.get((cur - 1) / 2)) < 0) {
                swap(data, cur, (cur - 1) / 2);
                cur = (cur - 1) / 2;
            }

        }

        public T pop() {
            if (data.isEmpty()) {
                throw new RuntimeException("堆为空.");
            }
            int n = data.size();
            swap(data, 0, n - 1);
            T v = data.get(n - 1);
            data.remove(n - 1);
            n -= 1;

            int cur = 0;
            while (cur * 2 + 1 < n) {
                int left = cur * 2 + 1;
                int smallest = left;
                if (left + 1 < n && data.get(left + 1).compareTo(data.get(left)) < 0) {
                    smallest = left + 1;
                }
                if (data.get(cur).compareTo(data.get(smallest)) < 0) {
                    break;
                }
                swap(data, cur, smallest);
                cur = smallest;
            }
            return v;
        }

        private void swap(List<T> nums, int l1, int l2) {
            T t = nums.get(l1);
            nums.set(l1, nums.get(l2));
            nums.set(l2, t);
        }
    }
}

