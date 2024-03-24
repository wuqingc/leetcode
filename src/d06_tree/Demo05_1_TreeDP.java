package d06_tree;

/**
 * @author tianjiaxuan
 * 树形dp
 * 直接的dfs()是返回不到值的, 要记录过程数据中的最大值(答案可能会出现在任何位置).
 */
public class Demo05_1_TreeDP {

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int ans = 0;
    public int dfs(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int left = dfs(root.left) + 1;
        int right = dfs(root.right) + 1;
        ans = Math.max(ans, left + right);
        return Math.max(left, right);
    }
}
