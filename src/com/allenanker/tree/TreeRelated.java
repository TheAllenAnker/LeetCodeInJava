package com.allenanker.tree;

import java.util.Stack;

public class TreeRelated {
    public static void main(String[] args) {
        preOrderTraversal(generateSimpleTree());
    }

    /**
     * Traversal a binary tree in pre-order. Not recursively.
     * @param root root of binary tree
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
