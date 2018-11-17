package com.allenanker.other;

import java.util.Arrays;
import java.util.HashMap;

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

        System.out.println("============== Zigzag Print Matrix ==============");
        zigZagPrintMatrix(matrix3);

        System.out.println("============== Is Palindrome(O(1) space complexity) ==============");
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(1);
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(2);
        head2.next.next.next.next = new ListNode(1);
        System.out.println(isPalindrome(head1));
        printLinkedList(head1);
        System.out.println(isPalindrome(head2));
        printLinkedList(head2);
        System.out.println(isPalindrome(generateListUtilN(4)));

        System.out.println("============== Partition a Linked List By Pivot ==============");
        partitionByPivot(head1, 1);
        partitionByPivot(head2, 1);
        printLinkedList(head1);
        printLinkedList(head2);

        System.out.println("============== Copy a Linked List With Random Pointers ==============");
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node1.random = node3;
        node2.random = node1;
        node3.random = node1;
        printLinkedList(copyLinkedListWithRand(node1));
    }

    /**
     * Deep copy a linked list, beside a next pointer, each node has a random pointer.
     * @param head
     */
    public static ListNode copyLinkedListWithRand(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        HashMap<ListNode, ListNode> copysMap = new HashMap<>();
        ListNode dummy = head;
        while (dummy != null) {
            ListNode newCurr = new ListNode(dummy.val);
            copysMap.put(dummy, newCurr);
            dummy = dummy.next;
        }

        dummy = head;
        ListNode res = new ListNode();
        ListNode resCopy = res;
        while (dummy != null) {
            resCopy.next = copysMap.get(dummy);
            resCopy = resCopy.next;
            resCopy.random = copysMap.get(dummy.random);
            dummy = dummy.next;
        }

        return res.next;
    }

    /**
     * Partition a linked list into smaller, equal, and larger parts respectively.
     * O(n) time complexity, O(1) space complexity. Stable
     *
     * @param head
     * @param pivot
     */
    public static void partitionByPivot(ListNode head, int pivot) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode dummy = head, smallerHead = new ListNode(), equalHead = new ListNode(), biggerHead = new ListNode();
        ListNode smaller = smallerHead, equal = equalHead, bigger = biggerHead;
        while (dummy != null) {
            ListNode next = dummy.next;
            if (dummy.val > pivot) {
                bigger.next = dummy;
                bigger.next.next = null;
                bigger = bigger.next;
            } else if (dummy.val == pivot) {
                equal.next = dummy;
                equal.next.next = null;
                equal = equal.next;
            } else {
                smaller.next = dummy;
                smaller.next.next = null;
                smaller = smaller.next;
            }
            dummy = next;
        }

        if (smallerHead.next != null) {
            head = smallerHead.next;
            equal.next = equalHead.next != null && biggerHead.next == null ? bigger.next : null;
            smaller.next = equalHead.next == null ? biggerHead.next : equalHead.next;
        } else if (equalHead.next != null) {
            head = equalHead.next;
            equal.next = biggerHead.next;
        } else {
            head = biggerHead.next;
        }
    }

    /**
     * With O(1) space complexity(Reverse the right half of the linked list, determine if it is a palindrome or not,
     * then reverse it back).
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        boolean res = true;

        // find the mid of the linked list
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            if (fast.next.next != null) slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the right half of the linked list(starting from slow)
        // the mid and the head of the right reversed half must have been stored after reversing the right half
        ListNode curr = slow, prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // the prev is the head of the reversed right half
        // store the prev pointer for reversing the right half back later
        ListNode dummy = head, rightHead = prev;
        while (dummy != null && prev != null) {
            if (dummy.val != prev.val) {
                res = false;
                break;
            }
            dummy = dummy.next;
            prev = prev.next;
        }

        // reverse the right half back
        curr = rightHead;
        prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return res;
    }

    private static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print("->");
            }
            head = head.next;
        }
        System.out.println();
    }

    private static ListNode generateListUtilN(int n) {
        if (n < 1) return null;

        ListNode head = new ListNode();
        ListNode dummy = head;
        for (int i = 0; i < n; i++) {
            dummy.next = new ListNode();
            dummy.next.val = i + 1;
            dummy = dummy.next;
        }

        return head.next;
    }

    public static void zigZagPrintMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        int r = matrix.length, c = matrix[0].length;
        int tR = 0, tC = 0, dR = 0, dC = 0;
        boolean up = true;
        while (tR <= dR) {
            if (up) {
                int i = dR, j = dC;
                while (i >= tR) {
                    System.out.print(matrix[i--][j++]);
                }
                System.out.println();
            } else {
                int i = tR, j = tC;
                while (i <= dR) {
                    System.out.print(matrix[i++][j--]);
                }
                System.out.println();
            }
            up = !up;
            if (tC == c - 1) {
                tR++;
            } else {
                tC++;
            }
            if (dR == r - 1) {
                dC++;
            } else {
                dR++;
            }
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


class ListNode {
    int val;
    ListNode next;
    ListNode random;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }
}
