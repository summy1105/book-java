package algo.programmers.lv1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/64061 lv1
// 5:34 -> 6:20

public class KakaoCraneClawStuffedDollGame {

    public int solution(int[][] board, int[] moves) {
        Stack<Integer>[] boards = new Stack[board.length];

        for(int row = board.length-1; row>=0 ; row--){
            for(int col = board.length-1; col>=0 ; col--){
                if(board[row][col]!=0){
                    if(boards[col]==null) boards[col] = new Stack<>();
                    boards[col].push(board[row][col]);
                }
            }
        }
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        for(int idx : moves){
            if(boards[idx-1].isEmpty()) continue;
            int doll = boards[idx - 1].pop();
            if(!stack.isEmpty() && stack.peek() == doll ){
                stack.pop();
                answer+=2;
            }else{
                stack.push(doll);
            }
        }
        return answer;
    }

    @Test
    public void ex1() {
        int result = solution(
                new int[][]{
                        {0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 3},
                        {0, 2, 5, 0, 1},
                        {4, 2, 4, 4, 2},
                        {3, 5, 1, 3, 1}
                },
                new int[]{1, 5, 3, 5, 1, 2, 1, 4}
        );
        Assertions.assertThat(result).isEqualTo(4);
    }
    @Test
    public void test() {
        //given
        System.out.println("-1%5 = " + -1 % 5);
        //when

        //then
    }
}
