package algo.programmers.lv1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/72410lv1
// 9:52 -> 10:27

/**
 */
public class KakaoPressingTheKeypad {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int lastLeft = 10 /*'*'*/, lastRight= 12 /*'#'*/;

        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i]==0) numbers[i] = 11;

            int leftCount = Math.abs(numbers[i] - lastLeft) % 3 + Math.abs(numbers[i] - lastLeft) / 3;
            int rightCount = Math.abs(numbers[i] - lastRight) % 3 + Math.abs(numbers[i] - lastRight) / 3;
            int col = numbers[i] % 3;

            if (col == 1
                    || (col == 2 && leftCount < rightCount)
                    || (col == 2 && leftCount == rightCount && "left".equals(hand))) {
                answer += "L";
                lastLeft = numbers[i];
            } else if (col == 0
                    || (col == 2 && leftCount > rightCount)
                    || (col == 2 && leftCount == rightCount && "right".equals(hand))) {
                answer += "R";
                lastRight = numbers[i];
            }
        }
        return answer;
    }

    @Test
    public void ex1() {
        String result = solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right");
        Assertions.assertThat(result).isEqualTo("LRLLLRLLRRL");
    }

    @Test
    public void ex2() {
        String result = solution(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left");
        Assertions.assertThat(result).isEqualTo("LRLLRRLLLRR");
    }

    @Test
    public void ex3() {
        String result = solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, "right");
        Assertions.assertThat(result).isEqualTo("LLRLLRLLRL");
    }
}
