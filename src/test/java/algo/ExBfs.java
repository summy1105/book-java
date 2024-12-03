package algo;

import lombok.AllArgsConstructor;
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

// 백준 https://www.acmicpc.net/problem/1926
public class ExBfs {
    int[][] map;
    boolean[][] check;
    int maxRow;
    int maxColumn;

    @Test
    public void test() {
        maxRow =6; maxColumn =5;
        map = new int[][]{{1,1,0,1,1}
                ,{0,1,1,0,0}
                ,{0,0,0,0,0}
                ,{1,0,1,1,1}
                ,{0,0,1,1,1}
                ,{0,0,1,1,1}};
        check = new boolean[maxRow][maxColumn];
        call();
    }

    private void call() {

        int cnt = 0;
        int maxValue = 0;

        for (int row = 0; row < maxRow; row++) {
            for (int column = 0; column < maxColumn; column++) {
                if (map[row][column] == 1 && check[row][column] == false) {
                    check[row][column] = true;
                    cnt++;
                    maxValue = Math.max(maxValue, bfs(row, column));
                }
            }
        }
        System.out.println(cnt);
        System.out.println(maxValue);
    }

    @AllArgsConstructor
    static class Tuple{int row; int column;}

    // 3, 6, 9, 12 시 방향 한칸 씩
    static int[] rowDirection = new int[]{0, 1, 0, -1};
    static int[] columnDirection = new int[]{1, 0, -1, 0};

    // queue 사용
    private int bfs(int curRow, int curColumn) {
        int result = 1;
        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(curRow, curColumn));

        while (queue.size() > 0) {
            Tuple e = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = e.row + rowDirection[i];
                int nextColumn = e.column + columnDirection[i];
                if (0 <= nextRow && nextRow < maxRow
                        && 0 <= nextColumn && nextColumn < maxColumn
                    && map[nextRow][nextColumn] == 1 && check[nextRow][nextColumn] == false
                ) {
                    result ++;
                    check[nextRow][nextColumn] = true;
                    queue.add(new Tuple(nextRow, nextColumn));
                }
            }
        }
        return result;
    }
}
