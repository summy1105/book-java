package algo.programmers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/120808
// 10:46 -> 11
// 분모 곱하고, 분모 분자 나누기 -> 최대공약수
public class AdditionOfFractions {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int denom = denom1 * denom2;
        int numer = numer1 * denom2 + numer2 * denom1;

        int gcd = gcd(numer, denom);

        return new int[]{numer/gcd, denom/gcd};
    }

    // 유클리드 호제법!!
    int gcd(int fidget, int divisor) {
        int remain = fidget;
        while (remain != 0){
            remain = fidget % divisor;
            fidget = divisor;
            divisor = remain;
        }
        return fidget;
    }

    // 재귀
    int gcdRecursion(int fidget, int divisor) {
        if (fidget % divisor == 0)
            return fidget;
        return gcdRecursion(divisor, fidget % divisor);
    }

    @Test
    public void ex1() {
        int[] result = solution(1, 2, 3, 4);
        Assertions.assertThat(result).containsExactly(5, 4);
    }

    @Test
    public void ex2() {
        int[] result = solution(9, 2, 1, 3);
        Assertions.assertThat(result).containsExactly(29, 6);
    }

    @Test
    public void ex3() {
        int[] result = solution(19, 15, 27, 20);
        Assertions.assertThat(result).containsExactly(157, 60);
    }

    @Test
    public void ex4() {
        int[] result = solution(1, 1, 1, 1);
        Assertions.assertThat(result).containsExactly(2, 1);
    }

    @Test
    public void ex5() {
        int[] result = solution(998, 999, 998, 999);
        Assertions.assertThat(result).containsExactly(1996, 999);
    }

    @Test
    public void ex6() {
        int[] result = solution(1, 5, 1, 6);
        Assertions.assertThat(result).containsExactly(11, 30);
    }

    @Test
    public void testCase4() {
        int[] result = solution(7, 8, 5, 12);
        Assertions.assertThat(result).containsExactly(31, 24);
    }

    @Test
    public void testCase5() {
        int[] result = solution(3, 5, 7, 10);
        Assertions.assertThat(result).containsExactly(13, 10);
    }

    @Test
    public void testCase6() {
        int[] result = solution( 5, 7, 3, 14);
        Assertions.assertThat(result).containsExactly(13, 14);
    }

    @Test
    public void testCase7() {
        int[] result = solution( 10, 15, 2, 5);
        Assertions.assertThat(result).containsExactly(16, 15);
    }
}
