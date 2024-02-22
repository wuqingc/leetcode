package d07_greedy;

/**
 * @author tianjiaxuan
 * @create 2023-03-06 19:36
 * 前缀树:在树的边上存储单个字符,通过压缩存储空间以及提高搜查效率.
 * 查找的时间复杂度O(n)
 * leetcode208
 */
public class Tire {
    /**
     * 前缀树的节点
     * 1.path: 有多少个字符串经过当前节点
     * 2.end: 有多少个字符串以当前节点结尾
     * 3.next: 当前节点后面有哪些路径
     * 如果字符不止26个,可以换成hash表.表达路的方式有很多.
     */
    private static class TireNode {
        public int path;
        public int end;
        public TireNode[] next;

        public TireNode() {
            this.path = 0;
            this.end = 0;
            next = new TireNode[26];
        }
    }

    public TireNode root;

    public Tire() {
    }

    /**
     * 新增一个字符串.
     * 1.从根节点往下找,如果根节点下面的路没有当前字符,新建一条路;
     * 2.只要有路,这个节点就要被新的字符串滑过,path++
     * 3.for结束之后的节点,是新字符串的结尾,end++
     */
    public void insert(String word) {
        if (word == null) {
            return;
        }
        TireNode tireNode = root;
        int index = 0;
        for (char ch : word.toCharArray()) {
            index = ch - 'a';
            if (tireNode.next[index] == null) {
                tireNode.next[index] = new TireNode();
            }
            tireNode = tireNode.next[index];
            tireNode.path++;
        }
        tireNode.end++;
    }

    /**
     * 判断当前字符串是否存在于前缀树中,如果存在就返回次数.
     */
    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        TireNode tireNode = root;
        int index = 0;
        for (char ch : word.toCharArray()) {
            index = ch - 'a';
            if (tireNode.next[index] == null) {
                return false;
            }
            tireNode = tireNode.next[index];
        }
        return tireNode.end > 0;
    }
    /**
     * 查询是否出现过以该字符串为前缀.
     */
    public boolean startsWith(String word){
        if (word == null){
            return false;
        }
        int index = 0;
        TireNode tireNode = root;
        for (char ch : word.toCharArray()) {
            index = ch - 'a';
            if (tireNode.next[index] == null) {
                return false;
            }
            tireNode = tireNode.next[index];
        }
        return true;
    }

    /**
     * 前缀树的删除:
     * 1.防止误删,得先确定是不是有这个字符串.如果树中不包含当前字符串,直接返回
     * 2.从上往下删除,沿途的pass节点--,最后一个字符的end--
     */
    public void delete(String word) {
        if (search(word)) {
            TireNode tireNode = root;
            int index = 0;
            for (char ch : word.toCharArray()) {
                tireNode.path--;
                index = ch - 'a';
                /*
                 * 下一个pass--之后已经是0了 直接把下级节点置空
                 */
                if (tireNode.next[index].path - 1 == 0) {
                    tireNode.next[index] = null;
                    return;
                }
                tireNode = tireNode.next[index];
            }
            tireNode.end--;
        }
    }

    public static void main(String[] args) {
        Tire trie = new Tire();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }
}