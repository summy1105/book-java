package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

// https://school.programmers.co.kr/learn/courses/30/lessons/60062 lv3

public class KakaoCheckExteriorWall {
    static int answer;
    public int solution(int n, int[] weak, int[] dist) {
        int wLen = weak.length;
        // 원형 weak를 선형화: 기존 weak 배열 뒤에 n을 더한 값을 붙입니다.
        int[] extendedWeak = new int[wLen * 2];
        for (int i = 0; i < wLen; i++) {
            extendedWeak[i] = weak[i];
            extendedWeak[i + wLen] = weak[i] + n;
        }

        answer = dist.length + 1; // 가능한 친구의 최대 수보다 큰 값으로 초기화

        // dist 배열의 모든 순열을 생성합니다.
        ArrayList<int[]> perms = new ArrayList<>();
        permute(dist, 0, perms);

        // 모든 시작점을 고려합니다.
        for (int start = 0; start < wLen; start++) {
            // 각 친구 순열마다 검사
            for (int[] friendOrder : perms) {
                int count = 1; // 현재 투입된 친구 수
                // 첫 번째 친구가 커버할 수 있는 최대 위치
                int position = extendedWeak[start] + friendOrder[0];
                for (int i = start; i < start + wLen; i++) {
                    if (extendedWeak[i] > position) { // 현재 친구가 커버할 수 없는 weak 점
                        count++; // 다음 친구 투입
                        if (count > friendOrder.length) break;
                        position = extendedWeak[i] + friendOrder[count - 1];
                    }
                }
                answer = Math.min(answer, count);
            }
        }

        if (answer > dist.length) return -1;
        return answer;
    }

    // dist 배열의 순열을 생성하는 재귀 함수 (깊이 idx부터)
    private void permute(int[] arr, int idx, ArrayList<int[]> perms) {
        if (idx == arr.length) {
            perms.add(arr.clone());
            return;
        }
        for (int i = idx; i < arr.length; i++) {
            swap(arr, idx, i);
            permute(arr, idx + 1, perms);
            swap(arr, idx, i);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    @Test
    public void ex1() {
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        int result = solution(n, weak, dist);
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void ex2() {
        int n = 12;
        int[] weak = {1, 3, 4, 9, 10};
        int[] dist = {3, 5, 7};
        int result = solution(n, weak, dist);
        Assertions.assertThat(result).isEqualTo(1);
    }
}
