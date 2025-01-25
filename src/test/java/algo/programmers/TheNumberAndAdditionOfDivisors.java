package algo.programmers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/77884 lv1
// 1:50 -> 2:00
public class TheNumberAndAdditionOfDivisors {
    public int solution(int left, int right) {
        int answer = 0;
        for (int i = left; i <=right ; i++) {
            boolean isEven = false;
            for (int j = 1; j <=i/2 ; j++) {
                if(i%j == 0) isEven = !isEven;
            }
            answer += isEven ? i : -i;
        }
        return answer;
    }

    public int 제곱수이용하기(int left, int right) {
        int answer = 0;
        for (int i=left;i<=right;i++) {
            //제곱수인 경우 약수의 개수가 홀수
            if (i % Math.sqrt(i) == 0) answer -= i;
            //제곱수가 아닌 경우 약수의 개수가 짝수
            else answer += i;
        }
        return answer;
    }

    @Test
    public void ex1() {
        int result = solution(13,17);
        Assertions.assertThat(result).isEqualTo(43);
    }

    @Test
    public void ex2() {
        int result = solution(24, 27);
        Assertions.assertThat(result).isEqualTo(52);
    }

    @Test
    public void ex3() {
        int result = solution(1, 1);
        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    public void ex4() {
        int result = solution(2, 2);
        Assertions.assertThat(result).isEqualTo(2);
    }

}
