package algo.programmers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//https://school.programmers.co.kr/learn/courses/30/lessons/120876
public class LengthOfOverlappingLineSegments {

    @Test
    public void test() {
        int solution = solution(new int[][]{{0, 5}, {3, 9}, {1, 10}});
        System.out.println(solution);
        Assertions.assertEquals(8, solution);
    }

    public int solution(int[][] lines) {
        int answer = 0;
        int leftPoint = 100;
        int rightPoint = -100;

        for (int i = 0; i < lines.length; i++) {
            if (leftPoint > lines[i][0]) {
                leftPoint = lines[i][0];
            }
            if (rightPoint < lines[i][1]) {
                rightPoint = lines[i][1];
            }
        }

        for (int point = leftPoint; point < rightPoint ; point++) {
            int belongToCnt = 0;
            for (int j = 0; j < lines.length; j++) {
                if (lines[j][0] <= point && point < lines[j][1]) {
                    belongToCnt++;
                }
            }
            if (belongToCnt >= 2) answer++;
        }
        return answer;
    }
}
