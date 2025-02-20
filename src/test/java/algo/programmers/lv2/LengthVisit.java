package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/49994 lv2
// 55 -> 29
public class LengthVisit {

    // U, R, D, L
    final int[] rowDirection = new int[]{-1, 0, 1, 0};
    final int[] colDirection = new int[]{0, 1, 0, -1};
    final List<Character> directions = List.of('U', 'R', 'D', 'L');
    public int solution(String dirs) {
        int answer = 0;
        int[] curPosition = new int[2];
        boolean[][][] visitPath = new boolean[11][11][2];// x, y
        for (char direction : dirs.toCharArray()) {
            int dirIdx = directions.indexOf(direction);
            int nextRow = curPosition[0] + rowDirection[dirIdx];
            int nextCol = curPosition[1] + colDirection[dirIdx];
            if(isInMap(nextRow, nextCol)){
                int visitPathPointRow = Math.min(curPosition[0], nextRow) + 5;
                int visitPathPointCol = Math.min(curPosition[1], nextCol) + 5;
                if(visitPath[visitPathPointRow][visitPathPointCol][dirIdx%2] == false){
                    visitPath[visitPathPointRow][visitPathPointCol][dirIdx%2] = true;
                    answer++;
                }
                curPosition[0] = nextRow;
                curPosition[1] = nextCol;
            }
        }
        return answer;
    }

    private boolean isInMap(int nextRow, int nextCol) {
        if( nextRow < -5 || nextRow >5 || nextCol < -5 || nextCol > 5) return false;
        return true;
    }

    @Test
    public void ex1() {
        int result = solution("ULURRDLLU");
        Assertions.assertThat(result).isEqualTo(7);
    }

    @Test
    public void ex2() {
        int result = solution("LULLLLLLU");
        Assertions.assertThat(result).isEqualTo(7);
    }

    @Test
    public void ex3() {
        int result = solution("LLLLLUUUUUUUUUUU");
        Assertions.assertThat(result).isEqualTo(10);
    }

    @Test
    public void ex4() {
        int result = solution("RRRRRDDDDDUUUUULLLLL");
        Assertions.assertThat(result).isEqualTo(10);
    }
}
