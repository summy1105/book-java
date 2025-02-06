package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/131701 lv2
public class NumberOfConsecutivePartialSequenceSums {
    public int solution(int[] elements) {
        Set<Integer> answerSet = new TreeSet<>();
        int answer = 0;
        for (int i = 0; i < elements.length; i++) {
            int sum =0;
            for (int j = i; j < i+elements.length-1 ; j++) {
                sum += elements[j % elements.length];
                answerSet.add(sum);
            }
            answer += elements[i];
        }
        answerSet.add(answer);
        return answerSet.size();
    }

    @Test
    public void ex1() {
        int result = solution(new int[]{7,9,1,1,4});
        Assertions.assertThat(result).isEqualTo(18);
    }

    @Test
    public void ex2() {
        int result = solution(new int[]{1,2});
        Assertions.assertThat(result).isEqualTo(3);
    }

}
