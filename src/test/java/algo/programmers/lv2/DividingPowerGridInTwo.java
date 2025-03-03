package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/86971 lv2
// 인접리스트
// a1, a2를 끊는다 하면, a1연결된 노드수, a2에 연결된 노드수
// 연결 노드수: N logN
// weight갯수 반복 : N =>  N * N logN -> 100*100*10
public class DividingPowerGridInTwo {
    public int solution(int n, int[][] wires) {
        HashMap<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for (int i = 0; i < wires.length; i++) {
            List<Integer> aList = adjacencyList.getOrDefault(wires[i][0], new ArrayList<>());
            aList.add(wires[i][1]);
            adjacencyList.putIfAbsent(wires[i][0], aList);

            List<Integer> bList = adjacencyList.getOrDefault(wires[i][1], new ArrayList<>());
            bList.add(wires[i][0]);
            adjacencyList.putIfAbsent(wires[i][1], bList);
        }
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < wires.length; i++) {
            int leftNodeCount = getCount(n, wires[i][0], wires[i][1], adjacencyList);
            int rightNodeCount = getCount(n, wires[i][1], wires[i][0], adjacencyList);

            answer = Math.min(answer, Math.abs(leftNodeCount - rightNodeCount));
        }
        return answer;
    }

    private int getCount(int n, int startNode, int excludeNode, HashMap<Integer, List<Integer>> adjacencyList) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visitNode = new boolean[n+1];

        queue.add(startNode);
        visitNode[excludeNode] = true;

        int count = 0;
        while(!queue.isEmpty()){
            Integer node = queue.poll();
            count++;
            visitNode[node] = true;
            for (int i : adjacencyList.get(node)) {
                if(!visitNode[i]) queue.add(i);
            }
        }
        return count;
    }

    @Test
    public void ex1() {
        int result = solution(9, new int[][]{
                {1, 3},
                {2, 3},
                {3, 4},
                {4, 5},
                {4, 6},
                {4, 7},
                {7, 8},
                {7, 9}
        });
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void ex2() {
        int result = solution(4, new int[][]{
                {1, 2},
                {2, 3},
                {3, 4}
        });
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void ex3() {
        int result = solution(7, new int[][]{
                {1, 2},
                {2, 7},
                {3, 7},
                {3, 4},
                {4, 5},
                {6, 7}
        });
        Assertions.assertThat(result).isEqualTo(1);
    }
}
