package algo.programmers.lv0;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/120924 lv0
// 10:00 -> 10:10
public class TheNextNumber {
    public int solution(int[] common) {
        int lastIdx = common.length - 1;

        int first = common[0];
        int second = common[1];
        int third = common[2];
        if ((second - first) == (third - second)) {
            return common[lastIdx] + (second - first);
        }
        else
            return common[lastIdx] * (second / first);
    }

    @Test
    public void 예제1() {
        int result = solution(new int[]{1, 2, 3, 4});
        Assertions.assertThat(result).isEqualTo(5);
    }

    @Test
    public void 예제2() {
        int result = solution(new int[]{2, 4, 8});
        Assertions.assertThat(result).isEqualTo(16);
    }

    @Test
    public void 예제3() {
        int result = solution(new int[]{-1, 1, -1});
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void 예제4() {
        int result = solution(new int[]{10, 10, 10});
        Assertions.assertThat(result).isEqualTo(10);
    }
}
