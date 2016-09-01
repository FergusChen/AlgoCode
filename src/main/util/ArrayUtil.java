package main.util;

/**
 * Created by Administrator on 2016/8/30.
 */
public class ArrayUtil {
    /**
     * 辅助方法
     * ------打印数组------
     *
     * @param array 待打印的数组
     */
    public static void printArray(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                System.out.print(array[i]);
            } else {
                System.out.print(array[i] + ", ");
            }
        }
        System.out.println("]");
    }

    public static void exchangeElements(int[] array, int i, int j) {
        if(array == null || array.length == 0 || i < 0 || j > array.length - 1){
            return;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 将array中src处的元素插入到dest. 如{1,3,6,4,7,5} 将元素7插入到元素3的位置,得到{1,7,3,6,4,5}
     * test (null, 0,0) ({}, 0, 1), ({1}, 0, 0), ({1,3,6,4,7,5},4,1)
     * @param array 整数数组
     * @param src 整数数组中源位置
     * @param dest 整数数组中目的位置
     * */
    public static void insertElementInArray(int[] array, int src, int dest){
        if(array == null || array.length == 0 || src < 0 || src > array.length - 1 || dest < 0 || dest > array.length - 1 || src == dest){
            return;
        }
        int temp = array[src];
        //移动src到dest之间的元素
        if(src > dest) {
            while (src > dest){
                array[src] = array[src - 1];
                src--;
            }

        }else{
            while(src < dest){
                array[src] = array[src + 1];
                src++;
            }
        }
        array[dest] = temp;
    }

    public static boolean isArrayEmpty(int[] arr){
        return arr == null || arr.length == 0;
    }

    /**测试*/
    public  static void main(String[] args){
        int[] arr1 = null;
        int[] arr2 = {};
        int[] arr3 = {1};
        int[] arr4 = {1,3,6,4,7,5};

        insertElementInArray(arr4, 4, 1);
        printArray(arr4);
    }
}
