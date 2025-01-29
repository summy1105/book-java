package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// https://school.programmers.co.kr/learn/courses/30/lessons/12951 lv2

public class JadenCaseString {
    // 다른 사람 풀이
    public String solution(String s) {
        Matcher m = Pattern.compile("\\b([\\w])([\\w]*)").matcher(s);
        while (m.find()) {
            s = s.replaceAll("\\b" + m.group(), m.group(1).toUpperCase() + m.group(2).toLowerCase());
        }
        return s;
    }

    // gpt추천
    public String solution2(String s) {
        StringBuffer sb = new StringBuffer();
        Matcher m = Pattern.compile("\\b([\\w])([\\w]*)").matcher(s);
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase() + m.group(2).toLowerCase());
        }
        return sb.toString();
    }

    @Test
    public void ex1() {
        String result = solution2("3people unFollowed me");
        Assertions.assertThat(result).isEqualTo("3people Unfollowed Me");
    }

    @Test
    public void ex2() {
        String result = solution2("for the last week");
        Assertions.assertThat(result).isEqualTo("For The Last Week");
    }

    @Test
    public void ex3() {
        String result = solution2("2  Th34E l w" );
        Assertions.assertThat(result).isEqualTo("2  Th34e L W");
    }

    @Test
    public void ex4() {
        String result = solution2("2");
        Assertions.assertThat(result).isEqualTo("2");
    }
}
