package com.allenanker.topinterviewed;

import java.util.*;

public class TopInterviewed2 {
    public static void main(String[] args) {
        printLinkedList(generateLinkedList(7));
        ListNode head1 = generateLinkedList(5);
        reorderList(head1);
        printLinkedList(head1);
    }

    /**
     * Generate and return a linked list: 1->2->3->...->size
     *
     * @param size size of the linked list
     * @return the head of the linked list
     */
    private static ListNode generateLinkedList(int size) {
        ListNode head = new ListNode(0);
        ListNode dummy = head;
        for (int i = 1; i < size + 1; i++) {
            dummy.next = new ListNode(i);
            dummy = dummy.next;
        }

        return head.next;
    }

    /**
     * Print the given linked list in this pattern: n1->n2->n3->...->
     * @param head head of given linked list
     */
    private static void printLinkedList(ListNode head) {
        while (true) {
            System.out.print(head.val);
            head = head.next;
            if (head == null) {
                break;
            }
            System.out.print("->");
        }
        System.out.println();
    }

    /**
     * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
     * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
     * <p>
     * You may not modify the values in the list's nodes, only nodes itself may be changed.
     * <p>
     * Example 1:
     * <p>
     * Given 1->2->3->4, reorder it to 1->4->2->3.
     * Example 2:
     * <p>
     * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
     *
     * @param head
     */
    public static void reorderList(ListNode head) {
        // find the mid of the linked list first
        if (head == null) return;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // nodesAfterMid stores the nodes we need to insert into the first part of the linked list
        Stack<ListNode> nodesAfterMid = new Stack<>();
        ListNode start = slow.next;
        while (start != null) {
            nodesAfterMid.push(start);
            start = start.next;
        }
        slow.next = null;

        ListNode dummy = head;
        while (!nodesAfterMid.isEmpty()) {
            ListNode oldNext = dummy.next;
            ListNode newNext = nodesAfterMid.pop();
            newNext.next = null;
            dummy.next = newNext;
            newNext.next = oldNext;
            dummy = dummy.next.next;
        }
    }

    /**
     * Given a binary tree, return the preorder traversal of its nodes' values.
     *
     * Example:
     *
     * Input: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * Output: [1,2,3]
     * Follow up: Recursive solution is trivial, could you do it iteratively?
     *
     * @param root root of given binary tree
     * @return pre-order traversal list
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return null;
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new PriorityQueue<>();
        queue.add(root);
        result.add(root.val);

        TreeNode curr = null;
        for (int i = 0; i < queue.size(); i++) {
            curr = queue.poll();
            if (curr.left != null) result.add(root.left.val);
            if (curr.right != null) result.add(root.right.val);
        }

        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
