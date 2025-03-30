package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

// https://school.programmers.co.kr/learn/courses/30/lessons/87694 lv3

public class PickingUpItems {

    int[][] rectangle;
    int rLength;
    int[][] points;// 해당 rectangle index표시
    int[] rowDir = {1, 0, -1, 0};
    int[] colDir = {0, 1, 0, -1};
    int MAX_POINT = 101;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        this.rectangle = rectangle;
        this.rLength = rectangle.length;
        for(int r=0; r<rLength; r++){
            for(int c=0; c<4; c++){
                rectangle[r][c] *= 2;
            }
        }
        fillPoints();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{characterX*2, characterY*2, 0});
        boolean[][] visit = new boolean[MAX_POINT][MAX_POINT];
        int answer = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int curX = poll[0];
            int curY = poll[1];
//             System.out.printf("%2d %2d %2d\n", curX, curY, poll[2]);
            if(curX == itemX*2 && curY == itemY*2) answer = Math.min(answer, poll[2]);

            for (int d = 0; d < 4; d++) {
                int nextX = curX + rowDir[d];
                int nextY = curY + colDir[d];
                if (0 < nextX && nextX < MAX_POINT && 0 < nextY && nextY < MAX_POINT
                        && !visit[nextX][nextY] && Integer.bitCount(points[curX][curY] & points[nextX][nextY]) == 1) {
                    visit[nextX][nextY] = true;
                    queue.add(new int[]{nextX, nextY, poll[2] + 1});
                }
            }
        }
        return answer/2;
    }

    void fillPoints(){
        points = new int[MAX_POINT][MAX_POINT];
        for (int x = 1; x < MAX_POINT; x++) {
            for (int y = 1; y < MAX_POINT; y++) {
                for (int k = 0; k < rLength; k++) {
                    if ( ((rectangle[k][0] == x || rectangle[k][2]==x) && rectangle[k][1]<= y && y<=rectangle[k][3])
                            || (rectangle[k][0]<=x && x<= rectangle[k][2]&& (rectangle[k][1]== y || y==rectangle[k][3]))
                    ) points[x][y] += (1 << k);
                }
                for (int k = 0; k < rLength; k++) {
                    if (rectangle[k][0]<x && x<rectangle[k][2] && rectangle[k][1]< y && y< rectangle[k][3] ) points[x][y] =0 ;
                }
//                System.out.printf("%2d ", points[x][y]);
            }
//            System.out.println();
        }
    }

    @Test
    public void ex1() {
        int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
        int result = solution(rectangle, 1, 3, 7, 8);
        Assertions.assertThat(result).isEqualTo(17);
    }

    @Test
    public void ex2() {
        int[][] rectangle = {{1,1,8,4},{2,2,4,9},{3,6,9,8},{6,3,7,7}};
        int result = solution(rectangle, 9, 7, 6, 1);
        Assertions.assertThat(result).isEqualTo(11);
    }

    @Test
    public void ex3() {
        int[][] rectangle = {{1,1,5,7}};
        int result = solution(rectangle, 1, 1, 4, 7);
        Assertions.assertThat(result).isEqualTo(9);
    }

    @Test
    public void ex4() {
        int[][] rectangle = {{2,1,7,5},{6,4,10,10}};
        int result = solution(rectangle, 3, 1, 7, 10);
        Assertions.assertThat(result).isEqualTo(15);
    }

    @Test
    public void ex5() {
        int[][] rectangle = {{2,2,5,5},{1,3,6,4},{3,1,4,6}};
        int result = solution(rectangle, 1, 4, 6, 3);
        Assertions.assertThat(result).isEqualTo(10);
    }
}
