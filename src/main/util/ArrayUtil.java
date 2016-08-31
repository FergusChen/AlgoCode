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
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
