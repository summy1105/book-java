package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;

// https://school.programmers.co.kr/learn/courses/30/lessons/12981 lv2
// 8:11 -> 8: 27

public class EnglishWordChainGame {


    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        HashSet<String> wordSet = new HashSet<>();
        String beforeWord = words[0];
        wordSet.add(beforeWord);
        for (int i = 1; i < words.length; i++) {
            if(beforeWord.charAt(beforeWord.length()-1) != words[i].charAt(0)
                    || wordSet.contains(words[i])){
                answer = new int[]{i % n + 1, i / n + 1};
                break;
            }
            wordSet.add(words[i]);
            beforeWord = words[i];
        }

        return answer;
    }

    @Test
    public void ex1() {
        int[] result = solution(3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"});
        Assertions.assertThat(result).containsExactly(3,3);
    }

    @Test
    public void ex2() {
        int[] result = solution(5, new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"});
        Assertions.assertThat(result).containsExactly(0,0);
    }

    @Test
    public void ex3() {
        int[] result = solution(2, new String[]{"hello", "one", "even", "never", "now", "world", "draw"});
        Assertions.assertThat(result).containsExactly(1,3);
    }

}
