package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42885 lv2
// 남아있는 사람중 가장 가벼운 사람 + 무거운 사람 조합
public class Lifeboat {

    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int answer = 0;
        int lightIdx = 0;
        int heavyIdx = people.length-1;
        while (lightIdx <= heavyIdx) {
            if(people[lightIdx]+people[heavyIdx]<=limit){
                lightIdx++;
            }
            heavyIdx--;
            answer++;
        }
        return answer;
    }

    @Test
    public void ex1() {
        int result = solution(new int[]{70, 50, 80, 50}, 100);
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void ex2() {
        int result = solution(new int[]{70, 80, 50}, 100);
        Assertions.assertThat(result).isEqualTo(3);
    }
}
