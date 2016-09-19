package main.linklist;

/**
 * Created by Administrator on 2016/8/24.
 */

import java.util.HashSet;
import java.util.Stack;

public class LinkListAlgo {

    public static void main(String[] args) {

        //特殊链表 单结点循环链表。
        Node s1 = new Node(7);
        s1.next = s1;

        Node s2 = null;
        Node s3 = new Node(7);

        //偶数结点的链表
        Node d1 = new Node(5);
        Node d2 = new Node(8);
        Node d3 = new Node(12);
        Node d4 = new Node(18);

        d1.next = d2;
        d2.next = d3;
        d3.next = d4;

        //奇数结点的链表
        Node n1 = new Node(2);
        Node n2 = new Node(6);
        Node n3 = new Node(12);
        Node n4 = new Node(28);
        Node n5 = new Node(30);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        /**test printListReversingly_Iterattively(Node head)*/
//        printListReversingly_Iterattively(d1);

        /**test reverseLinklist(Node head)*/
        Node reverseHead = reverseLinklist(n1);
        printLinkList(reverseHead);

        /**test getReKthNode2(Node d1, int k)*/
//        Node result = getReKthNode2(d1, 1);
//        System.out.println(result.data);
        /**test getMiddleNode(Node d1):（null）, (奇数个)， （偶数个）， （1个元素）*/
//        Node d5 = new Node(17);
//        d4.next = d5;
//        Node mid = getMiddleNode(d1);
//        System.out.println(mid.data);

        /**test mergeSortedLinklist(Node d1, Node d2):（null）, (奇数个)， （偶数个）， （1个元素）*/


//        Node merge = mergeSortedLinklist2(d1, n1);
//        printLinkList(merge);

        /**test: isLinkListIntersect(Node head1, Node head2)*/
//        n5.next = d3;
//        System.out.println(isLinkListIntersect(d1, n1));

        /**test: getCommonNode(Node head1, Node head2)*/
//        n5.next = d2;
//        Node commondNode = getCommonNode(d1, n1);
//        printNode(commondNode);

        /**test: hasCycle(Node head)*/
//        d4.next = n5; //奇数无环
//        d4.next = d2; //偶数有环
//        n5.next = n2; //奇数有环
//        n5.next = n1; //圈链表
//        System.out.println(hasCycle(n1));

        /**test：cycleLength(Node head)*/
//        d4.next = n5; //奇数无环
//        d4.next = d2; //偶数有环
//        n5.next = n2; //奇数有环
//        n5.next = n1; //圈链表
//        System.out.println(cycleLength(n1));

        /**test getCycleStartNode(Node head)*/
//        d4.next = n5; //奇数无环
//        d4.next = d2; //偶数有环
//        n5.next = n2; //奇数有环
//        n5.next = n1; //圈链表
//        n2.next = n1; //两个结点的环
//        printNode(getCycleStartNode1(n1));
//        printNode(getCycleStartNode2(n1));
//        printNode(getCycleStartNode3(d1));

        /**test deleteNode(Node head, Node )*/
//        deleteNode(d1, n3);
//        printLinkList(d1);

    }

    /**
     * 【辅助方法】
     * 打印链表
     *
     * @param head：Node 链表的表头结点
     */
    public static void printLinkList(Node head) {
        System.out.print("[");
        while (head != null) {
            if (head.next == null) {
                System.out.print(head.data);
            } else {
                System.out.print(head.data + " -> ");
            }
            head = head.next;
        }
        System.out.println("]");
    }

    /**
     * -打印结点-
     *
     * @param p：Node，结点
     */
    public static void printNode(Node p) {
        if (p == null) {
            System.out.println("null");
        } else {
            System.out.println(p.data);
        }
    }

    /**
     * 单链表的倒序输出
     * test (null), (d1)
     *
     * @param head 待倒序输出的链表
     */
    public static void printListReversingly_Iterattively(Node head) {
        Stack<Integer> nodes = new Stack<Integer>();

        Node pNode = head;
        while (pNode != null) {
            nodes.push(pNode.data);
            pNode = pNode.next;
        }
        while (!nodes.isEmpty()) {
            System.out.print(nodes.pop() + ", ");
        }
    }

    /**
     * 单向链表的倒序输出
     *
     * @param head 待倒序输出的链表
     */
    public static void printListReversingly_Recursively(Node head) {
        if (head != null) {
            if (head.next != null) {
                printListReversingly_Recursively(head.next);
            }
            System.out.print(head.data + ", ");
        }
    }

    /**
     * 反转链表
     * 反转后,使head指向反转后的链表头.
     * test: (null), (单结点链表), (奇数个结点的链表),(偶数个结点的链表) 不处理循环链表
     * @param head 链表表头
     * */
    public static Node reverseLinklist(Node head){
        if(head == null || head.next == null){
            return head;
        }

        Node reverseHead = null;
        Node preNode = null; //当前结点的前一个结点
        Node curNode = head; //当前节点

        //从前往后遍历,改变curNode的指针,以反转整个链表
        while(curNode != null){
            Node nextNode = curNode.next;
            if(nextNode == null){
                reverseHead = curNode;
            }

            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }
        return reverseHead;
    }

    /**
     * 查找单链表倒数第k个元素
     * test: (null, 2), (p1, 2), (p1, 200), (p1, 0)
     *
     * @param head 待查找的链表
     * @param k    倒数第k个元素
     * @return Node 返回倒数第k个结点。若k=0或链表为null，则返回null，若k大于链表长度，则抛RuntimeException异常
     */
    public static Node getReKthNode1(Node head, int k) {
        if (head == null || k == 0) return null;
        int length = 0;
        Node cur = head;
        while (cur != null) {
            cur = cur.next;
            ++length;
        }
        //这里做一步异常处理
        if (k > length) {
            throw new RuntimeException("exceed length of linklist");
        }
        cur = head;
        for (int i = 0; i < length - k; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 查到单链表倒数第k个元素
     * test: (null, 2), (p1, 2), (p1, 200), (p1, 0)
     *
     * @param head 待查找的结点
     * @param k    倒数第k个元素
     * @return Node 返回倒数第k个结点 ， 若k=0或链表为null，则返回null，若k大于链表长度，则抛RuntimeException异常
     */
    public static Node getReKthNode2(Node head, int k) {
        if (head == null || k == 0) {
            return null;
        }
        Node pFront = head;
        Node pBack = head;

        while (k > 1 && pFront != null) { //从1开始数第k个数。
            pFront = pFront.next;
            --k;
        }

        if (pFront == null) {
            throw new RuntimeException("exceed length of linklist");
        }

        while (pFront.next != null) {
            pBack = pBack.next;
            pFront = pFront.next;
        }
        return pBack;
    }

    /**
     * 获取单链表的中间元素。
     * test： （null）, (奇数个)， （偶数个）， （1个元素）
     * @param head 待查找的链表
     * @return Node 链表的中间结点。 若链表为null，则返回null
     */
    public static Node getMiddleNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node pFront = head;
        Node pBack = head;

        while (!(pFront.next == null || pFront.next.next == null)) {
            pFront = pFront.next.next;
            pBack = pBack.next;
        }

        return pBack;
    }

    /**
     * 两个有序单链表合并
     * test： (null, null), (null, head2), (head1, null), (head1,head2) 注：若head1和head2都为null，则返回null
     * @param head1 第1个有序链表
     * @param head2 第2个有序链表
     * @return Node 归并之后的结果链表 若两个链表都为null，则返回null
     */
    public static Node mergeSortedLinklist1(Node head1, Node head2) {

        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        Node mergeHead = null;
        if (head1.data <= head2.data) {
            mergeHead = head1;
            head1 = head1.next;
            mergeHead.next = null;
        } else {
            mergeHead = head2;
            head2 = head2.next;  //注意链表的指针指向，这里的顺序不能错了。只有head指向下一个元素之后，mergeHead的next才能断开
            mergeHead.next = null;
        }
        Node mergeCur = mergeHead;
        while (head1 != null && head2 != null) {
            if (head1.data <= head2.data) {
                mergeCur.next = head1;
                head1 = head1.next;
            } else {
                mergeCur.next = head2;
                head2 = head2.next;
            }
            mergeCur = mergeCur.next;
            mergeCur.next = null; //使merge结果与原链表后面的元素断开。
        }
        if (head1 != null) {
            mergeCur.next = head1;
        } else if (head2 != null) {
            mergeCur.next = head2;
        }

        return mergeHead;
    }

    /**
     * 两个有序单链表合并
     * @param head1 第1个有序链表
     * @param head2 第2个有序链表
     * @return Node 归并之后的结果链表 ，若两个链表都为null，则返回null
     */
    public static Node mergeSortedLinklist2(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        Node mergeHead = null;
        if (head1.data <= head2.data) {
            mergeHead = head1;
            mergeHead.next = mergeSortedLinklist2(head1.next, head2);
        } else {
            mergeHead = head2;
            mergeHead.next = mergeSortedLinklist2(head1, head2.next);
        }

        return mergeHead;
    }

    /**
     * -----判断两个单链表是否相交------
     * test: (null, null), (null, head2),(head1, null), (head1和head2相交), (head1和head2不相交)
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return boolean。 true：两个链表相交； false：两个链表不相交
     */
    public static boolean isLinkListIntersect(Node head1, Node head2) {
        if (head1 == null || head2 == null) return false;
        Node tail1 = head1;
        Node tail2 = head2;
        while (tail1.next != null) {
            tail1 = tail1.next;
        }
        while (tail2.next != null) {
            tail2 = tail2.next;
        }

        return tail1 == tail2;
    }

    /**
     * 获取两个单链表相交的结点
     * test:(null, null), (null, head2),(head1, null), (head1和head2相交), (head1和head2不相交)
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return Node 返回两个链表相交的结点。若没有相交，则返回null
     */
    public static Node getCommonNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;

        int len1 = 1;
        int len2 = 1;
        Node tail1 = head1;
        Node tail2 = head2;
        while (tail1.next != null) {
            tail1 = tail1.next;
            ++len1;
        }
        while (tail2.next != null) {
            tail2 = tail2.next;
            ++len2;
        }

        if (tail1 != tail2) {
            return null;//不相交
        }

        Node pFront = null;
        Node pBack = null;
        int distance = 0;
        //确定head1和heda2哪个比较长，pBack指向短的链表表头

        if (len1 <= len2) {
            pBack = head1;
            pFront = head2;
            distance = len2 - len1;
        } else {
            pBack = head2;
            pFront = head1;
            distance = len1 - len2;
        }

        //让pFront指向lenLong-lenShort的位置
        for (int i = 0; i < distance; i++) {
            pFront = pFront.next;
        }

        //两个指针同时向链表尾部移动，直到找到交点。
        while (pBack != pFront) {
            pBack = pBack.next;
            pFront = pFront.next;

        }
        return pFront;
    }

    /**
     * 判断单链表是否有环
     * test：(null), (偶数个元素、偶数环元素的链表)， （偶数个元素且无环的链表），(奇数个元素、奇数环的链表)，
     * （奇数个元素且无环的链表），（1个元素的无环链表），（1个元素有环链表），（只有2个元素且有环的链表），（圈链表）
     * @param head 待判断是否有环的链表
     * @return boolean 是否有环。false：无环。true：有环
     */
    public static boolean hasCycle(Node head) {
        if (head == null || head.next == null) return false;

        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;//这一步需要考虑健壮性。即如果p2.next为null，则p2.next.next就会报异常。所以，要保证p2.next不为null
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断有环单链表中，环的长度
     * test：(null), (偶数个元素、偶数环元素的链表)， （偶数个元素且无环的链表），(奇数个元素、奇数环的链表)，
     * （奇数个元素且无环的链表），（1个元素的无环链表），（1个元素有环链表），（只有2个元素且有环的链表），（圈链表）
     * @param head 待计算环长度的链表
     * @return int 若有环，返回环的长度；若无环，返回0；
     */
    public static int cycleLength(Node head) {
        if (head == null || head.next == null) {
            return 0;
        }

        Node p1 = head;
        Node p2 = head;
        Node kNode = null;//p1和p2相遇时的结点。
        //p1和p2相遇
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                kNode = p2;
                break;
            }
        }
        //若p1和p2没有相遇，证明没有环
        if (p2 == null || p2.next == null) {
            return 0;
        }

        //再次移动p2一圈，计算环长度
        int length = 1; //先包括p2结点
        while (p2.next != kNode) {
            p2 = p2.next;
            ++length;
        }

        return length;
    }

    /**
     * 获取单链表中，环的起始结点
     * test：(null), (偶数个元素、偶数环元素的链表)， （偶数个元素且无环的链表），(奇数个元素、奇数环的链表)，
     * （奇数个元素且无环的链表），（1个元素的无环链表），（1个元素有环链表），（只有2个元素且有环的链表），（圈链表）
     * @param head 待处理的链表
     * @return Node 环的起始结点。 若没有环，则返回null
     */
    public static Node getCycleStartNode1(Node head) {
        int cycleLength = cycleLength(head);
        if (cycleLength == 0) {
            return null; //环长度为0
        } else {
            Node pFront = head;
            Node pBack = head;
            for (int i = 0; i < cycleLength; i++) {
                pFront = pFront.next;
            }

            while (pFront != pBack) {
                pFront = pFront.next;
                pBack = pBack.next;
            }

            return pFront;
        }
    }

    /**
     * 获取单链表中，环的起始结点
     * test：(null), (偶数个元素、偶数环元素的链表)， （偶数个元素且无环的链表），(奇数个元素、奇数环的链表)，
     * （奇数个元素且无环的链表），（1个元素的无环链表），（1个元素有环链表），（只有2个元素且有环的链表），（圈链表）
     *
     * @param head 待处理的链表
     * @return Node 环的起始结点。 若没有环，则返回null
     */
    public static Node getCycleStartNode2(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node p1 = head;
        Node p2 = head;
        //找到相遇的结点。
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                break;
            }
        }

        if (p2 == null || p2.next == null) return null; //没有环

        //将p1重新指向head，p1和p2一起走，直到两个指针再次相遇
        p1 = head;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }

    /**
     * 获取单链表中，环的起始结点
     *
     * @param head 待处理的链表
     * @return Node 环的起始结点。 若没有环，则返回null
     */
    public static Node getCycleStartNode3(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        HashSet nodeSet = new HashSet();
        Node p = head;
        while (p != null && !nodeSet.contains(p)) {
            nodeSet.add(p);
            p = p.next;
        }
        //如果无环，此时p == null，如果有环，此时p指向环的起始结点。
        return p;
    }

    /**
     * 删除单链表中指定结点
     * test:(null,null),(d1, null),(null, d2),(链表中间的结点) ，(链表结尾的结点)，（链表的头结点）
     *
     * @param head         待删除的链表
     * @param nodeToDelete 待删除结点
     */
    public static void deleteNode(Node head, Node nodeToDelete) {
        if (head == null || nodeToDelete == null) {
            return;
        }
        if (head.next == null && nodeToDelete == head) {//当链表只有1 个元素，并且删除此元素
            head = null;
        }
        //如果nodeToDelete是最后一个结点，或链表只有1个结点。则直接置为null，倒数第2个结点的next就指向了null

        if (nodeToDelete.next != null) {  //删除中间结点
            nodeToDelete.data = nodeToDelete.next.data;
            nodeToDelete.next = nodeToDelete.next.next;
        } else { //删除链表最后一个结点
            Node preNode = head;
            while (preNode.next != nodeToDelete) {
                preNode = preNode.next;
            }
            preNode.next = null;
        }
    }

}
