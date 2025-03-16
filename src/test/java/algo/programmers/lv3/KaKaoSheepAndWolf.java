package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://school.programmers.co.kr/learn/courses/30/lessons/92343 lv3

public class KaKaoSheepAndWolf {
    int solution(int[] info, int[][] edges) {
        int length = info.length, answer = 0;
        ArrayList<Integer> adj[] = new ArrayList[length];

        for(int i=0; i<length; i++)
            adj[i] = new ArrayList<>();

        for(int e[] : edges) {
            adj[e[0]].add(e[1]);
//            adj[e[1]].add(e[0]);
        }
        Queue<int[]> queue = new LinkedList<>(); // 양, 늑대, visit bit
        queue.add(new int[] {1, 0, 1});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            answer = Math.max(answer, cur[0]);
            int visitedNodes = cur[2];
            for(int i = 0, bit = 1; i<length && bit<= visitedNodes; bit= (1<<++i) ) {
                if((visitedNodes & bit) > 0) { // 현재의 candidate만 체크
                    for(int nextNode : adj[i]) {
                        if((visitedNodes & (1<<nextNode)) > 0 ) continue; // nextNode가 벌써 visit된 상태면 넘김
                        if(info[nextNode] == 1 && cur[0]-cur[1] == 1) continue; // nextNode를 포함할때 양 == 늑대 이면 넘김
                        if(info[nextNode] == 1 && adj[nextNode].size() == 0) continue;
                        int next[] = new int[3];
                        next[0] = info[nextNode] == 0 ? cur[0]+1 : cur[0];
                        next[1] = info[nextNode] == 1 ? cur[1]+1 : cur[1];
                        next[2] = visitedNodes | (1<<nextNode);
                        queue.add(next);
                    }
                }
            }
        }
        return answer;
    }

    @Test
    public void ex1() {
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {
                {0, 1},
                {1, 2},
                {1, 4},
                {0, 8},
                {8, 7},
                {9, 10},
                {9, 11},
                {4, 3},
                {6, 5},
                {4, 6},
                {8, 9}
        };
        int result = solution(info, edges);
        Assertions.assertThat(result).isEqualTo(5);
    }

    @Test
    public void ex2() {
        int[] info = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0};
        int[][] edges = {
                {0, 1},
                {0, 2},
                {1, 3},
                {1, 4},
                {2, 5},
                {2, 6},
                {3, 7},
                {4, 8},
                {6, 9},
                {9, 10}
        };
        int result = solution(info, edges);
        Assertions.assertThat(result).isEqualTo(5);
    }
}
