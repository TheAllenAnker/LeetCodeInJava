package com.allenanker.linkedlist;

import java.util.Stack;

public class LinkedListRelated {
    public static void main(String[] args) throws IllegalAccessException {
//        ListNode head1 = generateLinkedList();
//        ListNode head2 = generateLinkedList();
//        printLinkedList(reverseLinkedList1(head1));
//        printLinkedList(reverseLinkedList2(head2));
//        printLinkedList(mergeSortedLinkedList(generateLinkedList(), generateLinkedList()));
//        printLinkedList(deleteKthToLastNode(generateLinkedList(), 4));
//        System.out.println(findMid(generateLinkedList()).val);
//        ListNode node1 = generateLinkedList();
//        node1.next.next.next.next = new ListNode(4);
//        printLinkedList(node1);
//        System.out.println(findMid(node1).val);
//        printLinkedList(removeNode(generateLinkedList(), 3));
//        printLinkedList(kInverse(generateLinkedList(), 3));
        printLinkedList(clearVal(generateLinkedList(), 3));
    }

    /**
     * Reverse a single-linked list iteratively.
     *
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
     *
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
     *
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

    /**
     * Delete the kth element to the last of the linked list.
     *
     * @param head the head of the linked list
     * @param k    the kth node to the last
     */
    public static ListNode deleteKthToLastNode(ListNode head, int k) throws IllegalAccessException {
        if (k <= 0) return head;

        ListNode p1 = head, p2 = head;
        for (int i = 0; i < k; i++) {
            if (p1 == null) throw new IllegalAccessException("K cannot be greater than the length of the " +
                    "provided linked list.");
            p1 = p1.next;
        }
        // return head.next if the target is the first element
        if (p1 == null) return head.next;

        // find the kth element to the last
        ListNode prev = null;
        while (p1 != null) {
            p1 = p1.next;
            prev = p2;
            p2 = p2.next;
        }

        // now p2 is the target to be deleted
        prev.next = p2.next;

        return head;
    }

    public static ListNode findMid(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * Starting from the head, reverse k nodes as a group, if left nodes is smaller than k. Do nothing to the left
     * nodes. For example, if k = 3, 1->2->3->4->5->6->7->8->null, than the result is 3->2->1->6->5->4->7->8->null.
     * 7->8 is not reversed because they cannot form a k-node group.
     *
     * @param head head of the linked list
     * @param k    k nodes as a group to be reversed
     * @return the new head
     */
    public static ListNode kInverse(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();
        ListNode newHead = new ListNode(0);
        ListNode dummy = newHead;
        while (head != null) {
            stack.push(head);
            head = head.next;
            if (stack.size() == k) {
                while (!stack.isEmpty()) {
                    dummy.next = stack.pop();
                    dummy.next.next = null;
                    dummy = dummy.next;
                }
            }
        }

        while (!stack.isEmpty()) {
            dummy.next = stack.remove(0);
            dummy = dummy.next;
        }

        return newHead.next;
    }

    /**
     * Remove all nodes with the given val as the value in the linked list.
     * @param head head of the linked list
     * @param val the given val
     * @return the new head of linked list
     */
    public static ListNode clearVal(ListNode head, int val) {
        ListNode newHead = new ListNode(0);
        ListNode dummy = newHead;
        while (head != null) {
            if (head.val != val) {
                dummy.next = head;
                dummy = dummy.next;
            }
            head = head.next;
        }

        return newHead.next;
    }

    private static ListNode generateLinkedList() {
        ListNode head = new ListNode(0);
        ListNode dummy = head;
        for (int i = 0; i < 8; i++) {
            dummy.next = new ListNode(i);
            dummy = dummy.next;
        }

        return head.next;
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