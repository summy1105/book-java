package algo.programmers.lv1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/12930 lv1
// 11:30 -> 12:10
public class CreatingStrangeCharacters {

    public String solution(String s) {
        char[] chars = s.toCharArray();
        int wordIdx = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == ' ') {
                wordIdx = 0;
                continue;
            }
            if(wordIdx %2 == 0 && chars[i] >= 'a' && chars[i]<= 'z') chars[i] -= 32;
            if(wordIdx %2 == 1 && chars[i] >= 'A' && chars[i] <= 'Z') chars[i] += 32;
            wordIdx++;
        }
        return new String(chars);
    }

    @Test
    public void ex1() {
        String result = solution("try hello world");
        Assertions.assertThat(result).isEqualTo("TrY HeLlO WoRlD");
    }

    @Test
    public void ex2() {
        String result = solution(" hI tHiS  Is tHE tHiS  cOmPuTer ");
        Assertions.assertThat(result).isEqualTo(" Hi ThIs  Is ThE ThIs  CoMpUtEr ");
    }

    @Test
    public void ex3() {
        String result = solution("   abc de    fghi ");
        Assertions.assertThat(result).isEqualTo("   AbC De    FgHi ");
    }
}
