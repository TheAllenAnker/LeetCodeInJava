package com.allenanker.fundamentals;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 5, 7, 1, 0, 9};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * The recursive quicksort process.
     *
     * @param arr
     * @param L
     * @param R
     */
    public static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            int[] bounds = partition(arr, L, R);
            quickSort(arr, L, bounds[0]);
            quickSort(arr, bounds[1], R);
        }
    }

    /**
     * This process partitions the arr into three parts and return the boundaries of the part
     * with the value that equals to the pivot.
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int[] partition(int[] arr, int L, int R) {
        // choose a pivot randomly first
        swap(arr, L + new Random().nextInt(R - L), R);
        int smaller = L - 1;
        int larger = R + 1;
        int pivot = arr[R];
        int curr = L;
        while (curr < larger) {
            if (arr[curr] < pivot) {
                swap(arr, curr++, ++smaller);
            } else if (arr[curr] > pivot) {
                swap(arr, curr, --larger);
            } else {
                curr++;
            }
        }

        return new int[]{smaller, larger};
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
