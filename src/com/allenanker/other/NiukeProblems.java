package com.allenanker.other;

import java.util.Arrays;

public class NiukeProblems {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 6, 7, 2, 2, 0, 1, 8, 2};
//        flagOfNetherlands(arr, 0, arr.length - 1, 1);
//        System.out.println(Arrays.toString(arr));
        System.out.println(maxGapInArray(arr));
    }

    /**
     * Return the max gap between two neighbors if the arr is sorted.
     *
     * @param arr the array
     * @return largest gap
     */
    public static int maxGapInArray(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            max = max > arr[i] ? max : arr[i];
            min = min < arr[i] ? min : arr[i];
        }

        if (max == min) {
            return 0;
        }
        boolean[] hasNum = new boolean[len + 1];
        int[] maxes = new int[len + 1];
        int[] mins = new int[len + 1];
        for (int i = 0; i < len; i++) {
            int num = arr[i];
            int bucket = bucket(num, len, max, min);
            maxes[bucket] = hasNum[bucket] ? Math.max(maxes[bucket], num) : num;
            mins[bucket] = hasNum[bucket] ? Math.min(mins[bucket], num) : num;
            hasNum[bucket] = true;
        }

        int maxGap = Integer.MIN_VALUE;
        // the first bucket must has stored the min
        int lastMax = maxes[0];
        for (int i = 1; i < len + 1; i++) {
            if (hasNum[i]) {
                maxGap = Math.max(maxGap, mins[i] - lastMax);
                lastMax = maxes[i];
            }
        }

        return maxGap;
    }

    private static int bucket(int num, int len, int max, int min) {
        return (((num - min) * len) / (max - min));
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
