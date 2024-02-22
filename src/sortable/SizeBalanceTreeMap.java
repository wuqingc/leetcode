package sortable;

/**
 * @author tianjiaxuan
 * @date 2023-12-12 14:04
 * sb树实现.
 */
public class SizeBalanceTreeMap<K extends Comparable<K>, V> {

    /*
     * 维持平衡的因素从高度改成了以当前节点为头的节点数目.
     */
    private static class SizeBalanceTreeNode<K extends Comparable<K>, V> {
        public K k;
        public V v;
        public int size;
        public SizeBalanceTreeNode<K, V> l;
        public SizeBalanceTreeNode<K, V> r;

        public SizeBalanceTreeNode(K k, V v) {
            this.k = k;
            this.v = v;
            this.size = 1;
        }
    }

    public SizeBalanceTreeNode<K, V> root;

    /**
     * 当前节点做右旋操作, 返回新的头节点.
     * 右旋只是换了一个头, 所以新头节点的大小还是跟之前一样.
     */
    private SizeBalanceTreeNode<K, V> rightRotate(SizeBalanceTreeNode<K, V> cur) {
        SizeBalanceTreeNode<K, V> leftNode = cur.l;
        cur.l = leftNode.r;
        leftNode.r = cur;

        leftNode.size = cur.size;
        cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
        return leftNode;
    }

    /*
     * 左旋.
     */
    private SizeBalanceTreeNode<K, V> leftRotate(SizeBalanceTreeNode<K, V> cur) {
        SizeBalanceTreeNode<K, V> rightNode = cur.r;
        cur.r = rightNode.l;
        rightNode.l = cur;
        rightNode.size = cur.size;
        cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
        return rightNode;
    }

    /**
     * 维持cur平衡的操作.
     * <p>
     * 判断cur的子树, 每一个 侄子节点的size <= 它叔叔节点的size.
     * <p>
     * LL
     * 判断标准: 左子树的左边侄子节点size > 右子树叔叔的size.
     * 执行动作:
     * 对头节点右旋, 头节点已经变了, 所以cur接住新的头节点.
     * cur, cur.r 这俩节点的size发生变化了, 自底向上调整.
     * <p>
     * LR
     * 判断标准: 左子树的右边侄子节点size > 右子树叔叔的size.(如果同时触发LL LR, 执行LL)
     * 执行动作:
     * 左子树节点左旋, cur.l发生变化, cur.l接住新的左子树头
     * 头节点右旋, cur发生变化, cur接住新的头节点
     * 一共三个节点发生变化, cur.l cur cur.r , 自底向上调整(cur.l只变更一次, 第二次右旋时变更cur.r).
     * <p>
     * RR
     * 判断标准: 右子树的右边侄子节点size > 左子树叔叔的size
     * 执行动作:
     * 头节点左旋, 参考LL调整.
     * <p>
     * RL
     * 判断标注: 右子树的左边侄子节点size > 左子树叔叔的size
     * 执行动作:
     * 参考LR调整.
     */
    private SizeBalanceTreeNode<K, V> maintain(SizeBalanceTreeNode<K, V> cur) {
        if (cur == null) {
            return null;
        }
        int leftSize = cur.l != null ? cur.l.size : 0;
        int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
        int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;

        int rightSize = cur.r != null ? cur.r.size : 0;
        int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
        int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
        if (leftLeftSize > rightSize) {
            cur = rightRotate(cur);
            cur.r = maintain(cur.r);
            cur = maintain(cur);
        } else if (leftRightSize > rightSize) {
            cur.l = leftRotate(cur.l);
            cur = rightRotate(cur);
            cur.l = maintain(cur.l);
            cur.r = maintain(cur.r);
            cur = maintain(cur);
        } else if (rightRightSize > leftSize) {
            cur = leftRotate(cur);
            cur.l = maintain(cur.l);
            cur = maintain(cur);
        } else if (rightLeftSize > leftSize) {
            cur.r = rightRotate(cur.r);
            cur = leftRotate(cur);
            cur.l = maintain(cur.l);
            cur.r = maintain(cur.r);
            cur = maintain(cur);
        }
        return cur;
    }


    /**
     * 查找当前key是否在有序表中.
     * 小于就往左滑, 大于就往右滑, 等于就返回.
     */
    private SizeBalanceTreeNode<K, V> findIndex(K key) {
        SizeBalanceTreeNode<K, V> cur = this.root;
        while (cur != null) {
            if (key.compareTo(cur.k) == 0) {
                return cur;
            }
            if (key.compareTo(cur.k) < 0) {
                cur = cur.l;
            } else {
                cur = cur.r;
            }
        }
        return null;
    }

    /**
     * 向树中插入元素.dfs
     * <p>
     * 出口条件: 找到空的位置了, 就不需要滑动了, 直接在这个位置返回节点.
     * dfs:
     * cur.size + 1
     * 如果较小, 就去左子树找位置, 找到位置后, 把更新好的左子树的新头节点挂在cur.
     * 如果较大, 就去右子树找位置, 找到位置后, 把更新好的右子树的新头节点挂在cur.
     * 对cur调整平衡性.
     */
    private SizeBalanceTreeNode<K, V> add(SizeBalanceTreeNode<K, V> cur, K key, V value) {
        if (cur == null) {
            return new SizeBalanceTreeNode<>(key, value);
        } else {
            cur.size++;
            if (cur.k.compareTo(key) > 0) {
                cur.l = add(cur.l, key, value);
            } else {
                cur.r = add(cur.r, key, value);
            }
            return maintain(cur);
        }
    }


    /**
     * 新增操作
     * 1.如果存在当前K, 就更新V.
     * 2.如果不存在当前K, 作为新节点插入, 并返回插入后整体树的头节点.
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new RuntimeException("key 不能为空.");
        }
        SizeBalanceTreeNode<K, V> curNode = findIndex(key);
        if (curNode != null) {
            curNode.v = value;
        } else {
            root = add(root, key, value);
        }
    }

    /**
     * 树中一定有这个节点.
     * 向左 向右滑动, 找到目标节点后.
     * 1.如果是叶子节点, 直接删掉即可.
     * 2.如果有一个孩子: 左/右, 用孩子替掉cur.
     * 3.左右孩子都有, 找到cur的后继节点, 替换cur. 右子树的最左节点.
     * 后继节点的父节点, 接管后继节点的右孩子,
     * 后继节点的左右替换成cur的左右
     * 替换cur
     */
    private SizeBalanceTreeNode<K, V> delete(SizeBalanceTreeNode<K, V> cur, K key) {

        cur.size--;
        if (key.compareTo(cur.k) < 0) {
            cur.l = delete(cur.l, key);
        } else if (key.compareTo(cur.k) > 0) {
            cur.r = delete(cur.r, key);
        } else {
            if (cur.l == null && cur.r == null) {
                cur = null;
            } else if (cur.l != null && cur.r == null) {
                cur = cur.l;
            } else if (cur.l == null && cur.r != null) {
                cur = cur.r;
            } else {
                SizeBalanceTreeNode<K, V> pre = null;
                SizeBalanceTreeNode<K, V> dest = cur.r;
                dest.size--;
                while (dest.l != null) {
                    pre = dest;
                    dest = dest.l;
                    dest.size--;
                }

                if (pre != null) {
                    pre.l = dest.r;
                    dest.r = cur.r;
                }
                dest.l = cur.l;
                dest.size = (dest.l != null ? dest.l.size : 0) + (dest.r != null ? dest.r.size : 0) + 1;
                cur = dest;
            }
        }
        return maintain(cur);
    }

    public void remove(K key) {
        if (key == null) {
            throw new RuntimeException("key 不能为空.");
        }
        SizeBalanceTreeNode<K, V> curNode = findIndex(key);
        if (curNode != null) {
            root = delete(root, key);
        }
    }


    /**
     * 返回有序表中最小的K.
     */
    public K firstKey() {
        if (root == null) {
            return null;
        }
        SizeBalanceTreeNode<K, V> cur = this.root;
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
        SizeBalanceTreeNode<K, V> cur = this.root;
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
        SizeBalanceTreeNode<K, V> ans = null;
        SizeBalanceTreeNode<K, V> cur = this.root;
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
        SizeBalanceTreeNode<K, V> ans = null;
        SizeBalanceTreeNode<K, V> cur = this.root;
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
    public static void printAll(SizeBalanceTreeNode<String, Integer> head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(SizeBalanceTreeNode<String, Integer> head, int height, String to, int len) {
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
