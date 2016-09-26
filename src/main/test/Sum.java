package main.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/9/23.
 */
public class Sum {
    /**
     * 找到数组中部分元素的和为sum的方案数。
     * 思路：找到两个元素和为sum的方案数，其它的就遍历并递归计算。如3个元素的方案数就是遍历每个元素，同时计算剩余元素中两个元素为sum-array[i]的方案数
     * test:(null, 0), (0个元素的数组，0) ，([1], 1), ([5,5,10,2,3], 15)
     *
     * */
    public static int getSumSolutionNumber(int[] array, int sum){
        if(array == null || array.length == 0 || sum < 0){
            return 0;
        }

        int solutions = 0;
        Arrays.sort(array);
        int endIndex = 0;
        //获取1个元素的方案数
        for(int i = 0; i < array.length; i++){
            if(array[i] == sum){
                ++solutions;
            }
            if(array[i] > sum){
                endIndex = i - solutions - 1;
                break;
            }
        }

        //递归进行计算
        for (int i = 1; i < endIndex;i++){
            for(int j = 0; j < endIndex; j++){
                int preNumber = array[j];
                solutions += getSolutionNumberRecursive(array, endIndex, sum - preNumber, i);
            }
        }


        return solutions;
    }

    private static int getSolutionNumberRecursive(int[] array, int endIndex, int sum,  int nums){
        if(nums == 2){
            return getSolutionNumberWith2Number(array, endIndex, sum);
        }else{
            return getSolutionNumberRecursive(array, endIndex, sum, nums - 1);
        }


    }

    private static int getSolutionNumberWith2Number(int[] array, int endIndex, int sum){
        int solutions = 0;
        int start = 0;
        int end = endIndex;
        while(start < endIndex){
            if(array[start] + array[end] == sum){
                solutions++;
                start++;
            }else if(array[start] + array[end] < sum){
                start++;
            }else{
                end--;
            }
        }

        return solutions;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String sizeSumStr = sc.nextLine();
        String elements = sc.nextLine();
    }
}

