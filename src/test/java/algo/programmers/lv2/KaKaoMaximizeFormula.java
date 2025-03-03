package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/67257 lv2
public class KaKaoMaximizeFormula {
    char[][] priorityOperands = new char[][]{
        {'*', '+', '-'},
        {'*', '-', '+'},
        {'+', '*', '-'},
        {'+', '-', '*'},
        {'-', '*', '+'},
        {'-', '+', '*'}
    };
    public long solution(String expression) {
        List<Long> numbers = Arrays.stream(expression.split("[\\+\\-*]")).map(Long::valueOf).collect(Collectors.toList());
        List<Character> operands = Arrays.stream(expression.split("[0-9]")).filter(Predicate.not(String::isBlank)).map(s->s.charAt(0)).collect(Collectors.toList());

        long answer = 0;
        for (int i = 0; i < priorityOperands.length; i++) {
            List<Long> calNumbers =  new ArrayList<>();
            for(long num:numbers) calNumbers.add(num);
            List<Character> calOperands = new ArrayList<>();
            for(char op:operands) calOperands.add(op);

            for(int j=0; j<priorityOperands[i].length; j++){
                char curOperand = priorityOperands[i][j];
                int idx;
                while((idx = calOperands.indexOf(curOperand))>-1){
                    long leftNum = calNumbers.get(idx);
                    long rightNum = calNumbers.get(idx + 1);
                    long after = 0;
                    switch (calOperands.get(idx)) {
                        case '*':
                            after = leftNum * rightNum;
                            break;
                        case '+':
                            after = leftNum + rightNum;
                            break;
                        case '-':
                            after = leftNum - rightNum;
                            break;
                    }
                    calNumbers.remove(idx);
                    calNumbers.remove(idx);
                    calOperands.remove(idx);
                    calNumbers.add(idx, after);
                }
            }
            answer = Math.max(answer, Math.abs(calNumbers.get(0)));
        }
        return answer;
    }

    @Test
    public void ex1() {
        long result = solution("100-200*300-500+20");
        Assertions.assertThat(result).isEqualTo(60420);
    }

    @Test
    public void ex2() {
        long result = solution("50*6-3*2");
        Assertions.assertThat(result).isEqualTo(300);
    }
}
