package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/43105 lv3
// 17 -> 45
public class IntegerTriangle {
    public int solution(int[][] triangle) {
        int[] bottomSum = new int[triangle[triangle.length - 1].length+1];
        for (int[] floor : triangle) {
            int previousSum = bottomSum[0] + floor[0];
            for (int i = 1; i < floor.length; i++) {
                int leftPathSum = floor[i] + bottomSum[i - 1];
                int rightPathSum = floor[i] + bottomSum[i];
                bottomSum[i - 1] = previousSum;
                previousSum = Math.max(leftPathSum, rightPathSum);
            }
            bottomSum[floor.length - 1] = previousSum;
        }
        Arrays.sort(bottomSum);
        return bottomSum[bottomSum.length-1];
    }

    @Test
    public void ex1() {
        int result = solution(new int[][]{
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}
        });
        Assertions.assertThat(result).isEqualTo(30);
    }

    @Test
    public void ex2() {
        int result = solution(new int[][]{
                {5},
                {9, 2},
                {4, 6, 8},
                {0, 7, 1, 5},
                {3, 4, 9, 2, 6}
        });
        Assertions.assertThat(result).isEqualTo(36);
    }
}
