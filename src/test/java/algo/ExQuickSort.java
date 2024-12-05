package algo;

import org.junit.jupiter.api.Test;

public class ExQuickSort {
    static int[] nList;

    @Test
    public void test() {
        nList = new int[]{4, 1, 5, 2, 3};

        // 외우기
        quickSort(nList, 0, nList.length-1);

        for (int i : nList) {
            System.out.print(i+" ");
        }
    }

    private void quickSort(int[] list, int left, int right) {
        if(left >= right) return;

        int part = partition(list, left, right);

        quickSort(list, left, part - 1);
        quickSort(list, part + 1, right);
    }

    private int partition(int[] list, int left, int right) {
        int mid = (left + right) / 2;
        swap(list, mid, right); // pivot right 선택

        int pivot = list[right];

        int lCur = left;
        int rCur = right;
        while (lCur < rCur) {
            while(pivot > list[lCur]) lCur++;
            while(lCur<rCur && pivot <= list[rCur]) rCur--; // pivot equal 조건은 right cursor랑
            swap(list, lCur, rCur);
        }
        list[right] = list[rCur]; // right cursor랑 right랑 swap
        list[rCur] = pivot;
        return rCur;
    }

    private void swap(int[] list, int aPoint, int bPoint) {
        int temp = list[aPoint];
        list[aPoint] = list[bPoint];
        list[bPoint] = temp;
    }
}
