package com.allenanker.other;

import java.util.Arrays;
import java.util.HashMap;

public class NiukeProblems {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 6, 7, 2, 2, 0, 1, 8, 2};
//        flagOfNetherlands(arr, 0, arr.length - 1, 1);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(maxGapInArray(arr));
//
//        int[][] matrix = {{1}, {2}};
//        spiralMatrix(matrix);
//        int[][] matrix1 = {{1, 2, 3}};
//        spiralMatrix(matrix1);
//        int[][] matrix2 = {{1, 2, 3}, {4, 5, 6}};
//        spiralMatrix(matrix2);
//        int[][] matrix3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        spiralMatrix(matrix3);
//
//        System.out.println("============== Rotate Matrix ==============");
//        rotateMatrix(matrix3);
//        for (int i = 0; i < matrix3.length; i++) {
//            System.out.println(Arrays.toString(matrix3[i]));
//        }
//
//        System.out.println("============== Zigzag Print Matrix ==============");
//        zigZagPrintMatrix(matrix3);
//
//        System.out.println("============== Is Palindrome(O(1) space complexity) ==============");
//        ListNode head1 = new ListNode(1);
//        head1.next = new ListNode(2);
//        head1.next.next = new ListNode(2);
//        head1.next.next.next = new ListNode(1);
//        ListNode head2 = new ListNode(1);
//        head2.next = new ListNode(2);
//        head2.next.next = new ListNode(3);
//        head2.next.next.next = new ListNode(2);
//        head2.next.next.next.next = new ListNode(1);
//        System.out.println(isPalindrome(head1));
//        printLinkedList(head1);
//        System.out.println(isPalindrome(head2));
//        printLinkedList(head2);
//        System.out.println(isPalindrome(generateListUtilN(4)));
//
//        System.out.println("============== Partition a Linked List By Pivot ==============");
//        partitionByPivot(head1, 1);
//        partitionByPivot(head2, 1);
//        printLinkedList(head1);
//        printLinkedList(head2);
//
//        System.out.println("============== Copy a Linked List With Random Pointers ==============");
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        node1.next = node2;
//        node2.next = node3;
//        node1.random = node3;
//        node2.random = node1;
//        node3.random = node1;
//        printLinkedList(copyLinkedListWithRand(node1));
//
//        System.out.println("============== Copy a Linked List With Random Pointers(Without Using HashMap) " +
//                "==============");
//        printLinkedList(copyLinkedListWithRand2(node1));
//        printLinkedList(copyLinkedListWithRand2(generateListUtilN(4)));
//
//        System.out.println("============== Find The Interaction Node ==============");
//        System.out.println(getIntersectionNode(head1, head1).val);
//        ListNode node4 = new ListNode(1);
//        ListNode node5 = new ListNode(2);
//        ListNode node6 = new ListNode(3);
//        ListNode node7 = new ListNode(4);
//        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;
//        node7.next = node5;
//        ListNode node8 = new ListNode(1);
//        node8.next = node6;
//        System.out.println(getIntersectionNode(node4, node8).val);

//        printAllStrSub("abc", 0, "");

        printAllPermutations("abc");
    }

    public static void printAllPermutations(String s) {
        if (s == null || s.trim().equals("")) {
            return;
        }

        char[] chars = s.toCharArray();
        printPermutationsHelper(chars, 0);
    }

    private static void printPermutationsHelper(char[] chars, int i) {
        // when i has reached the end, a string permutation is generated
        if (i == chars.length) {
            System.out.println(String.valueOf(chars));
        }

        for (int j = i; j < chars.length; j++) {
            swap(chars, i, j);
            printPermutationsHelper(chars, i + 1);
            // swap it back and swap the the curr char to the next possible position
            swap(chars, i, j);
        }
    }

    private static void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    public static void printAllSub(String s) {
        if (s == null || s.trim().equals("")) {
            return;
        }

        printAllStrSub(s, 0, "");
    }

    public static void printAllStrSub(String s, int i, String res) {
        if (i == s.length()) {
            System.out.println(res);
            return;
        }

        printAllStrSub(s, i + 1, res);
        printAllStrSub(s, i + 1, res + s.charAt(i));
    }

    public static ListNode getIntersectionNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        ListNode loop1 = getLoopStartNode(head1), loop2 = getLoopStartNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        } else if (loop1 == null || loop2 == null) {
            return null;
        } else {
            return bothLoop(head1, loop1, head2, loop2);
        }
    }

    /**
     * Return the loop start node if the linked list has a loop, or return null.
     *
     * @param head
     */
    private static ListNode getLoopStartNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        if (slow != fast) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    /**
     * Find the intersection node of two linked lists without loop.
     *
     * @param head1
     * @param head2
     * @return
     */
    private static ListNode noLoop(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        int len1 = 1, len2 = 1;
        ListNode dummy1 = head1, dummy2 = head2;
        while (dummy1.next != null) {
            dummy1 = dummy1.next;
            len1++;
        }
        while (dummy2.next != null) {
            dummy2 = dummy2.next;
            len2++;
        }

        // if the two lists do not have the same tail, then they don't inters
        if (dummy1 != dummy2) {
            return null;
        }

        ListNode curr1 = head1, curr2 = head2;
        // the list with larger length moves first
        if (len1 > len2) {
            for (int i = 0; i < len1 - len2; i++) {
                curr1 = curr1.next;
            }
        } else {
            for (int i = 0; i < len2 - len1; i++) {
                curr2 = curr2.next;
            }
        }
        while (curr1 != curr2) {
            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        return curr1;
    }

    /**
     * Find the intersection node of two linked lists that have loops.
     *
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    private static ListNode bothLoop(ListNode head1, ListNode loop1, ListNode head2, ListNode loop2) {
        ListNode curr1, curr2;
        if (loop1 == loop2) {
            int n = 0;
            curr1 = head1;
            curr2 = head2;
            while (curr1 != loop1) {
                curr1 = curr1.next;
                n++;
            }
            while (curr2 != loop2) {
                curr2 = curr2.next;
                n--;
            }

            curr1 = head1;
            curr2 = head2;
            if (n > 0) {
                while (n-- != 0) {
                    curr1 = curr1.next;
                }
            } else {
                while (n++ != 0) {
                    curr2 = curr2.next;
                }
            }

            while (curr1 != curr2) {
                curr1 = curr1.next;
                curr2 = curr2.next;
            }

            return curr1;
        } else {
            ListNode dummy = loop1.next;
            while (dummy != loop1) {
                // if loop1 next chain has loop2, then the two list has the same loop
                // but with different entry points of the loop
                if (dummy == loop2) {
                    return loop1;
                }
                dummy = dummy.next;
            }
            // if they don't have the same loop, there cannot be an intersection between them
            return null;
        }
    }

    /**
     * Deep copy a linked list, beside a next pointer, each node has a random pointer.
     * Without using HashMap.
     *
     * @param head
     * @return
     */
    public static ListNode copyLinkedListWithRand2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = head;
        ListNode next;
        // link the old nodes and the new nodes together
        while (dummy != null) {
            next = dummy.next;
            dummy.next = new ListNode(dummy.val);
            dummy.next.next = next;
            dummy = dummy.next.next;
        }
        // initialize the random pointers
        dummy = head;
        while (dummy != null) {
            dummy.next.random = dummy.random == null ? null : dummy.random.next;
            dummy = dummy.next.next;
        }
        // separate the linked list in to two linked list(original one and new one)
        ListNode newHead = head.next;
        dummy = head;
        ListNode newHeadDummy = newHead;
        while (dummy != null) {
            dummy.next = dummy.next.next;
            if (dummy.next != null) {
                newHeadDummy.next = dummy.next.next;
            }
            dummy = dummy.next;
            newHeadDummy = newHeadDummy.next;
        }

        return newHead;
    }

    /**
     * Deep copy a linked list, beside a next pointer, each node has a random pointer.
     *
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
        while (dummy != null) {
            copysMap.get(dummy).next = copysMap.get(dummy.next);
            copysMap.get(dummy).random = copysMap.get(dummy.random);
            dummy = dummy.next;
        }

        return copysMap.get(head);
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
