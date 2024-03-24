package d00_array;

/**
 * @author tianjiaxuan
 * 数组版本,leetcode用.
 */
public class Demo04_HeapSortArray {
    public int[] sortArray(int[] nums) {
        Heap integerHeap = new Heap(nums.length);
        for (int i = 0; i < nums.length; i++) {
            integerHeap.append(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = integerHeap.pop();
        }
        return nums;
    }

    class Heap {
        private int[] data;
        private int length = 0;

        Heap(int length) {
            data = new int[length];
        }

        public void append(int x) {
            data[length++] = x;
            int cur = length - 1;
            while (cur != 0 && data[cur] < data[(cur - 1) / 2]) {
                swap(data, cur, (cur - 1) / 2);
                cur = (cur - 1) / 2;
            }
        }

        public int pop() {
            swap(data, 0, length - 1);
            int x = data[length - 1];
            length--;
            int cur = 0;

            while (cur * 2 + 1 < length) {
                int left = cur * 2 + 1;
                int smallest = left;
                if (left + 1 < length) {
                    smallest = data[left] < data[left + 1] ? left : left + 1;
                }
                if (data[cur] < data[smallest]) {
                    break;
                }
                swap(data, cur, smallest);
                cur = smallest;
            }
            return x;
        }

        private void swap(int[] nums, int l1, int l2) {
            int t = nums[l1];
            nums[l1] = nums[l2];
            nums[l2] = t;
        }
    }
}
