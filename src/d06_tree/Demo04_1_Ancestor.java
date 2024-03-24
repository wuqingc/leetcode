package d06_tree;

/**
 * @author tianjiaxuan
 *
 * 从root向下dfs:
 * 1.如果当前节点就是p or q, 直接返回
 * 2.递归找左右子树是否有p q
 * 3.如果左右子树都不为空(各有p q一个), 当前节点就是最近公共祖先(分叉点)
 * 4.只有一个不为空,这个子树的头节点就是最近公共祖先
 */
public class Demo04_1_Ancestor {

    /*
     * 236. 二叉树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
