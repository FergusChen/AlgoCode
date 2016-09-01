package main.sort;

import main.util.ArrayUtil;

/**
 * Created by ferguschen on 16/9/1.
 */
public class ReOrder {
    public static void main(String[] args) {
        int[] arr1 = null;
        int[] arr2 = {};
        int[] arr3 = {1};
        int[] arr4 = {2};
        int[] arr5 = {1, 3, 2, 4, 5, 19, 28, 30, 31};
        int[] arr6 = {1, 3, 2, 4, 5, 19, 28, 30, 31, 28};

//        reOrderOddEven(arr5);
//        ArrayUtil.printArray(arr5);

        /**test:  reOrderOddEvenKeepPosition*/
        reOrderOddEvenKeepPosition(arr6);
        ArrayUtil.printArray(arr6);

    }

    /**
     * 调整整数数组的元素, 使奇数都位于偶数的前面
     * test: (null) ,({}), ({1}),({2}), ({1,3,2,4,5,19,28,30,31})  ({1,3,2,4,5,19,28,30,31,28}) ({1,3,5}) ({2,4,6})
     * 思路:使用两个指针head, tail. head从前往后找偶数, tail从后往前找奇数,找到之后,偶数和奇数互换位置. 这样,知道head < tail就结束.
     *
     * @param array 整数数组
     */
    public static void reOrderOddEven(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int head = 0;
        int tail = array.length - 1;
        while (head < tail) {
            //当head指向的数是奇数,就++head
            while (head < tail && !isEven(array[head])) {
                ++head;
            }
            //当tail指向的数是偶数,就--tail
            while (head < tail && isEven(array[tail])) {
                --tail;
            }

            ArrayUtil.exchangeElements(array, head, tail);
            ++head;
            --tail;
        }
    }

    /**
     * 调整整数数组的元素, 使奇数都位于偶数的前面. 并且保证原奇数与奇数,偶数与偶数的相对位置
     * test: (null) ,({}), ({1}),({2}), ({1,3,2,4,5,19,28,30,31})  ({1,3,2,4,5,19,28,30,31,28}) ({1,3,5}) ({2,4,6})
     * 思路:使用两个指针pFront,pBack,指向数组开始位置,同时向数组结尾移动. pFront找未处理部分的奇数,pBack找未处理部分的偶数, 并且保证pBack 小于或等于 pFront.
     * 找到后将pBack和pFront -1 的元素都右移,腾出pBack的位置放pFront指向的元素,直到pFront超过数组长度
     *
     * @param array 整数数组
     */
    public static void reOrderOddEvenKeepPosition(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int pBack = 0;
        int pFront = pBack + 1;
        while (pFront < array.length) {
            //pBack 找未处理部分的偶数
            while (pBack < array.length && !isEven(array[pBack])) {
                ++pBack;
            }
            //pFront 找未处理部分的奇数,pBack之前的都是奇数,已经符合要求,就不需要处理了.
            pFront = pBack + 1;
            while (pFront < array.length && isEven(array[pFront])) {
                ++pFront;
            }
            if (pFront < array.length) {
                ArrayUtil.insertElementInArray(array, pFront, pBack);
                ++pBack;
                ++pFront;
            } else {
                //查找结束
                break;
            }
        }
    }

    /**
     * * 调整整数数组的元素, 使奇数都位于偶数的前面. 并且保证原奇数与奇数,偶数与偶数的相对位置
     * test: (null) ,({}), ({1}),({2}), ({1,3,2,4,5,19,28,30,31})  ({1,3,2,4,5,19,28,30,31,28}) ({1,3,5}) ({2,4,6})
     * 思路:用插入排序的思想交换,基本同上.
     *
     * @param array 整数数组
     */
//    public static void reOrderOddEvenkeepPositionInsert(int[] array) {
//        if(array == null || array.length <= 1){
//            return;
//        }
//
//        for(int i = 1; i < array.length - 1; i++){
//            for(int j = i ; j >= 0; j++){
//                if(!isEven(array[j]) && isEven(array[i])){
//                    ArrayUtil.insertElementInArray(array, j, i);
//                }
//            }
//        }
//    }

    public static boolean isEven(int num) {
        return (num & 0x1) == 0;
    }
}
