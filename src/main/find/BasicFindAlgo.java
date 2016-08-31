package main.find;

/**
 * Created by Administrator on 2016/8/30.
 */
public class BasicFindAlgo {
    public static void main(String[] args) {

        int[] arr0 = null;
        int[] arr1 = {};
        int[] arr2 = {3};
        int[] arr3 = {2,6,8,20,30,32};
        int[] arr4 = {2,6,8,20,30};

        /**test: -----binarySearch------*/
        System.out.println(binarySearch2(arr4, 3, 0, arr4.length - 1));
    }


    /**
     * 二分查找的非递归算法
     * test： （null） （）， （array.length ==1, array[0] != num），（array.length ==1, array[0] == num）
     * （array是奇数数组, array包含num）,（array是偶数数组, array包含num）
     *（array是奇数数组, array不包含num）,（array是偶数数组, array不包含num）
     *
     * @param array 待查找的有序数组
     * @param num   待查找的数
     * @return int 返回待查找的数在数组中的索引，如果没有找到，则返回-1。
     */
    public static int binarySearch1(int[] array, int num) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int low = 0;
        int high = array.length - 1;

        //array已排序，则先查看num是否在此范围内。
        if (num < array[low] || num > array[high]) {
            return -1;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;  //取中间索引，这样以防栈溢出。整型4个字节可以表示21亿的索引，数组可以存放多少数据呢？如果存放多的话，需要调整jvm栈的大小
            if (array[mid] == num) {
                return mid;
            } else if (array[mid] > num) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }


    /**
     * 二分查找的递归算法
     * test： （null） （）， （array.length ==1, array[0] != num），（array.length ==1, array[0] == num）
     * （array是奇数数组, array包含num）,（array是偶数数组, array包含num）
     * （array是奇数数组, array不包含num）,（array是偶数数组, array不包含num）
     *
     * @param array 待查找的有序数组
     * @param num   待查找的数
     * @return int 返回待查找的数在数组中的索引，如果没有找到，则返回-1。
     */
    public static int binarySearch2(int[] array, int num, int low, int high) {
        if (array == null || array.length == 0 || num < array[low] || num > array[high] || low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;
        if (array[mid] == num) {
            return mid;
        } else if (array[mid] > num) {
            return binarySearch2(array, num, low, mid - 1);
        } else {
            return binarySearch2(array, num, mid + 1, high);
        }

    }

}
