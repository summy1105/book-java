package algo;

/**
 Two Pointer 개념
 - 각 원소마다 모든 값을 순회해야 할 때, o(n^2)
 - 연속하다는 특성을 이용해서 처리, o(n)
 - 두개의 포인터(커서)가 움직이면서 계산
 - 처음부터 생각하기 어려움, 쉬운 방법부터 생각

 tip
 - 연속하다는 특징을 활용할 수 있는지 확인
 - 투 포인터내 계산하는 값의 최대값 확인 필수
 - 문제 종류
    1. 둘다 왼쪽에서 시작
    2. 왼쪽 시작, 오른쪽 시작
    3. 배열이 2개(포인터별 배열)
 - 일반 : o(n)이지만, 정렬 후 투포인터 문제 : o(n log n + n)
 */

// 백준 https://www.acmicpc.net/problem/2559

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 - 처음 아이디어 : for문으로 각 숫자의 위치에서 이후 k개의 수를 더함 -> 이때마다 최대값으로 갱신
 - 처음 시간 복잡도 : o(nk)
    > n의 최대수 100,000 & K의 최대수 99,999 -> 9,999,900,000 > 2억

 - 수정 아이디어
    > 처음에 k개의 값을 구함
    > for문 : 다음 인덱스의 값을 더하고, 앞의 값을 뺌
    > 이때 최대값을 갱신
 - 시간 복잡도 : o(n) X2
 - 자료구조
    > 정수배열 : int[]
    > 합한 수
        > k는 10만개 * 각수의 최대값 100 : 10^7 : 1억 -> int 가능(약 -21 ~ 21억)
    > cursor : int
 */

public class ExTwoPointer {
    static int temperatureListLength;
    static int continuousDateNumber;
    static int[] temperatureList;

    @Test
    public void test() {
//        temperatureListLength = 10;
//        continuousDateNumber = 2;
//        temperatureList = new int[]{3, -2, -4, -9, 0, 3, 7, 13, 8, -3};
//        int expected = 21;

        temperatureListLength = 10;
        continuousDateNumber = 5;
        temperatureList = new int[]{3, -2, -4, -9, 0, 3, 7, 13, 8, -3};
        int expected = 31;

        int answer = call();

        Assertions.assertEquals(expected, answer);
    }

    private int call() {

        int each = 0;
        for (int i = 0; i < continuousDateNumber; i++) {
            each += temperatureList[i];
        }
        int firstCursor = 0;
        int maxValue = each;
        for (int lastCursor = continuousDateNumber;
             lastCursor < temperatureListLength; lastCursor++, firstCursor++) {
            each -= temperatureList[firstCursor];
            each += temperatureList[lastCursor];

            maxValue = Math.max(maxValue, each);
        }
        return maxValue;
    }
}
