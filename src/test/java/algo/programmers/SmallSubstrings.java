package algo.programmers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/147355 lv1
// 11:00 -> 11:18
public class SmallSubstrings {
    public int solution(String t, String p) {
        int answer=0;
        Long pNum = Long.valueOf(p);
        for (int i = 0, j=p.length(); j <= t.length(); i++, j++) {
            if(Long.valueOf(t.substring(i, j)).compareTo(pNum) < 1) answer++;
        }
        return answer;
    }

    @Test
    public void ex1() {
        int result = solution("3141592", "271");
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void ex2() {
        int result = solution("500220839878", "7");
        Assertions.assertThat(result).isEqualTo(8);
    }

    @Test
    public void ex3() {
        int result = solution("10203", "15");
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void ex4() {
        int result = solution("10000000", "20");
        Assertions.assertThat(result).isEqualTo(7);
    }
}
