package com.allenanker.fundamentals;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 5, 7, 1, 0, 9};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // build a max heap(which is also a complete binary tree)
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        // build the new array using the heap
        int heapSize = arr.length;
        while (heapSize > 0) {
            // the last max number(the root of the heap) has been taken out and replaced with the tail of the heap
            // and the old root is at the right place(the tail)
            swap(arr, 0, heapSize - 1);
            // so we need to adjust the heap(make it a heap again)
            heapify(arr, 0, --heapSize);
        }
    }

    private static void heapInsert(int[] arr, int index) {
        while (index > 0 && arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[index]
                    ? left + 1
                    : index;
            largest = arr[left] > arr[largest] ? left : largest;
            // break if the tree is heap now
            if (index == largest) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = 2 * index + 1;
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
