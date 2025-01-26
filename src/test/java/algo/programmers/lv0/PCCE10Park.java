package algo.programmers.lv0;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/340198 lv1
// 8:27 -> 9:45  dfsX -> greedy 방법 단순하게...

/**
 * -1일때, mats의 가장 큰수부터 범위 가능한지 확인
 *
 * 시간 복잡도 50*50*10*20*20 = 1천만 < 20억
 */
public class PCCE10Park {

    public int solution(int[] mats, String[][] park) {
        Arrays.sort(mats);
        int maxMatWidth = -1;
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[i].length; j++) {
                if (park[i][j].equals("-1")) {
                    maxMatWidth = Math.max(maxMatWidth, findMaxMatWidth(mats, park, i, j));
                }
            }
        }
        return maxMatWidth;
    }

    private int findMaxMatWidth(int[] mats, String[][] park, int row, int col) {
        for (int matIdx = mats.length-1; matIdx >= 0 ; matIdx--) {
            if(possible(mats[matIdx], park, row, col)) return mats[matIdx];
        }
        return -1;
    }

    private boolean possible(int matLength, String[][] park, int startRow, int startCol) {
        if(startRow+matLength> park.length || startCol+matLength>park[0].length) return false;
        for (int i = startRow; i < startRow + matLength; i++) {
            for (int j = startCol; j < startCol + matLength; j++) {
                if(!park[i][j].equals("-1")) return false;
            }
        }
        return true;
    }

    @Test
    public void 예제1() {
        String[][] park = {{"A", "A", "-1", "B", "B", "B", "B", "-1"}
                        , {"A", "A", "-1", "B", "B", "B", "B", "-1"}
                        , {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"}
                        , {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}
                        , {"D", "D", "-1", "-1", "-1", "-1", "-1", "F"}
                        , {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}};
        int result = solution(new int[]{5, 3, 2}, park);
        Assertions.assertThat(result).isEqualTo(3);
    }
}

