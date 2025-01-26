package algo.programmers.lv0;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/92334 lv1
// 9:52 -> 10:30

/**
 * 유저수 : 1000
 * report : 200,000
 *  1 <= k <= 200
 *
 * - 이름, index -> map = 1000
 * - boolean[][] row:신고자, col:불량이용자 = 200,000
 * - K이상 불량 이용자 점검 및 mail 갯수 세기 = 1000 * 1000 * 2 = 2,000,000
 *
 */
public class KakaoReportResultsMisbehavingUsers {

    public int[] solution(String[] id_list, String[] reports, int k) {
        // 이름 - index map생성
        HashMap<String, Integer> nameIndexMap = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            nameIndexMap.put(id_list[i], i);
        }

        // 신고 내역 채우기
        boolean[][] accusations = new boolean[id_list.length][id_list.length];
        for (String report : reports) {
            String[] names = report.split(" ");
            accusations[nameIndexMap.get(names[0])][nameIndexMap.get(names[1])] = true;
        }

        int[] mailCount = new int[id_list.length];

        for (int col = 0; col < accusations.length; col++) {
            // 신고 갯수 세기
            int count = 0;
            for (int row = 0; row < accusations.length; row++) {
                count += accusations[row][col] == true ? 1 : 0;
                if(count>=k) break;
            }
            if(count<k) continue;
            for (int row = 0; row < accusations.length; row++) {
                if(accusations[row][col]) mailCount[row]++;
            }
        }
        return mailCount;
    }

    @Test
    public void 예제1() {
        int[] result = solution(new String[]{"muzi", "frodo", "apeach", "neo"},
                new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"},
                2);
        Assertions.assertThat(result).containsExactly(2, 1, 1, 0);
    }

    @Test
    public void 예제2() {
        int[] result = solution(new String[]{"con", "ryan"},
                new String[]{"ryan con", "ryan con", "ryan con", "ryan con"},
                2);
        Assertions.assertThat(result).containsExactly(0,0);
    }
}

