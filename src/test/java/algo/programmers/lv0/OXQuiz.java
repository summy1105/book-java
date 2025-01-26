package algo.programmers.lv0;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/120907 lv0
// 10:40 -> 10:55
public class OXQuiz {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        for (int i = 0; i < quiz.length; i++) {
            String[] split = quiz[i].split(" ");

            Integer num1 = Integer.valueOf(split[0]);
            boolean isPlus = "+".equals(split[1]);
            Integer num2 = Integer.valueOf(split[2]);
            Integer result = Integer.valueOf(split[4]);

            if (result == (isPlus ? num1 + num2 : num1 - num2))
                answer[i] = "O";
            else
                answer[i] = "X";
        }
        return answer;
    }

    @Test
    public void 예제1() {
        String[] result = solution(new String[]{"3 - 4 = -3", "5 + 6 = 11"});
        Assertions.assertThat(result).containsExactly("X", "O");
    }

    @Test
    public void 예제2() {
        String[] result = solution(new String[]{"19 - 6 = 13", "5 + 66 = 71", "5 - 15 = 63", "3 - 1 = 2"});
        Assertions.assertThat(result).containsExactly("O", "O", "X", "O");
    }

    @Test
    public void 예제3() {
        String[] result = solution(new String[]{"-10000 - 24 = -10024", "5 + 0 = 11"});
        Assertions.assertThat(result).containsExactly("O", "X");
    }
}
