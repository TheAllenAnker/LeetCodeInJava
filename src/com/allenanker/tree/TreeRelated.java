package com.allenanker.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeRelated {
    public static void main(String[] args) {
//        preOrderTraversal(generateSimpleTree());
//        inOrderTraversal(generateSimpleTree());
//        postOrderTraversal(generateSimpleTree());
//        TreeNode2 treeNode2 = generateSimpleTree2();
//        System.out.println(getNextTreenode2(treeNode2.right.left).val);
//        TreeNode root = reconByPreStr(serializeBTInPre(generateSimpleTree()));
//        System.out.println();
//        System.out.println(isBalancedBT(generateSimpleTree()));

//        System.out.println(isBST(generateSimpleTree()));

//        System.out.println(isCBT(generateSimpleTree()));

        System.out.println(countNodesInCBT2(generateSimpleTree()));
    }

    /**
     * Calculate the number of nodes in a CBT.
     * Time complexity must be smaller than O(n)
     *
     * @param root
     * @return
     */
    public static int countNodesInCBT2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int res = 1;

        int leftD = 1;
        TreeNode curr = root.left;
        while (curr != null) {
            leftD++;
            curr = curr.left;
        }
        // rightLD stores the left most nodes depth in the right subtree
        int rightLD = 1;
        curr = root.right;
        while (curr != null) {
            rightLD++;
            curr = curr.left;
        }

        if (leftD == rightLD) {
            res += (1 << leftD - 1) - 1 + countNodesInCBT2(root.right);
        } else {
            res += (1 << rightLD - 1) - 1 + countNodesInCBT2(root.left);
        }

        return res;
    }

    /**
     * Calculate the number of nodes in a CBT.
     *
     * @param root
     * @return
     */
    public static int countNodesInCBT(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int count = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode curr;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.left == null) {
                return count;
            } else {
                queue.offer(curr.left);
                count++;
                if (curr.right != null) {
                    queue.offer(curr.right);
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Determine if a binary tree is CBT or not.
     * Traverse the tree by level, if a node with right child but without left child, not a CBT.
     * Has left, but does not have right, or doesn't have left and right, then all the nodes remain must
     * be left nodes.
     *
     * @param root
     * @return
     */
    public static boolean isCBT(TreeNode root) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode curr, l, r;
        boolean remainAllLeafs = false;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            l = curr.left;
            r = curr.right;
            // if (r != null and l == null) or ((allLeafs) and (l != null or r != null)(this means some nodes is not
            // leafs)))
            if ((r != null && l == null) || (remainAllLeafs && l != null)) {
                return false;
            }
            if (l != null) {
                queue.offer(l);
            }
            if (r != null) {
                queue.offer(r);
            } else {
                // if r is null, the remaining nodes cannot have any child
                remainAllLeafs = true;
            }
        }

        return true;
    }

    /**
     * Determine if a binary tree is BST or not.
     * If the in-order traversal of the binary tree is in ascending order. Then it's BST.
     *
     * @param root
     * @return
     */
    public static boolean isBST(TreeNode root) {
        if (root == null) {
            return false;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        int prevNum = Integer.MIN_VALUE;
        while (!stack.isEmpty() || curr != null) {
            // push all the left nodes into the stack first
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                // reach the end of the left subtree, the parent of this end should be pop out
                // and the right subtree of this parent should be handled in the above way
                curr = stack.pop();
                // if it is not in ascending order, the it's not a BST
                if (curr.val < prevNum) {
                    return false;
                } else {
                    prevNum = curr.val;
                }
                curr = curr.right;
            }
        }

        return true;
    }

    /**
     * Determine if a given binary tree is balanced or not.
     *
     * @param root
     * @return
     */
    public static boolean isBalancedBT(TreeNode root) {
        return getDepth(root) >= 0;
    }

    private static int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftH = getDepth(node.left);
        if (leftH < 0) {
            return -1;
        }
        int rightH = getDepth(node.right);
        if (rightH < 0) {
            return -1;
        }

        if (Math.abs(leftH - rightH) > 1) {
            return -1;
        }

        return Math.max(leftH, rightH) + 1;
    }

    /**
     * Deserialize a binary tree from a pre-order traversal string.
     *
     * @param preString
     * @return
     */
    public static TreeNode reconByPreStr(String preString) {
        if (preString == null) {
            return null;
        }

        String[] values = preString.split("!");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            queue.offer(values[i]);
        }

        return reconByPreOrder(queue);
    }

    private static TreeNode reconByPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.valueOf(value));
        head.left = reconByPreOrder(queue);
        head.right = reconByPreOrder(queue);

        return head;
    }

    /**
     * Serialize a binary tree in pre-order.
     *
     * @param root
     * @return
     */
    public static String serializeBTInPre(TreeNode root) {
        if (root == null) {
            return "#!";
        }
        String res = root.val + "!";
        res += serializeBTInPre(root.left);
        res += serializeBTInPre(root.right);

        return res;
    }

    /**
     * Get "the next treenode" of the given node. "The next treenode" is defined as the next node after the given node
     * in in-order traversal. (the treenode has an extra parent pointer)
     * 返回 node 的后继节点
     *
     * @param node
     * @return next node in in-order traversal
     */
    public static TreeNode2 getNextTreenode2(TreeNode2 node) {
        if (node == null) {
            return null;
        }

        // if the right child is not null, return the left most node if the right subtree
        if (node.right != null) {
            TreeNode2 curr = node.right;
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr;
        } else {
            // if the right child is null, then this node is the last node in its biggest left subtree
            // or the last node in the whole tree
            TreeNode2 curr = node;
            while (curr.parent != null && curr != curr.parent.left) {
                curr = curr.parent;
            }
            return curr.parent;
        }

    }

    /**
     * Traversal a binary tree in post-order. Not recursively.
     * Using an extra stack to store the result.
     *
     * @param root root of the binary tree
     */
    public static void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println("Post-order Traversal:");
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> res = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.push(curr);
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }

        while (!res.isEmpty()) {
            System.out.print(res.pop().val + "->");
        }
        System.out.print("end\n");
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

    private static TreeNode2 generateSimpleTree2() {
        TreeNode2 head = new TreeNode2(1);
        head.parent = null;
        head.left = new TreeNode2(2);
        head.right = new TreeNode2(3);
        head.left.parent = head;
        head.right.parent = head;
        head.left.left = new TreeNode2(4);
        head.left.right = new TreeNode2(5);
        head.left.left.parent = head.left;
        head.left.right.parent = head.left;
        head.right.left = new TreeNode2(6);
        head.right.right = new TreeNode2(7);
        head.right.left.parent = head.right;
        head.right.right.parent = head.right;

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

class TreeNode2 {
    int val;
    TreeNode2 left;
    TreeNode2 right;
    TreeNode2 parent;

    public TreeNode2(int val) {
        this.val = val;
    }
}
