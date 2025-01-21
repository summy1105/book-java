package algo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 - 지금 이 순간 당장 최적인 답을 선택
 - 그리디 알고리즘은 부분의 최적해들의 집합이 곧 전체 문제의 해답이 될 때 사용할 수 있다.
 - 팁
    > 실전 문제에서, 그리디로 푸는 문제임을 생각하기 어려움
    > 그리디 사용 이유 설명 or 반례 찾기 연습 : 그리디 알고리즘이 맞다는 보장이 없음..

 백준 11047 https://www.acmicpc.net/problem/11047
 1<= n <= 10, 1<= k <= 100,000,000 (1억)
 1<= Ai(동전) <= 1,000,000(100만), A1 = 1, 각각 동전은 앞의 동전의 배수이다 첫번째가 10이면 두번째는 20,30,40,50... 중의 하나
 첫번째 동전은 1원
 K원을 만드는데 필요한 동전 갯수의 최솟값

 아이디어 :
  - 큰 금액 부터 차감
  - 반례 : 있을 수 없음. 조건 앞순서의 동전은 뒤 순서의 동전의 1/2보다 작기 때문에, 그리고 1원이 있음
  - k를 동전 금액으로 나눈 뒤 남은 값으로 갱신

 자료구조
    - n : int
    - k : int (20억보다 작음)
    - a[](동전들) : int[]

 시간 복잡도 : o(n)

 */
public class ExGreedy {
    @Test
    public void 백준_예제1() {
        int n = 10;
        int k = 4200;
        int[] coinValues = {1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000};

        int result = greedy(n, k, coinValues);

        Assertions.assertThat(result).isEqualTo(6);
    }

    private int greedy(int n, int k, int[] coinValues) {
        int remain = k;
        int coinCount = 0;
        for (int i = n-1; i >=0 ; i--) {
            int coinValue = coinValues[i];
            coinCount += (remain / coinValue);
            remain = remain % coinValue;
        }
        return coinCount;
    }


    @Test
    public void 백준_예제2() {
        int n = 10;
        int k = 4790;
        int[] coinValues = {1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000};

        int result = greedy(n, k, coinValues);

        Assertions.assertThat(result).isEqualTo(12);
    }

    @Test
    public void 큰_금액() {
        int n = 5;
        int k = 1_000_000;
        int[] coinValues = {1, 10, 100, 1_000, 10_000};

        int result = greedy(n, k, coinValues);

        Assertions.assertThat(result).isEqualTo(100);
    }

    @Test
    public void 다양한_배수() {
        int n = 6;
        int k = 12345;
        int[] coinValues = {1, 5, 10, 50, 100, 500};

        int result = greedy(n, k, coinValues);

        Assertions.assertThat(result).isEqualTo(24+3+4+1);
    }

    @Test
    public void 큰_동전_부족() {
        int n = 4;
        int k = 9999;
        int[] coinValues = {1, 100, 500, 1000};

        int result = greedy(n, k, coinValues);

        Assertions.assertThat(result).isEqualTo(9+1+4+99);
    }

    @Test
    public void 모두_동일한_동전() {
        int n = 1;
        int k = 100;
        int[] coinValues = {1};

        int result = greedy(n, k, coinValues);

        Assertions.assertThat(result).isEqualTo(100);
    }

    @Test
    public void 큰_동전의_완벽한_배수() {
        int n = 3;
        int k = 3000;
        int[] coinValues = {1, 100, 1000};

        int result = greedy(n, k, coinValues);

        Assertions.assertThat(result).isEqualTo(3);
    }
}