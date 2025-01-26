package algo.programmers.lv0;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// https://school.programmers.co.kr/learn/courses/30/lessons/120812 lv0
// 10:15 -> 10:35
public class GettingTheMostFrequentPrice {
    public int solution(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            Integer valueCount = map.getOrDefault(array[i], 0);
            map.put(array[i], ++valueCount);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        for (Map.Entry<Integer, Integer> mapEntry : map.entrySet()) {
            heap.add(mapEntry);
        }

        Map.Entry first = heap.poll();
        Map.Entry second = heap.poll();

        if(second!= null && first.getValue() == second.getValue()) return -1;
        return (int)first.getKey();
    }

    @Test
    public void 예제1() {
        int result = solution(new int[]{1, 2, 3, 3, 3, 4});
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void 예제2() {
        int result = solution(new int[]{1, 1, 2, 2});
        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    public void 예제3() {
        int result = solution(new int[]{1});
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void 예제4() {
        int result = solution(new int[]{0,0,0,0,0});
        Assertions.assertThat(result).isEqualTo(0);
    }
}
