package algo;

import lombok.AllArgsConstructor;
import org.apache.commons.math3.util.Pair;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 - 그래프 탐색 : 어떤 것들이 연속해서 이어질 때, 모두 확인하는 방법
    > Graph : Vertex(어떤것) + Edge(이어지는 것)
 - 그래프 탐색 종류
    > BFS : Breadth-first search(너비 우선 탐색)
    > DFS : Depth-first search(깊이 우선 탐색)
 */

/**
 - 아이디어
    > 시작점에 연결된 Vertex 찾기
    > 찾은 Vertex를 Queue에 저장
    > Queue의 가장 먼저 것 뽑아서 반복

 - 시간 복잡도
    > 알고리즘이 얼마나 오래걸리는지
    > BFS : o(v+e)

 - 자료구조
    > 검색할 그래프 : int[][]
    > 방문여부 : boolean[][]
    > queue
 */

/** 백준 https://www.acmicpc.net/problem/1926
    도화지의 세로 크기 n(1 ≤ n ≤ 500)
            가로 크기 m(1 ≤ m ≤ 500)
    1은 색칠
    0은 색칠
 */
public class ExBfs {

    int[][] map;
    boolean[][] check;
    int maxRow;
    int maxColumn;

    @Test
    public void 백준_예제() {
        maxRow = 6; // n
        maxColumn = 5; // m
        map = new int[][]{{1, 1, 0, 1, 1}
                , {0, 1, 1, 0, 0}
                , {0, 0, 0, 0, 0}
                , {1, 0, 1, 1, 1}
                , {0, 0, 1, 1, 1}
                , {0, 0, 1, 1, 1}};
        check = new boolean[maxRow][maxColumn];
        PaintInfo paintInfo = execute();

        Assertions.assertThat(paintInfo.count).isEqualTo(4);
        Assertions.assertThat(paintInfo.maxArea).isEqualTo(9);

        System.out.println(paintInfo.count);
        System.out.println(paintInfo.maxArea);
    }

    @Test
    void 모든_셀이_빈_공간() {
        maxRow = 4;
        maxColumn = 4;
        map = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        check = new boolean[maxRow][maxColumn];
        PaintInfo paintInfo = execute();

        Assertions.assertThat(paintInfo.count).isEqualTo(0);
        Assertions.assertThat(paintInfo.maxArea).isEqualTo(0);
    }

    @Test
    void 가장자리에_있는_그림() {
        maxRow = 5;
        maxColumn = 5;
        map = new int[][]{
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1}
        };
        check = new boolean[maxRow][maxColumn];
        PaintInfo paintInfo = execute();

        Assertions.assertThat(paintInfo.count).isEqualTo(4);
        Assertions.assertThat(paintInfo.maxArea).isEqualTo(1);
    }

    @Test
    void 큰_그림이_격리된_경우() {
        maxRow = 6;
        maxColumn = 6;
        map = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1}
        };
        check = new boolean[maxRow][maxColumn];
        PaintInfo paintInfo = execute();

        Assertions.assertThat(paintInfo.count).isEqualTo(2);
        Assertions.assertThat(paintInfo.maxArea).isEqualTo(9);
    }

    @Test
    void 모든_칸이_그림() {
        // 모든 칸이 그림
        maxRow = 3;
        maxColumn = 3;
        map = new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        check = new boolean[maxRow][maxColumn];
        PaintInfo paintInfo = execute();

        Assertions.assertThat(paintInfo.count).isEqualTo(1);
        Assertions.assertThat(paintInfo.maxArea).isEqualTo(9);
    }

    static private class PaintInfo{
        int count;
        int maxArea;

        PaintInfo(int count, int maxArea) {
            this.count = count;
            this.maxArea = maxArea;
        }
    }

    private PaintInfo execute() {
        int cnt = 0;
        int maxValue = 0;

        for (int row = 0; row < maxRow; row++) {
            for (int column = 0; column < maxColumn; column++) {
                if( check[row][column] == false
                    && map[row][column] == 1){
                    cnt++;
                    maxValue = Math.max(maxValue, bfs(row, column));
                }
            }
        }

        return new PaintInfo(cnt, maxValue);
    }

    static private class Position {
        int row;
        int column;

        Position(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }

    // 3, 6, 9, 12
    static final int[] rowDirection = {0, 1, 0, -1};
    static final int[] columnDirection = {1, 0, -1, 0};

    // queue 사용
    private int bfs(int curRow, int curColumn) {
        int areaValue = 1;
        check[curRow][curColumn] = true;

        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(curRow, curColumn));

        while (!queue.isEmpty()) {
            Position curPosition = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = curPosition.row + rowDirection[i];
                int nextColumn = curPosition.column + columnDirection[i];

                if (isNextPositionInMapArea(nextRow, nextColumn)
                        && check[nextRow][nextColumn] == false
                        && map[nextRow][nextColumn] == 1
                ) {
                    check[nextRow][nextColumn] = true;
                    areaValue++;
                    queue.add(new Position(nextRow, nextColumn));
                }
            }
        }

        return areaValue;
    }

    private boolean isNextPositionInMapArea(int nextRow, int nextColumn) {
        return nextRow >= 0 && nextRow < maxRow
                && nextColumn >= 0 && nextColumn < maxColumn;
    }
}
