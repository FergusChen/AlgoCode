package main.other;

/**
 * Created by Administrator on 2016/8/24.
 */
import java.util.Stack;
public class LinkListAlgo {
    public static void main(String[] args){
        Node d1 = new Node(5);
        Node d2 = new Node(8);
        Node d3 = new Node(12);
        Node d4 = new Node(18);

        d1.next = d2;
        d2.next = d3;
        d3.next = d4;

        printListReversingly_Recursively(d1);
    }

    /** 单向链表的倒序输出。一种是迭代实现，一种是递归实现。
     * 迭代实现关键是考虑倒序输出正好是堆栈的特点，先遍历到的最后输出，则可以遍历一遍，将数据存储堆栈，然后堆栈pop即可
     * 下面是用迭代实现*/
    public static void printListReversingly_Iterattively(Node head){
        Stack<Integer> nodes = new Stack<Integer>();

        Node pNode = head;
        while(pNode != null){
            nodes.push(pNode.data);
            pNode = pNode.next;
        }
        while(!nodes.isEmpty()){
            System.out.print(nodes.pop() + ", ");
        }
    }

    /** 单向链表的倒序输出
     * 递归实现。递归地找，直到找到最后一个元素，再回溯输出即可
     * 递归实现有个很大的缺陷，就是当链表元素特别多的时候，递归层次太深，容易造成栈溢出。*/
    public static void printListReversingly_Recursively(Node head){
        if(head != null){
            if(head.next != null){
                printListReversingly_Recursively(head.next);
            }
            System.out.print(head.data + ", ");
        }
    }
}
