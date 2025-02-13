package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/42578 lv2
// Dynamic? -> dynamic이란걸 파악하는게 중요
public class Clothes {
    public int solution(String[][] clothes) {
        //종류별 갯수 구하기
        HashMap<String, Integer> countsOfKindMap = new HashMap();
        for (int i = 0; i < clothes.length; i++) {
            Integer cnt = countsOfKindMap.getOrDefault(clothes[i][1], 0);
            countsOfKindMap.put(clothes[i][1], ++cnt);
        }
        Integer[] countsOfKind = countsOfKindMap.values().toArray(new Integer[]{});

        int sum=0;
        for (int i = 0; i < countsOfKind.length; i++) {
            sum += (sum + 1) * countsOfKind[i];
        }
        return sum;
    }

    @Test
    public void ex1() {
        int result = solution(new String[][]{
                {"yellow_hat", "headgear"},
                {"blue_sunglasses", "eyewear"},
                {"green_turban", "headgear"}
        });
        Assertions.assertThat(result).isEqualTo(5);
    }

    @Test
    public void ex2() {
        int result = solution(new String[][]{
                {"crow_mask", "face"},
                {"blue_sunglasses", "face"},
                {"smoky_makeup", "face"}
        });
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void ex3() {
        int result = solution(new String[][]{
                {"crow_mask", "face"},
                {"blue_sunglasses", "face"},
                {"smoky_makeup", "face"},
                {"yellow_hat", "headgear"},
                {"blue_sunglasses", "eyewear"},
                {"green_turban", "headgear"}
        });
        Assertions.assertThat(result).isEqualTo(23);
    }
}
