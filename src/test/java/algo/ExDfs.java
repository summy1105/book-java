package algo;

import org.junit.jupiter.api.Test;

import java.util.TreeSet;

// 백준 2667
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
        TreeSet<Integer> houseCount = new TreeSet<>();

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (map[y][x] == 1 && check[y][x] == false) {
                    // 방문체크 표시
                    check[y][x] = true;
                    // dfs로 크기 구하기
                    // 결과 리스트에 추가
                    houseCount.add(dfs(y, x, 1));
                }
            }
        }
        System.out.println(houseCount.size());
        houseCount.stream().forEach(System.out::println);
    }
    // 3,6,9,12시 방향 순
    static final int[] xDirection = {1, 0, -1, 0};
    static final int[] yDirection = {0, -1, 0, 1};

    private int dfs(int y1, int x1, int each) {
        for (int k = 0; k < 4; k++) {
            int y2 = y1 + yDirection[k];
            int x2 = x1 + xDirection[k];
            if (0 <= y2 && y2 < n
                    && 0 <= x2 && x2 < n
                    && map[y2][x2] == 1 && check[y2][x2] == false) {
                check[y2][x2] = true;
                each = dfs(y2, x2, each+1);
            }
        }
        return each;
    }
}
