package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

// https://school.programmers.co.kr/learn/courses/30/lessons/42577 lv2
// 15 -> 33
public class ListPhoneNumbers {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            if(phone_book[i+1].startsWith(phone_book[i])) return false;
        }
        return true;
    }

    @Test
    public void ex1() {
        boolean result = solution(new String[]{"119", "97674223", "1195524421"});
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void ex2() {
        boolean result = solution(new String[]{"123", "456", "789"});
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void ex3() {
        boolean result = solution(new String[]{"12", "123", "1235", "567", "88"});
        Assertions.assertThat(result).isFalse();
    }
}
