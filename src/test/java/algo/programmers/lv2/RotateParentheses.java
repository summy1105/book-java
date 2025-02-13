package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/76502 lv2
// 58 ->
public class RotateParentheses {

    public int solution(String s) {
        Stack<Character> stack;
        String open = "[{(";
        String close = "]})";

        int length = s.length();
        int answer = 0;
        for (int i = 0; i < length; i++) {
            stack = new Stack<>();
            for (int j = i; j < length + i; j++) {
                char c = s.charAt(j% length);
                if (open.indexOf(c)>-1 || stack.isEmpty()) {
                    stack.add(c);
                } else if (stack.peek() == open.charAt(close.indexOf(c))) {
                    stack.pop();
                }
            }
            if(stack.isEmpty()) answer++;
        }
        return answer;
    }

    @Test
    public void ex1() {
        int result = solution("[](){}");
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void ex2() {
        int result = solution("}]()[{");
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void ex3() {
        int result = solution("[)(]");
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void ex4() {
        int result = solution("}}}");
        Assertions.assertThat(result).isEqualTo(0);
    }

}
