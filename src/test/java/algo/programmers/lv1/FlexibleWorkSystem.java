package algo.programmers.lv1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/388351 lv1
public class FlexibleWorkSystem {
    public int solution(int[] schedules, int[][] timeLogs, int startDay) {
        int answer = 0;
        for(int i =0; i<schedules.length; i++){
            if((schedules[i]+10) %100 >= 60){
                schedules[i] = (schedules[i]+100) - (schedules[i] %100) + ((schedules[i]+10)%100 %60);
            }else
                schedules[i]+=10;

            boolean isPersonPass = true;
            for(int j=0, dayPerPerson=(startDay-1)%7;
                j<timeLogs[i].length && isPersonPass;
                j++, dayPerPerson=(dayPerPerson+1)%7){
                if(dayPerPerson>=5) continue;

                if(timeLogs[i][j]>schedules[i])
                    isPersonPass = false;
            }
            if(isPersonPass) answer++;
        }
        return answer;
    }

    @Test
    public void ex1() {
        int result = solution(
                new int[]{700, 800, 1100},
                new int[][]{
                        {710, 2359, 1050, 700, 650, 631, 659},
                        {800, 801, 805, 800, 759, 810, 809},
                        {1105, 1001, 1002, 600, 1059, 1001, 1100}
                },
                5
        );
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void ex2() {
        int result = solution(
                new int[]{730, 855, 700, 720},
                new int[][]{
                        {710, 700, 650, 735, 700, 931, 912},
                        {908, 901, 805, 815, 800, 831, 835},
                        {705, 701, 702, 705, 710, 710, 711},
                        {707, 731, 859, 913, 934, 931, 905}
                },
                1
        );
        Assertions.assertThat(result).isEqualTo(2);
    }
}
