package algo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
    MST : Minimum Spanning Tree
    Spanning Tree : 모든 노드가 연결된 트리
    => 최소의 비용으로 모든 노드가 연결된 트리

    - mst 푸는 방법 : Kruskal or Prim
        > Kruskal : 전체 간선 중 작은 것 부터 연결 - Union Find 알고리즘 사용
        > Prim : 현재 연결된 트리에 이어진 간선 중 가장 작은 것을 추가 - min 값을 찾는데, heap 자료구조 사용

    - heap
        > 최대값, 최소값을 빠르게 계산하기 위한 자료구조
        > 이진 트리 구조
        > 처음에 저장할 때 부터 최대값 or 최소값 결정하도록
        > 노드를 push, pop할 때, 시간 복잡도는 log e

    ** mst 문제는 코드를 외우고 있어야함
    ** 중요한 건, 해당 문제가 mst문제인지 알아내는 능력
        > 모든 노드가 연결되도록 한다거나
        > 이미 연결된 노드를 최소의 비용으로 줄이기

    문제
    백준 1197 https://www.acmicpc.net/problem/1197
    V : 정점(Node)의 갯수 1<= V <= 10,000
    E : 간선(Edge)의 갯수 1<= E <= 100,000
    A B C : A B 노드 번호, C는 A,B의 엣지의 값 /  -1,000,000 <= C <= 1,000,000
    결과값은 -2,146... <= result <= 2,147... - int 범위 내

    heap구조 : java에서는 PriorityQueue를 사용

    아이디어 :
        > 최소스패닝트리 기본문제(외우기)
        > 간선을 인접 리스트 형태로 저장 ( 인접(Adjacency) List : 배열의 index를 노드의 번호로 보고, 각 배열 요소에 인접한(연결된) 노드의 번호(혹은 간선정보와 같이)를 추가해서 나타내는 구조)
        > 시작점부터 힙에 넣기
        > 힙이 빌때까지,
            >> 힙의 최소값을 꺼내서, 해당 노드 방문 안한 곳일 경우
                >> 방문 체크, 비용 추가, 연결된 간선 새롭게 추가
    시간복잡도 :
        > Edge 리스트에 저장 : o(E logE)  10만 * 17
        > Heap안에 모든 Edge에 연결된 간선 확인 : o(E+E) 20만
        > 모든 간선 힙에 삽입 : o(E logE) 10만 * 17
        > o(E + 2E + E logE) = o(E(3+logE)) = o(E logE)
        > 총 : 360만 < 2억

    자료구조 :
        > Edge 저장 리스트 (int-무게, int-노드번호)[]
        > 힙 : (int-무게, int-노드번호)
        > 정점 방문 : boolean[]
        > mst 비용 : int
*/
public class ExMinimumSpanningTree {

    @Test
    public void 예제1() {
        int v = 3;
        int e = 3;
        int[][] edgeInfoList = {
                {1, 2, 1},
                {2, 3, 2},
                {1, 3, 3}
        };

        int result = execute(edgeInfoList, v, e);
        Assertions.assertThat(result).isEqualTo(3);
    }

    @AllArgsConstructor
    @Getter
    static private class EdgeInfo{
        int curVertex;
        int edgeWeight;
    }

    private int execute(int[][] edgeInfoList, int v, int e) {
        Map<Integer, List<int[]>> adjacencyList = new HashMap<>();
        for (int[] edgeInfo : edgeInfoList) {
            int vertexA = edgeInfo[0];
            int vertexB = edgeInfo[1];
            int edgeWeight = edgeInfo[2];

            List<int[]> aList = adjacencyList.getOrDefault(vertexA, new ArrayList<>());
            aList.add(new int[]{vertexB, edgeWeight});
            adjacencyList.putIfAbsent(vertexA, aList);

            List<int[]> bList = adjacencyList.getOrDefault(vertexB, new ArrayList<>());
            bList.add(new int[]{vertexA, edgeWeight});
            adjacencyList.putIfAbsent(vertexB, bList);
        }

        PriorityQueue<EdgeInfo> heap = new PriorityQueue<>(Comparator.comparing(EdgeInfo::getEdgeWeight));
        heap.offer(new EdgeInfo(1, 0));
        boolean[] visitVertex = new boolean[v + 1];

        int result = 0;

        while (!heap.isEmpty()) {
            EdgeInfo minEdge = heap.poll();
            if(visitVertex[minEdge.curVertex]) continue;

            visitVertex[minEdge.curVertex] = true;
            result += minEdge.getEdgeWeight();

            for ( int[] nextVerNEdge : adjacencyList.get(minEdge.curVertex)) {
                int nextVertex = nextVerNEdge[0];
                if (visitVertex[nextVertex] == false) {
                    heap.offer(new EdgeInfo(nextVertex, nextVerNEdge[1]));
                }
            }
        }

        return result;
    }

    @Test
    public void 간선_음수_가중치_테스트() {
        int v = 4;
        int e = 5;
        int[][] edgeInfoList = {
                {1, 2, -5},
                {1, 3, -10},
                {3, 4, 2},
                {2, 4, 1},
                {2, 3, 3}
        };

        int result = execute(edgeInfoList, v, e);
        Assertions.assertThat(result).isEqualTo(-14);
    }

    @Test
    public void 같은_간선_값() {
        int v = 1000;
        int e = 1000;
        int[][] edgeInfoList = new int[e][3];
        for (int i = 0; i < e; i++) {
            edgeInfoList[i][0] = i % v + 1;
            edgeInfoList[i][1] = (i + 1) % v + 1;
            edgeInfoList[i][2] = 1000;
        }

        int result = execute(edgeInfoList, v, e);
        Assertions.assertThat(result).isEqualTo((1000-1)*1000);
    }

    @Test
    public void 단일_간선_테스트() {
        int v = 2;
        int e = 1;
        int[][] edgeInfoList = {
                {1, 2, 100}
        };

        int result = execute(edgeInfoList, v, e);
        Assertions.assertThat(result).isEqualTo(100);
    }

    @Test
    public void 완전_그래프_테스트() {
        int v = 5;
        int e = 10;
        int[][] edgeInfoList = {
                {1, 2, 2},
                {1, 3, 3},
                {1, 4, 1},
                {1, 5, 4},
                {2, 3, 3},
                {2, 4, 2},
                {2, 5, 5},
                {3, 4, 1},
                {3, 5, 7},
                {4, 5, 6}
        };

        int result = execute(edgeInfoList, v, e);
        Assertions.assertThat(result).isEqualTo(8);
    }

    @Test
    public void 음수_양수_혼합_가중치() {
        int v = 6;
        int e = 8;
        int[][] edgeInfoList = {
                {1, 2, -4},
                {1, 3, 2},
                {2, 3, 3},
                {2, 4, -1},
                {3, 5, 4},
                {4, 5, -2},
                {4, 6, 6},
                {5, 6, -3}
        };

        int result = execute(edgeInfoList, v, e);
        Assertions.assertThat(result).isEqualTo(-8);
    }


    @Test
    public void 큰_가중치_테스트() {
        int v = 3;
        int e = 3;
        int[][] edgeInfoList = {
                {1, 2, 1000000},
                {2, 3, 1000000},
                {1, 3, 1000000}
        };

        int result = execute(edgeInfoList, v, e);
        Assertions.assertThat(result).isEqualTo(2000000);
    }

}
