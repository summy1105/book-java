package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/131703 lv3

public class CoinFlipping {
    public int solution(int[][] beginning, int[][] target) {
        int rowLength = beginning.length;
        int colLength = beginning[0].length;
        int[] beginBitArr = new int[rowLength];
        int[] targetArr = new int[rowLength];
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if (beginning[i][j] == 1) {
                    beginBitArr[i] |= (1 << j);
                }
                if (target[i][j] == 1) {
                    targetArr[i] |= (1 << j);
                }
            }
        }
        int answer = rowLength + colLength + 1;
        for (int i = 0; i < Math.pow(2, rowLength); i++) {
            for (int j = 0; j < Math.pow(2, colLength); j++) {
                int[] copyArr = Arrays.copyOfRange(beginBitArr, 0, rowLength);

                for (int k = 0; k < rowLength; k++) {
                    if ( ((1 << k) & i) >= 1) {
                        copyArr[k] ^= (int)(Math.pow(2, colLength) - 1);
                    }
                }

                for (int k = 0; k < colLength; k++) {
                    if (((1 << k) & j) >= 1) {
                        System.out.printf("");
                        for (int l = 0; l < rowLength; l++) {
                            copyArr[l] ^= (1 << k);
                        }
                    }
                }

                boolean isMatch = true;
                for (int k = 0; k < rowLength; k++) {
                    if(copyArr[k] != targetArr[k]){
                        isMatch = false;
                        break;
                    }
                }
                int count = Integer.bitCount(i) + Integer.bitCount(j);
                if (isMatch && answer > count) {
                    answer = count;
                }
            }
        }

        return answer == rowLength + colLength + 1 ? -1 : answer;
    }

    //[[0, 1, 0, 0, 0], [1, 0, 1, 0, 1], [0, 1, 1, 1, 0], [1, 0, 1, 1, 0], [0, 1, 0, 1, 0]]	[[0, 0, 0, 1, 1], [0, 0, 0, 0, 1], [0, 0, 1, 0, 1], [0, 0, 0, 1, 0], [0, 0, 0, 0, 1]]	5
    @Test
    void test1() {
        int[][] beginning = {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};
        int[][] target = {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
        Assertions.assertThat(solution(beginning,target)).isEqualTo(5);
    }

    //[[0, 0, 0], [0, 0, 0], [0, 0, 0]]	[[1, 0, 1], [0, 0, 0], [0, 0, 0]]	-1
    @Test
    void test2() {
        int[][] beginning = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] target = {{1, 0, 1}, {0, 0, 0}, {0, 0, 0}};
        Assertions.assertThat(solution(beginning,target)).isEqualTo(-1);
    }

    //[[0, 0, 0], [0, 0, 0], [0, 0, 0]]	[[1, 0, 1], [0, 0, 0], [0, 0, 0]]	-1
    @Test
    void test3() {
        int[][] beginning = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] target = {{1, 0, 1}, {1, 0, 1}, {1, 0, 1}};
        Assertions.assertThat(solution(beginning,target)).isEqualTo(2);
    }

    @Test
    void test4() {
        int[][] beginning = {{0, 0, 1, 0}, {0, 0, 0, 0}, {0, 0, 1, 0}};
        int[][] target = {{0, 0, 0, 1}, {0, 0, 1, 1}, {0, 0, 0, 1}};
        Assertions.assertThat(solution(beginning,target)).isEqualTo(2);
    }

    //[[0, 0, 0], [0, 0, 0], [0, 0, 0]]	[[1, 0, 1], [0, 0, 0], [0, 0, 0]]	-1
    @Test
    void test5() {
        int[][] beginning = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] target = {{1, 0, 1}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}};
        Assertions.assertThat(solution(beginning,target)).isEqualTo(2);
    }
}
