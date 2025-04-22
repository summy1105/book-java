package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/76503 lv3

public class MakingAllZero {
    List<Integer>[] adjacency;
    boolean[] visit;
    long answer=0L;
    long[] sumArr;
    public long solution(int[] a, int[][] edges) {
        adjacency = new List[a.length];
        visit = new boolean[a.length];
        sumArr = new long[a.length];
        for (int[] edge : edges) {
            List<Integer> listA = adjacency[edge[0]];
            if(listA==null) listA = new ArrayList<>();
            listA.add(edge[1]);
            adjacency[edge[0]] = listA;

            List<Integer> listB = adjacency[edge[1]];
            if(listB==null) listB = new ArrayList<>();
            listB.add(edge[0]);
            adjacency[edge[1]] = listB;
        }

        int sum = 0;
        boolean allZero = true;
        int[] rootCandidate = new int[]{-1, 0};
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if(sum != 0) allZero = false;
            List<Integer> list = adjacency[i];
            if (a[i] != 0 && list != null && list.size() > rootCandidate[1]) {
                rootCandidate[0] = i;
                rootCandidate[1] = list.size();
            }
        }
        if(allZero) return 0;
        if(sum!=0) return -1;
        Stack<int[]> leafFirst = getLeafFirst(rootCandidate[0]);
        while (!leafFirst.isEmpty()) {
            int[] pop = leafFirst.pop();

            sumArr[pop[1]] += a[pop[1]];
            sumArr[pop[0]] += sumArr[pop[1]];
            answer += Math.abs(sumArr[pop[1]]);
        }

        return answer;
    }

    Stack<int[]> getLeafFirst(int root){
        Stack<int[]> stack = new Stack<>();
        Stack<int[]> output = new Stack<>();
        stack.push(new int[]{-1, root});
        visit[root] = true;
        while (!stack.isEmpty()) {
            int[] pop = stack.pop();
            if(pop[0]>=0) output.push(pop);
            for (int child : adjacency[pop[1]]) {
                if(!visit[child]) {
                    visit[child] = true;
                    stack.push(new int[]{pop[1], child});
                }
            }
        }
        return output;
    }

    //[-5,0,2,1,2]	[[0,1],[3,4],[2,3],[0,3]]	9

    @Test
    void test() {
        int[] a = {-5, 0, 2, 1, 2};
        int[][] edges = {{0, 1}, {3, 4}, {2, 3}, {0, 3}};
        long result = solution(a, edges);
        Assertions.assertThat(result).isEqualTo(9);
    }

    //[0,1,0]	[[0,1],[1,2]]	-1
    @Test
    void test2() {
        int[] a = {0, 1, 0};
        int[][] edges = {{0, 1}, {1, 2}};
        long result = solution(a, edges);
        Assertions.assertThat(result).isEqualTo(-1);
    }

    // [1, -1] [[0,1]]
    @Test
    void test3() {
        int[] a = {1, -1};
        int[][] edges = {{0, 1}};
        long result = solution(a, edges);
        Assertions.assertThat(result).isEqualTo(1);
    }
}
