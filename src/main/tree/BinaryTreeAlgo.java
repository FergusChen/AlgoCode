package main.tree;

/**
 * Created by Administrator on 2016/8/26.
 */

import java.util.*;

public class BinaryTreeAlgo {
    public static void main(String[] args) {

        BTNode s1 = null;
        BTNode s2 = new BTNode(1);
        /**默认二叉树
         *       3
         *   6      5
         * 7   2  8
         * 前序遍历：根结点->左子树->右子树  3,6,7,2,5,8
         * 中序遍历：左子树->根结点->右子树  7,6,2,3,8,5
         * 后序遍历：左子树->右子树->根结点  7,2,6,8,5,3
         * */
        BTNode n1 = new BTNode(3);
        BTNode n2 = new BTNode(6);
        BTNode n3 = new BTNode(5);
        BTNode n4 = new BTNode(7);
        BTNode n5 = new BTNode(2);
        BTNode n6 = new BTNode(8);
        n1.left = n2;
        n2.parent = n1;
        n1.right = n3;
        n3.parent = n1;
        n2.left = n4;
        n4.parent = n2;
        n2.right = n5;
        n5.parent = n2;
        n3.left = n6;
        n6.parent = n3;

        BTNode ns = new BTNode(6);
        BTNode ns1 = new BTNode(7);
        BTNode ns2 = new BTNode(8);
        ns.left = ns1;
        ns.right = ns2;


        int[] preOrder = {3, 6, 7, 2, 5, 8};
        int[] inOrder = {7, 6, 2, 3, 8, 5};
        int[] spePreOrder = {3}; //只有1个结点的树
        int[] speInOrder = {3};
        int[] leftPreOrder = {3, 6, 7}; //只有左子树的二叉树
        int[] leftInOrder = {7, 6, 3};
//        printTree(n1);

        /**test: reContructBinaryTree*/
//        BTNode root = reContructBinaryTree(leftPreOrder, leftInOrder);
//        printTree(root);

        /**test: preOrder*/
//        preOrder(n1);
//        preOrder_nonRec(n1);

        /**test:midOrder*/
//        midOrder(n1);
//        midOrder_nonRec(n1);
        /**test:postOrder*/
//        postOrder(n1);

        /**test：hasSubTree*/
//        System.out.println("是否包含相同的子树:" + hasSubTree(n1, ns));

        /**test: mirrorRecursively*/
        printTree(n1);
        mirrorRecursively(n1);
        printTree(n1);


    }


    /**
     * 辅助方法
     * 按层次打印二叉树
     * 按层次遍历二叉树，其思想就是利用队列的先进先出的特点，逐个将左右结点放入队列中
     */
    public static void printTree(BTNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }

        Queue<BTNode> treeQueue = new LinkedList<BTNode>();
        treeQueue.offer(root); //先将根结点放入队列。
        while (!treeQueue.isEmpty()) {
            BTNode node = treeQueue.poll(); //将队列中已有的结点出队列。
            System.out.print(node.data + "\t");
            //出队列的同时扫描该结点的左右孩子，并逐个放入到队列中。
            if (node.left != null) {
                treeQueue.offer(node.left);
            }
            if (node.right != null) {
                treeQueue.offer(node.right);
            }
        }
        System.out.println();

    }

    /**
     * 前序遍历二叉树（递归实现）
     * 思路：根据前序遍历的特点：根结点->左子树->右子树，用递归实现
     * test:(null),(单结点)，（默认二叉树）
     *
     * @param root 二叉树的根结点
     */
    public static void preOrder(BTNode root) {
        if (root != null) {
            System.out.print(root.data + "\t");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * 前序遍历二叉树（非递归实现）
     * 思路：利用栈从根结点，一直将左子树入栈，直到左子树为null，
     * 然后逐个将结点出栈，同时遍历该结点的右子树，
     * 遍历右子树为根的子树时，用同样的方法从根节点，一直将左子树入栈。
     * test:(null),(单结点)，（默认二叉树）
     *
     * @param root 二叉树的根节点
     */
    public static void preOrder_nonRec(BTNode root) {
        if (root == null) {
            System.out.println("empty tree");
            return;
        }

        Stack<BTNode> treeStack = new Stack<BTNode>();
        BTNode node = root;
        while (node != null || treeStack.size() > 0) {
            while (node != null) {
                System.out.print(node.data + "\t");
                treeStack.push(node);
                node = node.left;
            }
            node = treeStack.pop();
            node = node.right;
        }
    }


    /**
     * 中序遍历二叉树(递归遍历)
     * 思路：根据中序遍历的特点：左子树->根结点->右子树 递归遍历
     * test:(null),(单结点)，（默认二叉树）
     *
     * @param root 二叉树的根结点
     */
    public static void midOrder(BTNode root) {
        if (root != null) {
            midOrder(root.left);
            System.out.print(root.data + "\t");
            midOrder(root.right);
        }
    }

    /**
     * 中序遍历二叉树（非递归遍历）
     * 思路：与非递归前序遍历类似。只是访问结点是时机不同
     * test:(null),(单结点)，（默认二叉树）
     *
     * @param root 二叉树的根结点
     */
    public static void midOrder_nonRec(BTNode root) {
        if (root == null) {
            System.out.println("empty tree");
            return;
        }
        Stack<BTNode> treeStack = new Stack<BTNode>();
        BTNode node = root;
        while (node != null || treeStack.size() > 0) {
            while (node != null) {
                treeStack.push(node);
                node = node.left;
            }
            node = treeStack.pop();
            System.out.print(node.data + "\t");
            node = node.right;
        }
    }

    /**
     * 后续遍历二叉树
     * 思路：根据后序遍历的特点：左子树->右子树->根结点 递归遍历
     * test:(null),(单结点)，（默认二叉树）
     *
     * @param root 二叉树的根结点
     */
    public static void postOrder(BTNode root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + "\t");
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

    /**
     * 有两棵树A和B，判断树B是否是树A的子结构。
     * 思路：分两步
     * step1：先在树A中找到树B根节点相同的结点p。
     * step2：判断以p为结点的子树与树B的结构是否相同。
     */
    public static boolean hasSubTree(BTNode pRoot1, BTNode pRoot2) {
        boolean result = false;
        if (pRoot1 != null && pRoot2 != null) {
            if (pRoot1.data == pRoot2.data) {
                //如果根节点相同，则判断树1是否包含树2
                result = doesTree1HaveTree2(pRoot1, pRoot2);
            }
            if (!result) {
                //递归判断树1的左子树，
                result = hasSubTree(pRoot1.left, pRoot2);
            }
            if (!result) {
                //递归判断树1的右子树
                result = hasSubTree(pRoot1.right, pRoot2);
            }
        }
        return result;
    }

    /**
     * 判断树A是否包含树B
     */
    public static boolean doesTree1HaveTree2(BTNode tree1, BTNode tree2) {
        if (tree2 == null) {
            return true;
        }
        if (tree1 == null) {
            return false;
        }
        if (tree1.data != tree2.data) {
            return false;
        }

        return doesTree1HaveTree2(tree1.left, tree2.left)
                && doesTree1HaveTree2(tree1.right, tree2.right);
    }


    /**
     * 获取二叉树的镜像（反转）
     * 思路：通过画图，可以看到镜像就是左右子树反转。这样，可以在前序遍历的同时，反转左右子树即可。
     * test:(null), (单个结点)， （只有左子树），（完整二叉树）
     */
    public static void mirrorRecursively(BTNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }

        BTNode temp = root.right;
        root.right = root.left;
        root.left = temp;

        if (root.left != null) {
            mirrorRecursively(root.left);
        }
        if (root.right != null) {
            mirrorRecursively(root.right);
        }
    }

    public static boolean isOrderValid(int[] preOrder, int[] inOrder) {
        if (preOrder.length == 0 || inOrder.length == 0 || preOrder.length != inOrder.length) {
            return false;
        } else {
            return true;
        }


//        Arrays.sort(preOrder);  //sort会改变原数组，这里一定不能这么判断。
//        Arrays.sort(inOrder);

//        return Arrays.equals(preOrder, inOrder);
    }
}

