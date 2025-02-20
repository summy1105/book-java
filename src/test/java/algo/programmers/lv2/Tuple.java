package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/64065 lv2
// 40 -> 10
public class Tuple {
    public int[] solution(String s) {
        TreeMap<Integer, String[]> treeMap = new TreeMap<>();
        boolean[] atom = new boolean[100_000];

        String[] split = s.split("(,\\{)");
        for (String part : split) {
            String[] numbers = part.replaceAll("[\\{\\}]", "").split(",");
            treeMap.put(numbers.length, numbers);
        }
        int[] answer = new int[treeMap.size()];

        for (int i = 0; !treeMap.isEmpty() ; i++) {
            String[] numbers = treeMap.pollFirstEntry().getValue();
            for (String numberStr : numbers) {
                Integer num = Integer.valueOf(numberStr);
                if (atom[num] == false) {
                    answer[i] = num;
                    atom[num] = true;
                    break;
                }
            }
        }
        return answer;
    }

    @Test
    public void ex1() {
        int[] result = solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
        Assertions.assertThat(result).containsExactly(2, 1, 3, 4);
    }

    @Test
    public void ex2() {
        int[] result = solution("{{1,2,3},{2,1},{1,2,4,3},{2}}");
        Assertions.assertThat(result).containsExactly(2, 1, 3, 4);
    }

    @Test
    public void ex3() {
        int[] result = solution("{{20,111},{111}}");
        Assertions.assertThat(result).containsExactly(111, 20);
    }

    @Test
    public void ex4() {
        int[] result = solution("{{123}}");
        Assertions.assertThat(result).containsExactly(123);
    }

    @Test
    public void ex5() {
        int[] result = solution("{{4,2,3},{3},{2,3,4,1},{2,3}}");
        Assertions.assertThat(result).containsExactly(3, 2, 4, 1);
    }
}
