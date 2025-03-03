package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/42883 lv2
public class MakeBigNumber {
    public String solution(String number, int k) {
        int n = number.length();
        int need = n - k;  // 최종적으로 남겨야 할 자리 수
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            char c = number.charAt(i);
            // 스택이 비어있지 않고, 제거할 수가 남았으며,
            // 스택의 마지막 숫자가 현재 숫자보다 작으면 pop
            while (k > 0 && !stack.isEmpty() && stack.peek() < c) {
                k--;
                stack.pop();
            }
            stack.push(c);
        }
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < need; i++) {
            answer.append(stack.get(i));
        }
        return answer.toString();
    }

    @Test
    public void ex1() {
        String result = solution("1924", 2);
        Assertions.assertThat(result).isEqualTo("94");
    }

    @Test
    public void ex2() {
        String result = solution("1231234", 3);
        Assertions.assertThat(result).isEqualTo("3234");
    }

    @Test
    public void ex3() {
        String result = solution("4177252841", 4);
        Assertions.assertThat(result).isEqualTo("775841");
    }

    @Test
    public void ex4() {
        String result = solution("11114", 2);
        Assertions.assertThat(result).isEqualTo("114");
    }

    @Test
    public void ex5() {
        String result = solution("11114", 4);
        Assertions.assertThat(result).isEqualTo("4");
    }

    @Test
    public void ex6() {
        String result = solution("5177242841", 3);
        Assertions.assertThat(result).isEqualTo("7742841");
    }

    @Test
    public void ex7() {
        String result = solution("987654321", 2);
        Assertions.assertThat(result).isEqualTo("9876543");
    }

    @Test
    public void ex8() {
        String result = solution("9999", 1);
        Assertions.assertThat(result).isEqualTo("999");
    }
}
