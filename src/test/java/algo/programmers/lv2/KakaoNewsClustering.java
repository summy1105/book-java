package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/17677 lv2
// 30-> ....
// 문제 잘 읽기, 알고 있는 메소드로만 사용하기
public class KakaoNewsClustering {
    public int solution(String str1, String str2) {
        Map<String, Integer> union = new HashMap<>();
        Map<String, Integer> intersection = new HashMap<>();

        Map<String, Integer> str1Map = new HashMap<>();
        for (int i = 0; i < str1.length()-1; i++) {
            String substring = str1.substring(i, i + 2).toLowerCase();
            if(substring.matches("[a-z]{2}")) {
                str1Map.put(substring, str1Map.getOrDefault(substring, 0) + 1);
            }
        }

        Map<String, Integer> str2Map = new HashMap<>();
        for (int i = 0; i < str2.length()-1; i++) {
            String substring = str2.substring(i, i + 2).toLowerCase();
            if(substring.matches("[a-z]{2}")) {
                str2Map.put(substring, str2Map.getOrDefault(substring, 0) + 1);
            }
        }

        for (String s : str1Map.keySet()) {
            union.put(s, str1Map.get(s));
            if (str2Map.containsKey(s)) {
                intersection.put(s, Math.min(str1Map.get(s), str2Map.get(s)));
            }
        }
        for (String s : str2Map.keySet()) {
            union.put(s, Math.max(str1Map.getOrDefault(s, 0), str2Map.get(s)));
        }

        Integer unionCount = union.values().stream().reduce(Integer::sum).orElseGet(() -> 0);
        Integer intersectionCount = intersection.values().stream().reduce(Integer::sum).orElseGet(() -> 0);

        double answer = unionCount != 0 ? (double) intersectionCount/ (double)unionCount : 1;
        return (int)Math.floor(answer*65536);
    }

    @Test
    public void ex1() {
        int result = solution("FRANCE", "french");
        Assertions.assertThat(result).isEqualTo(16384);
    }

    @Test
    public void ex2() {
        int result = solution("handshake", "shake hands");
        Assertions.assertThat(result).isEqualTo(65536);
    }

    @Test
    public void ex3() {
        int result = solution("aa1+aa2", "AAAA12");
        Assertions.assertThat(result).isEqualTo(43690);
    }

    @Test
    public void ex4() {
        int result = solution("E=M*C^2", "e=m*c^2");
        Assertions.assertThat(result).isEqualTo(65536);
    }

    @Test
    public void ex5() {
        int result = solution("AB", "CD");
        Assertions.assertThat(result).isEqualTo(0);
    }
}
