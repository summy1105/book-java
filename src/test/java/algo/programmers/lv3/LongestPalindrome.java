package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/12904 lv3

public class LongestPalindrome {

    public int solution(String s) {
        if (s.length() == 0) return 0;

        // 1️⃣ 특수 문자 삽입 (모든 팰린드롬을 홀수 길이로 변환)
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        for (char c : s.toCharArray()) {
            sb.append(c).append("#");
        }
        String parsingText = sb.toString(); // 변환된 문자열
        int n = parsingText.length();

        int[] P = new int[n]; // 각 위치에서의 팰린드롬 반지름 길이
        int center = 0, right = 0; // 현재 가장 오른쪽까지 확장된 팰린드롬의 중심과 끝
        int maxLen = 0, centerIndex = 0; // 최장 팰린드롬 저장용

        // 2️⃣ Manacher 알고리즘 적용
        for (int i = 0; i < n; i++) {
            // 대칭을 이용하여 최소한의 계산
            if (i < right) {
                P[i] = Math.min(right - i, P[2 * center - i]);
            }

            // 좌우 확장하면서 팰린드롬 길이 증가
            while (i - P[i] - 1 >= 0
                    && i + P[i] + 1 < n
                    && parsingText.charAt(i - P[i] - 1) == parsingText.charAt(i + P[i] + 1)) {
                P[i]++;
            }

            // 팰린드롬이 더 오른쪽까지 확장되면 center, right 업데이트
            if (i + P[i] > right) {
                center = i;
                right = i + P[i];
            }

            // 최장 팰린드롬 갱신
            if (P[i] > maxLen) {
                maxLen = P[i];
                centerIndex = i;
            }
        }

        // 3️⃣ 원본 문자열로 변환하여 정답 반환
        int start = (centerIndex - maxLen) / 2;
        String substring = s.substring(start, start + maxLen);
        return maxLen;
    }

    @Test
    public void ex1() {
        int result = solution("abcdcba");
        Assertions.assertThat(result).isEqualTo(7);
    }

    @Test
    public void ex2() {
        int result = solution("abacde");
        Assertions.assertThat(result).isEqualTo(3);
    }

    @Test
    public void ex3() {
        int result = solution("abaabe");
        Assertions.assertThat(result).isEqualTo(4);
    }
}
