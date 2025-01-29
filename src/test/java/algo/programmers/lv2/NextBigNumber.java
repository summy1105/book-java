package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// https://school.programmers.co.kr/learn/courses/30/lessons/12911 lv2

public class NextBigNumber {
    public int solution(int n) {
        int targetCount = Integer.bitCount(n); // n의 2진수에서 1의 개수
        int next = n + 1;
        while (Integer.bitCount(next) != targetCount) {
            next++; // 조건을 만족할 때까지 증가
        }
        return next; // 조건을 만족하는 가장 작은 수 반환
    }

    @Test
    public void ex1() {
        int result = solution(78);
        Assertions.assertThat(result).isEqualTo(83);
    }

    @Test
    public void ex2() {
        int result = solution(15);
        Assertions.assertThat(result).isEqualTo(23);
    }

    @Test
    public void ex3() {
        int result = solution(1);
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void ex4() {
        int result = solution(74);
        Assertions.assertThat(result).isEqualTo(76);
    }

    @Test
    public void ex5() {
        int result = solution(1_000_000);
        Assertions.assertThat(result).isEqualTo(1000064);
    }
}
