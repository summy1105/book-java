package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.TreeSet;

// https://school.programmers.co.kr/learn/courses/30/lessons/43238 lv3
public class Immigration {
    public long solution(int n, int[] times) {
        long left = 0;
        long right = Long.MAX_VALUE;
        while(left<right) {
            long mid = (left+right) / 2;

            long peopleNum=n;
            long compareTime = 0;
            int i=0;
            for(; i<times.length && peopleNum>=0 ; i++){
                long passed = mid/times[i];
                peopleNum-=passed;
                compareTime = Math.max(compareTime, passed * times[i]);
            }
            if(peopleNum==0 && i==times.length && compareTime == mid)
                return mid;
            else if (peopleNum <0 || (peopleNum==0 && compareTime< mid))
                right = mid - 1;
            else
                left = mid+1;
        }
        return left;
    }

    @Test
    public void ex1() {
        long result = solution(6, new int[]{7, 10});
        Assertions.assertThat(result).isEqualTo(28);
    }
}
