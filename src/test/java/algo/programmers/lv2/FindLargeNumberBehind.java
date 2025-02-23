package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;
import java.util.TreeMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/154539 lv2
public class FindLargeNumberBehind {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();

        for(int i=numbers.length-1; i>=0; i--){
            int next = -1;
            while(!stack.isEmpty()){
                if(numbers[i]>=stack.peek()) stack.pop();
                else{
                    next = stack.peek();
                    break;
                }
            }
            answer[i] = next;
            stack.push(numbers[i]);
        }

        return answer;
    }

    @Test
    public void ex1() {
        int[] result = solution(new int[]{2, 3, 3, 5});
        Assertions.assertThat(result).containsExactly(3, 5, 5, -1);
    }

    @Test
    public void ex2() {
        int[] result = solution(new int[]{9, 1, 5, 3, 6, 2});
        Assertions.assertThat(result).containsExactly(-1, 5, 6, 6, -1, -1);
    }
}
