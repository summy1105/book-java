package algo.programmers.lv3;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

// https://school.programmers.co.kr/learn/courses/30/lessons/60063 lv3

public class KaKaoMovingBlock {
    int LENGTH;
    int[][] BOARD;
    int[] rowMove = {0, 1, 0, -1};
    int[] colMove = {1, 0, -1, 0};
    public int solution(int[][] board) {
        LENGTH = board.length;
        BOARD = board;

        Queue<int[]> queue = new LinkedList<>();
        int[] position = {0, 0, 0, 1, 0};
        queue.offer(position);
        HashSet<String> visit = new HashSet<>();
        visit.add(getKey(position));

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r1 = cur[0];
            int c1 = cur[1];
            int r2 = cur[2];
            int c2 = cur[3];
            int time = cur[4];
            if ((r1 == LENGTH - 1 && c1 == LENGTH - 1)
                    || (r2 == LENGTH - 1 && c2 == LENGTH - 1)) {
                return time;
            }

            // 단순이동
            for (int i = 0; i < 4; i++) {
                int[] nextPosition = new int[]{r1 + rowMove[i], c1 + colMove[i]
                        , r2 + rowMove[i], c2 + colMove[i]
                        , time + 1};
                String key = getKey(nextPosition);
                if (isPossible(nextPosition[0], nextPosition[1])
                        && isPossible(nextPosition[2], nextPosition[3])
                        && !visit.contains(key)) {
                    visit.add(key);
                    queue.add(nextPosition);
                }
            }
            //회전
            if(r1 == r2) { // 수평
                {
                    int[] nextPosition = new int[]{r1, c1, r1 - 1, c1, time + 1};
                    String key = getKey(nextPosition);
                    if (isPossible(nextPosition[2], c1) && isPossible(nextPosition[2], c2)
                            && !visit.contains(key)) {
                        visit.add(key);
                        queue.add(nextPosition);
                    }
                }
                {
                    int[] nextPosition = new int[]{r1, c1, r1 + 1, c1, time + 1};
                    String key = getKey(nextPosition);
                    if (isPossible(nextPosition[2], c1) && isPossible(nextPosition[2], c2)
                            && !visit.contains(key)) {
                        visit.add(key);
                        queue.add(nextPosition);
                    }
                }
                {
                    int[] nextPosition = new int[]{r2, c2, r2 - 1, c2, time + 1};
                    String key = getKey(nextPosition);
                    if (isPossible(nextPosition[2], c1) && isPossible(nextPosition[2], c2)
                            && !visit.contains(key)) {
                        visit.add(key);
                        queue.add(nextPosition);
                    }
                }
                {
                    int[] nextPosition = new int[]{r2, c2, r2 + 1, c2, time + 1};
                    String key = getKey(nextPosition);
                    if (isPossible(nextPosition[2], c1) && isPossible(nextPosition[2], c2)
                            && !visit.contains(key)) {
                        visit.add(key);
                        queue.add(nextPosition);
                    }
                }
            }else{//수직
                {
                    int[] nextPosition = new int[]{r1, c1, r1, c1 - 1, time + 1};
                    String key = getKey(nextPosition);
                    if (isPossible(r1, nextPosition[3]) && isPossible(r2, nextPosition[3])
                            && !visit.contains(key)) {
                        visit.add(key);
                        queue.add(nextPosition);
                    }
                }
                {
                    int[] nextPosition = new int[]{r1, c1, r1, c1 + 1, time + 1};
                    String key = getKey(nextPosition);
                    if (isPossible(r1, nextPosition[3]) && isPossible(r2, nextPosition[3])
                            && !visit.contains(key)) {
                        visit.add(key);
                        queue.add(nextPosition);
                    }
                }
                {
                    int[] nextPosition = new int[]{r2, c2, r2, c2 - 1, time + 1};
                    String key = getKey(nextPosition);
                    if (isPossible(r1, nextPosition[3]) && isPossible(r2, nextPosition[3])
                            && !visit.contains(key)) {
                        visit.add(key);
                        queue.add(nextPosition);
                    }
                }
                {
                    int[] nextPosition = new int[]{r2, c2, r2, c2 + 1, time + 1};
                    String key = getKey(nextPosition);
                    if (isPossible(r1, nextPosition[3]) && isPossible(r2, nextPosition[3])
                            && !visit.contains(key)) {
                        visit.add(key);
                        queue.add(nextPosition);
                    }
                }
            }
        }
        return 0;
    }

    private boolean isPossible(int row, int col) {
        return row >= 0 && row < LENGTH && col >= 0 && col < LENGTH && BOARD[row][col] == 0;
    }

    String getKey(int[] position) {
        if(position[0]<position[2] || (position[0]==position[2] && position[1]<=position[3]))
            return position[0] + "-" + position[1] + "-" + position[2] + "-" + position[3];
        return position[2] + "-" + position[3] + "-" + position[0] + "-" + position[1];
    }

    @Test
    public void ex1() {
        int[][] board = {
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}
        };
        int expected = 7;

        int result = solution(board);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void ex2() {
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0, 0, 0}
        };
        int expected = 21;
        int result = solution(board);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void ex3() {
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 0}
        };
        int expected = 11;
        int result = solution(board);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void ex4() {
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0}
        };
        int expected = 33;
        int result = solution(board);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void ex5() {
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 0}
        };
        int expected = 11;
        int result = solution(board);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void ex6() {
        int[][] board = new int[10][10];
        int expected = 17;
        int result = solution(board);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void ex7() {
        int[][] board = {
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int expected = 18;
        int result = solution(board);
        assertThat(result).isEqualTo(expected);
    }
}
