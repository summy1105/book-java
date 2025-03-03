package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/72411 lv2
public class KaKaoMenuRenewal {
    TreeSet<Character> menuSet;
    TreeMap<String, Integer> candidateCourses;
    boolean[] courseCountCheck;
    int maxCourseCnt;
    int[] courseMaxCount;

    public String[] solution(String[] orders, int[] course) {
        int[] menuCounts = new int[26];
        for(String order:orders){
            for(char menu:order.toCharArray())
                menuCounts[menu-'A']++;
        }

        menuSet = new TreeSet<>();
        for(int i=0; i<menuCounts.length; i++){
            if(menuCounts[i]>=2) menuSet.add((char)(i+'A'));
        }
        // for(char ch : menuSet) System.out.println(ch);

        courseCountCheck = new boolean[11];
        for(int cnt:course) courseCountCheck[cnt] = true;
        maxCourseCnt = course[course.length-1];

        candidateCourses = new TreeMap<>();
        courseMaxCount = new int[11];
        // for(String ch : candidateCourses.keySet()) System.out.println(ch);
        for(String order:orders){
            char[] menus = order.toCharArray();
            Arrays.sort(menus);
            backtracking(new StringBuilder(), 0, menus, orders);
        }

        TreeSet<String> answer = new TreeSet<>();
        for(String candidateCourse : candidateCourses.keySet()){
            int count = candidateCourses.get(candidateCourse);
            if(count>=2 && count == courseMaxCount[candidateCourse.length()]){
                answer.add(candidateCourse);
            }
        }

        return answer.toArray(String[]::new);
    }

    private void backtracking(StringBuilder builder, int start, char[] menus, String[] orders) {
        for(int i=start; i<menus.length; i++){
            builder.append(menus[i]);
            int matchCount;
            if(courseCountCheck[builder.length()] && !candidateCourses.containsKey(builder.toString())
                    && (matchCount = matchCount(builder.toString(), orders))>=2) {
                candidateCourses.put(builder.toString(), matchCount);
                courseMaxCount[builder.length()] = Math.max(courseMaxCount[builder.length()], matchCount);
            }
            if(builder.length()<maxCourseCnt) backtracking(builder, i+1, menus, orders);
            builder.deleteCharAt(builder.length()-1);
        }
    }

    private int matchCount(String course, String[] orders){
        int count=0;
        for(String order:orders){
            boolean isMatch = true;
            for(char ch:course.toCharArray()) if(order.indexOf(ch)==-1) isMatch=false;
            if(isMatch) count++;
        }
        return count;
    }

    @Test
    public void ex1() {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};
        String[] expected = {"AC", "ACDE", "BCFG", "CDE"};
        String[] result = solution(orders, course);
        Assertions.assertThat(result).containsExactly(expected);
    }

    @Test
    public void ex2() {
        String[] orders = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course = {2, 3, 5};
        String[] expected = {"ACD", "AD", "ADE", "CD", "XYZ"};
        String[] result = solution(orders, course);
        Assertions.assertThat(result).containsExactly(expected);
    }

    @Test
    public void ex3() {
        String[] orders = {"XYZ", "XWY", "WXA"};
        int[] course = {2, 3, 4};
        String[] expected = {"WX", "XY"};
        String[] result = solution(orders, course);
        Assertions.assertThat(result).containsExactly(expected);
    }
}
