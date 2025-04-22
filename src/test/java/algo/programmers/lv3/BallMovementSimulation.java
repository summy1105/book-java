package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/87391 lv3

public class BallMovementSimulation {

    public long solution(int n, int m, int x, int y, int[][] queries) {
        long rowBoundary = n - 1;
        long colBoundary = m - 1;
        long[] range = new long[]{x, y, x, y}; // minX, minY, minX, maxY
        for (int i = queries.length-1; i >= 0 ; i--) {
            int[] query = queries[i];

            int cmd = query[0];
            long move = query[1];
            if(cmd == 0){
                if (range[1] > 0) {
                    range[1] = Math.min(range[1] + move, colBoundary);
                }
                range[3] = Math.min(range[3] + move, colBoundary);
            } else if (cmd == 1) {
                if (range[3] < colBoundary) {
                    range[3] = Math.max(range[3] - move, 0L);
                }
                range[1] = Math.max(range[1] - move, 0L);
            } else if (cmd == 2) {
                if (range[0] > 0) {
                    range[0] = Math.min(range[0] + move, rowBoundary);
                }
                range[2] = Math.min(range[2] + move, rowBoundary);
            } else {
                if (range[2] < rowBoundary) {
                    range[2] = Math.max(range[2] - move, 0L);
                }
                range[0] = Math.max(range[0] - move, 0L);
            }
        }
        long[] check = new long[]{range[0], range[1]};
        for (int i = 0; i < queries.length ; i++) {
            int[] query = queries[i];

            int cmd = query[0];
            long move = query[1];
            if(cmd == 0){
                check[1] = Math.max(0, check[1] - move);
            } else if (cmd == 1) {
                check[1] = Math.min(check[1] + move, colBoundary);
            } else if (cmd == 2) {
                check[0] = Math.max(0, check[0] - move);
            } else {
                check[0] = Math.min(check[0] + move, rowBoundary);
            }
        }
        if(check[0] != x || check[1] != y) return 0;

        return (range[2] - range[0] + 1L) * (range[3] - range[1] + 1L);
    }

    // 2	2	0	0	[[2,1],[0,1],[1,1],[0,1],[2,1]]	4
    @Test
    void test1() {
        int n = 2;
        int m = 2;
        int x = 0;
        int y = 0;
        int[][] queries = {{2, 1}, {0, 1}, {1, 1}, {0, 1}, {2, 1}};
        long result = solution(n, m, x, y, queries);
        Assertions.assertThat(result).isEqualTo(4);
    }

    //2	5	0	1	[[3,1],[2,2],[1,1],[2,3],[0,1],[2,1]]	2
    @Test
    void test2() {
        int n = 2;
        int m = 5;
        int x = 0;
        int y = 1;
        int[][] queries = {{3, 1}, {2, 2}, {1, 1}, {2, 3}, {0, 1}, {2, 1}};
        long result = solution(n, m, x, y, queries);
        Assertions.assertThat(result).isEqualTo(2);
    }

    //1000 1000 1 1  [[0,100001],[2,100001]]
    @Test
    void test3() {
        int n = 1000;
        int m = 1000;
        int x = 1;
        int y = 1;
        int[][] queries = {{0, 999_999_999}, {2, 999_999_999}};
        long result = solution(n, m, x, y, queries);
        Assertions.assertThat(result).isEqualTo(0);
    }
}
