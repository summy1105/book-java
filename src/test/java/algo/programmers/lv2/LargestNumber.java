package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://school.programmers.co.kr/learn/courses/30/lessons/42746 lv2
public class LargestNumber {
    public String solution(int[] numbers) {
        PriorityQueue<String> maxHeap = new PriorityQueue<>((s1, s2) -> (s2+s1).compareTo(s1+s2));
        Arrays.sort(numbers);

        for (int number : numbers) {
            maxHeap.add(String.valueOf(number));
        }
        StringBuilder answer = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            answer.append(maxHeap.poll());
        }
        return answer.toString().startsWith("0") ? "0" : answer.toString();
    }

    @Test
    public void ex1() {
        String result = solution(new int[]{6, 10, 2});
        Assertions.assertThat(result).isEqualTo("6210");
    }

    @Test
    public void ex2() {
        String result = solution(new int[]{3, 30, 34, 5, 9});
        Assertions.assertThat(result).isEqualTo("9534330");
    }

    @Test
    public void ex3() {
        String result = solution(new int[]{9,998, 999,9,98, 0});
        Assertions.assertThat(result).isEqualTo("330430111011000");
    }
    @Test
    public void ex4() {
        String result = solution(new int[]{0,0,0,0});
        Assertions.assertThat(result).isEqualTo("330430111011000");
    }
}
