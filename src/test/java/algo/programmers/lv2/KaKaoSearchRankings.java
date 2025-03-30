package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

// https://school.programmers.co.kr/learn/courses/30/lessons/72412 lv2
public class KaKaoSearchRankings {
    HashMap<String, Integer> dic = new HashMap<>();
    {
        dic.put("-", 7);
        dic.put("cpp", 4);
        dic.put("java", 2);
        dic.put("python", 1);
        dic.put("backend", 2);
        dic.put("frontend", 1);
        dic.put("junior", 2);
        dic.put("senior", 1);
        dic.put("chicken", 2);
        dic.put("pizza", 1);
    }

    public int[] solution(String[] infos, String[] query) {

        HashMap<Integer, ArrayList<int[]>> requestMap = new HashMap<>(); // 조건, {점수, 팀 index, 점수넘는 인원}

        for(int rIdx=0; rIdx<query.length; rIdx++){
            String[] split = query[rIdx].split(" ");
            int rFlag = 0;
            int i=0;
            for(String each : split){
                if(dic.get(each) == null) continue;
                rFlag |= dic.get(each)<<(3*i);
                i++;
            }
            int rScore = Integer.valueOf(split[split.length-1]);

            ArrayList<int[]> scoreList = requestMap.getOrDefault(rFlag, new ArrayList<>());
            scoreList.add(new int[]{rScore, rIdx, 0});
            requestMap.put(rFlag, scoreList);
        }

        for(String info: infos){
            String[] split = info.split(" ");
            int flag = 0;
            for(int i=0; i<split.length-1; i++){
                flag |= dic.get(split[i])<<(3*i);
            }
            Integer score = Integer.valueOf(split[split.length - 1]);

            for (Integer rFlag : requestMap.keySet()) {
                if( (rFlag & flag) != flag) continue;
                ArrayList<int[]> scoreList = requestMap.get(rFlag);
                scoreList.add(new int[]{score, -1, 1});
            }
        }

        int[] answer = new int[query.length];
        for (ArrayList<int[]> scoreList : requestMap.values()) {
            scoreList.sort((a1, a2)->{
                if(a1[0] != a2[0]) return a2[0] - a1[0];
                return a1[1] - a2[1];
            });
            int sum = 0;
            for (int[] score : scoreList) {
                sum += score[2];
                if (score[1] >= 0) {
                    answer[score[1]] = sum;
                }
            }
        }
        return answer;
    }

    @Test
    public void ex1() {
        String[] info = {
                "java backend junior pizza 150",
                "python frontend senior chicken 210",
                "python frontend senior chicken 150",
                "cpp backend senior pizza 260",
                "java backend junior chicken 80",
                "python backend senior chicken 50"
        };

        String[] query = {
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"
        };

        int[] result = solution(info, query);
        Assertions.assertThat(result).containsExactly(1, 1, 1, 1, 2, 4);
    }

    @Test
    public void ex2() {
        String[] info = {
                "java backend junior pizza 150",
                "python frontend senior chicken 210",
                "python frontend senior chicken 150",
                "cpp backend senior pizza 260",
                "java backend junior chicken 80",
                "java backend junior chicken 80",
                "python backend senior chicken 50"
        };

        String[] query = {
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150",
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150",
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150",
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150",
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150",
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150",
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150",
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150",
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150",
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"
        };

        int[] result = solution(info, query);
        Assertions.assertThat(result).containsExactly(1, 1, 1, 1, 2, 4, 1, 1, 1, 1, 2, 4, 1, 1, 1, 1, 2, 4, 1, 1, 1, 1, 2, 4, 1, 1, 1, 1, 2, 4, 1, 1, 1, 1, 2, 4, 1, 1, 1, 1, 2, 4, 1, 1, 1, 1, 2, 4, 1, 1, 1, 1, 2, 4, 1, 1, 1, 1, 2, 4);
    }
}
