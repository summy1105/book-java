package algo.programmers.lv3;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

// https://school.programmers.co.kr/learn/courses/30/lessons/43163 lv3
public class BfsCountingWordConversion {
    @Test
    public void test() {

        // 4
//        String begin = "hit";
//        String target = "cog";
//        String[] words = new String[]{"hot", "dot", "dog", "lot", "log", "cog"};

        // 0
//        String begin = "hit";
//        String target = "cog";
//        String[] words = new String[]{"hot", "dot", "dog", "lot", "log"};

        // 0
        String begin = "cool";
        String target = "keep";
        String[] words = new String[]{"keip", "kepp", "keep", "kiep", "koep", "koei", "kooi", "coil", "cooi"};

        int answer = solution(begin, target, words);
        System.out.println(answer);
    }

    public int solution(String begin, String target, String[] words) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visitCheck = new boolean[words.length];
        queue.add(new int[]{-1/*begin*/, 0/*count*/});
        int answer = words.length + 1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int currentIdx = poll[0];
            String currentWord = currentIdx ==-1 ? begin: words[currentIdx];
            int currentCount = poll[1];

            if (currentWord.equals(target)) {
                answer = Math.min(answer, currentCount);
            }

            for (int i = 0; i < words.length; i++) {
                String nextWord = words[i];
                if (visitCheck[i] == false && isOneCharacterDifferent(currentWord, nextWord)) {
                    visitCheck[i] = true;
                    queue.add(new int[]{i, currentCount + 1});
                }
            }
        }
        return answer > words.length ? 0 : answer;
    }

    boolean isOneCharacterDifferent(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int count = 0;
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) count++;
        }
        return count == 1;
    }
}
