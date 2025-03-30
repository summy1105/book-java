package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

// https://school.programmers.co.kr/learn/courses/30/lessons/60061 lv3

//기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
//보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
// 만약, 작업을 수행한 결과가 조건을 만족하지 않는다면 해당 작업은 무시됩니다.
// 0은 기둥, 1은 보
// 0은 삭제, 1은 설치
public class KaKaoInstallationPillarBeam {
    public int[][] solution(int n, int[][] build_frame) {
        // 기둥(pillar)과 보(beam)를 설치 여부로 저장하는 2차원 배열
        boolean[][] pillar = new boolean[n+1][n+1];
        boolean[][] beam = new boolean[n+1][n+1];

        for (int[] frame : build_frame) {
            int x = frame[0], y = frame[1], a = frame[2], b = frame[3];
            if (b == 1) { // 설치
                if (a == 0) { // 기둥 설치
                    if (canInstallPillar(x, y, n, pillar, beam)) {
                        pillar[x][y] = true;
                    }
                } else { // 보 설치
                    if (canInstallBeam(x, y, n, pillar, beam)) {
                        beam[x][y] = true;
                    }
                }
            } else { // 삭제
                if (a == 0) { // 기둥 삭제
                    pillar[x][y] = false;
                    if (!isValid(n, pillar, beam)) {
                        pillar[x][y] = true;
                    }
                } else { // 보 삭제
                    beam[x][y] = false;
                    if (!isValid(n, pillar, beam)) {
                        beam[x][y] = true;
                    }
                }
            }
        }

        // 최종 설치된 구조물을 배열에 담기
        ArrayList<int[]> resultList = new ArrayList<>();
        for (int i = 0; i <= n; i++){
            for (int j = 0; j <= n; j++){
                if (pillar[i][j]) resultList.add(new int[]{i, j, 0});
                if (beam[i][j]) resultList.add(new int[]{i, j, 1});
            }
        }

        // 문제에서 요구하는 정렬: x좌표 오름차순, y좌표 오름차순, 기둥(0)이 보(1)보다 먼저
        Collections.sort(resultList, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            else if (a[1] != b[1]) return a[1] - b[1];
            else return a[2] - b[2];
        });

        int[][] answer = new int[resultList.size()][3];
        for (int i = 0; i < resultList.size(); i++){
            answer[i] = resultList.get(i);
        }
        return answer;
    }

    // 기둥 설치가 가능한지 확인하는 함수
    private boolean canInstallPillar(int x, int y, int n, boolean[][] pillar, boolean[][] beam) {
        // 바닥 위에 있거나
        if (y == 0) return true;
        // 바로 아래에 기둥이 설치되어 있거나
        if (y > 0 && pillar[x][y-1]) return true;
        // 현재 위치에서 보의 한쪽 끝 위에 설치되어 있거나
        if (x > 0 && beam[x-1][y]) return true;
        if (beam[x][y]) return true;
        return false;
    }

    // 보 설치가 가능한지 확인하는 함수
    private boolean canInstallBeam(int x, int y, int n, boolean[][] pillar, boolean[][] beam) {
        // 왼쪽 아래에 기둥이 있거나
        if (y > 0 && pillar[x][y-1]) return true;
        // 오른쪽 아래에 기둥이 있거나
        if (x < n && y > 0 && pillar[x+1][y-1]) return true;
        // 양쪽 끝이 다른 보와 동시에 연결되어 있는 경우
        if (x > 0 && x < n && beam[x-1][y] && beam[x+1][y]) return true;
        return false;
    }

    // 전체 구조물이 올바른지 확인하는 함수
    private boolean isValid(int n, boolean[][] pillar, boolean[][] beam) {
        for (int x = 0; x <= n; x++){
            for (int y = 0; y <= n; y++){
                if (pillar[x][y] && !canInstallPillar(x, y, n, pillar, beam)) return false;
                if (beam[x][y] && !canInstallBeam(x, y, n, pillar, beam)) return false;
            }
        }
        return true;
    }

    @Test
    public void ex1() {
        int n = 5;
        int[][] build_frame = {
                {1, 0, 0, 1},
                {1, 1, 1, 1},
                {2, 1, 0, 1},
                {2, 2, 1, 1},
                {5, 0, 0, 1},
                {5, 1, 0, 1},
                {4, 2, 1, 1},
                {3, 2, 1, 1}
        };
        int[][] expected = {
                {1, 0, 0},
                {1, 1, 1},
                {2, 1, 0},
                {2, 2, 1},
                {3, 2, 1},
                {4, 2, 1},
                {5, 0, 0},
                {5, 1, 0}
        };
        int[][] result = solution(n, build_frame);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @Test
    public void ex2() {
        int n = 5;
        int[][] build_frame = {
                {0, 0, 0, 1},
                {2, 0, 0, 1},
                {4, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {2, 1, 1, 1},
                {3, 1, 1, 1},
                {2, 0, 0, 0},
                {1, 1, 1, 0},
                {2, 2, 0, 1}
        };
        int[][] expected = {
                {0, 0, 0},
                {0, 1, 1},
                {1, 1, 1},
                {2, 1, 1},
                {3, 1, 1},
                {4, 0, 0}
        };
        int[][] result = solution(n, build_frame);
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
