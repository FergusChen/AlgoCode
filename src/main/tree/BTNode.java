package main.tree;

/**
 * Created by Administrator on 2016/8/26.
 * 二叉树的结点
 */
public class BTNode {
    public int data;
    public BTNode left;
    public BTNode right;
    public BTNode parent;

    public BTNode(int data){
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}
