package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/87946 lv2
// 12 -> 33
// backtracking!!
public class FatigueLevel {
    public int solution(int k, int[][] dungeons) {
        return findMaxCount(dungeons, new boolean[dungeons.length], k, 0);
    }

    int findMaxCount(int[][] dungeons, boolean[] visited, int remainFatigue, int depth) {

        int max = depth;
        for (int i = 0; i < dungeons.length; i++) {
            if(visited[i]) continue;
            if (remainFatigue < dungeons[i][0]) continue;
            visited[i] = true;
            max = Math.max(max, findMaxCount(dungeons, visited, remainFatigue - dungeons[i][1], depth + 1));
            visited[i] = false;
        }
        return max;
    }

    @Test
    public void ex1() {
        int result = solution(80, new int[][]{
                {80, 20}, {50, 40}, {30, 10}
        });
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void ex2() {
        int result = solution(80, new int[][]{
                {80, 20}, {50, 40}, {30, 20}
        });
        Assertions.assertThat(result).isEqualTo(2);
    }
}
