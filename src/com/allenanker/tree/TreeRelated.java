package com.allenanker.tree;

import java.util.Stack;

public class TreeRelated {
    public static void main(String[] args) {
        preOrderTraversal(generateSimpleTree());
        inOrderTraversal(generateSimpleTree());
    }

    /**
     * Traversal a binary tree in in-order. Not recursively.
     *
     * @param root root of the binary tree
     */
    public static void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println("In-order Traversal:");
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            // push all the left nodes into the stack first
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                // reach the end of the left subtree, the parent of this end should be pop out
                // and the right subtree of this parent should be handled in the above way
                curr = stack.pop();
                System.out.print(curr.val + "->");
                curr = curr.right;
            }
        }
        System.out.print("end\n");
    }

    /**
     * Traversal a binary tree in pre-order. Not recursively.
     *
     * @param root root of the binary tree
     */
    public static void preOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        System.out.println("Pre-order Traversal:");
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            System.out.print(curr.val + "->");
            // push right node first because it should be popped out first
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
        System.out.print("end\n");
    }

    private static TreeNode generateSimpleTree() {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        return head;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }
}
