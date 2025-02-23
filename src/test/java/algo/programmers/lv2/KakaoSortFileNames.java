package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/17686 lv2
public class KakaoSortFileNames {
    public String[] solution(String[] files) {
        PriorityQueue<String[]> order = new PriorityQueue<>((arr1, arr2) -> {
            if (arr1[0].compareTo(arr2[0]) != 0) return arr1[0].compareTo(arr2[0]);
            if (arr1[1].compareTo(arr2[1]) != 0) return arr1[1].compareTo(arr2[1]);
            return arr1[2].compareTo(arr2[2]);
        });

        for (int i = 0; i < files.length; i++) {
            String[] keySet = files[i].toLowerCase().replaceAll("([^0-9]+)([0-9]{1,5})(.*)", "$1+$2").split("\\+");
            String numberPart = "0000" + keySet[1];
            String indexPart = "00" + i;
            order.add(new String[]{keySet[0], numberPart.substring(numberPart.length() - 5), indexPart.substring(indexPart.length() - 3)});
        }
        String[] answer = new String[files.length];
        for (int i = 0; i < answer.length; i++) {
            int idx = Integer.valueOf(order.poll()[2]);
            answer[i] = files[idx];
        }
        return answer;
    }

    @Test
    public void ex1() {
        String[] input = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] expected = {"img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"};
        String[] result = solution(input);
        Assertions.assertThat(result).containsExactly(expected);
    }

    @Test
    public void ex2() {
        String[] input = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        String[] expected = {"A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"};
        String[] result = solution(input);
        Assertions.assertThat(result).containsExactly(expected);
    }

    @Test
    public void ex3() {
        String[] input = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG", "img .0abs02"};
        String[] expected = {"img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png", "img .0abs02"};
        String[] result = solution(input);
        Assertions.assertThat(result).containsExactly(expected);
    }

    @Test
    public void ex4() {
        String[] input = {"F-5 Freedom Fighter", "F-14 Tomcat"};
        String[] expected = {"F-5 Freedom Fighter", "F-14 Tomcat"};
        String[] result = solution(input);
        Assertions.assertThat(result).containsExactly(expected);
    }

    @Test
    public void ex5() {
        String[] input = {"muzi1.txt", "MUZI1.txt", "muzi001.txt", "muzi1.TXT"};
        String[] expected = {"muzi1.txt", "MUZI1.txt", "muzi001.txt", "muzi1.TXT"};
        String[] result = solution(input);
        Assertions.assertThat(result).containsExactly(expected);
    }
}
