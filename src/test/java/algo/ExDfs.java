package algo;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Stack;
import java.util.TreeSet;

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

// 백준 https://www.acmicpc.net/problem/2667
public class ExDfs {
    int n;
    int[][] map;
    boolean[][] check;

    @Test
    public void test() {
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
        call();
    }

    private void call() {
        check = new boolean[n][n];
        TreeSet<Integer> houseCountList = new TreeSet<>();

        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                if (map[row][column] == 1 && check[row][column] == false) {
                    // 방문체크 표시
                    check[row][column] = true;
                    // dfs로 크기 구하기
                    // 결과 리스트에 추가
                    houseCountList.add(dfs(row, column, 1));
                }
            }
        }
        System.out.println(houseCountList.size());
        houseCountList.stream().forEach(System.out::println);
    }

    @AllArgsConstructor
    static class Tuple{int row; int column;}

    // 3,6,9,12시 방향 순
    static final int[] rowDirection = {1, 0, -1, 0};
    static final int[] columnDirection = {0, -1, 0, 1};

    private int dfs(int row, int column, int count) {
        Stack<Tuple> stack = new Stack<>();
        stack.push(new Tuple(row, column));
        while (true) {
            if(stack.empty()) return count;

            Tuple current = stack.pop();
            for (int k = 0; k < 4; k++) {
                int nextRow = current.row + rowDirection[k];
                int nextColumn = current.column + columnDirection[k];
                if (0 <= nextRow && nextRow < n
                        && 0 <= nextColumn && nextColumn < n
                        && map[nextRow][nextColumn] == 1 && check[nextRow][nextColumn] == false) {
                    check[nextRow][nextColumn] = true;
                    count++;
                    stack.push(new Tuple(nextRow, nextColumn));
                }
            }
        }
    }
}
