package algo.programmers.lv0;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/340213 lv1
// 10:43 -> 11:07

/**
 * 1초마다 x 회복 => t초연속 성공 : x*t+y
 * 회복도중 공격 -> 기술 취소
 *  1=< t <= 50
 *  1<= x <= 100
 *  1<= y <= 100
 *
 *  1<= health(캐릭체력) <= 1000
 *  1<= attacks(공격횟수) <= 100  -- 공격시간 오름차순 정렬
 *  attacks[i] = { 1<=공격시간<=1000, 1<=피해랑<=100}
 *
 *  ============================
 *
 */
public class PCCP1BandageWrapping {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int healTime = bandage[0];
        int healPerSec = bandage[1];
        int healBonus = bandage[2];

        int lastAttackSec = 0;
        int curTurnHealth = health;
        for (int i = 0; i < attacks.length; i++) {
            if(lastAttackSec != attacks[i][0]){
                int periodSec = attacks[i][0] - lastAttackSec -1;
                curTurnHealth = Math.min(health, curTurnHealth + (periodSec * healPerSec + healBonus * (periodSec / healTime)));
            }
            curTurnHealth -= attacks[i][1];
            lastAttackSec = attacks[i][0];
            if(curTurnHealth<=0) return -1;
        }

        return curTurnHealth;
    }

    @Test
    public void 예제1() {
        int result = solution(new int[]{5, 1, 5}, 30, new int[][]{{2, 10}, {9, 15}, {10, 5}, {11, 5}});
        Assertions.assertThat(result).isEqualTo(5);
    }

    @Test
    public void 예제2() {
        int result = solution(new int[]{3, 2, 7}, 20, new int[][]{{1, 15}, {5, 16}, {8, 6}});
        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    public void 예제3() {
        int result = solution(new int[]{4, 2, 7}, 20, new int[][]{{1, 15}, {5, 16}, {8, 6}});
        Assertions.assertThat(result).isEqualTo(-1);
    }

    @Test
    public void 예제4() {
        int result = solution(new int[]{1, 1, 1}, 5, new int[][]{{1, 2}, {3, 2}});
        Assertions.assertThat(result).isEqualTo(3);
    }
}
