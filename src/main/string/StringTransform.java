package main.string;

/**
 * Created by Administrator on 2016/10/4.
 */
public class StringTransform {

    /**
     * 将字符串转换为double类型，即实现C语言的atof函数
     * 思想：先转化整数部分，再转化小数部分。
     * test: (null), (0), (有小数部分的字符串)，（无小数部分的字符串）
     * 注：根据必须要，处理是否是非法字符串，如两个小数点，包含其它字符的字符串。
     */
    public static double atof(String str) {
        if (str == null || str.length() == 0) {
            return 0.0;
        }

        int intPart = 0; //整数部分
        int fractionPart = 0; //小数部分的数字
        int fractionDeno = 1; //小数的分母。1位小数->10, 2位小数->100

        int i = 0;

        //处理整数部分
        do {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                intPart = intPart * 10 + (ch - '0');
                i++;
            }
        } while (i < str.length() && str.charAt(i) != '.');

        //跳过小数点
        i++;

        //处理小数部分
        while (i < str.length()) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                fractionPart = fractionPart * 10 + (ch - '0');
                fractionDeno *= 10;
                i++;
            }

        }

        return intPart + (double)fractionPart / fractionDeno;
    }

    public static void main(String[] args) {
        String s1 = null;
        String s2 = "";

        String str1 = "3";
        String str2 = "532.01";
        String str3 = "440220";

        //test: atof
        System.out.println(atof(str3));

    }
}
