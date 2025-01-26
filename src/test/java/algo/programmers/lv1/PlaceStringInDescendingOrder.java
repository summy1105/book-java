package algo.programmers.lv1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// https://school.programmers.co.kr/learn/courses/30/lessons/12917 lv1
// 2:13 -> 2:34
public class PlaceStringInDescendingOrder {
    public String solution(String s) {
        char[] chars = s.toCharArray();
        quickSort(chars, 0, chars.length-1);
        return String.valueOf(chars);
    }

    void quickSort(char[] arr, int left, int right) {
        if(left >= right) return;
        int partition = partition(arr, left, right);
        quickSort(arr, left, partition-1);
        quickSort(arr, partition+1, right);
    }

    int partition(char[] arr, int left, int right) {
        int pivotIdx = right;
        char pivot = arr[pivotIdx];

        while (left < right) {
            while (arr[left] > pivot) left++;
            while (arr[right] <= pivot && left<right) right--;
            swap(arr, left, right);
        }
        arr[pivotIdx] = arr[right];
        arr[right] = pivot;
        return right;
    }

    private void swap(char[] arr, int left, int right) {
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    @Test
    public void ex1() {
        String result = solution("Zbcdefg");
        Assertions.assertThat(result).isEqualTo("gfedcbZ");
    }
}
