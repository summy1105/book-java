package algo.programmers.lv1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.BitSet;

// https://school.programmers.co.kr/learn/courses/30/lessons/17681 lv1
// 08 -> 48

/**
 */
public class KakaoSecretMapFirst {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String prefix = "               ";
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            String s = prefix + Integer.toBinaryString(arr1[i] | arr2[i]).replaceAll("0", " ").replaceAll("1", "#");
            answer[i] = s.substring(s.length() - n);
        }
        return answer;
    }

    public String[] solution2(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            answer[i] = String.format("%"+n+"s", Integer.toBinaryString(arr1[i] | arr2[i])).replaceAll("0", " ").replaceAll("1", "#");
        }
        return answer;
    }

    @Test
    public void ex1() {
        String[] result = solution2(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28});
        Assertions.assertThat(result).containsExactly("#####", "# # #", "### #", "#  ##", "#####");
    }

    @Test
    public void ex2() {
        String[] result = solution2(6, new int[]{46, 33, 33 ,22, 31, 50}, new int[]{27 ,56, 19, 14, 14, 10});
        Assertions.assertThat(result).containsExactly("######", "###  #", "##  ##", " #### ", " #####", "### # ");
    }
}
