package d09_remote;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianjiaxuan
 * 子集型回溯.
 * 两种实现方式:
 * 1.输入顺序: 当前位置选或不选
 * 2.答案视角: 当前位置选哪个
 * <p>
 * 回溯就是在递归的过程中, 增量构造答案.
 * 1.出口条件是什么?
 * 2.当前这步操作什么?
 * 3.下一个子问题?
 */
public class Demo02_1_Sub {
    /*
     * 78. 子集
     * 从输入的角度来看:
     * 1.遍历完所有位置, 返回
     * 2.从输入的角度看, 每一个位置都有选或不选两种决策.
     * 3.选完这个位置, 递推到下一个位置
     *
     * 从构造答案的角度来看:
     * 1.答案的长度不固定, 所以每一步都要记录答案. 当num位置选完之后, 返回.
     * 2.因为[1,2] [2,1]是同一个子集,为了避免这种情况, 答案的这个位置可选的就是当前下标以及它后面的所有数之一.
     * 3.递推到答案的下一个位置选什么.
     */
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> cur = new ArrayList<>();

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


    /*
     * 131. 分割回文串
     * 枚举答案选哪个,然后判断枚举的答案是否为回文.
     * 1.分割, 意味着必须要枚举完所有的位置, 才能记录答案并返回
     * 2.当前操作: 枚举答案选哪个子串, 且这个子串得是回文
     * 3.下一步操作:递推到当前位置往后.
     *
     * 输入顺序视角: 当前逗号选不选?
     * 所以需要额外维护一个指针,表示字符串截取到的实际位置.
     */
    public List<List<String>> partition(String s) {
        dfsInput2(s, 0, 0);
        return ans2;
    }

    private List<List<String>> ans2 = new ArrayList<>();
    private List<String> path = new ArrayList<>();

    private void dfsAnswer2(String s, int i) {
        if (i == s.length()) {
            ans2.add(new ArrayList<>(path));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            String t = s.substring(i, j + 1);
            if (isPlindrome(t)) {
                path.add(t);
                dfsAnswer2(s, j + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    private void dfsInput2(String s, int i, int curs) {
        if (i == s.length()) {
            if (curs == s.length()) {
                ans2.add(new ArrayList<>(path));
            }
            return;
        }

        dfsInput2(s, i + 1, curs);

        String t = s.substring(curs, i + 1);
        if (isPlindrome(t)) {
            path.add(t);
            dfsInput2(s, i + 1, i + 1);
            path.remove(path.size() - 1);
        }

    }

    private boolean isPlindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
