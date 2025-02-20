package algo.programmers.lv2;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ComparatorFactory;
import org.junit.jupiter.api.Test;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42587 lv2
//
public class Process {
    public int solution1(int[] priorities, int location) {
        PriorityQueue<Integer> sortedPriorities = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            sortedPriorities.add(priorities[i]);
            queue.add(i);
        }

        int answer = 0;
        while (true) {
            Integer maxPriority = sortedPriorities.poll();
            if(maxPriority == priorities[location]) break;
            while(priorities[queue.peek()] != maxPriority) queue.add(queue.poll());
            answer++;
            queue.poll();
        }
        while(queue.peek() != location){
            if(priorities[queue.poll()] == priorities[location]) answer++;
        }
        return answer+1;
    }

    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Integer> queue = new LinkedList<>();
        for (int priority : priorities) {
            queue.add(priority);
        }
        int currentLocation = location;
        quickSort(priorities, 0, priorities.length - 1);

        while (!queue.isEmpty()) {
            Integer curPriority = queue.poll();
            if (curPriority.equals(priorities[answer])) {
                answer++;
                currentLocation--;
                if (currentLocation < 0) break;
            } else {
                queue.add(curPriority);
                currentLocation = currentLocation - 1 < 0 ? queue.size() - 1 : currentLocation - 1;
            }
        }

        return answer;
    }

    void quickSort(int[] list, int left, int right) {
        if(left>=right) return;
        int pivot = list[right];

        int curL = left;
        int curR = right;
        while (curL < curR) {
            while (list[curL] > pivot) curL++;
            while (list[curR] <= pivot && curL < curR) curR--;
            int temp = list[curL];
            list[curL] = list[curR];
            list[curR] = temp;
        }

        list[right] = list[curR];
        list[curR] = pivot;

        quickSort(list, left, curR - 1);
        quickSort(list, curR + 1, right);
    }

    @Test
    public void ex1() {
        int result = solution(new int[]{2, 1, 3, 2}, 2);
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    public void ex2() {
        int result = solution(new int[]{1, 1, 9, 1, 1, 1}, 0);
        Assertions.assertThat(result).isEqualTo(5);
    }

    @Test
    public void ex3() {
        int result = solution(new int[]{1, 3, 9, 1, 1, 3}, 4);
        Assertions.assertThat(result).isEqualTo(5);
    }

    @Test
    public void ex4() {
        int result = solution(new int[]{1, 1}, 1);
        Assertions.assertThat(result).isEqualTo(2);
    }
}
