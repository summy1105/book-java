package algo.programmers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

// https://school.programmers.co.kr/learn/courses/30/lessons/120923
// 범위가 중요했음.....
public class TwoPointerContinuousSum {
    class Solution {
        public int[] solution(int num, int total) {
            int[] answer = {};
            int sum = 0;
            for (int i = -50; i < -50 + num; i++) {
                sum += i;
            }
            int firstCur = -50;

            for (int lastCur = -50 + num; lastCur <= 1000; lastCur++, firstCur++) {
                sum -= firstCur;
                sum += lastCur;
                if (sum == total) {
                    return IntStream.rangeClosed(firstCur + 1, lastCur).toArray();
                }
            }
            return answer;
        }
    }

    @Test
    public void execute() {
        Solution solution = new Solution();

        int[] solution1 = solution.solution(1, 1000); // 코너 케이스
        Assertions.assertArrayEquals(new int[]{1000}, solution1);

    }
}
