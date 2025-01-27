package algo.programmers.lv1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/72410lv1
// 9:25 -> 9:45

/**
 */
public class KakaoRecommendANewId {
    public String solution(String new_id) {
        new_id = new_id.toLowerCase();
        new_id = new_id.replaceAll("[^a-z0-9-_.]", "");
        new_id = new_id.replaceAll("(\\.)+", ".");
        new_id = new_id.replaceAll("^\\.|\\.$", "");
        new_id = new_id.isEmpty() ? "a" : new_id;
        new_id = new_id.substring(0, Math.min(15, new_id.length()))
                .replaceAll("\\.*$", "");
        if(new_id.length()<=2) new_id = new_id.replaceAll("^(.*)(.)$", "$1$2$2$2").substring(0, 3);
        return new_id;
    }

    @Test
    public void ex0() {
        String result = solution("...!@BaT#*..y.abcdefghi-jklm..");
        Assertions.assertThat(result).isEqualTo("bat.y.abcdefghi");
    }

    @Test
    public void ex1() {
        String result = solution("...!@BaT#*..y.abcdefghijklm");
        Assertions.assertThat(result).isEqualTo("bat.y.abcdefghi");
    }

    @Test
    public void ex2() {
        String result = solution("z-+.^.");
        Assertions.assertThat(result).isEqualTo("z--");
    }

    @Test
    public void ex3() {
        String result = solution("=.=");
        Assertions.assertThat(result).isEqualTo("aaa");
    }

    @Test
    public void ex4() {
        String result = solution("123_.def");
        Assertions.assertThat(result).isEqualTo("123_.def");
    }

    @Test
    public void ex5() {
        String result = solution("abcdefghijklmn.p");
        Assertions.assertThat(result).isEqualTo("abcdefghijklmn");
    }
}
