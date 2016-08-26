package main.tree;

/**
 * Created by Administrator on 2016/8/26.
 */

import java.util.*;

public class BinaryTreeAlgo {
    public static void main(String[] args) {

        BTNode n1 = new BTNode(3);
        BTNode n2 = new BTNode(6);
        BTNode n3 = new BTNode(5);
        BTNode n4 = new BTNode(7);
        BTNode n5 = new BTNode(2);
        BTNode n6 = new BTNode(8);
//        n1.left = n2;
//        n1.right = n3;
//        n2.left = n4;
//        n2.right = n5;
//        n3.left = n6;

        int[] preOrder = {3,6,7,2,5,8};
        int[] inOrder = {7,6,2,3,8,5};
        int[] spePreOrder = {3}; //只有1个结点的数
        int[] speInOrder = {3};
        int[] leftPreOrder = {3,6,7}; //只有左子树的二叉树
        int[] leftInOrder = {7,6,3};
//        printTree(n1);

        BTNode root = reContructBinaryTree(leftPreOrder, leftInOrder);
        printTree(root);


    }


    /**辅助方法
     * 打印二叉树
     * 按层次遍历二叉树，其思想就是利用队列的先进先出的特点，逐个将左右结点放入队列中
     * */
    public static void printTree(BTNode root){
        if(root == null){
            System.out.println("null");
            return;
        }

        Queue<BTNode> treeQueue = new LinkedList<BTNode>();
        treeQueue.offer(root);
        while(!treeQueue.isEmpty()){
            BTNode node = treeQueue.poll();
            System.out.print(node.data + "\t");
            if(node.left != null){
                treeQueue.offer(node.left);
            }
            if(node.right != null){
                treeQueue.offer(node.right);
            }

        }

    }

    /**
     * 根据前序遍历和中序遍历的序列构造二叉树
     * 如果动手去画还是很容易的。简单来说，前序序列的第1个结点是二叉树的根节点，以此结点将中序序列分为两部分，
     * 左半部分就是根结点的左子树，右半部分就是根结点的右子树。然后在左子树的结点中找前序序列第1个出现的结点，该结点就是左子树的根结点。
     * 然后以此结点将中序序列的左子树再分为两部分，左半部分就是左子树根结点的左子树，右半部分就是左子树根结点的右子树。
     * 有点绕，画个图就一清二楚了。但看上面的思路，这个就是一个递归的做法。
     * 注：下面的算法假设树中没有相同元素。
     * test: (null, null), (前序序列,中序序列 )， (只有1个结点的前序序列和中序序列)，(只有左子树的二叉树)，（前序序列和后续序列不匹配）
     */
    public static BTNode reContructBinaryTree(int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder == null) {
            return null;
        }

        //判断前序序列和中序序列是否合法
        if (!isOrderValid(preOrder, inOrder)) {
            throw new RuntimeException("order invalid！");
        }

        BTNode root = new BTNode(preOrder[0]);
        int len = preOrder.length;

        //当结点只有1个元素时，返回根结点即可
        if (len == 1) {
            root.left = null;
            root.right = null;
            return root;
        }

        //在中序序列中找根结点的位置
        int rootIndex = 0;
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] == root.data) {
                rootIndex = i;
                break;
            }
        }


        //获取左子树所有元素的前序序列和中序序列，继续构造
        int leftLength = rootIndex;
        if (leftLength > 0) {
            int[] leftPreOrder = new int[leftLength]; //左子树的前序序列
            int[] leftInOrder = new int[leftLength]; //左子树的中序序列
            for (int i = 0; i < leftLength; i++) {
                leftPreOrder[i] = preOrder[i + 1];
                leftInOrder[i] = inOrder[i];
            }
            root.left = reContructBinaryTree(leftPreOrder, leftInOrder);
        } else {
            root.left = null;
        }

        //获取右子树所有元素的前序序列和中序序列，继续构造
        int rightLength = len - rootIndex - 1;
        if (rightLength > 0) {
            int[] rightPreOrder = new int[rightLength];
            int[] rightInOrder = new int[rightLength];
            for (int i = 0; i < rightLength; i++) {
                rightPreOrder[i] = preOrder[rootIndex + i + 1];
                rightInOrder[i] = inOrder[rootIndex + i + 1];
            }
            root.right = reContructBinaryTree(rightPreOrder, rightInOrder);
        } else {
            root.right = null;
        }
        return root;
    }

    public static boolean isOrderValid(int[] preOrder, int[] inOrder) {
        if (preOrder.length == 0 || inOrder.length == 0 || preOrder.length != inOrder.length) {
            return false;
        }else{
            return true;
        }


//        Arrays.sort(preOrder);  //sort会改变原数组，这里一定不能这么判断。
//        Arrays.sort(inOrder);

//        return Arrays.equals(preOrder, inOrder);
    }
}

