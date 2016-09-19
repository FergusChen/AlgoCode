package main.test;

import main.linklist.LinkListAlgo;
import main.linklist.Node;

import java.util.Scanner;

/**
 * Created by Administrator on 2016/9/1.
 */
public class testBasic {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
//        System.out.println(str);
//
//        Scanner sc2 = new Scanner(System.in);
//        String str2;
//        while(sc2.hasNextLine()){
//            str2 = sc2.nextLine();
//            System.out.println(str2);
//        }
//        Integer i1 = 127, i2=127;
//        Integer i3=128,i4=128;
//        System.out.println(i1==i2);
//        System.out.println(i1.equals(i2));
//        System.out.println(i3==i4);
//        System.out.println(i3.equals(i4));
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

        Node d1 = new Node(5);
        Node d2 = new Node(8);
        Node d3 = new Node(12);
        Node d4 = new Node(18);

        d1.next = d2;
        d2.next = d3;
        d3.next = d4;

        while(sc.hasNextLine()) {
            int num = sc.nextInt();
            int nthDigit = findNthDigit(num);
            System.out.println("num:" + nthDigit);
        }
    }
/**
 * find the length of the number where the nth digit is from
   find the actual number where the nth digit is from
   find the nth digit and return
 * */
    public static int findNthDigit(int n) {
        int len = 1;  //记录实际指向的数字的位数，
        long count = 9;//记录不同位数的数字个数，1位：9个；2位：99-10+1=90个；3位：999-100+1=900个...
        int start = 1; //找到实际的数字

        //通过循环，n变换为差值，len确定实际数字的位数，count用于控制循环（减去1位，减去2位，减去3位...）。
        while (n > len * count) {
            n -= len * count;
            len += 1;
            count *= 10;
            start *= 10;
        }

        start += (n - 1) / len;
        String s = Integer.toString(start);
        return Character.getNumericValue(s.charAt((n - 1) % len));
    }


}
