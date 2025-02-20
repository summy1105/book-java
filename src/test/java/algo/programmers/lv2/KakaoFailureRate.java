package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42889 lv2
public class KakaoFailureRate {
    public int[] solution(int N, int[] stages) {
        int[] stageCount = new int[N+2];
        for(int stage : stages) stageCount[stage]++;
        double[][] rateNStage = new double[N+1][2];
        int sum=stageCount[N+1];
        for(int i = N; i>0; i--){
            sum+=stageCount[i];
            double rate = sum == 0 ? 0 : (double) stageCount[i]/(double)sum;
            rateNStage[i] = new double[]{rate, i};
        }
        Arrays.sort(rateNStage, (arr1, arr2)-> arr2[0]== arr1[0]? (int)arr1[1]-(int)arr2[1] :(arr2[0]>arr1[0]?1:-1));
        int[] answer = new int[N];
        int idx=0;
        for(double[] part : rateNStage){
            if(part[1]>0) answer[idx++]= (int)part[1];
        }
        return answer;
    }

    @Test
    public void ex1() {
        int[] result = solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3});
        Assertions.assertThat(result).containsExactly(3, 4, 2, 1, 5);
    }

    @Test
    public void ex2() {
        int[] result = solution(4, new int[]{4, 4, 4, 4, 4});
        Assertions.assertThat(result).containsExactly(4, 1, 2, 3);
    }

    @Test
    public void ex3() {
        int[] result = solution(2, new int[]{1, 1, 1, 1, 1});
        Assertions.assertThat(result).containsExactly(1, 2);
    }
}
