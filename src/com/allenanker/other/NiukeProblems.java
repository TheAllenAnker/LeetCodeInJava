package com.allenanker.other;

import java.util.Arrays;

public class NiukeProblems {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 6, 7, 2, 2, 0, 1, 8, 2};
//        flagOfNetherlands(arr, 0, arr.length - 1, 1);
//        System.out.println(Arrays.toString(arr));
        System.out.println(maxGapInArray(arr));

        int[][] matrix = {{1}, {2}};
        spiralMatrix(matrix);
        int[][] matrix1 = {{1, 2, 3}};
        spiralMatrix(matrix1);
        int[][] matrix2 = {{1, 2, 3}, {4, 5, 6}};
        spiralMatrix(matrix2);
        int[][] matrix3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        spiralMatrix(matrix3);

        System.out.println("============== Rotate Matrix ==============");
        rotateMatrix(matrix3);
        for (int i = 0; i < matrix3.length; i++) {
            System.out.println(Arrays.toString(matrix3[i]));
        }
    }

    /**
     * Rotate a matrix
     *
     * @param matrix the give matrix must have the same number of rows and cols
     */
    public static void rotateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix.length != matrix[0].length) {
            return;
        }

        int x1 = 0, y1 = 0, x2 = matrix.length - 1, y2 = matrix.length - 1;
        while (x1 < x2) {
            rotateMatrixEdges(matrix, x1++, y1++, x2--, y2--);
        }
    }

    /**
     * Rotate the matrix's four out most edges
     *
     * @param matrix
     * @param tR     top row
     * @param tC     top column
     * @param dR     down row
     * @param dC     down column
     */
    private static void rotateMatrixEdges(int[][] matrix, int tR, int tC, int dR, int dC) {
        int times = dR - tR;
        int tmp;
        for (int i = 0; i < times; i++) {
            tmp = matrix[tR][tC + i];
            matrix[tR][tC + i] = matrix[dR - i][tC];
            matrix[dR - i][tC] = matrix[dR][dC - i];
            matrix[dR][dC - i] = matrix[i][dC];
            matrix[i][dC] = tmp;
        }
    }

    /**
     * Iterate through the matrix in spiral order
     *
     * @param matrix
     * @return
     */
    public static int[] spiralMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return null;
        }

        int r = matrix.length;
        int c = matrix[0].length;
        int[] res = new int[r * c];

        int x1 = 0, y1 = 0, x2 = matrix.length - 1, y2 = matrix[0].length - 1;
        while (x1 <= x2) {
            System.out.println(Arrays.toString(edgeCircle(matrix, x1++, y1++, x2--, y2--)));
        }

        return res;
    }

    /**
     * (x1, y1), (x2, y2) are the two corners of the matrix.
     * Iterate the four edges of the matrix bases no this two points.
     * the input matrix must not be null and the two points are right
     *
     * @param matrix
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    private static int[] edgeCircle(int[][] matrix, int x1, int y1, int x2, int y2) {
        int r = x2 - x1 + 1, c = y2 - y1 + 1;
        int size = (r == c && x1 != x2) ? r * c - 1 : r * c;
        int[] res = new int[size];

        if (r == 1) {
            int index = 0;
            for (int i = y1; i <= y2; i++) {
                res[index++] = matrix[x1][i];
            }
        } else if (c == 1) {
            int index = 0;
            for (int i = x1; i <= x2; i++) {
                res[index++] = matrix[i][y1];
            }
        } else {
            int index = 0;
            for (int i = y1; i <= y2; i++) {
                res[index++] = matrix[x1][i];
            }
            for (int i = x1 + 1; i <= x2; i++) {
                res[index++] = matrix[i][y2];
            }
            for (int i = y2 - 1; i >= y1; i--) {
                res[index++] = matrix[x2][i];
            }
            for (int i = x2 - 1; i > x1; i--) {
                res[index++] = matrix[i][y1];
            }
        }

        return res;
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
