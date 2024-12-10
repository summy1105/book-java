package algo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 - 이전의 값을 재활용하는 알고리즘
 - 이전값을 활용 : 점화식 = An = An-1 + An-2

 팁 : 어떻게 할 지 모를땐, 하나씩 찾아보기
   : 점화식을 명확하게 세우고 코드짜기

 백준 11726 https://www.acmicpc.net/problem/11726

 아이디어
 n = 1 : 1
 n = 2 : 2
 n = 3 : 3
 n = 4 : 5
 n = 5 : 8
 n = 6 : 13

 시간복잡도 : o(n)
 자료구조
    입력값 n : int
    결과값 : int 최대 1000 * 1000 < 20억

    중간 저장 : int

 */
public class ExDynamicProgramming {
    static int n;
    @Test
    public void test() {
        n = 2;
        int answer = execute(n);
        Assertions.assertThat(answer).isEqualTo(2);

        n = 9;
        answer = execute(n);
        Assertions.assertThat(answer).isEqualTo(55);
    }

    private int execute(int n) {
        if(n<=2) return n;
        int sum1 = 1;
        int sum2 = 2;
        int answer = 0;
        for (int i = 3; i <= n; i++) {
            answer = sum1 + sum2;
            sum1 = sum2;
            sum2 = answer;
        }

        return answer % 10_007;
    }
}
