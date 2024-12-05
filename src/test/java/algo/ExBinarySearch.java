package algo;

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


 */
public class ExBinarySearch {

    static int[] nList;
    static int[] targetList;

    @Test
    public void test() {
        nList = new int[]{4, 1, 5, 2, 3};
        targetList = new int[]{1, 3, 7, 9, 5};

        nList = Arrays.stream(nList).sorted().toArray();

        for (int m : targetList) {
            boolean isFind = binarySearch(nList, 0, nList.length - 1, m);
            System.out.println(isFind ? "1" : "0");
        }
    }

    private boolean binarySearch(int[] sortedList, int left, int right, int target) {
        if(left > right) return false;
        int mid = (left + right) / 2;

        if(sortedList[mid] == target) return true;
        else if(target < sortedList[mid])
            return binarySearch(sortedList, left, mid - 1, target);
        else
            return binarySearch(sortedList, mid + 1, right, target);
    }

}
