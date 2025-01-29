package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/86052 lv2

public class LightPathCycle {
                                    // S, W, N, E
    static final int[] rowDirection = {1, 0, -1, 0};
    static final int[] colDirection = {0, -1, 0, 1};
    static final int directionLength = 4;

    public int[] solution(String[] grid) {
        char[][] map = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            map[i] = grid[i].toCharArray();
        }

        List<Integer> answer = new ArrayList<>();
        boolean[][][] visited = new boolean[directionLength][map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                for (int direction = 0; direction < directionLength ; direction++) {
                    int[] curPosition = new int[]{direction, i, j};
                    if(visited[direction][i][j]) continue;
                    Queue<int[]> cycle = new LinkedList<>();
                    while (!isFirstPosition(cycle, curPosition)){
                        cycle.add(Arrays.copyOf(curPosition, 3));
                        visited[curPosition[0]][curPosition[1]][curPosition[2]] = true;

                        char character = map[curPosition[1]][curPosition[2]];
                        switch (character){
                            case 'L' :
                                curPosition[0] = (curPosition[0] - 1 + directionLength) % directionLength;
                                break;
                            case 'R' :
                                curPosition[0] = (curPosition[0] + 1) % directionLength;
                                break;
                        }
                        curPosition[1] = (curPosition[1] + rowDirection[curPosition[0]] + map.length) % map.length;
                        curPosition[2] = (curPosition[2] + colDirection[curPosition[0]] + map[0].length) % map[0].length;
                    }
                    answer.add(cycle.size());
                }
            }
        }
        return answer.stream().mapToInt(Integer::intValue).sorted().toArray();
    }

    private boolean isFirstPosition(Queue<int[]> cycle, int[] curPosition) {
        int[] first = cycle.peek();
        if(first == null) return false;
        if(first[0] == curPosition[0] && first[1] == curPosition[1] && first[2] == curPosition[2]) return true;
        return false;
    }


    @Test
    public void ex1() {
        int[] result = solution(new String[]{"SL","LR"});
        Assertions.assertThat(result).containsExactly(16);
    }

    @Test
    public void ex2() {
        int[] result = solution(new String[]{"S"});
        Assertions.assertThat(result).containsExactly(1,1,1,1);
    }

    @Test
    public void ex3() {
        int[] result = solution(new String[]{"R","R"});
        Assertions.assertThat(result).containsExactly(4,4);
    }

    @Test
    public void ex4() {
        int[] result = solution(new String[]{"LL"});
        Assertions.assertThat(result).containsExactly(4,4);
    }

    @Test
    public void ex5() {
        int[] result = solution(new String[]{"SSS", "SSS"});
        Assertions.assertThat(result).containsExactly(2, 2, 2, 2, 2, 2, 3, 3, 3, 3);
    }
}
