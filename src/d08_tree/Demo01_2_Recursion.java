package d08_tree;

public class Demo01_2_Recursion {

    /*
     * 100. 相同的树
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null) {
            return p.val == q.val
                    && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }


    /*
     * 1448. 统计二叉树中好节点的数目
     */
    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }
    public int dfs(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        if (root.val >= val) {
            return dfs(root.left, root.val) + dfs(root.right, root.val) + 1;
        } else {
            return dfs(root.left, val) + dfs(root.right, val);
        }
    }

}
