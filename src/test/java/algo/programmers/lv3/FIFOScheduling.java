package algo.programmers.lv3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/12920 lv3

public class FIFOScheduling {
    public int solution(int n, int[] cores) {
        if( n <= cores.length) return n;

        long left = 0;
        long right = n * 10_000;
        long time = 0;
        long finishCount=0;
        // 모든 작업 완료시간 찾기
        while(left<=right){
            long mid = (left + right) / 2;
            finishCount=0;

            for (int core : cores) {
                finishCount += mid / core;
            }

            if (finishCount <= n) {
                time = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println("time = " + time);
        System.out.println("finishCount = " + finishCount);

        long remain = n - finishCount ;
        int coreIdx = 0;
        for (; coreIdx < cores.length && remain > 0; coreIdx++) {
            if(time % cores[coreIdx] != 0)
                remain--;
        }
        return coreIdx;
    }

    @Test
    public void ex1() {
        int n = 6;
        int[] cores = {1,2,3};
        int result = solution(n, cores);
        Assertions.assertThat(result).isEqualTo(2);
    }
}
