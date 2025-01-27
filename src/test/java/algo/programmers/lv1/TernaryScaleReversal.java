package algo.programmers.lv1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/68935 lv1

public class TernaryScaleReversal {
    public int solution(int n) {
        // 3진법
        String ternaryStr = "";
        while(n>0){
            ternaryStr = (n%3) + ternaryStr;
            n = n/3;
        }

        char[] ternaryChars = ternaryStr.toCharArray();
        int answer = 0;
        for(int i=0; i< ternaryChars.length; i++){
            answer += (ternaryChars[i]-'0')*Math.pow(3, i);
        }
        return answer;
    }

    @Test
    public void ex1() {
        int result = solution(45);
        Assertions.assertThat(result).isEqualTo(7);
    }

    @Test
    public void ex2() {
        int result = solution(125);
        Assertions.assertThat(result).isEqualTo(229);
    }

}
