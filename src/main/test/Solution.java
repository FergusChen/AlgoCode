package main.test;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Administrator on 2016/9/23.
 */
public class Solution {

    /**
     * 实现进制的转换。
     * 思路：按照进制转换计算公式，除以base，并取余数，因为余数是从低位到高位计算，在组成字符串时从高位到低位，因此需要栈来暂时存放余数。
     * test: (12, 18) (0,2), (1,16), (128, 16), (18,8)
     */
    public static String baseTransformer(int number, int base) {
        if (base < 2 || base > 16) {
            return String.valueOf(number);
            //throw new RuntimeException("只能转换为2-16进制");
        }
        if (number == 0) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        String digths = "0123456789ABCDEF";
        StringBuffer result = new StringBuffer("");

        //循环计算余数
        while (number != 0) {
            stack.push(digths.charAt(number % base));
            number = number / base;
        }
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] array = input.split(" ");
        if (array.length != 2) {
            throw new RuntimeException("输入格式不正确");
        }

        int number = Integer.valueOf(array[0]);
        int base = Integer.valueOf(array[1]);
        System.out.println(baseTransformer(number, base));

    }

}
