package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

// https://school.programmers.co.kr/learn/courses/30/lessons/42898 lv3
public class WayToSchool {
    public int solution(int m, int n, int[][] puddles) {
        long[][] map = new long[m + 1][n + 1];
        boolean[][] puddlesCheck = new boolean[m + 1][n + 1];
        for (int[] puddle : puddles) {
            puddlesCheck[puddle[0]][puddle[1]] = true;
        }
        map[1][1] = 1;
        for (int row = 1; row <=m ; row++) {
            for (int col = 1; col <= n; col++) {
                if(puddlesCheck[row][col] || row==1&&col==1) continue;
                else map[row][col] = (map[row - 1][col] + map[row][col - 1])% 1_000_000_007;
            }
        }
        return (int) (map[m][n] % 1_000_000_007);
    }

    @Test
    public void ex1() {
        int result = solution(4, 3, new int[][]{{2, 2}});
        Assertions.assertThat(result).isEqualTo(4);
    }

    @Test
    public void ex2() {
        int result = solution(5, 4, new int[][]{{4, 2}, {2,3}});
        Assertions.assertThat(result).isEqualTo(11);
    }

    @Test
    public void ex3() {
        int result = solution(5, 1, new int[][]{{2,1}});
        Assertions.assertThat(result).isEqualTo(0);
    }
}
