package d06_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author tianjiaxuan
 *
 */
public class Demo03_1_BFS {

    /*
     * 102. 二叉树的层序遍历
     * 记录队列开始时的大小,方便分层.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> cur = new ArrayList<>();
            while (n > 0) {
                TreeNode node = queue.pollFirst();
                if (node.left != null) queue.addLast(node.left);
                if (node.right != null) queue.addLast(node.right);
                cur.add(node.val);
                n--;
            }
            ans.add(cur);
        }
        return ans;
    }
}
