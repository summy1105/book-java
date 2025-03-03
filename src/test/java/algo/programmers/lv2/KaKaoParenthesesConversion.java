package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/60058 lv2

public class KaKaoParenthesesConversion {
    public String solution(String p) {
        return dfs(p);
    }

    private String dfs(String part){
        int idx=1, separateIdx=0;

        Stack<Character> stack = new Stack();
        for(char bracket : part.toCharArray()){
            if(stack.isEmpty()|| stack.peek().equals(bracket)){
                stack.add(bracket);
            }else {
                stack.pop();
            }
            if (stack.isEmpty()) {
                separateIdx = idx;
                break;
            }
            idx++;
        }

        if(part.startsWith("(") && separateIdx==part.length()) return part;
        if(part.startsWith("(")) return part.substring(0, separateIdx) + dfs(part.substring(separateIdx));

        String u = part.substring(0, separateIdx);
        String v = part.substring(separateIdx);

        StringBuilder builder = new StringBuilder();
        for(int i=1; i<u.length()-1; i++){
            if(u.charAt(i)=='(')builder.append(')');
            else builder.append('(');
        }
        u= builder.toString();

        return '('+(v.isBlank()?"":dfs(v))+')'+u;
    }

    @Test
    public void ex1() {
        String result = solution("(()())()");
        Assertions.assertThat(result).isEqualTo("(()())()");
    }

    @Test
    public void ex2() {
        String result = solution(")(");
        Assertions.assertThat(result).isEqualTo("()");
    }

    @Test
    public void ex3() {
        String result = solution("()))((()");
        Assertions.assertThat(result).isEqualTo("()(())()");
    }

    @Test
    public void ex4() {
        String result = solution(")((())()");
        Assertions.assertThat(result).isEqualTo("((())())");
    }

    @Test
    public void ex5() {
        String result = solution("((()))))((()()))((");
        Assertions.assertThat(result).isEqualTo("((()))(()()()())()");
    }
}
