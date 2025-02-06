package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/12985 lv2
// 39 ->
public class EstimatedMatchSheet {

    public int solution(int n, int a, int b)
    {
        int maxRound = Integer.toBinaryString(n).length() - 1;
        int reverseRound = binary(n, Math.min(a, b), Math.max(a, b), 0);

        return maxRound- reverseRound;
    }

    private int binary(int n, int a, int b, int depth){
        int min = n/2;
        if( a<= min && min < b) return depth;
        else if (a>min) return binary(min, a - min, b - min, ++depth);
        else return binary(min, a, b, ++depth);
    }

    // bit계산
    // 설명 : 8이면 -> 7 -> 3자리, 7-> 6  111^110 = 1
    public int solution1(int n, int a, int b)
    {
        return Integer.toBinaryString((a-1)^(b-1)).length();
    }

    @Test
    public void ex1() {
        int result = solution1(8,4,7);
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void ex2() {
        int result = solution1(16, 13, 16);
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void ex3() {
        int result = solution1(1024*1024, 1024*1024-1, 1024*1024);
        Assertions.assertThat(result).isEqualTo(1);
    }

}
