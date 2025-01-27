package algo.programmers.lv1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/178871 lv1
// 8:38 -> 9:00

/**
 * 단순
 * callings * players = 5백억 > 2억 -> 안됨
 *
 * map1 <이름, 등수>
 *
 * 등수 배열 -> 5만
 */
public class RunningRace {
    public String[] solution(String[] players, String[] callings) {
        HashMap<String, Integer> nameBy = new HashMap<>();

        for (int i = 0; i < players.length; i++) {
            nameBy.put(players[i], i);
        }

        for (String curPlayer : callings) {
            Integer callingGrade = nameBy.get(curPlayer);

            String prevPlayer = players[callingGrade - 1];
            players[callingGrade - 1] = players[callingGrade];
            players[callingGrade] = prevPlayer;

            nameBy.put(curPlayer, callingGrade - 1);
            nameBy.put(prevPlayer, callingGrade);
        }

        return players;
    }

    @Test
    public void ex1() {
        String[] result = solution(new String[]{"mumu", "soe", "poe", "kai", "mine"}, new String[]{"kai", "kai", "mine", "mine"});
        Assertions.assertThat(result).containsExactly("mumu", "kai", "mine", "soe", "poe");
    }


    @Test
    public void ex2() {
        String[] result = solution(new String[]{"A", "B", "C", "D", "E"}, new String[]{"E", "E", "E", "E"});
        Assertions.assertThat(result).containsExactly("E", "A", "B", "C", "D");
    }

}
