package d10_datastruct.sortable_source;


import static d10_datastruct.sortable_source.AVLTreeMap.printAll;

/**
 * @author tianjiaxuan
 * @date 2023-12-12 15:07
 */
public class TestAVL {

    public static void main(String[] args) {
        AVLTreeMap<String, Integer> avlTreeMap = new AVLTreeMap<>();
        avlTreeMap.put("d", 4);
        avlTreeMap.put("c", 3);
        avlTreeMap.put("a", 1);
        avlTreeMap.put("b", 2);
        avlTreeMap.put("e", 5);
        avlTreeMap.put("g", 7);
        avlTreeMap.put("f", 6);
        avlTreeMap.put("h", 8);
        printAll(avlTreeMap.root);

        System.out.println("当前有序表中最小的key\t" + avlTreeMap.firstKey());
		System.out.println("当前有序表中最大的key\t" + avlTreeMap.lastKey());

		System.out.println("当前有序表中 >= g的第一个数\t" + avlTreeMap.bisectLeft("g"));
        System.out.println("当前有序表中 > g的第一个数\t" + avlTreeMap.bisectRight("g"));

        avlTreeMap.remove("g");
        printAll(avlTreeMap.root);
        System.out.println("当前有序表中 >= g的第一个数\t" + avlTreeMap.bisectLeft("g"));
        System.out.println("当前有序表中 > g的第一个数\t" + avlTreeMap.bisectRight("g"));

        avlTreeMap.remove("h");
        printAll(avlTreeMap.root);
        System.out.println("当前有序表中 >= g的第一个数\t" + avlTreeMap.bisectLeft("g"));
        System.out.println("当前有序表中 > g的第一个数\t" + avlTreeMap.bisectRight("g"));
    }
}
