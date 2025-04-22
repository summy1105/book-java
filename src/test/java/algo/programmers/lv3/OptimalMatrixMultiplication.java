package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/12942 lv3

public class OptimalMatrixMultiplication {
    public int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length;

        int[][] dp = new int[n][n];
        // 길이가 1인 구간은 곱셈이 필요 없으므로 dp[i][i] = 0 (기본값)

        // 구간의 길이(L)를 2부터 n까지 늘려가며 계산합니다.
        for (int len = 2; len <= n; len++) {
            for (int left = 0; left <= n - len; left++) {
                int right = left + len - 1;
                dp[left][right] = Integer.MAX_VALUE;
                // 가능한 k (left <= k < right) 위치에서 나누어 계산
                for (int k = left; k < right; k++) {
                    // matrix_sizes[left][0] = A(left)의 행, matrix_sizes[k][1] = A(k)의 열,
                    // matrix_sizes[right][1] = A(right)의 열 이므로 곱셈 연산 수는 다음과 같이 계산됩니다.
                    int cost = dp[left][k] + dp[k + 1][right] + matrix_sizes[left][0] * matrix_sizes[k][1] * matrix_sizes[right][1];
                    dp[left][right] = Math.min(dp[left][right], cost);
                }
            }
        }
        return dp[0][n - 1];
    }

    //    matrix_sizes	result
    //[[5,3],[3,10],[10,6]]	270
    @Test
    public void test() {
        int[][] matrix_sizes = {{5, 3}, {3, 10}, {10, 6}};
        int result = solution(matrix_sizes);
        Assertions.assertThat(result).isEqualTo(270);
    }

    //[[5, 3], [3, 10], [10, 6], [6, 3]]
    @Test
    public void test2() {
        int[][] matrix_sizes = {{5, 3}, {3, 10}, {10, 6}, {6, 3}};
        int result = solution(matrix_sizes);
        Assertions.assertThat(result).isEqualTo(180+54+45);
    }

    //[[5, 3], [3, 10], [10, 11], [11, 3], [3,2]]
    @Test
    public void test3() {
        int[][] matrix_sizes = {{5, 3}, {3, 10}, {10, 11}, {11, 3}, {3,2}};
        int result = solution(matrix_sizes);
        Assertions.assertThat(result).isEqualTo(376);
    }

    //[[5, 3],[3, 3], [3, 10], [10, 11], [11, 11], [11, 3], [3,2]]
    @Test
    public void test4() {
        int[][] matrix_sizes = {{5, 3}, {3, 3}, {3, 10}, {10, 11}, {11, 11}, {11, 3}, {3,2}};
        int result = solution(matrix_sizes);
        Assertions.assertThat(result).isEqualTo(636);
    }
}
