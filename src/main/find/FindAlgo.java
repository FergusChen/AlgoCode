package main.find;

/**
 * Created by Administrator on 2016/8/30.
 */
public class FindAlgo {
    public static void main(String[] args){
        int[] arr0 = null;
        int[] arr1 = {};
        int[] arr2 = {3};
        int[] arr3 = {2,6,8,20,30,32};
        int[] arr4 = {8,20,30,32,2,6};
        int[] arr5 = {2,6,8,20,30};
        int[] arr6 = {20,30,2,6,8};
        int[] arr7 = {1,0,1,1,1}; //重复元素
        int[] arr8 = {1,1,1,0,1};//重复元素


        /**test： -----FindRotateArray-------*/
        System.out.println(minRotateArray(arr8));
    }

    /**
     * 查找旋转数组中的最小值。
     * 利用二分查找的思想
     * 旋转数组是有序数组的一个旋转。如[1,2,3,4,5]的旋转两个元素：[3,4,5,1,2]， 旋转0个元素：[1,2,3,4,5]
     * test：（null），（）， （旋转2个元素的数组），（旋转0个元素的数组），（3个相同数字的数组）
     * @param array 旋转数组
     * @return int 返回旋转数组最小值，如果数组没有元素，则抛出异常
     * */
    public static int minRotateArray(int[] array){
        if(array == null || array.length  == 0){
            throw new RuntimeException("数组不能为空");
        }
        int low  = 0;       //旋转数组的特点，让low始终指向旋转分界线的前半部分
        int high = array.length - 1;  //旋转数组的特点，让high始终指向旋转分界线的前半部分

        if(low == high){ //如果只有1个元素时，直接返回该元素
            return array[low];
        }
        int mid = low;  //支持“旋转0个元素”的情况，如果第1个元素就是最小值，则直接返回第1个元素

        while (array[low] >= array[high]){
            if(high - low == 1 ){  //当low和high相差1时，证明low指向前半部分的末尾，high指向后半部分的开始，也就是最小元素
                mid = high;
                break;
            }

            mid = (high + low) /2 ;
            if(array[low] == array[mid] && array[high] == array[mid]){
                return minInArray(array, low, high);
            }
            if(array[low] <= array[mid]){
                low = mid;
            }else if(array[high] >= array[mid]){
                high = mid;
            }
        }

        return array[mid];
    }

    public static int minInArray(int[] array, int start, int end) {
        if(array == null || array.length == 0 || start>end){
            throw new RuntimeException("数组不能为空");
        }
        int min = array[0];
        if(array.length == 1){
            return min;
        }
        for(int i = 1; i < array.length; i++){
            if(array[i] < min){
                min = array[i];
            }
        }

        return min;
    }

}
