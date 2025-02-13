package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

// https://school.programmers.co.kr/learn/courses/30/lessons/12949 lv2
// tow point
public class MultiplicationMatrices {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        for (int row = 0; row < arr1.length; row++) {
            for (int col = 0; col < arr2[0].length; col++) {
                final int rowIdx = row, colIdx = col;
                answer[row][col] = IntStream.range(0, arr1[row].length).map(i -> arr1[rowIdx][i] * arr2[i][colIdx]).sum();
            }
        }
        return answer;
    }

    @Test
    public void ex1() {
        int[][] result = solution(new int[][]{{1, 4}, {3, 2}, {4, 1}},
                new int[][]{{3, 3}, {3, 3}});
        Assertions.assertThat(result)
                .isDeepEqualTo(new int[][]{{15, 15}, {15, 15}, {15, 15}});
    }

    @Test
    public void ex2() {
        int[][] result = solution(new int[][]{{2, 3, 2}, {4, 2, 4}, {3, 1, 4}},
                new int[][]{{5, 4, 3}, {2, 4, 1}, {3, 1, 1}});
        Assertions.assertThat(result)
                .isDeepEqualTo(new int[][]{{22, 22, 11}, {36, 28, 18}, {29, 20, 14}});
    }

}
