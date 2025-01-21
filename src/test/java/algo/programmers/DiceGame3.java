package algo.programmers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/181916 lv0
// 10:55
public class DiceGame3 {
    int solution(int a, int b, int c, int d) {
        int[] dice = new int[7];
        dice[a]++;
        dice[b]++;
        dice[c]++;
        dice[d]++;

        int[] selectedNumber = new int[4];
        int qr = 1;
        for (int i = 6; i >0 ; i--) {
            if(dice[i] == 4) return i * 1111;
            if(dice[i] == 3) selectedNumber[3] = i;
            if(dice[i] == 2 && selectedNumber[2] > 0)
                return (selectedNumber[2] + i) * (selectedNumber[2] - i);
            if(dice[i] == 2 ) selectedNumber[2] = i;
            if(dice[i]==1) {
                selectedNumber[1] = i;
                qr *= i;
            }
        }
        if(selectedNumber[3]>0 && selectedNumber[1]>0) return (selectedNumber[3] * 10 + selectedNumber[1]) * (selectedNumber[3] * 10 + selectedNumber[1]);
        if(selectedNumber[2]>0) return qr;
        return selectedNumber[1];
    }

    @Test
    public void ex1() {
        int result = solution(2, 2, 2, 2);
        Assertions.assertThat(result).isEqualTo(2222);
    }

    @Test
    public void ex2() {
        int result = solution(4,1,4,4);
        Assertions.assertThat(result).isEqualTo(1681);
    }

    @Test
    public void ex3() {
        int result = solution(6,3,3,6);
        Assertions.assertThat(result).isEqualTo(27);
    }

    @Test
    public void ex4() {
        int result = solution(2,5,2,6);
        Assertions.assertThat(result).isEqualTo(30);
    }

    @Test
    public void ex5() {
        int result = solution(6,4,2,5);
        Assertions.assertThat(result).isEqualTo(2);
    }
}
