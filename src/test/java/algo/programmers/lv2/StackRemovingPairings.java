package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/12973 lv2

public class StackRemovingPairings {
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() || stack.peek() != s.charAt(i)) {
                stack.push(s.charAt(i));
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }

    @Test
    public void ex1() {
        int result = solution("baabaa");
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void ex2() {
        int result = solution("cdcd");
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void ex3() {
        int result = solution("aaabbabb");
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void ex4() {
        String s = "aaaaaaaaaabbbbbbbbbb";
        while(s.length() < 1_000_000) s+=s;
        int result = solution(s);
        Assertions.assertThat(result).isEqualTo(1);
    }
}
