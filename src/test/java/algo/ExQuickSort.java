package algo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExQuickSort {

    @Test
    public void test() {
        int n = 100;
        List<Integer> collect = IntStream.rangeClosed(1, n)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(collect);

        int[] list = collect.stream().mapToInt(Integer::intValue).toArray();

        quickSort(list, 0, list.length-1);

        Assertions.assertThat(list).containsExactly(IntStream.rangeClosed(1, n).toArray());
    }

    private void quickSort(int[] list, int left, int right) {
        if (left >= right) return;

        int partBasicIdx = partition(list, left, right);

        quickSort(list, left, partBasicIdx - 1);
        quickSort(list, partBasicIdx + 1, right);
    }

    private int partition(int[] list, int leftCur, int rightCur) {
        int pivotIdx = rightCur;
        int pivotNum = list[pivotIdx];

        while (leftCur < rightCur) {
            while (list[leftCur] < pivotNum) leftCur++;
            while (leftCur < rightCur && pivotNum <= list[rightCur]) rightCur--;
            swap(list, leftCur, rightCur);
        }
        list[pivotIdx] = list[rightCur];
        list[rightCur] = pivotNum;
        return rightCur;
    }

    private void swap(int[] list, int aIdx, int bIdx) {
        int temp = list[aIdx];
        list[aIdx] = list[bIdx];
        list[bIdx] = temp;
    }
}
