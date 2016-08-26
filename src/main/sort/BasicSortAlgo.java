package main.sort;

/**
 * Created by Administrator on 2016/8/25.
 * 一些基本排序算法的分析和实现。
 * 主要包括：
 *  --插入排序
 */

public class BasicSortAlgo {

    public static void main(String[] args) {
        /**test： ------插入排序------*/
        int[] arr1 = null;
        int[] arr2 = {3,2,8,9,5};
        int[] arr3 ={};
        int[] arr4 = {1};
        insertSort(arr4);
        printArray(arr4);


    }

    /** 辅助方法
     * ------打印数组------
     * @param array 待打印的数组
     * */
    public static void printArray(int[] array){
        if(array == null || array.length == 0) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        for(int i = 0; i < array.length; i++){
            if(i == array.length-1){
                System.out.print(array[i]);
            }else{
                System.out.print(array[i] + ", ");
            }
        }
        System.out.println("]");
    }

    /**
     * ------插入排序------
     * @param array 待排序数组
     * test: (null), （1个元素的数组），（空数组），(5个无序整数数组)，（6个有序整数数组）
     */
    public static void insertSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        for (int i = 1; i < array.length; i++) {
            int curData = array[i];
            int posForInsert = i;
            for (int j = i - 1; j >= 0; j--) {
                if (curData < array[j]) {
                    array[j + 1] = array[j];
                    posForInsert--;
                } else {
                    break;
                }
            }
            array[posForInsert] = curData;
        }

    }
}
