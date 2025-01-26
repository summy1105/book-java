package algo.programmers.lv0;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/258712 lv1
// 2:10 -> 3:05

/**
 * 1. 두사람 사이에 선물을 많이 준 사람이 하나 받는다
 * 2. 두사람 사이에 주고받은게 같다면, 받는 것보다 준 사람이 많은 사람이 선물 하나 받는다 ( 선물 지수도 같다면 주고 받지 않는다.)
 *
 * 2<= 친구수 <=50
 * gift "A B" a->b한테 줌
 *
 * \
 */
public class MostReceivedGift {
    public int solution(String[] friends, String[] gifts) {
        // 이름 -> 배열인덱스
        HashMap<String, Integer> nameIndex = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            nameIndex.put(friends[i], i);
        }

        // 지금까지 주고 받은거 세기, 선물지수
        int[][] recordCounterParty = new int[friends.length][friends.length];
        int[] recordSendReceive = new int[friends.length];
        Map<String, Integer> giftsCounts = Arrays.stream(gifts).collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
        for (String s : giftsCounts.keySet()) {
            String[] names = s.split(" ");
            recordCounterParty[nameIndex.get(names[0])][nameIndex.get(names[1])] += giftsCounts.get(s);
            recordSendReceive[nameIndex.get(names[0])] += giftsCounts.get(s);
            recordSendReceive[nameIndex.get(names[1])] -= giftsCounts.get(s);
        }// -> group 하지말고 한번의 루프로 한개씩 늘려도 됨

        // 받을 선물 개수 구하기
        int[] nextGiftCounts = new int[friends.length];
        // row배열 값이 준 사람, col배열 값이 받은 사람
        for (int i = 1; i < recordCounterParty.length; i++) {
            for (int j = 0; j < i; j++) {
                if(recordCounterParty[i][j] > recordCounterParty[j][i]) nextGiftCounts[i]++;
                else if(recordCounterParty[i][j] < recordCounterParty[j][i]) nextGiftCounts[j]++;
                else{
                    if(recordSendReceive[i]>recordSendReceive[j]) nextGiftCounts[i]++;
                    else if(recordSendReceive[i]< recordSendReceive[j]) nextGiftCounts[j]++;
                }
            } // -> 한번에 양쪽 비교 발고 한명이 받을값 구한후 max값 찾아도됨
        }

        int max = 0;
        for (int i = 0; i < nextGiftCounts.length; i++) {
            max = Math.max(max, nextGiftCounts[i]);
        }
        return max;
    }

    @Test
    public void 예제1() {
        int result = solution(new String[]{"muzi", "ryan", "frodo", "neo"}, new String[]{"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"});
        Assertions.assertThat(result).isEqualTo(2);
    }

    @Test
    public void 예제2() {
        int result = solution(new String[]{"joy", "brad", "alessandro", "conan", "david"}, new String[]{"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"});
        Assertions.assertThat(result).isEqualTo(4);
    }

    @Test
    public void 예제3() {
        int result = solution(new String[]{"a", "b", "c"}, new String[]{"a b", "b a", "c a", "a c", "a c", "c a"});
        Assertions.assertThat(result).isEqualTo(0);
    }
}
