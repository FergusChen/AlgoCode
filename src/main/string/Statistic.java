package main.string;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Administrator on 2016/10/1.
 */
public class Statistic {

    /**
     * 字符数组各个元素的全排列。
     *
     * 思路：分两步递归处理，step1：确定第1个位置的元素；step2：对第1个位置之外的剩余元素进行全排列。
     * step2仍然是用这两步处理，直到剩余元素只有1个，则直接输出排列。这显然是一个递归的过程。
     * test：（null） （只有1个元素）（3个元素数组）(4个元素的数组)
     * */
    public static void permutation(char[] arr, int start, int end){
        if(arr == null){
            return;
        }

        //递归结束条件：start 和end相等，即后续元素只剩下1个，
        if(start == end){
            for(int i = 0; i <= end; i++){
                System.out.print(arr[i]);
            }
            System.out.println();
        }else{
            for(int i = start; i <=end; i++){
                //后续元素和start位置的元素交换位置。
                char temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;

                //递归计算后续元素的全排列
                permutation(arr, start + 1, end);

                //将数组还原
                temp = arr[start];
                arr[start] = arr[i];
                arr[i] = temp;
            }
        }
    }

    /**
     * 字符数组中字符的组合
     * 思路：假设我们想在长度为n的字符串中求m个字符的组合。我们先选择组合中的第一个字符。
     * 针对第一个字符，我们有两种选择：
     *      一是把这个字符放到组合中去，接下来我们需要在剩下的n-1个字符中选取m-1个字符；
     *      二是不把这个字符放到组合中去，接下来我们需要在剩下的n-1个字符中选择m个字符。
     * 这显然是一个递归的过程。其中组合的长度m可以从1到n。
     * */
    public static void combine(char[] arr){
        if(arr == null || arr.length == 0){
            return;
        }

        Stack<Character> stack = new Stack<>();
        for(int i = 1; i <= arr.length; i++){
            combine(arr, 0, i, stack);
        }
    }
    /**
     * 选取number个元素的组合。
     * */
    public static void combine(char[] arr, int start, int number, Stack<Character> stack){
        if(number == 0){
            System.out.println(stack.toString());
            return;
        }
        if(start == arr.length){
            return;
        }

        stack.push(arr[start]);
        combine(arr, start + 1, number - 1, stack);
        stack.pop();
        combine(arr, start + 1, number, stack);

    }

    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();

        String s1 = null;
        String s2 = "";

        String str = "abc";
        String str1 = "abcd";
//        permutation(str.toCharArray(), 0, str.length() - 1);
        combine(str.toCharArray());
    }
}
