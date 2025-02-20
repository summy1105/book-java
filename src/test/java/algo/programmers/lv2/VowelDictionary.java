package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/84512 lv2
//
public class VowelDictionary {

    public int solution(String word) {
        word = (word + "0000").substring(0, 5);
        return dfs(new StringBuffer("00000"), word, 0, 0);
    }

    final char[] vowels = {'A', 'E', 'I', 'O', 'U'};
    public int dfs(StringBuffer buffer, String word, int count, int depth) {
        if(depth>=5) return count;
        for (int i = 0; i < 5; i++) {
            buffer.setCharAt(depth, vowels[i]);
            if(buffer.toString().compareTo(word)<=0) count++;
            count = dfs(buffer, word, count, depth + 1);
        }
        buffer.setCharAt(depth, '0');
        return count;
    }

    public int solution2(String word) {
        int maxWordLength = 5;
        int vowelsCount = "AEIOU".length();
        int[] weights = new int[maxWordLength];
        weights[maxWordLength-1] = 1;
        for (int i = 1; i < maxWordLength; i++) {
            weights[maxWordLength-1 - i] = (int) Math.pow(vowelsCount, i) + weights[maxWordLength - i];
        }
        int answer = 0;
        for (int i = 0; i < word.length(); i++) {
            int idx = "AEIOU".indexOf(word.charAt(i));
            answer += idx * weights[i];
        }
        return answer + word.length();
    }

    @Test
    public void ex1() {
        int result = solution2("AAAAE");
        Assertions.assertThat(result).isEqualTo(6);
    }

    @Test
    public void ex2() {
        int result = solution2("AAAE");
        Assertions.assertThat(result).isEqualTo(10);
    }

    @Test
    public void ex3() {
        int result = solution2("I");
        Assertions.assertThat(result).isEqualTo(1563);
    }

    @Test
    public void ex4() {
        int result = solution2("EIO");
        Assertions.assertThat(result).isEqualTo(1189);
        String s = Integer.toString(10, 3);
        System.out.println("s = " + s);
    }
}
