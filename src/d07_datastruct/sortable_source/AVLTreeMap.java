package d07_datastruct.sortable_source;

/**
 * @author tianjiaxuan
 * @date 2023-12-11 13:53
 * Java工具库的有序表使用红黑树来实现.
 * AVL树实现的TreeMap.
 */
public class AVLTreeMap<K extends Comparable<K>, V> {

    /*
     * 有序表内部具体的节点定义.
     * <>: 泛型表示k,v. 可以传任意的类型.
     * k: 表示排序的索引定义, 所以必须是可排序的.
     * v: 实际存储的值.
     *
     * 每一个节点都应该维持自己的左右孩子, 以它为头节点的树的高度
     *
     * 默认的构造方法一定得有kv, 高度一开始就是1.
     */
    private static class AVLNode<K extends Comparable<K>, V> {
        public K k;
        public V v;
        public int h;
        public AVLNode<K, V> l;
        public AVLNode<K, V> r;

        public AVLNode(K k, V v) {
            this.k = k;
            this.v = v;
            h = 1;
        }
    }

    public AVLNode<K, V> root;

    /**
     * 最开始没有元素,就是空的.
     */
    public AVLTreeMap() {
        this.root = null;
    }


    /**
     * 开放API:向有序表中添加值.
     * 1.如果在有序表中找到当前key, 就更新v.
     * 2.如果没有找到, 就调用add()放在有序表中.
     */
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        AVLNode<K, V> lastNode = findIndex(key);
        if (lastNode != null) {
            lastNode.v = value;
        } else {
            root = add(root, key, value);
        }
    }

    /**
     * 通过key拿到value.
     */
    public V get(K key) {
        if (key == null) {
            return null;
        }
        AVLNode<K, V> lastNode = findIndex(key);
        if (lastNode != null) {
            return lastNode.v;
        }
        return null;
    }

    /**
     * 删除指定key.
     */
    public void remove(K key) {
        if (key == null) {
            return;
        }
        AVLNode<K, V> lastNode = findIndex(key);
        if (lastNode != null) {
            root = delete(root, key);
        }
    }


    /**
     * 定义左旋, 右旋操作.
     * 1.修改指针
     * 2.更新变动节点的高度
     * 3.返回新的头节点
     * <p>
     * 右旋: 头节点向右倒,头节点的左边拿上新节点的右孩子. 新节点的右边指向头节点.(所以新节点的右边一定有值)
     * <p>
     * 需要先更新cur的高度, 因为cur已经是子树了, 从下往上更新.
     */
    private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
        AVLNode<K, V> left = cur.l;
        cur.l = left.r;
        left.r = cur;
        cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
        left.h = Math.max((left.l != null ? left.l.h : 0), left.r.h) + 1;
        return left;
    }

    /**
     * 左旋: 头节点向左边倒, 头节点的右边拿上新节点的左孩子. 新节点的左边指向头节点(所以新节点的左边一定有值).
     */
    private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
        AVLNode<K, V> right = cur.r;
        cur.r = right.l;
        right.l = cur;

        cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
        right.h = Math.max(right.l.h, (right.r != null ? right.r.h : 0)) + 1;

        return right;
    }

    /**
     * 校验cur为头节点的子树是否平衡? 如果不平衡就调整.
     * 具体调整:
     * 1.如果是左子树的高度高, 求左子树的左右子树高度.判断是什么类型?
     * 左子树高 LL
     * 右子树高 LR
     * 特殊考虑相等位置, 既满足LL又满足LR, 优先按照LL调整.(R型类似.)
     * 2.如果是右子树的高度高, 求右子树的左右子树高度.判断是什么类型?
     * 右子树高 RR
     * 左子树高 RL
     * <p>
     * 完成调整之后, 把新的当前位置的头节点返回.
     */
    private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
        if (cur == null) {
            return null;
        }
        int leftHeight = cur.l != null ? cur.l.h : 0;
        int rightHeight = cur.r != null ? cur.r.h : 0;

        if (Math.abs(leftHeight - rightHeight) > 1) {
            if (leftHeight > rightHeight) {

                int leftLeftHeight = cur.l != null && cur.l.l != null ? cur.l.l.h : 0;
                int leftRightHeight = cur.l != null && cur.l.r != null ? cur.l.r.h : 0;

                if (leftLeftHeight >= leftRightHeight) {
                    cur = rightRotate(cur);
                } else {
                    cur.l = leftRotate(cur.l);
                    cur = rightRotate(cur);
                }

            } else {
                int rightRightHeight = cur.r != null && cur.r.r != null ? cur.r.r.h : 0;
                int rightLeftHeight = cur.r != null && cur.r.l != null ? cur.r.l.h : 0;

                if (rightRightHeight >= rightLeftHeight) {
                    cur = leftRotate(cur);
                } else {
                    cur.r = rightRotate(cur.r);
                    cur = leftRotate(cur);
                }
            }
        }

        return cur;
    }

    /**
     * 有序表的查找.
     * 小于就左滑, 大于就右滑. 等于就返回当前节点.
     */
    private AVLNode<K, V> findIndex(K key) {
        AVLNode<K, V> cur = this.root;
        while (cur != null) {
            if (key.compareTo(cur.k) < 0) {
                cur = cur.l;
            } else if (key.compareTo(cur.k) > 0) {
                cur = cur.r;
            } else {
                return cur;
            }
        }
        return null;
    }

    /**
     * 新增元素.
     * <p>
     * 删除跟新增的平衡性校验, 都是要从变动的节点开始, 一直找到头节点(每一个位置都得校验).
     *
     * @param cur   在cur为头节点的二叉树上新增.
     * @param key   新增的k
     * @param value 新增的v
     * @return 新增节点
     * <p>
     * 比较索引值, 小于就左滑, 大于就右滑. 默认是没有等于情况的.
     * dfs()到最后, 肯定是新加节点, 然后在回栈的过程中, 对于每一个头节点已经调整.
     */
    private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
        if (cur == null) {
            return new AVLNode<>(key, value);
        }
        if (key.compareTo(cur.k) < 0) {
            cur.l = add(cur.l, key, value);
        } else {
            cur.r = add(cur.r, key, value);
        }
        cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
        return maintain(cur);
    }

    /**
     * 删除节点的实际操作.
     * 1.当前节点没有左右孩子, 直接把这个节点置空.
     * 2.左右有一个孩子, 直接替换当前节点的位置.
     * <p>
     * 3.左右孩子都不为空:
     * 记录cur右孩子中最左的节点dest.
     * 在cur.r中删除这个节点.
     * 修改dest的指向, 赋值cur.
     * <p>
     * 如果最后cur不为空, 需要重新计算一下cur的高度.
     * <p>
     * dfs()一直往下找的过程, 找到后弹出, 会对途径的所有节点都进行一次调整的过程.
     */
    private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
        if (key.compareTo(cur.k) > 0) {
            cur.r = delete(cur.r, key);
        } else if (key.compareTo(cur.k) < 0) {
            cur.l = delete(cur.l, key);
        } else {
            if (cur.l == null && cur.r == null) {
                cur = null;
            } else if (cur.l != null && cur.r == null) {
                cur = cur.l;
            } else if (cur.l == null && cur.r != null) {
                cur = cur.r;
            } else {
                AVLNode<K, V> dest = cur.r;
                while (dest.l != null) {
                    dest = dest.l;
                }

                cur.r = delete(cur.r, dest.k);
                dest.l = cur.l;
                dest.r = cur.r;
                cur = dest;
            }
        }

        if (cur != null) {
            cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
        }

        return maintain(cur);
    }


    /**
     * 返回有序表中最小的K.
     */
    public K firstKey() {
        if (root == null) {
            return null;
        }
        AVLNode<K, V> cur = this.root;
        while (cur.l != null) {
            cur = cur.l;
        }
        return cur.k;
    }

    /**
     * 返回有序表中最大的k.
     */
    public K lastKey() {
        if (root == null) {
            return null;
        }
        AVLNode<K, V> cur = this.root;
        while (cur.r != null) {
            cur = cur.r;
        }
        return cur.k;
    }

    /**
     * 返回有序表中 >= K的第一个位置
     */
    public K bisectLeft(K key) {
        if (this.root == null) {
            return null;
        }
        AVLNode<K, V> ans = null;
        AVLNode<K, V> cur = this.root;
        while (cur != null) {
            if (key.compareTo(cur.k) == 0) {
                ans = cur;
                break;
            }
            if (key.compareTo(cur.k) < 0) {
                ans = cur;
                cur = cur.l;
            } else {
                cur = cur.r;
            }
        }
        return ans != null ? ans.k : null;
    }

    /**
     * 在整数K中, 如果想求离K最近的小于K的位置, bisectRight(K - 1)
     * <p>
     * 返回有序表中 > K的第一个位置
     */
    public K bisectRight(K key) {
        if (this.root == null) {
            return null;
        }
        AVLNode<K, V> ans = null;
        AVLNode<K, V> cur = this.root;
        while (cur != null) {

            if (key.compareTo(cur.k) < 0) {
                ans = cur;
                cur = cur.l;
            } else {
                cur = cur.r;
            }
        }
        return ans != null ? ans.k : null;
    }


    /**
     * 后面都是打印树操作, 方便测试.
     */
    public static void printAll(AVLNode<String, Integer> head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(AVLNode<String, Integer> head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.r, height + 1, "v", len);
        String val = to + "(" + head.k + "," + head.v + ")" + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.l, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }
}