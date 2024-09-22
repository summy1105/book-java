package algo;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

// 백준 1926
public class ExBfs {
    int[][] map;
    boolean[][] check;
    int m;
    int n;

    @Test
    public void test() {
        m=6; n=5;
        map = new int[][]{{1,1,0,1,1}
                ,{0,1,1,0,0}
                ,{0,0,0,0,0}
                ,{1,0,1,1,1}
                ,{0,0,1,1,1}
                ,{0,0,1,1,1}};
        check = new boolean[m][n];
        call();
    }

    private void call() {

        int cnt = 0;
        int maxValue = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && check[i][j] == false) {
                    check[i][j] = true;
                    cnt++;
                    maxValue = Math.max(maxValue, bfs(i, j));
                }
            }
        }
        System.out.println(cnt);
        System.out.println(maxValue);
    }

    @AllArgsConstructor
    static class Tuple{int x; int y;}

    // 3, 6, 9, 12 시 방향 한칸 씩
    static int[] xDirection = new int[]{1, 0, -1, 0};
    static int[] yDirection = new int[]{0, 1, 0, -1};

    // queue 사용
    private int bfs(int x, int y) {
        int result = 1;
        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(x, y));

        while (queue.size() > 0) {
            Tuple e = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = e.x + xDirection[i];
                int nextY = e.y + yDirection[i];
                if (0 <= nextX && nextX < m
                        && 0 <= nextY && nextY < n
                    && map[nextX][nextY] == 1 && check[nextX][nextY] == false
                ) {
                    result ++;
                    check[nextX][nextY] = true;
                    queue.add(new Tuple(nextX, nextY));
                }
            }
        }
        return result;
    }
}
