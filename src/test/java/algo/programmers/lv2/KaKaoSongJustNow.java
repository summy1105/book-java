package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/17683 lv2
public class KaKaoSongJustNow {
    public String solution(String m, String[] musicinfos) {
        m = parseMusic(m, -1);
        String answer = "";
        int maxLength = 0;
        for (String info : musicinfos) {
            String[] split = info.split(",");
            int startMins = Integer.valueOf(split[0].split(":")[0]) * 60 + Integer.valueOf(split[0].split(":")[1]);
            int endMins = Integer.valueOf(split[1].split(":")[0]) * 60 + Integer.valueOf(split[1].split(":")[1]);
            String title = split[2];
            String parseMusic = parseMusic(split[3], endMins-startMins-1);
            if (parseMusic.contains(m) && (endMins-startMins)>maxLength) {
                answer = title;
                maxLength = endMins - startMins;
            }
        }
        return answer;
    }

    private String parseMusic(String music, int period){
        StringBuilder builder = new StringBuilder();
        char[] notes = music.toCharArray();
        for (int i=0; period>-1? builder.length()<=period : i < notes.length ; i++) {
            if ( (i % notes.length) < notes.length-1 && notes[(i + 1) % notes.length] == '#') {
                builder.append((char) (notes[i % notes.length] + 32));
                i++;
            } else {
                builder.append(notes[i % notes.length]);
            }
        }
        return builder.toString();
    }

    @Test
    public void ex1() {
        String result = solution("ABCDEFG", new String[]{
                "12:00,12:14,HELLO,CDEFGAB",
                "13:00,13:05,WORLD,ABCDEF"
        });
        Assertions.assertThat(result).isEqualTo("HELLO");
    }

    @Test
    public void ex2() {
        String result = solution("CC#BCC#BCC#BCC#B", new String[]{
                "03:00,03:30,FOO,CC#B",
                "04:00,04:08,BAR,CC#BCC#BCC#B"
        });
        Assertions.assertThat(result).isEqualTo("FOO");
    }

    @Test
    public void ex3() {
        String result = solution("ABC", new String[]{
                "12:00,12:14,HELLO,C#DEFGAB",
                "13:00,13:05,WORLD,ABCDEF"
        });
        Assertions.assertThat(result).isEqualTo("WORLD");
    }
    
    @Test
    public void test() {
        //given
        String s = parseMusic("CC#BC#", -1);
        Assertions.assertThat(s).isEqualTo("CcBc");
    }
}
