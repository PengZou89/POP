package solutions;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class Description
 *
 * @author zoup
 */
public class BinaryTreeTraversal {
    public static void main(String[] args) {
        TreeNode tn10 = new TreeNode(10);
        TreeNode tn6 = new TreeNode(6);
        TreeNode tn4 = new TreeNode(4);
        TreeNode tn8 = new TreeNode(8);
        TreeNode tn14 = new TreeNode(14);
        TreeNode tn12 = new TreeNode(12);
        TreeNode tn16 = new TreeNode(16);
        tn10.left = tn6;
        tn10.right = tn14;
        tn6.left = tn4;
        tn6.right = tn8;
        tn14.left = tn12;
        tn14.right = tn16;
        TreeNode head = tn10;

        // 前序遍历：10 6 4 8 14 12 16
        System.out.println("前序遍历：");
        BinaryTreeTraversal binaryTreeTraversal = new BinaryTreeTraversal();
        binaryTreeTraversal.preOrder(head);
        System.out.println();
        binaryTreeTraversal.preOrder2(head);
        System.out.println();

        // 中序遍历：4 6 8 10 12 14 16
        System.out.println("中序遍历：");
        binaryTreeTraversal.inOrder(head);
        System.out.println();
        binaryTreeTraversal.inOrder2(head);
        System.out.println();

        // 后序遍历：4 8 6 12 16 14 10
        System.out.println("后序遍历：");
        binaryTreeTraversal.postOrder(head);
        System.out.println();
        binaryTreeTraversal.postOrder2(head);

    }

    // 前序遍历
    private void preOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.print(treeNode.val + " ");
        preOrder(treeNode.left);
        preOrder(treeNode.right);
    }
    private void preOrder2(TreeNode treeNode) {
        TreeNode node = treeNode;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                System.out.print(node.val + " ");
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    // 中序遍历
    private void inOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inOrder(treeNode.left);
        System.out.print(treeNode.val + " ");
        inOrder(treeNode.right);
    }
    private void inOrder2(TreeNode treeNode) {
        TreeNode node = treeNode;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.print(node.val + " ");
                node = node.right;
            }
        }
    }

    // 后序遍历
    private void postOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        postOrder(treeNode.left);
        postOrder(treeNode.right);
        System.out.print(treeNode.val + " ");
    }
    // 思路：核心的问题在于结点的打印需要在左右子树打印之后，即结点会遍历两次，第二次遍历即右子树打印完后才能打印
    // 因此使用一个标志lastVisit保存当前结点curNode的右子树结点，当右子树结点出栈时，右子树即遍历完毕，此时当前结点出栈，
    // 通过判断当前结点的右子树是否为标志保存的结点，既可以判断当前结点是否应该打印
    private void postOrder2(TreeNode treeNode) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = treeNode;
        TreeNode lastVisit = null;

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.peek();
            if (node.right == null || lastVisit == node.right) {
                stack.pop();
                System.out.print(node.val + " ");
                lastVisit = node;
                node = null;
            } else {
                node = node.right;
            }

        }

    }



}
