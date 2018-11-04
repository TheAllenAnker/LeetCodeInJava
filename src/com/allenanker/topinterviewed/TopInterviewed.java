package com.allenanker.topinterviewed;

import java.util.*;

public class TopInterviewed {
    public static void main(String[] args) {
//        System.out.println(sumOfIntegers(1, 2));
//        System.out.println(firstUniqChar("loveleetcode"));
        TopInterviewed topInterviewed = new TopInterviewed();
        System.out.println(topInterviewed.lengthOfLongestSubStr("aabbb", 3));
        System.out.println(topInterviewed.longestSubstring2("aabbb", 3));
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

    /**
     * Find the length of the longest substring T of a given string (consists of lowercase letters only)
     * such that every character in T appears no less than k times.
     *
     * @param s the given string
     * @param k no less than k times
     * @return length of substring
     */
    public int lengthOfLongestSubStr(String s, int k) {
        // Analysis: each separate substring cannot be next to each other
        if (k == 1 && s != null) return s.length();

        int res = 0;
        int[] records = new int[k];
        HashMap<Character, Integer> counts = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (sumArrayIndex(records) > s.length() - i) {
                counts.clear();
                records = new int[k];
                counts.put(s.charAt(i), 1);
                records[k - 1] += 1;
                continue;
            }
            char c = s.charAt(i);
            if (counts.containsKey(c)) {
                int count = counts.get(c);
                counts.put(c, count + 1);
                if (count < k) records[k - count] -= 1;
                if (count < k) records[k - count - 1] += 1;
            } else {
                counts.put(c, 1);
                records[k - 1] += 1;
            }

            boolean flag = true;
            if (records[0] != counts.size()) flag = false;
            int length = sumMapIndex(counts);
            if (flag && res < length) {
                res = length;
            }
        }

        return res;
    }

    public int longestSubstring2(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = new char[26];
        // record the frequency of each character
        for (int i = 0; i < s.length(); i += 1) chars[s.charAt(i) - 'a'] += 1;
        boolean flag = true;
        for (int i = 0; i < chars.length; i += 1) {
            if (chars[i] < k && chars[i] > 0) flag = false;
        }
        // return the length of string if this string is a valid string
        if (flag == true) return s.length();
        int result = 0;
        int start = 0, cur = 0;
        // otherwise we use all the infrequent elements as splits
        while (cur < s.length()) {
            if (chars[s.charAt(cur) - 'a'] < k) {
                result = Math.max(result, longestSubstring2(s.substring(start, cur), k));
                start = cur + 1;
            }
            cur++;
        }
        result = Math.max(result, longestSubstring2(s.substring(start), k));
        return result;
    }

    private static int sumArrayIndex(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += i * a[i];
        }

        return sum;
    }

    private static int sumMapIndex(Map<Character, Integer> map) {
        Set<Character> keySet = map.keySet();
        int sum = 0;
        for (char c : keySet) {
            sum += map.get(c);
        }

        return sum;
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}