package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/389480 lv2

public class PerfectCrime {
    public int solution(int[][] info, int n, int m) {
        int length = info.length;
        // accumulate로 가능한 곳에 true
        // [i][a][b] = true i번째에, a가 훔친 누적가능수, b가 훔친 누적가능수 => 조합을 true
        boolean[][][] dp = new boolean[length+1][n][m];
        dp[0][0][0] = true;

        for(int i=0; i<length; i++){
            int traceA = info[i][0];
            int traceB = info[i][1];
            for(int possibleA = 0; possibleA<n; possibleA++){
                for(int possibleB = 0; possibleB< m; possibleB++){
                    if(dp[i][possibleA][possibleB] == false) continue;

                    if(possibleA + traceA < n){
                        dp[i+1][possibleA + traceA][possibleB] = true;
                    }

                    if(possibleB + traceB < m){
                        dp[i+1][possibleA][possibleB + traceB] = true;
                    }
                }
            }
        }
        int answer = 121;
        for(int a=0; a<n; a++){
            for(int b=0; b<m; b++){
                if(dp[length][a][b] && a<answer)
                    answer = a;
            }
        }

        return answer>120? -1: answer;
    }

    @Test
    public void ex1() {
        int[][] info = { {1,2}, {2,3}, {2,1} };
        int n = 4;
        int m = 4;
        int result = solution(info, n, m);
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void ex2() {
        int[][] info = { {1,2}, {2,3}, {2,1} };
        int n = 1;
        int m = 7;
        int result = solution(info, n, m);
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void ex3() {
        int[][] info = { {3,3}, {3,3} };
        int n = 7;
        int m = 1;
        int result = solution(info, n, m);
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(6);
    }

    @Test
    public void ex4() {
        int[][] info = { {3,3}, {3,3} };
        int n = 6;
        int m = 1;
        int result = solution(info, n, m);
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    public void ex5() {
        int[][] info = { {5, 2}, {3, 3}, {5, 3} };
        int result = solution(info, 10, 10);
        Assertions.assertThat(result).isEqualTo(0);
    }
}
