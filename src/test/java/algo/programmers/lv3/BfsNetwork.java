package algo.programmers.lv3;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

// https://school.programmers.co.kr/learn/courses/30/lessons/43162 lv3
public class BfsNetwork {
    @Test
    public void test() {
        int n = 5;
        int[][] computers = new int[][]{
                  {1, 0, 0, 0, 0}
                , {0, 1, 0, 0, 0}
                , {0, 0, 1, 0, 0}
                , {0, 0, 0, 1, 0}
                , {0, 0, 0, 0, 1}
        };

        int solution = solution(n, computers);
        System.out.println(solution);
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] checkVisitNode = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        for (int currentNode = 0; currentNode < n; currentNode++) {
            if(checkVisitNode[currentNode] == false){
                checkVisitNode[currentNode] = true;
                queue.add(currentNode);
                while (queue.peek() != null) {
                    int cur = queue.poll().intValue();
                    for (int nextNode = 0 ; nextNode <  n ; nextNode++) {
                        if (computers[cur][nextNode] == 1 && checkVisitNode[nextNode] == false) {
                            checkVisitNode[nextNode] = true;
                            queue.add(nextNode);
                        }
                    }
                }
                answer++;
            }
        }
        return answer;
    }
}
