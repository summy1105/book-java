package algo.programmers.lv1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/172928 lv1
// 5:34 -> 6:20

/**
 *  3<= park row, col <= 50
 *  "S" : Start
 *  "O" : 통로
 *  "X" : 장애물
 *
 *  N, S, W, E
 *  1<= 이동 범위 <=9
 *  ===========================
 *  방향에 따라 row, col -> static
 *  N S W E -> direction index값 맵
 *  - park -> char[][] 변경 50*50
 *
 *  - 시작위치 찾기 : 50*50
 *  - routes for문에 따라 이동 가능 불가 확인 : 50
 *    가능 -> position이동 : 이동 가능 확인 while : 9
 *    불가 -> position변경 X
 *    position -> int[2]
 */
public class WalkInThePark {
                                            // 북. 동, 남, 서
    static final int[] rowDirection = new int[]{-1, 0, 1, 0};
    static final int[] colDirection = new int[]{0, 1, 0, -1};
    static final HashMap<String, Integer> directionIndexMap = new HashMap<>();

    public int[] solution(String[] park, String[] routes) {
        {
            directionIndexMap.put("N", 0);
            directionIndexMap.put("E", 1);
            directionIndexMap.put("S", 2);
            directionIndexMap.put("W", 3);
        }
        char[][] parkMap = new char[park.length][park[0].length()];
        for (int i=0; i<park.length; i++) {
            parkMap[i] = park[i].toCharArray();
        }

        //시작위치 찾기
        int[] position = new int[]{0, 0}; // 0: row, 1:col
        for (int i = 0; i < parkMap.length; i++) {
            boolean find = false;
            for (int j = 0; j < parkMap[i].length; j++) {
                if(parkMap[i][j] == 'S'){
                    position[0] = i;
                    position[1] = j;
                    find = true;
                    break;
                }
            }
            if(find) break;
        }

        for (String route : routes) {
            String[] command = route.split(" ");
            int dirIdx = directionIndexMap.get(command[0]);
            int moveCount = Integer.valueOf(command[1]);
            boolean isPossible = true;
            for (int i = 1; i <= moveCount; i++) {
                int[] nextPosition = new int[]{position[0]+rowDirection[dirIdx]*i, position[1]+colDirection[dirIdx]*i};
                if (nextPosition[0] < 0 || nextPosition[0] >= parkMap.length
                        || nextPosition[1] < 0 || nextPosition[1] >= parkMap[0].length
                        || parkMap[nextPosition[0]][nextPosition[1]] == 'X') {
                    isPossible = false;
                    break;
                }
            }
            if(isPossible) {
                position[0] = position[0] + rowDirection[dirIdx] * moveCount;
                position[1] = position[1] + colDirection[dirIdx] * moveCount;
            }
        }

        return position;
    }

    @Test
    public void ex1() {
        int[] result = solution(new String[]{"SOO", "OOO", "OOO"}, new String[]{"E 2", "S 2", "W 1"});
        Assertions.assertThat(result).containsExactly(2, 1);
    }

    @Test
    public void ex2() {
        int[] result = solution(new String[]{"SOO","OXX","OOO"}, new String[]{"E 2","S 2","W 1"});
        Assertions.assertThat(result).containsExactly(0, 1);
    }

    @Test
    public void ex3() {
        int[] result = solution(new String[]{"OSO","OOO","OXO","OOO"}, new String[]{"E 2","S 3","W 1"});
        Assertions.assertThat(result).containsExactly(0, 0);
    }

    @Test
    public void ex4() {
        int[] result = solution(new String[]{"SOOOO","OOOOO","OOOOO","OOOOO"}, new String[]{"S 9"});
        Assertions.assertThat(result).containsExactly(0, 0);
    }

    @Test
    public void ex5() {
        int[] result = solution(new String[]{"OOOOO", "OOOOO", "OOSOO", "OOOOO", "OOOOO"}, new String[]{"E 3", "W 3", "S 3", "N 3", "E 2", "E 1", "W 4", "W 1", "S 2", "S 1", "N 4", "N 1"});
        Assertions.assertThat(result).containsExactly(0, 0);
    }
}
