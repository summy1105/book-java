package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://school.programmers.co.kr/learn/courses/30/lessons/12927 lv3
// 적절한 자료구조 선택
public class IndexOvertimeWork {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) {
            heap.add(work);
        }

        while(n>0){
            Integer max = heap.poll();
            if(max<= 0) break;
            heap.add(max-1);
            n--;
        }
        return heap.stream().mapToLong(i -> i).reduce(0L, (a, b) -> a + b * b);
    }

    @Test
    public void ex1() {
        long result = solution(4, new int[]{4, 3, 3});
        Assertions.assertThat(result).isEqualTo(12);
    }

    @Test
    public void ex2() {
        long result = solution(1, new int[]{2, 1, 2});
        Assertions.assertThat(result).isEqualTo(6);
    }

    @Test
    public void ex3() {
        long result = solution(3, new int[]{1, 1});
        Assertions.assertThat(result).isEqualTo(0);
    }
}
