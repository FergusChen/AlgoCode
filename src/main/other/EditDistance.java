package main.other;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/10/16.
 */
public class EditDistance {
    private static int editCost = 0; //编辑代价

    public static int minEditDistance(String str1, String str2) {
        //当字符串为null时，返回错误-1
        if (str1 == null || str2 == null) {
            return -1;
        }
        int len1 = str1.length();
        int len2 = str2.length();

        //首先判断，如果其中一个字符串是空串，编辑距离就是另一个字符串的长度。
        if (len1 == 0) {
            return len2;
        }
        if (len2 == 0) {
            return len1;
        }

        //初始化编辑距离转移矩阵
        int[][] distMatrix = new int[len1 + 1][len2 + 1];
        distMatrix[0][0] = 0;
        for (int i = 0; i <= len1; i++) {

            distMatrix[i][0] = i;
        }
        for (int i = 0; i <= len2; i++) {

            distMatrix[0][i] = i;
        }


        for (int m = 1; m <= len1; m++) {
            char ci = str1.charAt(m - 1);
            for (int n = 1; n <= len2; n++) {
                char cj = str2.charAt(n - 1);
                if (ci == cj) {
                    editCost = 0;
                } else {
                    editCost = 1;
                }

                int leftUpCost = distMatrix[m - 1][n - 1] + editCost;
                int leftCost = distMatrix[m - 1][n] + 1;
                int upCost = distMatrix[m][n - 1] + 1;
                int min = leftUpCost;
                if(min > leftCost ){
                    min = leftCost;
                }
                if(min > upCost){
                    min = upCost;
                }
                distMatrix[m][n] = min;

            }
        }

        return distMatrix[len1][len2];
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String str = sc.nextLine();
//        String[] strArr = str.split("\t| ");
//        if (strArr.length != 2) {
//            System.out.println("输入格式不正确");
//            return;
//        }
//
//        String str1 = strArr[0];
//        String str2 = strArr[1];
//
//        System.out.println(minEditDistance(str1, str2));


    }
}

