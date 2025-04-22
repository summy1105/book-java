package algo.programmers.lv4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/12983 lv4

public class WordPuzzle {
    public int solution(String[] strs, String t) {
        int MAX = t.length() + 1;
        int[] dp = new int[MAX];
        Arrays.fill(dp, MAX);
        dp[0] = 0;

        for (int i = 0; i <= t.length(); i++) {
            if(dp[i] == MAX) continue;
            for (String str : strs) {
                int nextLen = i + str.length();
                if (nextLen <= t.length() && t.startsWith(str, i)) {
                    dp[nextLen] = Math.min(dp[nextLen], dp[i] + 1);
                }
            }
        }

        return dp[t.length()] == MAX ? -1 : dp[t.length()];
    }

    //["ba","na","n","a"]	"banana"	3
    @Test
    public void ex1(){
        String[] words = {"ba", "na", "n", "a"};
        String query = "banana";
        int expected = 3;
        int result = solution(words, query);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    //["app","ap","p","l","e","ple","pp"]	"apple"	2
    @Test
    public void ex2(){
        String[] words = {"app", "ap", "p", "l", "e", "ple", "pp"};
        String query = "apple";
        int expected = 2;
        int result = solution(words, query);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    //["ba","an","nan","ban","n"]	"banana"	-1
    @Test
    public void ex3(){
        String[] words = {"ba", "an", "nan", "ban", "n"};
        String query = "banana";
        int expected = -1;
        int result = solution(words, query);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    //["le","ap","pl", "a"]	"apple"	-1
    @Test
    public void ex4(){
        String[] words = {"le","ap","pl", "a"};
        String query = "apple";
        int expected = -1;
        int result = solution(words, query);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    //["a","p","l", "e"]	"apple"	5
    @Test
    public void ex5(){
        String[] words = {"apple"};
        String query = "apple";
        int expected = 1;
        int result = solution(words, query);
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
