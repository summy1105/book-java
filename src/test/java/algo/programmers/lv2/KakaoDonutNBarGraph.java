package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/258711 lv2

public class KakaoDonutNBarGraph {
    Map<Integer, ArrayList<Integer>> adjacency;
    boolean[] desVisit;
    public int[] solution(int[][] edges) {
        adjacency = new HashMap<>();
        desVisit = new boolean[edges.length + 3];
        for(int[] edge : edges){
            ArrayList<Integer> list = adjacency.getOrDefault(edge[0], new ArrayList<>());
            list.add(edge[1]);
            adjacency.put(edge[0], list);
            desVisit[edge[1]] = true;
        }
        int[] answer = new int[4];
        for (int i = 1; i < desVisit.length; i++) {
            int childCount = adjacency.getOrDefault(i, new ArrayList<>()).size();
            if(desVisit[i] || childCount<2 ) continue;
            answer[0] = i;
            break;
        }

        for (Integer subVertex : adjacency.get(answer[0])) {
            Queue<Integer> queue = new LinkedList<>();
            int edgeCount=0;
            HashSet<Integer> visitVertex = new HashSet<>();
            visitVertex.add(subVertex);
            queue.offer(subVertex);
            while (!queue.isEmpty()) {
                Integer cur = queue.poll();
                ArrayList<Integer> curEdges = adjacency.getOrDefault(cur, new ArrayList<>());
                edgeCount += curEdges.size();
                for (Integer nextVertex : curEdges) {
                    if(!visitVertex.contains(nextVertex)){
                        queue.offer(nextVertex);
                        visitVertex.add(nextVertex);
                    }
                }
            }

            int vertexCount = visitVertex.size();
            if(edgeCount == vertexCount)
                answer[1]++;
            else if(edgeCount < vertexCount)
                answer[2]++;
            else
                answer[3]++;
        }
        return answer; // {추가, 도넛, 막대, 8자}
    }

    //[[2, 3], [4, 3], [1, 1], [2, 1]]	[2, 1, 1, 0]
    @Test
    public void test1() {
        int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
        int[] result = solution(edges);
        Assertions.assertThat(result).containsExactly(2, 1, 1, 0);
    }

    //[[4, 11], [1, 12], [8, 3], [12, 7], [4, 2], [7, 11], [4, 8], [9, 6], [10, 11], [6, 10], [3, 5], [11, 1], [5, 3], [11, 9], [3, 8]]	[4, 0, 1, 2]
    @Test
    public void test2() {
        int[][] edges = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};
        int[] result = solution(edges);
        Assertions.assertThat(result).containsExactly(4, 0, 1, 2);
    }

    @Test
    public void test3() {
        int[][] edges = {{4, 1}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 3}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};
        int[] result = solution(edges);
        Assertions.assertThat(result).containsExactly(4, 0, 1, 2);
    }

    @Test
    public void test4() {
        int[][] edges = {{2, 3}, {4, 3}, {2, 1}, {1, 5}, {5, 1}, {2, 6}, {6, 7}};
        int[] result = solution(edges);
        Assertions.assertThat(result).containsExactly(2, 1, 2, 0);
    }
}