package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/92341 lv2
public class CalculationParkingFees {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> collect = Arrays.stream(records).collect(Collectors.groupingBy(
                s -> s.split(" ")[1]
                , Collectors.reducing(0, (s) -> {
                            String[] record = s.split("[:\\s]");
                            int seconds = Integer.valueOf(record[0]) * 60 + Integer.valueOf(record[1]);
                            return ("IN".equals(record[3]) ? -1 : 1) * seconds;
                        }
                        , (a1, a2) -> (a1 + a2)
                )
        ));

        int allDayMins = 23 * 60 + 59;
        int[] answer = new int[collect.size()];
        int idx = 0;
        for (String plateNum : collect.keySet().stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList())) {
            int mins = collect.get(plateNum) + (collect.get(plateNum) <=0? allDayMins : 0);
            answer[idx++] = fees[1] + ((int) Math.ceil((double) (mins < fees[0] ? 0 : mins - fees[0]) / fees[2])) * fees[3];
        }

        return answer;
    }

    @Test
    public void ex1() {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {
                "05:34 5961 IN",
                "06:00 0000 IN",
                "06:34 0000 OUT",
                "07:59 5961 OUT",
                "07:59 0148 IN",
                "18:59 0000 IN",
                "19:09 0148 OUT",
                "22:59 5961 IN",
                "23:00 5961 OUT"
        };
        int[] result = solution(fees, records);
        Assertions.assertThat(result).containsExactly(14600, 34400, 5000);
    }

    @Test
    public void ex2() {
        int[] fees = {120, 0, 60, 591};
        String[] records = {
                "16:00 3961 IN",
                "16:00 0202 IN",
                "18:00 3961 OUT",
                "18:00 0202 OUT",
                "23:58 3961 IN"
        };
        int[] result = solution(fees, records);
        Assertions.assertThat(result).containsExactly(0, 591);
    }

    @Test
    public void ex3() {
        int[] fees = {1, 461, 1, 10};
        String[] records = {"00:00 1234 IN"};
        int[] result = solution(fees, records);
        Assertions.assertThat(result).containsExactly(14841);
    }

    @Test
    public void ex4() {
        int[] fees = {1, 0, 1, 10};
        String[] records = {
                "00:00 0000 IN",
                "00:01 0000 OUT"
        };
        int[] result = solution(fees, records);
        Assertions.assertThat(result).containsExactly(0);
    }

    @Test
    public void ex5() {
        int[] fees = {1, 10, 1, 10};
        String[] records = {
                "06:00 0000 IN", "06:34 0000 OUT"
        };
        int[] result = solution(fees, records);
        Assertions.assertThat(result).containsExactly(340);
    }
}
