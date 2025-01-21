package algo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
    - 주어진 조건을 되도록 그대로 구현(나중에 매우 헷갈림)
    - 되도록 쉽게 구현
        > 최악 케이스 : 코드 복잡해 디버깅 안됨, 시간 대부분 소모

 **** 중요: 문제에서 로직의 순서 및 내용을 정확히 인지해야함

 */

/** 백준 https://www.acmicpc.net/problem/14503
 N : row 세로
 M : column 가로
 (3 ≤ N, M ≤ 50)
 d : 방향 0=북, 1=동, 2=남, 3=서

 (row, column, 벽여부) 1=벽, 0=빈칸

 1 아이디어
   while 문으로 특정조건 종료될때까지 반복
   4방향을 for문으로 탐색
   더이상 탐색 불가능할 경우, 뒤로 한칸 후진
   후진이 불가능 하면 종료
 2 시간 복잡도 : N*M*4 -> 2억을 초과하는지
 3 자료구조 :
 전체 지도 int[][],
 로봇청소기 위치 방향 청소한 곳 수
*/
public class ExSimulation {

    @Test
    public void 백준_예제1() {
        int n = 3; // rowCount
        int m = 3; // columnCount
        int[] robotPosition = {1, 1, 0};
        int[][] map = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        int cleanSectionCount = simulation(map, robotPosition);
        Assertions.assertThat(cleanSectionCount).isEqualTo(1);

        System.out.println(cleanSectionCount);
    }

    // 북, 동, 남, 서
    final private int[] rowDirection = {-1, 0, 1, 0};
    final private int[] columnDirection = {0, 1, 0, -1};

    private int simulation(int[][] map, int[] robotPosition) {
        int count = 0;
        int robotRow = robotPosition[0];
        int robotColumn = robotPosition[1];
        int robotDirection = robotPosition[2];
        while (true) {
            if(map[robotRow][robotColumn] == 0) {
                map[robotRow][robotColumn] = 2;
                count++;
            }
            boolean isMovedNext = false;
            for (int i = 0; i < 4; i++) {
                int nextDirection = (robotDirection +3 - i ) % 4; // 청소 구역이 있을 때, 현재 방향에서 다음 반시계 방향으로 먼저 회전함!!
                int nextRow = robotRow + rowDirection[nextDirection];
                int nextColumn = robotColumn + columnDirection[nextDirection];
                if (  map[nextRow][nextColumn] == 0) {
                    robotRow = nextRow;
                    robotColumn = nextColumn;
                    robotDirection = nextDirection;
                    isMovedNext = true;
                    break;
                }
            }
            if(isMovedNext) continue;
            int backRow = robotRow - rowDirection[robotDirection];
            int backColumn = robotColumn - columnDirection[robotDirection];
            if (map[backRow][backColumn] == 2) {
                robotRow = backRow;
                robotColumn = backColumn;
            } else {
                break;
            }
        }
        return count;
    }

    @Test
    public void 백준_예제2() {
        int n = 11; // rowCount
        int m = 10; // columnCount
        int[] robotPosition = {7, 4, 0};
        int[][] map = {
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
        int cleanSectionCount = simulation(map, robotPosition);
        Assertions.assertThat(cleanSectionCount).isEqualTo(57);

        System.out.println(cleanSectionCount);
    }

    @Test
    public void 예제3() {
        int n = 5; // rowCount
        int m = 5; // columnCount
        int[] robotPosition = {2, 2, 1}; // 초기 위치 (2, 2)에서 동쪽(1) 방향
        int[][] map = {
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        int cleanSectionCount = simulation(map, robotPosition);
        Assertions.assertThat(cleanSectionCount).isEqualTo(9);
    }

    @Test
    public void 예제4() {
        int n = 7; // rowCount
        int m = 7; // columnCount
        int[] robotPosition = {3, 3, 2}; // 초기 위치 (3, 3)에서 남쪽(2) 방향
        int[][] map = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };
        int cleanSectionCount = simulation(map, robotPosition);
        Assertions.assertThat(cleanSectionCount).isEqualTo(1);
    }

    @Test
    public void 예제5() {
        int n = 10; // rowCount
        int m = 10; // columnCount
        int[] robotPosition = {1, 1, 3}; // 초기 위치 (1, 1)에서 서쪽(3) 방향
        int[][] map = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        int cleanSectionCount = simulation(map, robotPosition);
        Assertions.assertThat(cleanSectionCount).isEqualTo(26);

        System.out.println(cleanSectionCount);
    }
}
