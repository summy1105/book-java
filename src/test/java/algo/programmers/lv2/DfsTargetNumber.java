package algo.programmers.lv2;

import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/43165 lv2
public class DfsTargetNumber {

    @Test
    public void test() {
        int[] numbers = new int[]{4, 1, 2, 1};
        int target = 4;
        int solution = solution(numbers, target);
        System.out.println(solution);
    }

    public int solution(int[] numbers, int target) {
        int answer = 0;
        answer = dfs(numbers, target, 0, 0);
        return answer;
    }

    private int dfs(int[] numbers, int target, int sum, int depth) {
        if (depth == numbers.length) {
            return target == sum ? 1 : 0;
        }
        return dfs(numbers, target, numbers[depth] + sum, depth + 1)
                + dfs(numbers, target, -1*numbers[depth]+sum, depth+1)
                ;
    }
}
