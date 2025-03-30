package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

// https://school.programmers.co.kr/learn/courses/30/lessons/150367 lv3

public class KakaoExpressableBinaryTree {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String binaryString = Long.toBinaryString(numbers[i]);

            int length = binaryString.length();
            int fullLengthExponent = 1;
            while ((1 << fullLengthExponent) <= length) {
                fullLengthExponent++;
            }

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < Math.pow(2, fullLengthExponent) -1 - length; j++) {
                sb.append('0');
            }
            sb.append(binaryString);

            answer[i] = isValid(sb.toString()) ? 1 : 0;
        }
        return answer;
    }

    boolean isValid(String binaryStr) {
        if(binaryStr.length()==1) return true;

        int midIdx = binaryStr.length()/2;
        char root = binaryStr.charAt(midIdx);

        if(root == '0'){
            return binaryStr.indexOf("1") == -1;
        }

        String left = binaryStr.substring(0, midIdx);
        String right = binaryStr.substring(midIdx + 1);
        return isValid(left) && isValid(right);
    }

    @Test
    public void ex1() {
        long[] numbers = {7, 42, 5};
        int[] expected = {1, 1, 0};
        int[] result = solution(numbers);
        Assertions.assertThat(result).containsExactly(expected);
    }

    @Test
    public void ex2() {
        long[] numbers = {63, 111, 95};
        int[] expected = {1, 1, 0};
        int[] result = solution(numbers);
        Assertions.assertThat(result).containsExactly(expected);
    }
}
