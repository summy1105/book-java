package algo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 Back Tracking 개념
 - 모든 경우의 수를 확인해야 할 때,
    > for로는 확인 불가한 경우(깊이가 달라질 때)
    > 예 : 순열 - M개의 수에서 N개를 뽑아서, 뽑는 순서(!)의 경우의 갯수
 - depth에 재귀함수 사용
 - 첫번째 고려할 알고리즘 DFS -> (재귀 혹은 스택)
 - 트리의 깊이가 무한대가 될 때(미로찾기에서 루프가 있는 경우등), dfs보다 bfs가 낫다
 - 최단거리는 BFS로
 */

// 1 아이디어 : 어떤 알고리즘?
// 2 시간 복잡도
// 3 자료구조

// 시간 복잡도에서 숫자가 크다, 보통 2억개 계산안에 해야 하기 때문에,
// 중복이 허용된다면 N은 8까지 가능하고 8^8 = 약 1700만 , 9^9 = 약3.9억
// 중복이 안된다면 N은 10까지 가능하다? 11! = 약4000만

// 백트래킹 문제는 N이 작음
// 재귀함수 사용할 때, 종료시점에 자료구조 복구 잊지 말기

// 백준 https://www.acmicpc.net/problem/15649
// 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
//  - 1부터 N까지 자연수 중에서 중복 없이, M개를 고른 수
//    1 ≤ M ≤ N ≤ 8
// 예제 input: "4 2"
//      output : "1 2 \n 1 3 \n 1 4 \n 2 1 \n 2 3..."
public class ExBacktracking {

    @Test
    public void 백준_예제1() {
        int n = 3;
        int m = 1;
        List<int[]> list = execute(n, m);
        for (int[] ints : list) {
            for (int i : ints) {
                System.out.printf("%d ", i);
            }
            System.out.println();
        }
        Assertions.assertThat(list.size()).isEqualTo(factorialUsingStreams(n, m));
    }

    @Test
    public void 백준_예제2() {
        int n = 4;
        int m = 2;
        List<int[]> list = execute(n, m);
        for (int[] ints : list) {
            for (int i : ints) {
                System.out.printf("%d ", i);
            }
            System.out.println();
        }
        Assertions.assertThat(list.size()).isEqualTo(factorialUsingStreams(n, m));
    }

    @Test
    public void 숫자_한개만_있는_경우() {
        int n = 1;
        int m = 1;
        List<int[]> list = execute(n, m);
        for (int[] ints : list) {
            for (int i : ints) {
                System.out.printf("%d ", i);
            }
            System.out.println();
        }
        Assertions.assertThat(list.size()).isEqualTo(factorialUsingStreams(n, m));
    }

    @Test
    public void 여덟개를_선택한_경우() {
        int n = 8;
        int m = 8;
        List<int[]> list = execute(n, m);
        System.out.println("list.size() = " + list.size());
        Assertions.assertThat(list.size()).isEqualTo(factorialUsingStreams(n, m));
    }

    public int factorialUsingStreams(int n, int m) {
        return IntStream.rangeClosed(n-m+1, n)
                .reduce(1, (int x, int y) -> x * y);
    }

    List<int[]> execute(int n, int m) {
        List<int[]> list = new ArrayList<>();
        backtracking(list, n, m, 0, new int[m], new boolean[n+1]);
        return list;
    }

    private void backtracking(List<int[]> list, int n, int m, int depth, int[] stack, boolean[] visit) {
        if (depth >= m) {
            list.add(stack.clone());
            return;
        }
        for (int i = 1; i <=n ; i++) {
            if(visit[i]) continue;

            stack[depth] = i;
            visit[i] = true;
            backtracking(list, n, m, depth + 1, stack, visit);
            visit[i] = false;
        }
    }
}
