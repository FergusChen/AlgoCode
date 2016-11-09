package main.linklist;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/9/29.
 */
public class ComplexListTest {

    /**
     * 复杂链表的复制
     * 复杂链表除了具有单链表的性质外，还有一个sibling指针，指向链表中随机的一个结点，或者指向null。
     * 复制复杂链表的关键就是sibling的复制。
     * 思路：有3种方法，最基本的：先复制每个结点，并将next指针连接成单链表。然后对每个sibling指向的结点，从单链表中查找，并指向相应结点。O(n^2)
     * 方法2：用一个Map维护单链表的结点S和复制的结点S',即(S, S')，然后第一步仍然将next指针连接起来，
     * 第2步设置sibling时，若一个结点的sibling为S，则在Map中查找，就能用O(1)时间找到S'
     *
     * 方法3：最高效的做法。分为三步。具体见《剑指Offer》的P147
     * step1：复制每个结点到原结点的后面
     * step2：设置sibling指针
     * step3：分离两个链表。
     * */

    /**
     * 方法2；用Map来辅助实现
     * test:(null), (1个结点)，（4个结点）
     */
    public static ComplexListNode copyComplexList(ComplexListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        HashMap nodeMap = new HashMap(); //存储原结点和复制结点的映射。

        //设置头结点
        ComplexListNode copyHead = new ComplexListNode(head.data);
        ComplexListNode curCopyNode = copyHead;
        ComplexListNode curNode = head;
        nodeMap.put(curNode, curCopyNode);

        //循环复制结点，并设置next指针
        curNode = curNode.next;
        while (curNode != null) {
            ComplexListNode node = new ComplexListNode(curNode.data);
            nodeMap.put(curNode, node);
            curCopyNode.next = node;
            curCopyNode = node;
            curNode = curNode.next;

        }

        //根据nodeMap，处理sibling结点
        curNode = head;
        curCopyNode = copyHead;
        while (curNode != null) {
            if (curNode.sibling != null) {
                curCopyNode.sibling = (ComplexListNode) nodeMap.get(curNode.sibling);
            }
            curNode = curNode.next;
            curCopyNode = curCopyNode.next;
        }

        return copyHead;
    }

    /**
     * 方法3：分三步处理
     * step1: 复制链表，原链表为N1->N2->N3。复制之后得到：N1->N1'->N2->N2'->N3->N3' 其中N1'是N1复制得到的结点。
     */
    public static void cloneNodes(ComplexListNode head) {
        ComplexListNode curNode = head;
        while (curNode != null) {
            ComplexListNode node = new ComplexListNode(curNode.data);
            node.next = curNode.next;
            curNode.next = node;
            curNode = node.next;
        }
    }

    /**
     * step2: 设置copyNode的sibling指针
     * */
    public static void connectSiblingNodes(ComplexListNode head) {
        ComplexListNode curNode = head;

        while (curNode != null) {
            ComplexListNode copyNode = curNode.next; //要先做curNode非空检查
            if (curNode.sibling != null) {
                copyNode.sibling = curNode.sibling.next;
            }
            curNode = copyNode.next;
        }
    }

    /**
     * step3:两个链表分离
     * */
    public static ComplexListNode reconnectNodes(ComplexListNode head){
        ComplexListNode curNode = head;
        ComplexListNode copyHead = null;
        ComplexListNode curCopyNode = null;

        //设置起步结点
        if(curNode != null){
            copyHead = curCopyNode = curNode.next;
            curNode.next = copyHead.next;
            curNode = curNode.next;
        }

        //逐个分离结点
        while(curNode != null){
            curCopyNode.next = curNode.next;
            curCopyNode = curCopyNode.next;
            curNode.next = curCopyNode.next;
            curNode = curNode.next;
        }
        return copyHead;
    }

public static ComplexListNode copyComplexList2(ComplexListNode head){
    cloneNodes(head);
    connectSiblingNodes(head);
    return reconnectNodes(head);
}
    public static void main(String[] args) {
        ComplexListNode s1 = null;
        ComplexListNode s2 = new ComplexListNode(5);

        ComplexListNode n1 = new ComplexListNode(5);
        ComplexListNode n2 = new ComplexListNode(7);
        ComplexListNode n3 = new ComplexListNode(9);
        ComplexListNode n4 = new ComplexListNode(11);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n1.sibling = n4;
        n2.sibling = null;
        n3.sibling = n1;
        n4.sibling = n2;


        printComplexList(n1);
//        ComplexListNode copyHead = copyComplexList(n1);
        ComplexListNode copyHead = copyComplexList2(n1);
        System.out.println();
        printComplexList(copyHead);

    }

    public static void printComplexList(ComplexListNode head) {
        ComplexListNode curNode = head;
        int num = 1;
        while (curNode != null) {
            System.out.println(num + ": " + curNode.data + "\tsibling:" + (curNode.sibling == null ? "null" : curNode.sibling.data));
            curNode = curNode.next;
            num++;
        }
    }

}


