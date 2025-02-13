package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/42747 lv2
// 알고리즘 설명 적어놓기
public class HIndex {
    public int solution1(int[] citations) {
        int answer = 0;

        for(int i=1; i<=citations.length; i++){
            int candidate = i;
            int graterEqCnt = 0;
            for(int j=0; j< citations.length; j++){
                if(citations[j]>= i) graterEqCnt++;
            }
            if(graterEqCnt>=candidate && citations.length-graterEqCnt< candidate)
                answer = Math.max(answer, candidate);
        }

        return answer;
    }

    // 좀더 빠른 시간 복잡도
    // n log n + n
    int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);

        for(int idx=citations.length-1; idx>=0; idx--){
            int candidate = citations.length - idx;
            if(citations[idx]>= candidate) answer = candidate;
            else break;
        }

        return answer;
    }

    @Test
    public void ex1() {
        int result = solution(new int[]{3, 0, 6, 1, 5});
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void ex2() {
        int result = solution(new int[]{10, 8, 5, 5, 3});
        Assertions.assertThat(result).isEqualTo(4);
    }

    @Test
    public void ex3() {
        int result = solution(new int[]{25, 8, 5, 3, 3});
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void ex4() {
        int result = solution(new int[]{1, 1, 1, 1, 1});
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void ex5() {
        int result = solution(new int[]{0, 0, 0, 0, 0});
        Assertions.assertThat(result).isEqualTo(0);
    }
}
