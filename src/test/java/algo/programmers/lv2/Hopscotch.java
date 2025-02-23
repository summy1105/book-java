package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

// https://school.programmers.co.kr/learn/courses/30/lessons/12913 lv2
public class Hopscotch {
    int solution(int[][] land) {
        int answer = 0;
        for (int i = 1; i < land.length; i++) {
            land[i][0] = land[i][0] + Math.max(Math.max(land[i - 1][1], land[i - 1][2]), land[i - 1][3]);
            land[i][1] = land[i][1] + Math.max(Math.max(land[i - 1][0], land[i - 1][2]), land[i - 1][3]);
            land[i][2] = land[i][2] + Math.max(Math.max(land[i - 1][0], land[i - 1][1]), land[i - 1][3]);
            land[i][3] = land[i][3] + Math.max(Math.max(land[i - 1][0], land[i - 1][1]), land[i - 1][2]);
        }
        int lastIdx = land.length-1;
        answer = Math.max(Math.max(land[lastIdx][0], land[lastIdx][1]), Math.max(land[lastIdx][2], land[lastIdx][3]));
        return answer;
    }

    @Test
    public void ex1() {
        int result = solution(new int[][]{
                {1, 2, 3, 5},
                {5, 6, 7, 8},
                {4, 3, 2, 1}
        });
        Assertions.assertThat(result).isEqualTo(16);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("test", 1);
        String s = map.keySet().toArray(new String[]{})[0];
        System.out.println("s = " + s);
    }
}
