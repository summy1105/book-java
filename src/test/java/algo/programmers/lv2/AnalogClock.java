package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/250135 lv2
public class AnalogClock {
    static final int temp360 = 360_000;
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        double hAnglePerSecond = temp360/(double)(12*60*60);
        double mAnglePerSecond = temp360/(double)(60*60);
        double sAnglePerSecond = temp360/(double)60;
        int noonSecond = 12*60*60;

        double secondMeetHSArrow = temp360 / (sAnglePerSecond - hAnglePerSecond);
        double secondMeetMSArrow = temp360 / (sAnglePerSecond - mAnglePerSecond);

        int fromSeconds = (h1 * 3600 + m1 * 60 + s1);
        int toSeconds = (h2 * 3600 + m2 * 60 + s2);

        int fromSecondsCount =(int)(fromSeconds / secondMeetHSArrow) + (int)(fromSeconds / secondMeetMSArrow);
        int toSecondsCount =(int)(toSeconds / secondMeetHSArrow) + (int)(toSeconds / secondMeetMSArrow);

        int answer = toSecondsCount - fromSecondsCount;
        if(fromSeconds<noonSecond && toSeconds>=noonSecond) answer--;
        if(fromSeconds == 0 || fromSeconds == noonSecond) answer++;
        return answer;
    }

    @Test
    public void ex1() {
        int result = solution(0, 5, 30, 0, 7, 0);
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void ex2() {
        int result = solution(12,	0,	0,	12,	0,	30);
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void ex3() {
        int result = solution(0,	6,	1,	0,	6,	6);
        Assertions.assertThat(result).isEqualTo(0);
    }

    @Test
    public void ex4() {
        int result = solution(11,	59,	30,	12,	0,	0);
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void ex5() {
        int result = solution(11,	58,	59,	11,	59,	0);
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void ex6() {
        int result = solution(1,	5,	5,	1,	5,	6);
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void ex7() {
        int result = solution(0,	0,	0,	23,	59,	59);
        Assertions.assertThat(result).isEqualTo(2852);
    }
}
