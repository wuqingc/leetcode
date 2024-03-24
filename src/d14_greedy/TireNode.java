package d14_greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianjiaxuan
 * @date 2023-04-23 21:19
 */
public class TireNode {
    private final static int N = 65536;  // Unicode字符集的大小
    private boolean isEnd;              // 当前节点是否是一个单词的结尾
    private TireNode[] children;        // 子节点数组

    public TireNode() {
        isEnd = false;
        children = new TireNode[N];
    }

    public void insert(String word) {
        TireNode curr = this;
        for (char c : word.toCharArray()) {
            if (curr.children[c] == null) {
                curr.children[c] = new TireNode();
            }
            curr = curr.children[c];
        }
        curr.isEnd = true;
    }

    public List<String> search(String prefix) {
        TireNode curr = this;
        for (char c : prefix.toCharArray()) {
            if (curr.children[c] == null) {
                return new ArrayList<>();  // 如果前缀不匹配，则返回空列表
            }
            curr = curr.children[c];
        }
        List<String> result = new ArrayList<>();
        collectWords(curr, prefix, result);  // 收集以当前节点为根的子树中的单词
        return result;
    }

    private void collectWords(TireNode node, String prefix, List<String> result) {
        if (node.isEnd) {
            result.add(prefix);  // 如果当前节点是一个单词的结尾，则将当前前缀添加到结果列表中
        }
        for (int i = 0; i < N; i++) {
            if (node.children[i] != null) {
                collectWords(node.children[i], prefix + (char) i, result);  // 递归遍历子树
            }
        }
    }

    public static void main(String[] args) {

        TireNode tireNode = new TireNode();
        tireNode.insert("北京大学");
        tireNode.insert("北京大学2");
        tireNode.insert("清华大学");

        System.out.println(tireNode.search("大学"));
    }
}