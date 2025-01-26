package algo.programmers.lv0;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/120866 lv0
// 11:10 -> 11:42
public class SafetyZone {
                                     // 12, 1, 3, 5, 6, 7, 9, 11
    static final int[] rowDirections = {-1, -1, 0, 1, 1, 1, 0, -1};
    static final int[] colDirections = {0, 1, 1, 1, 0, -1, -1, -1};

    public int solution(int[][] board) {
        int answer = 0;
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board.length; column++) {
                boolean isSafe = true;
                for (int i = 0; i < 8; i++) {
                    int searchRow = row + rowDirections[i];
                    int searchColumn = column + colDirections[i];
                    if (inBoardArea(board.length, searchRow, searchColumn)
                            && board[searchRow][searchColumn]==1) {
                        isSafe =false;
                        break;
                    }
                }
                if(isSafe && board[row][column]==0) answer++;
            }
        }
        return answer;
    }

    private boolean inBoardArea(int boardLength, int searchRow, int searchColumn) {
        return searchRow >= 0 && searchRow < boardLength
                && searchColumn >= 0 && searchColumn < boardLength;
    }

    @Test
    public void 예제1() {
        int[][] board = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0}
        };

        int result = solution(board);
        Assertions.assertThat(result).isEqualTo(16);
    }

    @Test
    public void 예제2() {
        int[][] board = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0}
        };

        int result = solution(board);
        Assertions.assertThat(result).isEqualTo(13);
    }

    @Test
    public void 예제3() {
        int[][] board = {
                {1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1}
        };

        int result = solution(board);
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void 예제4() {
        int[][] board = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        int result = solution(board);
        Assertions.assertThat(result).isEqualTo(25);
    }

    @Test
    public void 예제5() {
        int[][] board = {
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

        int result = solution(board);
        Assertions.assertThat(result).isEqualTo(21);
    }
}
