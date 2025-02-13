package algo.programmers.lv1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/1845 lv1
public class PhoneKetMon {
    public int solution(int[] nums) {
        int distinctCount = (int)Arrays.stream(nums).distinct().count();
        return Math.min(distinctCount, nums.length/2);
    }

    @Test
    public void ex1() {
        int result = solution(new int[]{3,1,2,3});
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void ex2() {
        int result = solution(new int[]{3,3,3,2,2,4});
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void ex3() {
        int result = solution(new int[]{3,3,3,2,2,2});
        Assertions.assertThat(result).isEqualTo(2);
    }

}
