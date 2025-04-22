package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/169198 lv2

public class BilliardsPractice {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for(int i=0; i<balls.length; i++){
            int ballX = balls[i][0];
            int ballY = balls[i][1];
            int min = Integer.MAX_VALUE;
            if(startY!=ballY || ballX>startX){// 왼쪽 쿠션
                int newX = -1 * startX;
                int lengthSquare = (int)Math.pow(ballX-newX, 2) + (int)Math.pow(ballY-startY,2);
                min = Math.min(min, lengthSquare);
            }
            if(startY!=ballY || ballX<startX){ // 오른쪽 쿠션
                int newX = m + (m - startX);
                int lengthSquare = (int)Math.pow(ballX-newX, 2) + (int)Math.pow(ballY-startY,2);
                min = Math.min(min, lengthSquare);
            }
            if(startX!=ballX || ballY>startY){//아래쪽 쿠션
                int newY = -1*startY;
                int lengthSquare = (int)Math.pow(ballX-startX, 2) + (int)Math.pow(ballY-newY,2);
                min = Math.min(min, lengthSquare);
            }
            if(startX!=ballX || ballY<startY){//윗쪽 쿠션
                int newY = startY+2*(n-startY);
                int lengthSquare = (int)Math.pow(ballX-startX, 2) + (int)Math.pow(ballY-newY,2);
                min = Math.min(min, lengthSquare);
            }
            answer[i] = min;
        }

        return answer;
    }

    //10	10	3	7	[[7, 7], [2, 7], [7, 3]]	[52, 37, 116]
    @Test
    void test() {
        int m = 10;
        int n = 10;
        int startX = 3;
        int startY = 7;
        int[][] balls = {{7, 7}, {2, 7}, {7, 3}};
        int[] result = solution(m, n, startX, startY, balls);
        Assertions.assertThat(result).isEqualTo(new int[]{52, 37, 116});
    }
}