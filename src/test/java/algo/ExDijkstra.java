package algo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 다익스트라
    - 한 노드에서 다른 모든 노드까지 가는데 최소비용

 작동원리
    - 간선(Edge) : 인접(Adjacency) 리스트
    - 거리 배열 : 초기값 무한대로 설정
    - 힙 시작점 추가
    - 힙에서 현재 노드 빼면서, 간선 통할 때 더 거리 짧아진다면

 팁:
    - 다익스트라 코드 그냥 외우기
    - 코드가 복잡하므로 연습 필요
    - 중요한건, 해당 문제가 디익스트라 문제인지 알아내는 능력
        > 한점에서 다른점으로 가는 최소 비용

 문제
 백준 1753 https://www.acmicpc.net/problem/1753
 V : vertex 갯수  1 ≤ V ≤ 20,000  -> int
    1~v까지 번호
 E : edge 갯수  1 ≤ E ≤ 300,000 -> int

 K : 거리 구하는 시작 정점의 번호 1 ≤ K ≤ V -> int

 (u, v, w) -> u 노드 -> v노드 : 간선 w 가중치  1<= w <= 10 자연수 -> int

 u와 v사이에 간선이 여러개 일 수 있음

 아이디어 :
    - 모든 점 거리 초기값 무한대로 설정
    - 인접리스트 저장
    - 시작점 거리 0 설정 및 힙에 추가
    - 힙에서 하나씩 빼면서 수행
        > 현재 거리가 새로운 간선 거칠때보다 크다면 갱신
        > 새로운 거리 힙에 추가

 시간 복잡도:
    힙 처리 과정 : E log V = 300,000 * 20 => 6,000,000 < 2억

 자료구조 :
  distance -> 최대값 20,000 * 10 = 200,000 < 20억

*/

public class ExDijkstra {

    @Test
    public void test() {
        int v = 5;
        int e = 6;
        int k = 1;

        int[][] maps = {
                {5, 1, 1},
                {1, 2, 2},
                {1, 3, 3},
                {2, 3, 4},
                {2, 4, 5},
                {3, 4, 6}
        };

        int[] distances = execute(v, e, k, maps);

        Assertions.assertThat(distances).containsExactly(-1, 0, 2, 3, 7, Integer.MAX_VALUE);

        for (int i = 1; i < distances.length ; i++) {
            if(distances[i]< Integer.MAX_VALUE)
                System.out.println(distances[i]);
            else
                System.out.println("INF");
        }
    }

    private int[] execute(int vertexCount, int edgeCount, int kStartVertex, int[][] maps) {
        Map<Integer, List<int[]>> adjacencyList = new HashMap<>();
        for (int[] map : maps) {
            int vertexFrom = map[0];
            int vertexTo = map[1];
            int edgeWeight = map[2];

            List<int[]> edgeList = adjacencyList.getOrDefault(vertexFrom, new ArrayList<>());
            edgeList.add(new int[]{vertexTo, edgeWeight});
            adjacencyList.putIfAbsent(vertexFrom, edgeList);
        }

        int[] distance = new int[vertexCount + 1];
        distance[0] = -1;
        for (int i = 1; i <=vertexCount ; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt((int[] o) -> o[1]));
        heap.add(new int[]{kStartVertex, 0});
        distance[kStartVertex] = 0;

        while (!heap.isEmpty()) {
            int[] middle = heap.poll();
            int midVertex = middle[0];
            int midWeight = middle[1];
            if(distance[midVertex] != midWeight) continue;

            for ( int[] next : adjacencyList.getOrDefault(midVertex, new ArrayList<>())) {
                int nextVertex = next[0];
                int nextWeight = next[1] + midWeight;
                if (distance[nextVertex] > nextWeight) {
                    distance[nextVertex] = nextWeight;
                    heap.offer(new int[]{nextVertex, nextWeight});
                }
            }
        }

        return distance;
    }

    @Test
    public void 단일_노드() {
        int v = 1;
        int e = 1;
        int k = 1;

        int[][] maps = {
                {1, 1, 1}
        };

        int[] distances = execute(v, e, k, maps);

        Assertions.assertThat(distances).containsExactly(-1, 0);
    }

    @Test
    public void 연결되지_않은_노드() {
        int v = 4;
        int e = 2;
        int k = 1;

        int[][] maps = {
                {1, 2, 3},
                {1, 3, 5}
        };

        int[] distances = execute(v, e, k, maps);

        Assertions.assertThat(distances).containsExactly(-1, 0, 3, 5, Integer.MAX_VALUE);
    }

    @Test
    public void 사이클_존재() {
        int v = 4;
        int e = 4;
        int k = 1;

        int[][] maps = {
                {1, 2, 2},
                {2, 3, 2},
                {3, 4, 2},
                {4, 2, 1}
        };

        int[] distances = execute(v, e, k, maps);

        Assertions.assertThat(distances).containsExactly(-1, 0, 2, 4, 6);
    }

    @Test
    public void 큰_입력_테스트() {
        int v = 20_000;
        int e = 30_000;
        int k = 1;

        int[][] maps = new int[e][3];
        for (int i = 0; i < e; i++) {
            maps[i] = new int[]{(i + 1) % (v + 1), (i + 2) % (v + 1), 10};
        }

        int[] distances = execute(v, e, k, maps);

        int[] expected = new int[v + 1];
        expected[0] = -1;
        for (int i = 0; i < v ; i++) {
            expected[i + 1] = i * 10;
        }
        Assertions.assertThat(distances).containsExactly(expected);
    }
}
