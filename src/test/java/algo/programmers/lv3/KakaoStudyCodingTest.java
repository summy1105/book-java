package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/118668 lv3

public class KakaoStudyCodingTest {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;

        int[][] timeNProblemTable = new int[problems.length + 2][];
        timeNProblemTable[0] = new int[]{0, 0, 1, 0, 1};
        timeNProblemTable[1] = new int[]{0, 0, 0, 1, 1};
        for (int i = 0; i < problems.length; i++) {
            timeNProblemTable[i + 2] = problems[i];

            if(maxAlp<problems[i][0]) maxAlp = problems[i][0];
            if(maxCop<problems[i][1]) maxCop = problems[i][1];
        }

        int[][] dp = new int[maxAlp*2][maxCop*2];
        int MAX_TIME = 300;
        for (int i = 0; i <= maxAlp; i++) {
            for (int j = 0; j <= maxCop; j++) {
                if(i<=alp && j<=cop)
                    dp[i][j] = 0;
                else
                    dp[i][j] = MAX_TIME;
            }
        }

        for (int cAlp = 0; cAlp <= maxAlp; cAlp++) {
            for (int cCop = 0; cCop <= maxCop; cCop++) {
                for (int[] tp : timeNProblemTable) {
                    if(cAlp<tp[0] || cCop<tp[1]) continue;

                    int afterAlp = cAlp + tp[2];
                    int afterCop = cCop + tp[3];
                    int afterTime = dp[cAlp][cCop] + tp[4];
                    for (int i = cAlp; i <= afterAlp; i++) {
                        for (int j = cCop; j <= afterCop; j++) {
                            if(dp[i][j]>afterTime)
                                dp[i][j] = afterTime;
                        }
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }

    //10	10	[[10,15,2,1,2],[20,20,3,3,4]]	15
    @Test
    public void test() {
        int alp = 10;
        int cop = 10;
        int[][] problems = {{10, 15, 2, 1, 2}, {20, 20, 3, 3, 4}};
        int result = solution(alp, cop, problems);
        Assertions.assertThat(result).isEqualTo(15);
    }

    //0	0	[[0,0,2,1,2],[4,5,3,1,2],[4,11,4,0,2],[10,4,0,4,2]]	13
    @Test
    public void test2() {
        int alp = 0;
        int cop = 0;
        int[][] problems = {{0, 0, 2, 1, 2}, {4, 5, 3, 1, 2}, {4, 11, 4, 0, 2}, {10, 4, 0, 4, 2}};
        int result = solution(alp, cop, problems);
        Assertions.assertThat(result).isEqualTo(13);
    }
}
