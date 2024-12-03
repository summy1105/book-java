package algo.programmers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

// https://school.programmers.co.kr/learn/courses/30/lessons/178870 lv2 : 약 한시간.....
// 비내림차순 : 오름차순이지만 같은 수가 있음
// closed 수열
// 합이 k인 수열들중 길이가 짧은 수열 찾기 -> 그중 앞의 인덱스가 젤 작은수열

// 5<= sequence의 길이 <= 1,000,000 (백만)
//  1<= 각각 원소 <= 1,000
// 5<= 합계(k) <= 1,000,000,000 (십억) (int)

public class SumOfConsecutiveSubsequences {
    class Solution {
        public int[] solution(int[] sequence, int k) {
            int sum = 0;
            int minLength = sequence.length + 1;
            int firstIdx = 0;
            int lastIdx = 0;

            int curFirst = 0;
            for (int curLast = 0; curLast < sequence.length; curLast++) {
                sum += sequence[curLast];
                if (sum == k) {
                    int subLength = curLast - curFirst + 1;
                    if ( subLength < minLength) {
                        firstIdx = curFirst;
                        lastIdx = curLast;
                        minLength = subLength;
                    }
                }

                if (sum > k) {
                    while (curFirst <= curLast) {
                        sum -= sequence[curFirst++];
                        if (sum == k) {
                            int subLength = curLast - curFirst + 1;
                            if ( subLength < minLength) {
                                firstIdx = curFirst;
                                lastIdx = curLast;
                                minLength = subLength;
                            }
                            break;
                        }
                        if(sum < k) break;
                    }
                }
            }

            return new int[]{firstIdx, lastIdx};
        }
    }

    @Test
    public void execute() {
        Solution solution = new Solution();
        // case 1
        int[] sequence = new int[]{1, 2, 3, 4, 5};
        int k = 7; // 부분 수열의 합
        int[] expected = new int[]{2, 3};
        int[] answer = solution.solution(sequence, k);
        Assertions.assertArrayEquals(expected, answer);

        // case 2
        sequence = new int[]{1, 1, 1, 2, 3, 4, 5};
        k = 5;
        expected = new int[]{6, 6};
        answer = solution.solution(sequence, k);
        Assertions.assertArrayEquals(expected, answer);

        // case 3
        sequence = new int[]{2, 2, 2, 2, 2};
        k = 6;
        expected = new int[]{0, 2};
        answer = solution.solution(sequence, k);
        Assertions.assertArrayEquals(expected, answer);

        // 코너 케이스 1 : {1,1,1,1,1}
        sequence = new int[]{1, 1, 1, 1, 1};
        k = 5;
        expected = new int[]{0, 4};
        answer = solution.solution(sequence, k);
        Assertions.assertArrayEquals(expected, answer);

        // 코너 케이스 2 : {1000*1,000,000,000개}
        sequence = IntStream.generate(() -> 1_000).limit(1_000_000).toArray();
        k = 1_000_000_000;
        expected = new int[]{0, 999_999};
        answer = solution.solution(sequence, k);
        Assertions.assertArrayEquals(expected, answer);

        // 코너 케이스 3 : {5,5,5,5,5}
        sequence = new int[]{5, 5, 5, 5, 5};
        k = 5;
        expected = new int[]{0, 0};
        answer = solution.solution(sequence, k);
        Assertions.assertArrayEquals(expected, answer);
    }
}
