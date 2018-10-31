package com.allenanker.topinterviewed;

public class TopInterviewed {
    public static void main(String[] args) {

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
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}