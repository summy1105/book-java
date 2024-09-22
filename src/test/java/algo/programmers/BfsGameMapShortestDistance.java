package algo.programmers;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/1844
public class BfsGameMapShortestDistance {
    @Test
    public void test() {
        // 13
//        int[][] maps = new int[][]{
//                {1, 0, 1, 1, 1},
//                {1, 0, 1, 0, 1},
//                {1, 1, 1, 0, 1},
//                {1, 1, 0, 1, 1},
//                {1, 0, 0, 0, 1}};
        // 9
//        int[][] maps = new int[][]{
//                {1, 1, 1, 1, 1},
//                {0, 0, 1, 0, 1},
//                {0, 0, 1, 1, 1},
//                {0, 1, 1, 0, 1},
//                {0, 0, 0, 0, 1}
//        };
        // 3
//        int[][] maps = new int[][]{
//                {1, 1},
//                {0, 1}
//        };
        // -1
//        int[][] maps = new int[][]{
//                {1, 0, 1, 1, 1},
//                {1, 0, 1, 0, 1},
//                {1, 0, 1, 1, 1},
//                {1, 1, 1, 0, 0},
//                {0, 0, 0, 0, 1}
//        };
        // 5
        int[][] maps = new int[][]{{1, 1, 1, 1, 1}};

        int answer = solution(maps);
        System.out.println(answer);
    }

    int lastY;
    int lastX;
    static final int[] directionX = new int[]{1, 0, -1, 0};
    static final int[] directionY = new int[]{0, 1, 0, -1};
    static class Position{
        int y; int x; int distance;

        public Position(int y, int x, int distance) {
            this.y = y;
            this.x = x;
            this.distance = distance;
        }
    }

    public int solution(int[][] maps) {
            int yLength = maps.length;
            int xLength = maps[0].length;

            this.lastY = yLength -1;
            this.lastX = xLength - 1;

            int answer = bfs(maps, yLength, xLength);

            return answer > yLength * xLength ? -1 : answer;
    }

    public int bfs(int[][] maps, int lengthY, int lengthX){
        Queue<Position> queue = new LinkedList<>();
        boolean[][] visitCheck = new boolean[lengthY][lengthX];
        {
            int startY = 0;
            int startX = 0;
            int startDistance = 1;
            queue.add(new Position(startY, startX, startDistance));
            visitCheck[startY][startX] = true;
        }
        int minDistance = lengthX * lengthY + 1;

        while (!queue.isEmpty()) {
            Position poll = queue.poll();
            if (poll.x == this.lastX && poll.y == this.lastY) {
                minDistance = Math.min(minDistance, poll.distance);
            }
            for (int i = 0; i < 4; i++) {
                int nextY = poll.y + directionY[i];
                int nextX = poll.x + directionX[i];
                if( 0<= nextY && nextY < lengthY
                        && 0<= nextX && nextX < lengthX
                        && maps[nextY][nextX] == 1
                        && visitCheck[nextY][nextX] == false
                ){
                    visitCheck[nextY][nextX] = true;
                    queue.add(new Position(nextY, nextX, poll.distance+1));

                }
            }
        }

        return minDistance;
    }

    {
    // dfs로는 테스트 케이스 통과했지만, 효율성에서 통과하지 못함, -> bfs로 풀기
//        public int solution(int[][] maps) {
//            int yLength = maps.length;
//            int xLength = maps[0].length;
//
//            this.lastY = yLength -1;
//            this.lastX = xLength - 1;
//
//            int answer = dfs(maps, new boolean[yLength][xLength], 0, 0, 0);
//
//            return answer > yLength * xLength ? -1 : answer;
//        }
//
//
//        public int dfs(int[][] maps, boolean[][] visitMap, int startY, int startX, int depth) {
//            if(startY == this.lastY && startX == this.lastX){
//                visitMap[startY][startX] = false;
//                return depth+1;
//            }
//            int minStackSize = Integer.MAX_VALUE;
//            for (int i = 0; i < 4; i++) {
//                int nextY = startY + directionY[i];
//                int nextX = startX + directionX[i];
//                if (0 <= nextY && nextY <= this.lastY
//                        && 0 <= nextX && nextX <= this.lastX
//                        && maps[nextY][nextX] == 1
//                        && visitMap[nextY][nextX]== false
//                ) {
//                    visitMap[nextY][nextX] = true;
//                    minStackSize = Math.min(minStackSize, dfs(maps, visitMap, nextY, nextX, depth+1));
//                }
//            }
//            visitMap[startY][startX] = false;
//            return minStackSize;
//        }
    }
}
