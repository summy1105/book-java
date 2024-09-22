package algo;

import org.junit.jupiter.api.Test;

// 1 아이디어 : 어떤 알고리즘?
// 2 시간 복잡도
// 3 자료구조

// 시간 복잡도에서 숫자가 크다, 보통 2억개 계산안에 해야 하기 때문에,
// 중복이 허용된다면 N은 8까지 가능하고
// 중복이 안된다면 N은 10까지 가능하다

// 백트래킹 문제는 N이 작음
// 재귀함수 사용할 때, 종료시점에 자료구조 복구 잊지 말기

// 백준 15649
// 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
//  - 1부터 N까지 자연수 중에서 중복 없이, M개를 고른 수열
// 예제 input: "4 2"
//      output : "1 2 \n 1 3 \n 1 4 \n 2 1 \n 2 3..."
public class ExBacktracking {
    int n;
    int m;
    @Test
    public void test() {
        n = 10;
        m = 4;
        boolean[] visitCheck = new boolean[n+1];
        int[] print = new int[m];

        for (int i = 1; i <= n; i++) {
            visitCheck[i] = true;
            print[0] = i;
            backtracking( 1, print, visitCheck);
            visitCheck[i] = false;
        }
    }

    void backtracking(int depth, int[] print, boolean[] visitCheck) {
        if (depth == m) {
            for (int i : print) {
                System.out.printf("%d ", i);
            }
            System.out.println();
            return;
        }
        for (int i = 1; i <= n ; i++) {
            if (visitCheck[i] == false) {
                print[depth] = i;
                visitCheck[i] = true;
                backtracking(depth+1, print, visitCheck);
                visitCheck[i] = false;
            }
        }
    }

}
