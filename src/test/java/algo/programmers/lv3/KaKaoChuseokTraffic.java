package algo.programmers.lv3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

// https://school.programmers.co.kr/learn/courses/30/lessons/17676 lv3

public class KaKaoChuseokTraffic {
    public int solution(String[] lines) {
        int[][] periodArr = new int[lines.length][2];
        for (int i = 0; i < lines.length ; i++) {
            String line = lines[i];
            String[] infos = line.split(" ");
            String[] times = infos[1].split(":");
            periodArr[i][1] = Integer.valueOf(times[0]) * 1000 * 60 * 60 + Integer.valueOf(times[1]) * 1000 * 60 + (int)(Double.valueOf(times[2]) * 1000);
            periodArr[i][0] = periodArr[i][1] - (int)(Float.valueOf(infos[2].substring(0, infos[2].length() - 1))*1000) + 1;
        }

        Arrays.sort(periodArr, (a1, a2)->{
            if(a1[1]!=a2[1]) return a1[1] - a2[1];
            return a1[0] - a2[0];
        });

        int answer = 0;
        for (int i = 0; i <periodArr.length ; i++) {
            int periodCount = 0;
            for (int j = i; j <periodArr.length ; j++) {
                if(periodArr[i][1]+999>=periodArr[j][0])
                    periodCount++;
            }

            answer = Math.max(answer, periodCount);
        }

        return answer;
    }

    // [
    //"2016-09-15 01:00:04.001 2.0s",
    //"2016-09-15 01:00:07.000 2s"
    //]
    @Test
    void test() {
        String[] lines = {
                "2016-09-15 01:00:04.001 2.0s",
                "2016-09-15 01:00:07.000 2s"
        };
        assertThat(solution(lines)).isEqualTo(1);
    }

    //[
    //"2016-09-15 20:59:57.421 0.351s",
    //"2016-09-15 20:59:58.233 1.181s",
    //"2016-09-15 20:59:58.299 0.8s",
    //"2016-09-15 20:59:58.688 1.041s",
    //"2016-09-15 20:59:59.591 1.412s",
    //"2016-09-15 21:00:00.464 1.466s",
    //"2016-09-15 21:00:00.741 1.581s",
    //"2016-09-15 21:00:00.748 2.31s",
    //"2016-09-15 21:00:00.966 0.381s",
    //"2016-09-15 21:00:02.066 2.62s"
    //]
    @Test
    void test2() {
        String[] lines = {
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"
        };
        assertThat(solution(lines)).isEqualTo(7);
    }

    //["2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"]
    @Test
    void test3() {
        String[] lines = {
                "2016-09-15 01:00:04.002 2.0s",
                "2016-09-15 01:00:07.000 2s"
        };
        assertThat(solution(lines)).isEqualTo(2);
    }
}
