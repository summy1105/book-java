package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

// https://school.programmers.co.kr/learn/courses/30/lessons/150365 lv3

// 탈출경로가 사전순으로 빠른거
// 사전 순 d l r u
public class KaKaoMazeEscapeInstructions {
    int[] rowDir = {1, 0, 0, 0, -1};
    int[] colDir = {0, -1, 0, 1, 0};
    String[] dir = {"d", "l", "rl", "r", "u"};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int length = Math.abs(x - r) + Math.abs(y - c);
        if( length%2 != k%2 || length>k) return "impossible";

        StringBuilder sb = new StringBuilder();

        // 움직일수 있을때까지 d -> l > rl*-> r->u
        int curX = x;
        int curY = y;
        int curDir = 0;
        while ( curX != r || curY != c || sb.length() < k) {
            int nextX = curX + rowDir[curDir];
            int nextY = curY + colDir[curDir];
            String nextAppend = dir[curDir];
            length = Math.abs(nextX - r) + Math.abs(nextY - c);
            if (0 < nextX && nextX <= n && 0 < nextY && nextY <= m
                    && sb.length() + nextAppend.length() + length <= k) {
                sb.append(dir[curDir]);
                curX = nextX;
                curY = nextY;
            } else {
                curDir++;
            }
        }

        return sb.toString();
    }

    @Test
    public void ex1() {
        String result = solution(3, 4, 2, 3, 3, 1, 5);
        Assertions.assertThat(result).isEqualTo("dllrl");
    }

    @Test
    public void ex2() {
        String result = solution(2, 2, 1, 1, 2, 2, 2);
        Assertions.assertThat(result).isEqualTo("dr");
    }

    @Test
    public void ex3() {
        String result = solution(3, 3, 1, 2, 3, 3, 4);
        Assertions.assertThat(result).isEqualTo("impossible");
    }
}
