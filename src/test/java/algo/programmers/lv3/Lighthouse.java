package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/133500 lv3

public class Lighthouse {

    int[][] treeDp;
    List<Integer>[] adjacency;
    public int solution(int n, int[][] lighthouse) {
        treeDp = new int[n+1][2]; // {안켯을떄 갯수, 켰을때}
        for (int i = 0; i <= n ; i++) {
            Arrays.fill(treeDp[i], n+1);
        }
        adjacency = new ArrayList[n + 1];

        int[] rootCandidate = new int[]{0, 0};
        for (int[] edge : lighthouse) {
            if (adjacency[edge[0]] == null)
                adjacency[edge[0]] = new ArrayList<>();
            adjacency[edge[0]].add(edge[1]);
            if (adjacency[edge[0]].size() > rootCandidate[1]) {
                rootCandidate[0] = edge[0];
                rootCandidate[1] = adjacency[edge[0]].size();
            }


            if(adjacency[edge[1]] == null)
                adjacency[edge[1]] = new ArrayList<>();
            adjacency[edge[1]].add(edge[0]);
            if (adjacency[edge[1]].size() > rootCandidate[1]) {
                rootCandidate[1] = edge[1];
                rootCandidate[1] = adjacency[edge[1]].size();
            }
        }
        int root = rootCandidate[0];
        dfs(root, n);
        return Math.min(treeDp[root][0], treeDp[root][1]);
    }

    void dfs(int parent, int n) {
        treeDp[parent][0] = 0;
        treeDp[parent][1] = 1;
        for (Integer child : adjacency[parent]) {
            if(treeDp[child][1] < n) continue;
            dfs(child, n);
            treeDp[parent][0] += treeDp[child][1];
            treeDp[parent][1] += Math.min(treeDp[child][0], treeDp[child][1]);
        }
    }


    //8	[[1, 2], [1, 3], [1, 4], [1, 5], [5, 6], [5, 7], [5, 8]]	2
    @Test
    public void test1() {
        int n = 8;
        int[][] lighthouse = {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}};
        int result = solution(n, lighthouse);
        Assertions.assertThat(result).isEqualTo(2);
    }

    // 10	[[4, 1], [5, 1], [5, 6], [7, 6], [1, 2], [1, 3], [6, 8], [2, 9], [9, 10]]	3
    @Test
    public void test2() {
        int n = 10;
        int[][] lighthouse = {{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}};
        int result = solution(n, lighthouse);
        Assertions.assertThat(result).isEqualTo(3);
    }

}