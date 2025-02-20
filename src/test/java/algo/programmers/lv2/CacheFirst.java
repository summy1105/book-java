package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

// https://school.programmers.co.kr/learn/courses/30/lessons/17680 lv2
// 43 -> 58
public class CacheFirst {
    public int solution(int cacheSize, String[] cities) {
        Queue<String> cache = new LinkedList<>();
        int answer = 0;
        for (int idx = 0; idx < cities.length  ; idx++) {
            if(isInCache(cache, cities[idx], cacheSize)) answer++;
            else answer += 5;
        }
        return answer;
    }

    private boolean isInCache(Queue<String> cache, String city, int cacheSize) {
        city = city.toLowerCase();
        if(!cache.contains(city)) {
            cache.add(city);
            if(cache.size()>cacheSize) cache.poll();
            return false;
        }
        else{
            cache.remove(city);
            cache.add(city);
            return true;
        }
    }

    @Test
    public void ex1() {
        int result = solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
        Assertions.assertThat(result).isEqualTo(50);
    }

    @Test
    public void ex2() {
        int result = solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"});
        Assertions.assertThat(result).isEqualTo(21);
    }

    @Test
    public void ex3() {
        int result = solution(2, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});
        Assertions.assertThat(result).isEqualTo(60);
    }

    @Test
    public void ex4() {
        int result = solution(5, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"});
        Assertions.assertThat(result).isEqualTo(52);
    }

    @Test
    public void ex5() {
        int result = solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"});
        Assertions.assertThat(result).isEqualTo(16);
    }

    @Test
    public void ex6() {
        int result = solution(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
        Assertions.assertThat(result).isEqualTo(25);
    }

    @Test
    public void ex7() {
        int result = solution(1, new String[]{"Pangyo", "Pangyo", "Pangyo", "pangyo", "pangyo"});
        Assertions.assertThat(result).isEqualTo(9);
    }
}
