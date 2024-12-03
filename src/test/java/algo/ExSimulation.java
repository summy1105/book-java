package algo;

import org.junit.jupiter.api.Test;

// 백준 https://www.acmicpc.net/problem/14503
// 1 아이디어
//   while 문으로 특정조건 종료될때까지 반복
//   4방향을 for문으로 탐색
//   더이상 탐색 불가능할 경우, 뒤로 한칸 후진
//   후진이 불가능 하면 종료
// 2 시간 복잡도 : N*M*4 -> 2억을 초과하는지
// 3 자료구조 :
// 전체 지도 int[][],
// 로봇청소기 위치 방향 청소한 곳 수
public class ExSimulation {
    int n;
    int m;

    int y;
    int x;
    int d;
    int[][] map;

    int count=0;

    int[] dy = new int[]{-1, 0, 1, 0};
    int[] dx = new int[]{0, -1, 0, 1};

    @Test
    public void test() {
        n = 11;
        m = 10;
        y = 7;
        x = 4;
        d = 0;
        map = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 1, 1, 1, 0, 1},
                {1, 0, 0, 1, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        simulation();
    }

    private void simulation() {
        while (true) {
            if(map[y][x] == 0) {
                map[y][x] = 2;
                count++;
            }
            boolean findNotClean = false;
            for (int i = 0; i < 4; i++) {
                int nextD = (d + i + 1) % 4; // +1을 해야함 보던방향에서 바라보던 방향을 먼저 탐색하는것이 아닌 반시계 90을 먼저 돌기때문
                int nextY = y + dy[nextD];
                int nextX = x + dx[nextD];
                if (map[nextY][nextX] == 0) { // nextY랑 nextX가 0보다 크고 각각 N, M보다 작아야 한다.
                    y = nextY;
                    x = nextX;
                    d = nextD;
                    findNotClean = true;
                    break;
                }
            }
            if (!findNotClean) {
                int nextY = y - dy[d];
                int nextX = x - dx[d];
                if (map[nextY][nextX] == 1) {
                    break;
                } else {
                    y = nextY;
                    x = nextX;
                }
            }
        }
        System.out.println("count = " + count);
    }
}
