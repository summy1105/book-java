package algo.programmers.lv0;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// https://school.programmers.co.kr/learn/courses/30/lessons/340213 lv1
// 3:15 -> 4:56 코너케이스 3번.......

/**
 * 10초전, 10초후, 오프닝 구간인경우 op_end로 자동이동
 */
public class PCCP1VideoPlayer {

    LocalTime startTime = LocalTime.of(0, 0, 0);
    LocalTime videoTime;
    LocalTime posTime;
    LocalTime opStartTime;
    LocalTime opEndTime;


    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        videoTime = LocalTime.parse("00:" + video_len);
        posTime = LocalTime.parse("00:" + pos);
        opStartTime = LocalTime.parse("00:" + op_start);
        opEndTime = LocalTime.parse("00:" + op_end);

        LocalTime curTime = returnIn(posTime);
        for (String command : commands) {
            boolean isPrev = "prev".equals(command);
            curTime = returnIn(curTime.plusSeconds(isPrev ? -10 : 10));
        }
        return curTime.format(DateTimeFormatter.ofPattern("mm:ss"));
    }

    // 순서가 중요함... start time이 오프닝 구간에 들어오면 무조건 opening end time으로 이동해야함
    private LocalTime returnIn(LocalTime posTime) {
        if(posTime.getHour()== 23) posTime = copyTime(startTime);
        if(posTime.compareTo(videoTime) >=0) posTime = copyTime(videoTime);
        if((posTime.compareTo(opStartTime) >=0 && posTime.compareTo(opEndTime)<=0)) posTime = copyTime(opEndTime);
        return posTime;
    }

    LocalTime copyTime(LocalTime localTime) {
        return LocalTime.of(localTime.getHour(), localTime.getMinute(), localTime.getSecond());
    }

    @Test
    public void 예제1() {
        String result = solution("34:33", "13:00"	, "00:55", "02:55", new String[]{"next", "prev"});
        Assertions.assertThat(result).isEqualTo("13:00");
    }

    @Test
    public void 예제2() {
        String result = solution("10:55", "00:05", "00:15", "06:55", new String[]{"prev", "next", "next"});
        Assertions.assertThat(result).isEqualTo("06:55");
    }

    @Test
    public void 예제3() {
        String result = solution("07:22", "04:05", "00:15", "04:07", new String[]{"next"});
        Assertions.assertThat(result).isEqualTo("04:17");
    }

    @Test
    public void 예제4() {
        String result = solution("59:59", "04:05", "00:00", "59:59", new String[]{"prev"});
        Assertions.assertThat(result).isEqualTo("59:59");
    }

    @Test
    public void 예제5() {
        String result = solution("59:59", "00:01", "00:00", "59:59", new String[]{"next", "next", "next"});
        Assertions.assertThat(result).isEqualTo("59:59");
    }

    @Test
    public void 예제6() {
        String result = solution("59:59", "00:00", "00:00", "59:59", new String[]{"prev", "prev", "prev"});
        Assertions.assertThat(result).isEqualTo("59:59");
    }

    @Test
    public void 예제7() {
        String result = solution("00:00", "00:00", "00:00", "00:00", new String[]{"prev", "next", "prev"});
        Assertions.assertThat(result).isEqualTo("00:00");
    }

    @Test
    public void 예제8() {
        String result = solution("20:10", "00:16", "00:16", "00:17", new String[]{"prev", "next", "prev"});
        Assertions.assertThat(result).isEqualTo("00:07");
    }

    @Test
    public void 코너케이스1() {
        String result = solution("00:20", "00:00", "00:00", "00:20", new String[]{"prev", "next"});
        Assertions.assertThat(result).isEqualTo("00:20");
    }

    @Test
    public void 코너케이스2() {
        String result = solution("59:59", "00:05", "00:05", "00:20", new String[]{"prev"});
        Assertions.assertThat(result).isEqualTo("00:20");
    }

    @Test
    public void 코너케이스3() {
        String result = solution("10:00", "00:03", "00:00", "00:05", new String[]{"prev", "next"});
        Assertions.assertThat(result).isEqualTo("00:15");
    }
}
