package d08_tree;

/**
 * @author tianjiaxuan
 *
 * [5,4,6,null,null,3,7] 不是一颗二叉搜索树,因为 5 > 3.
 * 二叉搜索树的中序遍历是一个有序数组.
 */
public class Demo02_1_Traverse {

    /*
     * 98. 验证二叉搜索树
     * 中序遍历,记录上一个节点的值, 当前节点的值一定 > 上一个节点.
     */
    private int pre = Integer.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        return isValidBST(root.right);
    }
}
