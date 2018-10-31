package com.allenanker.linkedlist;

public class LinkedListRelated {
    public static void main(String[] args) {
        ListNode head1 = generateLinkedList();
        ListNode head2 = generateLinkedList();
        printLinkedList(reverseLinkedList1(head1));
        printLinkedList(reverseLinkedList2(head2));
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
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}