package algo;

import org.junit.jupiter.api.Test;

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
    static int MAX_WEIGHT = 100_001;
    @Test
    public void test() {
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

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n ; j++) {
                System.out.print(floydWeight[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int[][] execute(int n, int m, int[][] edgeWeightArray) {
        int[][] floydWeightArray = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n ; j++) {
                floydWeightArray[i][j] = MAX_WEIGHT; //중요!! -> integer max값을 합하면 오버플로우되서 음수가 될 수 있다.
            }
        }
        for (int i = 1; i <=n ; i++) {
            floydWeightArray[i][i] = 0;
        }

        for (int[] edge : edgeWeightArray) {
            int node1 = edge[0];
            int node2 = edge[1];
            int weight = edge[2];
            floydWeightArray[node1][node2] = Math.min(floydWeightArray[node1][node2], weight); // 중요!!
        }

        for (int interNode = 1; interNode <= n; interNode++) {
            for (int startNode = 1; startNode <= n; startNode++) {
                for (int endNode = 1; endNode <= n; endNode++) {
                    int originalWeight = floydWeightArray[startNode][endNode];
                    int weightWithInterNode = floydWeightArray[startNode][interNode] + floydWeightArray[interNode][endNode];
                    if (originalWeight> weightWithInterNode) {
                        floydWeightArray[startNode][endNode] = weightWithInterNode;
                    }
                }
            }
        }


        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n ; j++) {
                if (floydWeightArray[i][j] >= MAX_WEIGHT) {
                    floydWeightArray[i][j] = 0;
                }
            }
        }

        return floydWeightArray;
    }
}
