package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/77886 lv3

public class Move110 {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        for(int i=0; i<s.length; i++){
            answer[i] = findMinString(s[i]);
        }
        return answer;
    }

    String findMinString(String s){
        StringBuilder sb = new StringBuilder();
        int delCount = 0;
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (sb.length() >= 3 && sb.substring(sb.length() - 3).equals("110")) {
                sb.delete(sb.length() - 3, sb.length());
                delCount++;
            }
        }

        int insertIdx = sb.lastIndexOf("0");
        StringBuilder block = new StringBuilder();
        for (int i = 0; i < delCount; i++) {
            block.append("110");
        }

        StringBuilder result = new StringBuilder();
        if(insertIdx == -1){
            result.append(block);
            result.append(sb);
        }else{
            result.append(sb.substring(0, insertIdx + 1));
            result.append(block);
            result.append(sb.substring(insertIdx + 1));
        }

        return result.toString();
    }

    @Test
    public void ex1() {
        String[] result = solution(new String[]{"1110","100111100","0111111010"});
        Assertions.assertThat(result).containsExactly("1101","100110110","0110110111");
    }

    @Test
    public void test() {
        //given
        double num = 1.0;
        System.out.println(num == (int)num);
        //when

        //then
    }
}
