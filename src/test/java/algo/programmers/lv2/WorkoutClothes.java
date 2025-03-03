package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42862 lv2
public class WorkoutClothes {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);

        int answer = n-lost.length;

        for (int l = 0, r = 0; l < lost.length && r<reserve.length; l++) {
            while(r<reserve.length && reserve[r]<lost[l]) r++;
            if(r<reserve.length && reserve[r]==lost[l]){
                lost[l] = -1;
                reserve[r] = -1;
                answer++;
            }
        }

        for (int l = 0, r = 0; l < lost.length && r < reserve.length; l++) {
            if(lost[l] == -1) continue;
            while (r < reserve.length && (reserve[r] == -1
                    || reserve[r] < lost[l] - 1)) r++;
            if (r < reserve.length
                    && (lost[l] - 1 == reserve[r] || lost[l] + 1 == reserve[r])) {
                answer++;
                r++;
            }
        }

        return answer;
    }

    @Test
    public void ex1() {
        int result = solution(5, new int[]{2, 4}, new int[]{1, 3, 5});
        Assertions.assertThat(result).isEqualTo(5);
    }

    @Test
    public void ex2() {
        int result = solution(5, new int[]{2, 4}, new int[]{3});
        Assertions.assertThat(result).isEqualTo(4);
    }

    @Test
    public void ex3() {
        int result = solution(3, new int[]{3}, new int[]{1});
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void ex4() {
        int result = solution(5, new int[]{2, 3, 4, 20}, new int[]{1, 3, 4, 30});
        Assertions.assertThat(result).isEqualTo(4);
    }
}
