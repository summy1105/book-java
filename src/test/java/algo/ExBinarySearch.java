package algo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 이진 탐색
    - 어떤 값을 찾을 때 정렬의 특징을 이용해 빨리 찾음
    - 정렬되어 있을 경우 : o(log N)
    - 처음부터 생각하기 어려움, 쉬운 방법부터 생각`

 $$$ 기본 코드 셋을 외워야 함

 백준 https://www.acmicpc.net/problem/1920
 문제
    N개 수가 있는 List에서 M개의 숫자가 있는지 없는지 찾기
    1<= N <= 100,000, 1<= M <= 100,000
 단순 반복
    최대 : 10^5 * 10^5 = 10^10(100억 > 2억)

 정렬 : n * log n
 이진 탐색 : m * log n
    2 * 10^5 * log 10^5 = 2* 10^5 * 약 17 = 34*10^ = 3,400,000 < 200,000,000


 1. 첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)
 2. N개의 정수 배열 A[] 정수는 -2^31 <= A[x] <= 2^31 int
 3. M(1 ≤ M ≤ 100,000)
 4. M개의 정수 배열

 4번째 정수들이 2번째 정수 배열에 있는지 확인
 있으면 1, 없으면 0

 배열 정렬 : n log n : 100,000 * 17 (2^17 = 약 13만)
 찾기 : m log n : 100,000 * 17
 */
public class ExBinarySearch {

    @Test
    public void 백준_예제() {
        int n = 5;
        int[] nList = {4, 1, 5, 2, 3};
        int m = 5;
        int[] mList = {1, 3, 7, 9, 5};

        int[] findList = execute(n, nList, m, mList);
        Assertions.assertThat(findList).containsExactly(1, 1, 0, 0, 1);
        for (int b : findList) {
            System.out.printf("%d\n", b);
        }
    }


    @Test
    public void 예제3() {
        // 검색할 값이 모두 배열에 없는 경우
        int n = 5;
        int[] nList = {100, 200, 300, 400, 500};
        int m = 4;
        int[] mList = {10, 20, 30, 40};

        int[] findList = execute(n, nList, m, mList);
        Assertions.assertThat(findList).containsExactly(0, 0, 0, 0);
    }

    @Test
    public void 예제4() {
        // 모든 값이 동일한 경우
        int n = 6;
        int[] nList = {7, 7, 7, 7, 7, 7};
        int m = 3;
        int[] mList = {7, 8, 7};

        int[] findList = execute(n, nList, m, mList);
        Assertions.assertThat(findList).containsExactly(1, 0, 1);
    }

    @Test
    public void 예제5() {
        // 중복된 값들이 포함된 경우
        int n = 7;
        int[] nList = {10, 20, 20, 30, 40, 40, 50};
        int m = 4;
        int[] mList = {20, 40, 60, 10};

        int[] findList = execute(n, nList, m, mList);
        Assertions.assertThat(findList).containsExactly(1, 1, 0, 1);
    }

    @Test
    public void 예제6() {
        // N과 M이 모두 1인 경우
        int n = 1;
        int[] nList = {5};
        int m = 1;
        int[] mList = {5};

        int[] findList = execute(n, nList, m, mList);
        Assertions.assertThat(findList).containsExactly(1);
    }

    private int[] execute(int n, int[] numberArray, int m, int[] targetNumberArray) {
        int[] sortedArray = Arrays.stream(numberArray).sorted().toArray();

        int[] resultArray = new int[m];

        for (int i = 0; i < m; i++) {
            int targetNumber = targetNumberArray[i];
            resultArray[i] = binarySearch(sortedArray, 0, n - 1, targetNumber); // right는 numberArray 배열 갯수 -1
        }
        return resultArray;
    }

    private int binarySearch(int[] sortedArray, int left, int right, int targetNumber) {
        if(right < left) return 0;

        int middle = (left + right) / 2;
        if(sortedArray[middle] == targetNumber) return 1;
        else if(sortedArray[middle] > targetNumber)
            return binarySearch(sortedArray, left, middle - 1, targetNumber); // right 확인 stackoverflow
        else // if(sortedArray[middle] < targetNumber)
            return binarySearch(sortedArray, middle + 1, right, targetNumber); // left 확인 stackoverflow
    }
}
