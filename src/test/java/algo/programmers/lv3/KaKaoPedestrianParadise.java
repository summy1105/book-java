package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/1832 lv3

public class KaKaoPedestrianParadise {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        long[][] dp = new long[m][n];
        dp[0][0] = 1;
        for(int col = 1; col<n && cityMap[0][col]!=1; col++){
            dp[0][col] = dp[0][col-1];
        }
        for(int row = 1; row<m && cityMap[row][0]!= 1; row++){
            dp[row][0] = dp[row-1][0];
        }

        for(int row = 1; row<m ; row++){
            for(int col = 1; col<n; col++){
                if(cityMap[row][col] == 1) continue;

                int left=1;
                long leftCol = 0;
                while(col-left>=0 && cityMap[row][col-left]==2) left++;
                leftCol = col-left<0? 0 : dp[row][col-left];


                int above=1;
                long aboveRow = 0;
                while(row-above>=0 && cityMap[row-above][col]==2) above++;
                aboveRow = row-above<0? 0 : dp[row-above][col];

                dp[row][col] = (leftCol+aboveRow)%MOD;
            }
        }

        return (int)(dp[m-1][n-1]%MOD);
    }

    @Test
    public void ex1() {
        int m = 3;
        int n = 3;
        int[][] city_map = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int result = solution(m, n, city_map);
        Assertions.assertThat(result).isEqualTo(6);
    }

    @Test
    public void ex2() {
        int m = 3;
        int n = 6;
        int[][] city_map = {
                {0, 2, 0, 0, 0, 2},
                {0, 0, 2, 0, 1, 0},
                {1, 0, 0, 2, 2, 0}
        };
        int result = solution(m, n, city_map);
        Assertions.assertThat(result).isEqualTo(2);
    }

}