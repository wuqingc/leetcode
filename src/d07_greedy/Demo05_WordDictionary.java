package d07_greedy;

/**
 * @author tianjiaxuan
 * @create 2023-03-08 15:17
 * leetcode211.数据字典
 */
public class Demo05_WordDictionary {
    static class WordDictionary {
        class TireNode {
            int end;
            TireNode[] next;

            public TireNode() {
                this.end = 0;
                this.next = new TireNode[26];
            }
        }

        TireNode root;

        public WordDictionary() {
            root = new TireNode();
        }

        public void addWord(String word) {
            if (word == null) {
                return;
            }
            TireNode root = this.root;
            char[] chs = word.toCharArray();
            for (char ch : chs) {
                int index = ch - 'a';
                if (root.next[index] == null) {
                    root.next[index] = new TireNode();
                }
                root = root.next[index];
            }
            root.end++;
        }

        public boolean search(String word) {
            if (word == null) {
                return true;
            }
            return dfs(word.toCharArray(),0,root);
        }

        /**
         * 针对于*的支持,就是一个dfs
         */
        public boolean dfs(char[] chs, int index, TireNode node) {
            if (index == chs.length) {
                return node.end > 0;
            }
            char ch = chs[index];
            if ('.' == ch) {
                for (TireNode tireNode : node.next) {
                    if (tireNode != null && dfs(chs,index + 1,tireNode)) {
                        return true;
                    }
                }
            } else {
                int curIndex = ch - 'a';
                if (node.next[curIndex] != null && dfs(chs,index + 1,node.next[curIndex])) {
                    return true;
                }
            }
            return false;
        }
    }

    // ["WordDictionary","addWord","addWord","addWord","addWord","search","search","addWord","search","search","search","search","search","search"]
    //[[],["at"],["and"],["an"],["add"],["a"],[".at"],["bat"],[".at"],["an."],["a.d."],["b."],["a.d"],["."]]
    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
//        System.out.println(wordDictionary.search("a"));
//        System.out.println(wordDictionary.search(".at"));
        wordDictionary.addWord("bat");
        System.out.println(wordDictionary.search(".at"));
//        System.out.println(wordDictionary.search("aa"));
//        System.out.println(wordDictionary.search("a"));
//        System.out.println(wordDictionary.search(".a"));
//        System.out.println(wordDictionary.search("a."));
    }
}
