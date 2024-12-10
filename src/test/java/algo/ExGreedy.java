package algo;

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
    static int n; // coin 종류 갯수
    static int k; // 동전들 합계
    static int[] coins;

    @Test
    public void test() {
//        n = 10;
//        k = 4200;
//        coins = new int[]{1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000};

        n = 10;
        k = 4790;
        coins = new int[]{1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000};

        int minCoinCount = greedy(n, k, coins);
        System.out.println("minCoinCount = " + minCoinCount);
    }

    private int greedy(int n, int k, int[] coins) {
        int remain = k;
        int allCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            int coin = coins[i];
            allCount += (remain / coin);
            remain = remain % coin;
        }
        return allCount;
    }
}