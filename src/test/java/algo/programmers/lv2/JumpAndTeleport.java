package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/12980 lv2
// 9:45 -> 9:59
// 2로 계속 나누고, 안나눠지면 -1 > 더해주기
public class JumpAndTeleport {

    public int solution(int n) {
        int ans = 0;
        while(n>1){
            if(n%2==1) {
                n--; ans++;
            }
            n = n/2;
        }
        return ++ans;
    }

    public int solution2(int n) {
        return Integer.bitCount(n); // (0 D0)!!
    }

    @Test
    public void ex1() {
        int result = solution(5);
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void ex2() {
        int result = solution(6);
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void ex3() {
        int result = solution(5000);
        Assertions.assertThat(result).isEqualTo(5);
    }
}
