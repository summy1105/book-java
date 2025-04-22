package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/258705 lv3

public class MountainShapeTiling {
    int MODULO = 10_007;
    public int solution(int n, int[] tops) {
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = tops[0] == 1 ? 4 : 3;
        for (int i = 2; i < n+1; i++) {
            int nextCase = tops[i - 1] == 1 ? 4 : 3;
            dp[i] = (dp[i - 1] * nextCase + MODULO - dp[i - 2]) % MODULO;
        }
        return (int)dp[n];
    }


    //4	[1, 1, 0, 1]	149
    @Test
    public void test() {
        MountainShapeTiling mountainShapeTiling = new MountainShapeTiling();
        int n = 4;
        int[] tops = {1, 1, 0, 1};
        int result = mountainShapeTiling.solution(n, tops);
        Assertions.assertThat(result).isEqualTo(149);
    }

    //2	[0, 1]	11
    @Test
    public void test2() {
        MountainShapeTiling mountainShapeTiling = new MountainShapeTiling();
        int n = 2;
        int[] tops = {0, 1};
        int result = mountainShapeTiling.solution(n, tops);
        Assertions.assertThat(result).isEqualTo(11);
    }

    //10	[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]	7704
    @Test
    public void test3() {
        MountainShapeTiling mountainShapeTiling = new MountainShapeTiling();
        int n = 10;
        int[] tops = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int result = mountainShapeTiling.solution(n, tops);
        Assertions.assertThat(result).isEqualTo(7704);
    }

    @Test
    public void test4() {
        MountainShapeTiling mountainShapeTiling = new MountainShapeTiling();
        int n = 100_000;
        int[] tops = new int[n];
        int result = mountainShapeTiling.solution(n, tops);
        Assertions.assertThat(result).isEqualTo(7704);
    }
}
