package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/131127 lv2
// tow point
public class DiscountEvent {

    final private int PERIOD = 10;
    public int solution(String[] want, int[] number, String[] discount) {
        List<String> wantList = Arrays.stream(want).collect(Collectors.toList());
        int answer = 0;
        int left = 0, right = 0;
        for (; right <PERIOD ; right++) {
            if (wantList.contains(discount[right])) {
                number[wantList.indexOf(discount[right])]--;
            }
        }
        if(isNumberEmpty(number)) answer++;

        for (;right < discount.length; right++, left++) {
            if (wantList.contains(discount[right])) {
                number[wantList.indexOf(discount[right])]--;
            }
            if (wantList.contains(discount[left])) {
                number[wantList.indexOf(discount[left])]++;
            }
            if(isNumberEmpty(number)) answer++;
        }

        return answer;
    }

    private boolean isNumberEmpty(int[] number) {
        for (int i = 0; i <number.length ; i++) {
            if(number[i] > 0) return false;
        }
        return true;
    }

    @Test
    public void ex1() {
        int result = solution(new String[]{"banana", "apple", "rice", "pork", "pot"}
                , new int[]{3, 2, 2, 2, 1}
                , new String[]{"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"});
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void ex2() {
        int result = solution(new String[]{"apple"}
                , new int[]{10}
                , new String[]{"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"});
        Assertions.assertThat(result).isEqualTo(0);
    }
}
