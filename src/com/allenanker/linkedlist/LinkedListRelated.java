package com.allenanker.linkedlist;

public class LinkedListRelated {
    public static void main(String[] args) {
        ListNode head1 = generateLinkedList();
        ListNode head2 = generateLinkedList();
        printLinkedList(reverseLinkedList1(head1));
        printLinkedList(reverseLinkedList2(head2));
        printLinkedList(mergeSortedLinkedList(generateLinkedList(), generateLinkedList()));
    }

    /**
     * Reverse a single-linked list iteratively.
     * @param head head node of the linked list
     * @return the head of the reversed linked list
     */
    public static ListNode reverseLinkedList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next;
        ListNode prev = null;
        while (true) {
            next = head.next;
            head.next = prev;
            prev = head;
            if (next == null) break;
            else head = next;
        }

        return head;
    }

    /**
     * Reverse a single-linked list recursively.
     * @param head head node of the linked list
     * @return the head of the reversed linked list
     */
    public static ListNode reverseLinkedList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // end condition: head is father node of the tail of the linked list
        ListNode currNode = reverseLinkedList2(head.next);
        head.next.next = head; // reverse the pointer direction
        head.next = null;
        return currNode;
    }

    /**
     * Search circle in linked list, return the circle's entry if exists, or return null.
     * A slow pointer and a fast pointer.
     * @param head the head of the linked list
     * @return circle entry or null
     */
    public static ListNode searchCircle(ListNode head) {
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

    public static ListNode mergeSortedLinkedList(ListNode head1, ListNode head2) {
        ListNode head = new ListNode(0);
        ListNode headCopy = head;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                head.next = new ListNode(head1.val);
                head1 = head1.next;
            } else {
                head.next = new ListNode(head2.val);
                head2 = head2.next;
            }
            head = head.next;
        }
        head.next = head1 != null ? head1 : head2;

        return headCopy.next;
    }

    private static ListNode generateLinkedList() {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);

        return head;
    }

    private static void printLinkedList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            head = head.next;
            if (head != null) System.out.print(" ");
        }
        System.out.println();
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}