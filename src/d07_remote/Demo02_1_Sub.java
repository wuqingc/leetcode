package d07_remote;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianjiaxuan
 * 子集型回溯.
 */
public class Demo02_1_Sub {
    /*
     * 78. 子集
     */
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> cur = new ArrayList<>();
    /*
     *
     */
    private void dfsInput(int[] nums, int i) {
        if (i == nums.length) {
            ans.add(new ArrayList<>(cur));
            return;
        }

        dfsInput(nums, i + 1);

        cur.add(nums[i]);
        dfsInput(nums, i + 1);
        cur.remove(cur.size() - 1);
    }
    private void dfsAnswer(int[] nums, int i) {
        ans.add(new ArrayList<>(cur));
        if (i == nums.length) {
            return;
        }
        for (int j = i; j < nums.length; j++) {
            cur.add(nums[j]);
            dfsAnswer(nums, j + 1);
            cur.remove(cur.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        dfsAnswer(nums, 0);
        return ans;
    }
}
