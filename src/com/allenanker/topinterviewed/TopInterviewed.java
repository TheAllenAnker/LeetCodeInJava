package com.allenanker.topinterviewed;

import java.util.LinkedHashMap;
import java.util.SortedMap;
import java.util.SortedSet;

public class TopInterviewed {
    public static void main(String[] args) {
//        System.out.println(sumOfIntegers(1, 2));
        System.out.println(firstUniqChar("loveleetcode"));
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while (true) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
            else if (fast == null || fast.next == null) return null;
        }

        ListNode dummy = head;
        while (dummy != fast) {
            dummy = dummy.next;
            fast = fast.next;
        }

        return fast;
    }

    public static int sumOfIntegers(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }

        return a;
    }

    /**
     * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
     * Note: You may assume the string contain only lowercase letters.
     *
     * @param s input string
     * @return first unique char's index
     */
    public static int firstUniqChar(String s) {
        LinkedHashMap<Character, Integer> chars = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (chars.containsKey(c)) {
                chars.put(c, chars.get(c) + 1);
            } else {
                chars.put(c, 1);
            }
        }
        char target = '*';
        for (char c : chars.keySet()) {
            if (chars.get(c) == 1) {
                target = c;
                break;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == target) {
                return i;
            }
        }

        return -1;
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}