package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;
import java.util.TreeSet;

// https://school.programmers.co.kr/learn/courses/30/lessons/42628 lv3
// 26 -> 40
public class DoublePriorityQueue {
    public int[] solution(String[] operations) {
        TreeSet<Integer> queue = new TreeSet<>();
        for (String operation : operations) {
            if(operation.startsWith("D") && queue.isEmpty()) continue;
            else if("D -1".equals(operation)) queue.pollFirst();
            else if("D 1".equals(operation)) queue.pollLast();
            else {
                Integer number = Integer.valueOf(operation.split(" ")[1]);
                queue.add(number);
            }
        }
        return queue.isEmpty()? new int[]{0, 0} : new int[]{queue.last(), queue.first()};
    }

    @Test
    public void ex1() {
        int[] result = solution(new String[]{
                "I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"
        });
        Assertions.assertThat(result).containsExactly(0, 0);
    }

    @Test
    public void ex2() {
        int[] result = solution(new String[]{
                "I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"
        });
        Assertions.assertThat(result).containsExactly(333, -45);
    }

    @Test
    public void test() {
        //given
        "dsd123din.zip".toLowerCase().replaceAll("(\\D+)(\\d+)(.*)", "$1 ");
        TreeMap<String, String> map = new TreeMap<>();
        map.put("test", "Test");
        String[] strings = map.values().toArray(new String[]{});
        //when
        for (String string : strings) {
            System.out.println("string = " + string);
        }
        //then
    }
}
