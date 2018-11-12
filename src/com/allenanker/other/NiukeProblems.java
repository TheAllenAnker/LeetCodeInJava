package com.allenanker.other;

import java.util.Arrays;

public class NiukeProblems {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 6, 7, 2, 2, 0, 1, 8, 2};
        flagOfNetherlands(arr, 0, arr.length - 1, 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void flagOfNetherlands(int[] arr, int L, int R, int num) {
        int smaller = L - 1;
        int larger = R + 1;
        int curr = L;
        while (curr < larger) {
            if (arr[curr] < num) {
                swap(arr, curr++, ++smaller);
            } else if (arr[curr] > num) {
                swap(arr, curr, --larger);
            } else {
                curr++;
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
