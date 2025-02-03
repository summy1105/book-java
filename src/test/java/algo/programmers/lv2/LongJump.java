package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/12914 lv2
// 1:17 -> 1:33
// dynamic
//
public class LongJump {

    public long solution(int n) {
        if (n <=2 )  return n;
        long a1 = 1, a2 = 2, answer=0;
        for(int i=3; i<=n; i++){
            answer = (a1 + a2) % 1234567;
            a1 = a2; a2 = answer;
        }
        return answer % 1234567;
    }

    @Test
    public void ex1() {
        long result = solution(4);
        Assertions.assertThat(result).isEqualTo(5);
    }

    @Test
    public void ex2() {
        long result = solution(3);
        Assertions.assertThat(result).isEqualTo(3);
    }

}
