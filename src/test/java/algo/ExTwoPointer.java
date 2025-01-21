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


/**
    2<= N <= 100,000 전체 날짜수
    1 < K < N  합할 날짜수

    -100 <=  온도 <= 100

 시간 복잡도 o(n)
 n, k: int
 시간합 최대 : 100 * 100,000 = 100,000,000 = 1억 < 20억 int

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

 백준 https://www.acmicpc.net/problem/2559
 */

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExTwoPointer {

    @Test
    public void 백준_예제1() {
        int temperatureListLength = 10;
        int continuousDateNumber = 2;
        int[] temperatureList = new int[]{3, -2, -4, -9, 0, 3, 7, 13, 8, -3};

        int answer = execute(temperatureListLength, continuousDateNumber, temperatureList);
        Assertions.assertThat(answer).isEqualTo(21);
        System.out.println(answer);
    }

    private int execute(int temperatureListLength, int continuousDateNumber, int[] temperatureList) {
        int aPoint = 0;
        int bPoint = 0;
        int continuousSumTemperature = 0;

        for ( ; bPoint < continuousDateNumber ; bPoint++) {
            continuousSumTemperature += temperatureList[bPoint];
        }

        int maxSum = continuousSumTemperature;

        for ( ; bPoint < temperatureListLength; bPoint++, aPoint++) {
            continuousSumTemperature -= temperatureList[aPoint];
            continuousSumTemperature += temperatureList[bPoint];
            maxSum = Math.max(maxSum, continuousSumTemperature);
        }

        return maxSum;
    }

    @Test
    public void 백준_예제2() {
        int temperatureListLength = 10;
        int continuousDateNumber = 5;
        int[] temperatureList = new int[]{3, -2, -4, -9, 0, 3, 7, 13, 8, -3};

        int answer = execute(temperatureListLength, continuousDateNumber, temperatureList);
        Assertions.assertThat(answer).isEqualTo(31);
    }
    @Test
    public void 예제3() {
        // 모든 온도가 동일한 경우
        int temperatureListLength = 10;
        int continuousDateNumber = 5;
        int[] temperatureList = new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5};

        int answer = execute(temperatureListLength, continuousDateNumber, temperatureList);
        Assertions.assertThat(answer).isEqualTo(25); // 연속 5일의 합: [5, 5, 5, 5, 5]
    }

    @Test
    public void 예제4() {
        // 모든 온도가 음수인 경우
        int temperatureListLength = 6;
        int continuousDateNumber = 3;
        int[] temperatureList = new int[]{-10, -20, -30, -40, -50, -60};

        int answer = execute(temperatureListLength, continuousDateNumber, temperatureList);
        Assertions.assertThat(answer).isEqualTo(-60); // 연속 3일의 최대 합: [-10, -20, -30]
    }

    @Test
    public void 예제5() {
        // 온도가 증가하는 경우
        int temperatureListLength = 8;
        int continuousDateNumber = 4;
        int[] temperatureList = new int[]{-5, -3, -1, 1, 3, 5, 7, 9};

        int answer = execute(temperatureListLength, continuousDateNumber, temperatureList);
        Assertions.assertThat(answer).isEqualTo(24); // 연속 4일의 최대 합: [3, 5, 7, 9]
    }

    @Test
    public void 예제6() {
        // 연속 날짜 수가 전체 길이와 같은 경우
        int temperatureListLength = 5;
        int continuousDateNumber = 5;
        int[] temperatureList = new int[]{1, 2, 3, 4, 5};

        int answer = execute(temperatureListLength, continuousDateNumber, temperatureList);
        Assertions.assertThat(answer).isEqualTo(15); // 전체 합: [1, 2, 3, 4, 5]
    }

    @Test
    public void 예제7() {
        // 배열의 길이가 2이고 연속 날짜 수가 1인 경우
        int temperatureListLength = 2;
        int continuousDateNumber = 1;
        int[] temperatureList = new int[]{100, -100};

        int answer = execute(temperatureListLength, continuousDateNumber, temperatureList);
        Assertions.assertThat(answer).isEqualTo(100); // 최대값: [100]
    }

    @Test
    public void 예제8() {
        // 모든 값이 0인 경우
        int temperatureListLength = 7;
        int continuousDateNumber = 3;
        int[] temperatureList = new int[]{0, 0, 0, 0, 0, 0, 0};

        int answer = execute(temperatureListLength, continuousDateNumber, temperatureList);
        Assertions.assertThat(answer).isEqualTo(0); // 합: [0, 0, 0]
    }

}
