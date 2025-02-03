package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/12953 lv2
// 7:19 -> 8:00
// 두수의 최대공약수 -> 공배수 ->공약수
public class NLeastCommonMultiple {

    public int solution(int[] arr) {
        int commonMultiple = arr[0];
        for (int i = 1; i < arr.length; i++) {
            commonMultiple = getLCM(Math.max(commonMultiple, arr[i]), Math.min(commonMultiple, arr[i]));
        }
        return commonMultiple;
    }

    int getGCD(int a, int b) {
        while(b!=0){
            int temp = a%b;
            a = b;
            b = temp;
        }
        return a;
    }

    int getLCM(int a, int b) {
        int gcd = getGCD(a, b);
        return (a / gcd) * b;
    }

    @Test
    public void ex1() {
        long result = solution(new int[]{2,6,8,14});
        Assertions.assertThat(result).isEqualTo(168);
    }

    @Test
    public void ex2() {
        long result = solution(new int[]{1,2,3});
        Assertions.assertThat(result).isEqualTo(6);
    }

}
