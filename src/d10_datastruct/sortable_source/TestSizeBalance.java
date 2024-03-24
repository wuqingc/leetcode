package d10_datastruct.sortable_source;

import static d10_datastruct.sortable_source.SizeBalanceTreeMap.printAll;

/**
 * @author tianjiaxuan
 * @date 2023-12-12 15:07
 */
public class TestSizeBalance {

    public static void main(String[] args) {
        SizeBalanceTreeMap<String, Integer> sbt = new SizeBalanceTreeMap<>();
        sbt.put("d", 4);
        sbt.put("c", 3);
        sbt.put("a", 1);
        sbt.put("b", 2);
         sbt.put("e", 5);
        sbt.put("g", 7);
        sbt.put("f", 6);
        sbt.put("h", 8);
        printAll(sbt.root);

        System.out.println("当前有序表中最小的key\t" + sbt.firstKey());
		System.out.println("当前有序表中最大的key\t" + sbt.lastKey());

		System.out.println("当前有序表中 >= g的第一个数\t" + sbt.bisectLeft("g"));
        System.out.println("当前有序表中 > g的第一个数\t" + sbt.bisectRight("g"));

        sbt.remove("g");
        printAll(sbt.root);
        System.out.println("当前有序表中 >= g的第一个数\t" + sbt.bisectLeft("g"));
        System.out.println("当前有序表中 > g的第一个数\t" + sbt.bisectRight("g"));

        sbt.remove("h");
        printAll(sbt.root);
        System.out.println("当前有序表中 >= g的第一个数\t" + sbt.bisectLeft("g"));
        System.out.println("当前有序表中 > g的第一个数\t" + sbt.bisectRight("g"));
    }
}
