package algo.programmers.lv1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/161990 lv1
// 9:04 -> 9:21

/**
 *  1<= wallpaper row, col <= 50
 *
 *  최소 row, col, 최대 row, col구하기
 *
 *  50*50
 */
public class DesktopCleanup {
    public int[] solution(String[] wallpaper) {
        int minRow = 51, minCol= 51, maxRow = 0, maxCol=0;

        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    minRow = Math.min(i, minRow);
                    minCol = Math.min(j, minCol);
                    maxRow = Math.max(i+1, maxRow);
                    maxCol = Math.max(j + 1, maxCol);
                }
            }
        }
        return new int[]{minRow, minCol, maxRow, maxCol};
    }

    @Test
    public void ex1() {
        int[] result = solution(new String[]{".#...", "..#..", "...#."});
        Assertions.assertThat(result).containsExactly(0, 1, 3, 4);
    }

    @Test
    public void ex2() {
        int[] result = solution(new String[]{"..........", ".....#....", "......##..", "...##.....", "....#....."});
        Assertions.assertThat(result).containsExactly(1, 3, 5, 8);
    }

    @Test
    public void ex3() {
        int[] result = solution(new String[]{".##...##.", "#..#.#..#", "#...#...#", ".#.....#.", "..#...#..", "...#.#...", "....#...."});
        Assertions.assertThat(result).containsExactly(0, 0, 7, 9);
    }

    @Test
    public void ex4() {
        int[] result = solution(new String[]{"..", "#."});
        Assertions.assertThat(result).containsExactly(1, 0, 2, 1);
    }
}
