package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/389481 lv3

public class SealedSpell {

    public String solution(long n, String[] bans) {
        Arrays.sort(bans, (s1, s2)->{
            if(s1.length() != s2.length()) return s1.length() - s2.length();
            return s1.compareTo(s2);
        });
        int bansLength = bans.length;
        long[] allCntByLength = new long[11];
        long sum = 0;
        for (int i = 1; i < 11; i++) {
            sum += (long) Math.pow(26, i);
            allCntByLength[i] = sum;
        }
        long[] banSpellNumber = new long[bansLength];
        for (int i = 0; i < bansLength; i++) {
            String banSpell = bans[i];
            // gqk -> zz숫자 더하고
            int spellLen = banSpell.length();
            long number = allCntByLength[spellLen - 1] + 1;
            char[] chars = banSpell.toCharArray();
            // g-a * zz -> 숫자
            // q * z -> 숫자
            // k
            for (int j = 0; j < spellLen ; j++) {
                number += (chars[j] - 'a') * (long) Math.pow(26, spellLen - j - 1);
            }
            banSpellNumber[i] = number;
        }
        for (int i = 0; i < bansLength; i++) {
            if(banSpellNumber[i]<=n)
                n++;
        }
        // answer length 구하기
        int answerLength = 11;
        while (answerLength > 0 && n<allCntByLength[answerLength-1]){
            answerLength--;
        }
        long offset = n - (answerLength == 1 ? 0 : allCntByLength[answerLength - 1]) -1;
        StringBuilder answer = new StringBuilder();
        for (int i = answerLength-1; i >=0; i--) {
            long divisor = (long) Math.pow(26, i);
            int digit = (int) (offset / divisor);
            answer.append((char) ('a' + digit));
            offset %= divisor;
        }

        return answer.toString();
    }

    //30	["d", "e", "bb", "aa", "ae"]	"ah"
    @Test
    public void test() {
        long n = 30;
        String[] bans = {"d", "e", "bb", "aa", "ae"};
        String result = solution(n, bans);
        Assertions.assertThat(result).isEqualTo("ah");
    }

    // 7388	["gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc"]	"jxk"
    @Test
    public void test2() {
        long n = 7388;
        String[] bans = {"gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc"};
        String result = solution(n, bans);
        Assertions.assertThat(result).isEqualTo("jxk");
    }

    @Test
    public void test3() {
        long n = 8353082582L + 12356630 + 12356630 + 18278 + 18278 + 18278 + 18278 + 18278 + 18278 + 18278+(long)Math.pow(26, 5)*3;
        String[] bans = {"gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc", "dkcie", "simc", "acbc", "z", "eimc", "asex"};
        String result = solution(n, bans);
        Assertions.assertThat(result).isEqualTo("aafcjjjo");
    }
}
