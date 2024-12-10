package algo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
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

    static int v; // node count
    static int e; // edge count

    @Test
    public void test() {
        v = 3;
        e = 3;
        int[][] edgeInfoList = {
                {1, 2, 1},
                {2, 3, 2},
                {1, 3, 3}
        };

        int expected = 3;
        int result = execute(edgeInfoList);
        Assertions.assertEquals(expected, result);
    }

    private int execute(int[][] edgeInfoList) {
        Map<Integer/*node*/, List<int[]>> adjList = new HashMap<>();
        boolean checkVisitNode[] = new boolean[v + 1];
        PriorityQueue<int[]> heap = new PriorityQueue<>(e, Comparator.comparingInt(o -> ((int[]) o)[0]));

        for (int[] edgeInfo : edgeInfoList) {
            int aNode = edgeInfo[0];
            int bNode = edgeInfo[1];
            int edgeWeight = edgeInfo[2];

            List<int[]> connectedANodeList = adjList.getOrDefault(aNode, new ArrayList<>());
            connectedANodeList.add(new int[]{edgeWeight, bNode});
            adjList.putIfAbsent(aNode, connectedANodeList);

            List<int[]> connectedBNodeList = adjList.getOrDefault(bNode, new ArrayList<>());
            connectedBNodeList.add(new int[]{edgeWeight, aNode});
            adjList.putIfAbsent(bNode, connectedBNodeList);
        }

        heap.offer(new int[]{0, 1});

        int result = 0;
        while (!heap.isEmpty()) {
            int[] curNode = heap.poll();
            if (checkVisitNode[curNode[1]] == false) {
                checkVisitNode[curNode[1]] = true;
                result += curNode[0];
                List<int[]> nextNodes = adjList.get(curNode[1]);
                for (int[] nextEdge : nextNodes) {
                    heap.offer(nextEdge);
                }
            }
        }

        return result;
    }


//    private int execute(int[][] edgeInfoList) {
//        Map<Integer/*node number*/, List<int[]>/*edge info*/> edgeAdjacencyList = new HashMap<>();
//        PriorityQueue<int[]> heap = new PriorityQueue(e, Comparator.comparingInt(a -> ((int[]) a)[0]));
//        boolean[] visitCheck = new boolean[v + 1];
//
//        for (int[] nodeNEdge : edgeInfoList) {
//            int aNode = nodeNEdge[0];
//            int bNode = nodeNEdge[1];
//            int betweenWeight = nodeNEdge[2];
//
//            List<int[]> edgeOfANode = edgeAdjacencyList.getOrDefault(aNode, new ArrayList<>());
//            edgeOfANode.add(new int[]{betweenWeight, bNode});
//            edgeAdjacencyList.putIfAbsent(aNode, edgeOfANode);
//
//            List<int[]> edgeOfBNode = edgeAdjacencyList.getOrDefault(bNode, new ArrayList<>());
//            edgeOfBNode.add(new int[]{betweenWeight, aNode});
//            edgeAdjacencyList.putIfAbsent(bNode, edgeOfBNode);
//        }
//
//        heap.offer(new int[]{0, 1});
//        int result = 0;
//        while (!heap.isEmpty()) {
//            int[] eachNode = heap.poll();
//            if (visitCheck[eachNode[1]] == false) {
//                visitCheck[eachNode[1]] = true;
//                result += eachNode[0];
//
//                for (int[] nextEdge : edgeAdjacencyList.get(eachNode[1])) {
//                    if (visitCheck[nextEdge[1]] == false)
//                        heap.offer(nextEdge);
//                }
//            }
//        }
//        return result;
//    }
}
