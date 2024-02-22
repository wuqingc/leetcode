package d07_greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 一些项目要占用一个会议室宣讲,会议室不能同时容纳两个项目的宣讲.
 * 给你每一个项目开始的时间和结束的时间(给你一个数组，里面是一个个具体的项目)，
 * 你来安排宣讲的日程,要求会议室进行的宣讲的场次最多,返回这个最多的宣讲场次。
 * @author tianjiaxuan
 *
 * 贪心策略:每次都选择最早结束的项目,排除因为做这个项目而延误的项目.
 */
public class Demo01_BestArrange {
    static class Program {
        public int start;
        public int end;
        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    static class ProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    /**
     * 1.把所有的项目按照最快结束时间排序
     * 2.每次都选最早结束的.
     */
    private static int bestArrange(int[][] arrange) {
        PriorityQueue<Program> programs = new PriorityQueue<>(new ProgramComparator());
        for (int[] program : arrange) {
            programs.offer(new Program(program[0],program[1]));
        }
        int endTime = 0;
        int count = 0;
        while (!programs.isEmpty()) {
            Program temp = programs.poll();
            if (temp.start >= endTime) {
                count++;
                endTime = temp.end;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] arr = {{0,30},{5,10},{15,20}};
        System.out.println(bestArrange(arr));
    }
}
