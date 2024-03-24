package d07_remote;

import java.util.ArrayList;
import java.util.List;

public class Demo01_Hanoi {
    /*
     * 面试题 08.06. 汉诺塔问题
     *
     * 1.list中的元素要从后往前拿, 最前面的是最底下的.
     * 2.要用count来判断是否只剩一个盘子.
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        process(A, C, B, A.size());
    }

    private void process(List<Integer> source, List<Integer> target, List<Integer> help, int count) {
        if (count == 1) {
            target.add(source.remove(source.size() - 1));
            return;
        }
        process(source, help, target, count - 1);
        process(source, target, help, 1);
        process(help, target, source, count - 1);
    }

    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        A.add(1);
        A.add(0);
        new Demo01_Hanoi().hanota(A, B, C);

        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
    }
}
