package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/42842 lv2
// 완전탐색
// 수학공식으로 푸는 방법도 있음
public class Carpet {
    public int[] solution(int brown, int yellow) {
        for(int divisor=1; divisor<=(yellow+1)/2; divisor++){
            if(yellow % divisor != 0) continue;
            int quotient = yellow / divisor;
            if((divisor+quotient)*2+4 == brown)
                return new int[]{quotient+2, divisor+2};
        }
        return new int[]{};
    }

    // w + y = (4+brown)/2
    // w * y = brown + yellow
    // 이차방적식 근의 공식 활용
    // x^2 + -1/2*(4+b)x + (b+y) = 0
    public int[] 수학공식으로_풀기(int brown, int yellow) {
        int b = -1 * (4 + brown) / 2;
        int c = brown + yellow;
        int width = (-b + (int) Math.sqrt(b * b - 4 * c)) / 2;
        int height = (-b - (int) Math.sqrt(b * b - 4 * c)) / 2;
        return new int[]{width, height};
    }

    @Test
    public void ex1() {
        int[] result = 수학공식으로_풀기(10, 2);
        Assertions.assertThat(result).containsExactly(4,3);
    }

    @Test
    public void ex2() {
        int[] result = 수학공식으로_풀기(8, 1);
        Assertions.assertThat(result).containsExactly(3,3);
    }

    @Test
    public void ex3() {
        int[] result = 수학공식으로_풀기(24, 24);
        Assertions.assertThat(result).containsExactly(8,6);
    }
}
