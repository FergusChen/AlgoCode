package main.other;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/9/21.
 */
public class RetainMaxNumber {
    /**
     * 保留最大整数。
     * 给定一个十进制的正整数number，选择从里面去掉一部分数字，使得剩余的数字组成的数最大（不改变数字顺序）。
     * 输入两行：第一行输入正整数number，第二行输入要删除的数字个数，如：
     * 93024
     * 2
     * output： 934
     * 思路：从左到右删除数字，如果位置i的数字比位置i+1的数字小，就把位置i的数字删掉。
     * 如果没有，则证明最后一个数字是最小的。删除最后一个数字
     * test：（0,0），（20,0），（39220,3），（299342918394023948,5）
     *
     * @param numStr   十进制正整数number
     * @param delCount 要删除的数字个数
     * @return 删除后保留的最大整数字符串
     */
    public static String retainMaxNumber(String numStr, int delCount) {
        if (numStr == null || numStr.length() == 0 || delCount >= numStr.length()) {
            return "0";
        }

        if (delCount <= 0) {
            return numStr;
        }

        int numLen = numStr.length(); //获取数字字符串的长度

        //将数字字符串转为整型数组，并存入numArr中。
        ArrayList numArr = new ArrayList(numLen);
        for (int i = 0; i < numLen; i++) {
            numArr.add(numStr.charAt(i) - '0');  //按ASCII码计算得到int值。
        }

        //循环删除delCount个数字，
        while (delCount > 0) {
            boolean mark = false;
            //如果numArr[i] 比 numArr[i+1]要小，那么就删除numArr[i]
            for (int i = 0; i < numArr.size() - 1; i++) {
                if ((int) numArr.get(i) < (int) numArr.get(i + 1)) {
                    numArr.remove(i);
                    mark = true;
                    break;
                }
            }

            //如果numArr[i] 大于或等于 numArr[i+1]，则删除最后一个元素
            if (!mark) {
                numArr.remove(numArr.size() - 1);
            }

            delCount--;//每次while循环都删除1个元素，delCount--
        }
        StringBuffer strbuf = new StringBuffer();
        for (int i = 0; i < numArr.size(); i++) {
            strbuf.append(numArr.get(i));
        }

        return strbuf.toString();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String number = sc.nextLine();
        int deleteCount = Integer.valueOf(sc.nextLine());
        String result = retainMaxNumber(number, deleteCount);
        System.out.println(result);
    }
}
