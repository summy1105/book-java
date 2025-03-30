package algo.programmers.lv4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42895 lv4

public class ExpressedN {
    public int solution(int N, int number) {
        if(N==number) return 1;

        List<Set<Integer>> dp = new ArrayList<>();
        dp.add(new HashSet<>());
        dp.add(new HashSet<>());
        dp.get(1).add(N);

        for (int count=2; count<=8; count++){
            Set<Integer> currentSet = new HashSet<>();
            dp.add(currentSet);
            //NN 숫자 만들기
            int concat = 0;
            for(int i=0; i<count; i++){
                concat = concat*10 + N;
            }
            currentSet.add(concat);
            for (int i = 1; i < count; i++) {
                for(int a: dp.get(i)){
                    for(int b: dp.get(count-i)){
                        currentSet.add(a + b);
                        currentSet.add(a - b);
                        currentSet.add(a * b);
                        if(b!=0){ currentSet.add(a / b);}
                    }
                }
            }

            if(dp.get(count).contains(number)) return count;
        }

        return -1;
    }

    @Test
    public void ex1() {
        int result = solution(5, 12);
        Assertions.assertThat(result).isEqualTo(4);
    }

    @Test
    public void ex2() {
        int result = solution(2, 11);
        Assertions.assertThat(result).isEqualTo(3);
    }
}
