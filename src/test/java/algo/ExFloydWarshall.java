package algo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**

 - 모든 노드에서 가능한 다른 모든 노드까지 최단경로를 구하는 알고리즘
 - 작동원리
    1. Node j -> Node i 로 가는 비용(Edge Weight) 배열 만들기 : 초기값 INF
    2. Edge의 값을 Weight 배열에 반영
    3. 모든 노드에 대해 해당 노드를 거쳐서 가는 비용이 작아질 경우 값 갱신
        > 거치는 노드 for
        > 시작 for
        > 끝 for
    => 시간 복잡도 V^3

  문제
    백준 11404 플로이드
    https://www.acmicpc.net/problem/11404

    - n개의 도시(노드)  2 ≤ n ≤ 100
    - m개의 버스(edge) 갯수 1 ≤ m ≤ 100,000
    - 버스 출발 도시 a = N1, b = N2, c = Edge Weight  1 ≤ c ≤ 100,000
    - a와 b를 연결하는 edge갯수 0 ≤ edge갯수 ≤ 100,000

    아이디어: 플로이드
    시간 복잡도: v^3 => 100^3 = 1,000,000 백만 < 2억
    자료구조
        n : int, m,c : int,
        합계 최대값 : int = 100*100,000 = 10,000,000 < 20억
 */
public class ExFloydWarshall {
    static final int MAX_EDGE_WEIGHT = 100_000;
    @Test
    public void 백준_예제() {
        int n = 5;
        int m = 14;
        int[][] edgeWeightArray= {
            {1, 2, 2},
            {1, 3, 3},
            {1, 4, 1},
            {1, 5, 10},
            {2, 4, 2},
            {3, 4, 1},
            {3, 5, 1},
            {4, 5, 3},
            {3, 5, 10},
            {3, 1, 8},
            {1, 4, 2},
            {5, 1, 7},
            {3, 4, 2},
            {5, 2, 4}
        };

        int[][] floydWeight = execute(n, m, edgeWeightArray);

        int[][] expected = new int[][]{
                {0, 2, 3, 1, 4},
                {12, 0, 15, 2, 5},
                {8, 5, 0, 1, 1},
                {10, 7, 13, 0, 3},
                {7, 4, 10, 6, 0}
        };

        for (int i = 0; i < floydWeight.length; i++) {
            Assertions.assertThat(floydWeight[i]).containsExactly(expected[i]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n ; j++) {
                System.out.print(floydWeight[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void 예제1() {
        int n = 3;
        int m = 6;
        int[][] edgeWeightArray = {
                {1, 2, 4},
                {1, 3, 2},
                {2, 3, 5},
                {3, 1, 1},
                {2, 1, 3},
                {3, 2, 1}
        };

        int[][] floydWeight = execute(n, m, edgeWeightArray);

        int[][] expected = new int[][]{
                {0, 3, 2},
                {3, 0, 5},
                {1, 1, 0}
        };

        for (int i = 0; i < floydWeight.length; i++) {
            Assertions.assertThat(floydWeight[i]).containsExactly(expected[i]);
        }
    }

    @Test
    public void 예제2() {
        int n = 4;
        int m = 5;
        int[][] edgeWeightArray = {
                {1, 2, 6},
                {1, 3, 8},
                {2, 3, 3},
                {2, 4, 5},
                {3, 4, 2}
        };

        int[][] floydWeight = execute(n, m, edgeWeightArray);

        int[][] expected = new int[][]{
                {0, 6, 8, 10},
                {0, 0, 3, 5},
                {0, 0, 0, 2},
                {0, 0, 0, 0}
        };

        for (int i = 0; i < floydWeight.length; i++) {
            Assertions.assertThat(floydWeight[i]).containsExactly(expected[i]);
        }
    }

    @Test
    public void 예제3() {
        int n = 5;
        int m = 7;
        int[][] edgeWeightArray = {
                {1, 2, 10},
                {2, 3, 2},
                {3, 4, 3},
                {4, 5, 2},
                {5, 1, 9},
                {1, 3, 7},
                {2, 4, 6}
        };

        int[][] floydWeight = execute(n, m, edgeWeightArray);

        int[][] expected = new int[][]{
                {0, 10, 7, 10, 12},
                {16, 0, 2, 5, 7},
                {14, 24, 0, 3, 5},
                {11, 21, 18, 0, 2},
                {9, 19, 16, 19, 0}
        };

        for (int i = 0; i < floydWeight.length; i++) {
            Assertions.assertThat(floydWeight[i]).containsExactly(expected[i]);
        }
    }

    @Test
    public void 예제4() {
        int n = 6;
        int m = 9;
        int[][] edgeWeightArray = {
                {1, 2, 2},
                {1, 3, 4},
                {2, 3, 1},
                {3, 4, 7},
                {4, 5, 3},
                {5, 6, 2},
                {6, 4, 8},
                {2, 5, 10},
                {3, 6, 5}
        };

        int[][] floydWeight = execute(n, m, edgeWeightArray);

        int[][] expected = new int[][]{
                {0, 2, 3, 10, 12, 8},
                {0, 0, 1, 8, 10, 6},
                {0, 0, 0, 7, 10, 5},
                {0, 0, 0, 0, 3, 5},
                {0, 0, 0, 10, 0, 2},
                {0, 0, 0, 8, 11, 0}
        };

        for (int i = 0; i < floydWeight.length; i++) {
            Assertions.assertThat(floydWeight[i]).containsExactly(expected[i]);
        }
    }

    @Test
    public void 예제5() {
        int n = 2;
        int m = 1;
        int[][] edgeWeightArray = {
                {1, 2, 5}
        };

        int[][] floydWeight = execute(n, m, edgeWeightArray);

        int[][] expected = new int[][]{
                {0, 5},
                {0, 0}
        };

        for (int i = 0; i < floydWeight.length; i++) {
            Assertions.assertThat(floydWeight[i]).containsExactly(expected[i]);
        }
    }

    private int[][] execute(int n, int m, int[][] edgeWeightArray) {
        int MAX_SUM_WEIGHT = (n - 1) * MAX_EDGE_WEIGHT + 1; // integer max값으로 하면 overflow현상 발생

        int[][] floydWeight = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                floydWeight[i][j] = MAX_SUM_WEIGHT;
            }
        }
        for (int i = 1; i <=n ; i++) {
            floydWeight[i][i] = 0;
        }
        for (int[] busInfo : edgeWeightArray) {
            int aCity = busInfo[0];
            int bCity = busInfo[1];
            int weight = busInfo[2];

            floydWeight[aCity][bCity] = Math.min(floydWeight[aCity][bCity], weight); // 중요!!
        }

        for (int midCity = 1; midCity <= n ; midCity++) {
            for (int startCity = 1; startCity <= n; startCity++) {
                for (int endCity = 1; endCity <=n ; endCity++) {
                    if (floydWeight[startCity][endCity] > floydWeight[startCity][midCity] + floydWeight[midCity][endCity]) {
                        floydWeight[startCity][endCity] = floydWeight[startCity][midCity] + floydWeight[midCity][endCity];
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (floydWeight[i][j] >= MAX_SUM_WEIGHT)
                    floydWeight[i][j] = 0;
            }
            floydWeight[i] = Arrays.copyOfRange(floydWeight[i], 1, n + 1);
        }
        return Arrays.copyOfRange(floydWeight, 1, n + 1);
    }
}
