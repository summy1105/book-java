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
        int vertexCount = 5;
        int edgeCount = 6;
        int k_startVertex = 1;

        int[][] maps = {
                {5, 1, 1},
                {1, 2, 2},
                {1, 3, 3},
                {2, 3, 4},
                {2, 4, 5},
                {3, 4, 6}
        };

        int[] distances = execute(vertexCount, edgeCount, k_startVertex, maps);

        Assertions.assertThat(distances).containsExactly(-1, 0, 2, 3, 7, Integer.MAX_VALUE);

        for (int i = 1; i < distances.length ; i++) {
            if(distances[i]< Integer.MAX_VALUE)
                System.out.println(distances[i]);
            else
                System.out.println("INF");
        }
    }

    private int[] execute(int vertexCount, int edgeCount, int k_startVertex, int[][] maps) {
        int[] distance = new int[vertexCount + 1];
        Map<Integer, List<int[]>> adjacencyList = new HashMap<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>(edgeCount, Comparator.comparingInt(o -> ((int[]) o)[0]));

        distance[0] = -1;
        for (int i = 1; i <= vertexCount; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        for (int[] map : maps) {
            int aVertex = map[0];
            int bVertex = map[1];
            int edgeWeight = map[2];

            List<int[]> connectedAVertexList = adjacencyList.getOrDefault(aVertex, new ArrayList<>());
            connectedAVertexList.add(new int[]{edgeWeight, bVertex});
            adjacencyList.putIfAbsent(aVertex, connectedAVertexList);

        }

        heap.offer(new int[]{0, k_startVertex});
        distance[k_startVertex] = 0;
        while (!heap.isEmpty()) {
            int[] current = heap.poll();
            int curWeight = current[0];
            int curVertex = current[1];

            if(distance[curVertex] != curWeight) continue;

            List<int[]> nexts = adjacencyList.getOrDefault(curVertex, new ArrayList<>());
            for (int[] next : nexts) {
                int nextEdgeWeight = next[0];
                int nextVertex = next[1];
                if (distance[nextVertex] > curWeight + nextEdgeWeight) {
                    distance[nextVertex] = curWeight + nextEdgeWeight;
                    heap.offer(new int[]{curWeight + nextEdgeWeight, nextVertex});
                }
            }
        }

        return distance;
    }
}
