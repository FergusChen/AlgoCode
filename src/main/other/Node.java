package main.other;

/**
 * Created by Administrator on 2016/8/24.
 */
public class Node {
    public int data; //数据域
    protected Node next; //指针域
    public Node(int data){
        this.data = data;
    }
    //显示此节点
    public void display(){
        System.out.print(this.data + " ");
    }
}
