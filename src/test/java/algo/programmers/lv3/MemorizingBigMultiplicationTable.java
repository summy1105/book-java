package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/138475 lv3

public class MemorizingBigMultiplicationTable {
    public int[] solution(int e, int[] starts) {
        int[] counts = new int[e+1];
        int[] answer = new int[starts.length];
        HashMap<Integer, Integer> startMap = new HashMap<>(); // key : s, value: {answer idx, maxCount number}
        int min = 0;
        for (int i = 0; i < starts.length; i++) {
            startMap.put(starts[i], i);
            min = Math.min(starts[i], min);
        }
        for (int i = 1; i <= e; i++) {
            for (int j = 1; j <= e / i; j++) {
                counts[i * j]++;
            }
        }

        int[] max = {e + 1, 0};
        for (int i = e; i >=min ; i--) {
            if (max[1] <= counts[i]) {
                max[0] = i;
                max[1] = counts[i];
            }
            if (startMap.containsKey(i)) {
                answer[startMap.get(i)] = max[0];
            }
        }
        return answer;
    }

    // 8	[1,3,7]	[6,6,8]
    @Test
    public void test() {
        int e = 8;
        int[] starts = {1, 3, 7};
        int[] result = solution(e, starts);
        Assertions.assertThat(result).isEqualTo(new int[]{6, 6, 8});
    }
}
