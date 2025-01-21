package algo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 - 아이디어
     > 시작점에 연결된 Vertex 찾기
     > 스택사용

 - 시간 복잡도
     > 알고리즘이 얼마나 오래걸리는지
     > DFS : o(v+e)

 - 자료구조
     > 검색할 그래프 : int[][]
     > 방문여부 : boolean[][]
     > stack
 */


/**
    백준 https://www.acmicpc.net/problem/2667

    정사각형 지도
     첫 번째 줄에는 지도의 크기 N  5≤N≤25
     집이 있음 : 1
     집이 없음 : 0
 */
public class ExDfs {
    int n;
    int[][] map;
    boolean[][] visitCheck;

    @Test
    public void 백준_예제() {
        n=7;
        map = new int[][]{
                {0,1,1,0,1,0,0}
                ,{0,1,1,0,1,0,1}
                ,{1,1,1,0,1,0,1}
                ,{0,0,0,0,1,1,1}
                ,{0,1,0,0,0,0,0}
                ,{0,1,1,1,1,1,0}
                ,{0,1,1,1,0,0,0}
        };
        visitCheck = new boolean[n][n];
        List<Integer> houseCountList = execute();

        Assertions.assertThat(houseCountList.size()).isEqualTo(3);
        Assertions.assertThat(houseCountList).containsExactly(7, 8, 9);

        System.out.println(houseCountList.size());
        houseCountList.stream().sorted(Integer::compareTo).forEach(System.out::println);
    }

    private List<Integer> execute() {
        List<Integer> houseCountList = new ArrayList<>();

        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                if (visitCheck[row][column] == false
                        && map[row][column] == 1) {
                    houseCountList.add(dfs(row, column, 1));
                }
            }
        }

        return houseCountList.stream().sorted(Integer::compareTo).collect(Collectors.toList());
    }

    // 3, 6, 9, 12 시 방향
    static final int[] rowDirection = {0, 1, 0, -1};
    static final int[] columnDirection = {1, 0, -1, 0};

    private int dfs(int curRow, int curColumn, int houseCount) {
        visitCheck[curRow][curColumn] = true;

        for (int i = 0; i < 4; i++) {
            int nextRow = curRow + rowDirection[i];
            int nextColumn = curColumn + columnDirection[i];

            if (isNextPositionInMapArea(nextRow, nextColumn)
                    && visitCheck[nextRow][nextColumn] == false
                    && map[nextRow][nextColumn] == 1) {
                visitCheck[nextRow][nextColumn] = true;
                houseCount = dfs(nextRow, nextColumn, houseCount + 1);
            }
        }

        return houseCount;
    }

    private boolean isNextPositionInMapArea(int nextRow, int nextColumn) {
        return nextRow >= 0 && nextRow < n && nextColumn >= 0 && nextColumn < n;
    }

    @Test
    public void 전체가_빈_공간() {
        n = 5;
        map = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        visitCheck = new boolean[n][n];
        List<Integer> houseCountList = execute();

        Assertions.assertThat(houseCountList.size()).isEqualTo(0);
    }

    @Test
    public void 가장자리에만_단지() {
        n = 5;
        map = new int[][]{
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1}
        };
        visitCheck = new boolean[n][n];
        List<Integer> houseCountList = execute();

        Assertions.assertThat(houseCountList.size()).isEqualTo(4);
        Assertions.assertThat(houseCountList).containsExactly(1, 1, 1, 1);
    }

    @Test
    public void 하나의_큰_단지() {
        n = 4;
        map = new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        };
        visitCheck = new boolean[n][n];
        List<Integer> houseCountList = execute();

        Assertions.assertThat(houseCountList.size()).isEqualTo(1);
        Assertions.assertThat(houseCountList).containsExactly(16);
    }

    @Test
    public void 단지가_흩어져_있고_크기가_다양() {
        n = 6;
        map = new int[][]{
                {1, 1, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 0},
                {0, 0, 0, 1, 1, 0},
                {1, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1}
        };
        visitCheck = new boolean[n][n];
        List<Integer> houseCountList = execute();

        Assertions.assertThat(houseCountList.size()).isEqualTo(4);
        Assertions.assertThat(houseCountList).containsExactly(1, 2, 4, 11);
    }

    @Test
    public void 단지의_크기가_모두_1인_경우() {
        n = 5;
        map = new int[][]{
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1}
        };
        visitCheck = new boolean[n][n];
        List<Integer> houseCountList = execute();

        Assertions.assertThat(houseCountList.size()).isEqualTo(13);
        Assertions.assertThat(houseCountList).containsExactly(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
    }
}
