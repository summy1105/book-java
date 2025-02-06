package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/138476 lv2
// 47 ->
public class PickTangerine {

    public int solution(int k, int[] tangerine) {
        List<Long> sortedCountList = Arrays.stream(tangerine)
                .mapToObj(Integer::valueOf).collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .values().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        int answer = 0;
        while(k>0) {
            k -= sortedCountList.get(answer++);
        }
        return answer;
    }

    @Test
    public void ex1() {
        int result = solution(6, new int[]{1, 3, 2, 5, 4, 5, 2, 3});
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void ex2() {
        int result = solution(4, new int[]{1, 3, 2, 5, 4, 5, 2, 3});
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void ex3() {
        int result = solution(2, new int[]{1, 1, 1, 1, 2, 2, 2, 3});
        Assertions.assertThat(result).isEqualTo(1);
    }
}
