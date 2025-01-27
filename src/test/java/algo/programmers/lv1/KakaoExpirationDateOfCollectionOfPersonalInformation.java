package algo.programmers.lv1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/150370 lv1
// 8:15 -> 8:37

/**
 */
public class KakaoExpirationDateOfCollectionOfPersonalInformation {
    public int[] solution(String todayStr, String[] terms, String[] privacies) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate today = LocalDate.parse(todayStr, formatter);
        HashMap<String, Integer> termsMap = new HashMap<>();
        for (String term : terms) {
            String[] termArr = term.split(" ");
            termsMap.put(termArr[0], Integer.valueOf(termArr[1]));
        }

        List<Integer> expirationTermIdx = new ArrayList<>();
        for (int i = 0; i < privacies.length ; i++) {
            String[] privacyArr = privacies[i].split(" ");
            LocalDate termDate = LocalDate.parse(privacyArr[0], formatter);
            if(today.compareTo(termDate.plusMonths(termsMap.get(privacyArr[1]))) >= 0){
                expirationTermIdx.add(i+1);
            }
        }

        return expirationTermIdx.stream().mapToInt(i -> i).toArray();
    }

    @Test
    public void ex1() {
        int[] result = solution("2022.05.19", new String[]{"A 6", "B 12", "C 3"}, new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"});
        Assertions.assertThat(result).containsExactly(1, 3);
    }

    @Test
    public void ex2() {
        int[] result = solution("2020.01.01", new String[]{"Z 3", "D 5"}, new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"});
        Assertions.assertThat(result).containsExactly(1, 4, 5);
    }
}
