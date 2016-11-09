package main.linklist;

/**
 * Created by Administrator on 2016/9/29.
 */
public class ComplexListNode {
    public int data; //结点的值
    public ComplexListNode next; //指向下一个结点的指针
    public ComplexListNode sibling; //指向链表中的任意一个结点，或者为空。

    public ComplexListNode(int data){
        this.data = data;
    }
}

