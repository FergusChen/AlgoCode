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
//        System.out.println(minRotateArray(arr8));

        //test: moreThanHalfNum
        int[] arr9 = {1,2,3,4,0,5,6,2,2};
//        int halfNum = moreThanHalfNum(arr9, arr9.length);
        int halfNum = moreThanHalfNum2(arr9, arr9.length);
        System.out.println(halfNum);

        //test：findKMin
//        int kmin = findKMin(arr4, 0,arr4.length - 1,3);
//        int kMax = findKMax(arr7, 0, arr7.length - 1, 4);
//        System.out.println("第k大的元素："+ kMax);


        //test: printMaxMin
//        int[] arr = {2,2};
//        printMaxMin(arr3);
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
        int high = array.length - 1;  //旋转数组的特点，让high始终指向旋转分界线的后半部分

        if(low == high){ //如果只有1个元素时，直接返回该元素
            return array[low];
        }
        int mid = low;  //支持“旋转0个元素”的情况，如果第1个元素就是最小值，则直接返回第1个元素

        while (array[low] >= array[high]){
            if(high - low == 1 ){  //当low和high相差1时，证明low指向前半部分的末尾，high指向后半部分的开始，也就是最小元素
                mid = high;
                break;
            }

            mid = (high + low) / 2 ;
            //如果数组中很多相同元素，旋转后low != high,但array[low] == array[high]，就只能按照一般的查找办法
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

    /**
     * 查找数组中出现次数超过一半的数字
     * 如下数组：[1,2,3,3,2,2,6,2,2]，其中数字2出现了5次，超过了数组长度的一半，则返回2.
     * test：(null) ,(1个元素的数组),(9个元素的数组，包含超过一半的数字)，（9个元素的数组，但并不包含超过一半的数字）
     * 注：当没有找到时，返回0
     * */
    /**
     * 思路：如果数组是已经排好序的，那么找中间的数就可以直接判断。但排序需要O(NlogN)的时间复杂度，需要更高效的做法。
     * 解法1：题目的要求其实就是找到排序数组中间的元素，就是中位数，
     * 我们有O(n)的成熟算法来得到第k大的数字，就是利用partition的思想。下面是利用此思想的实现。
     * */
    static boolean g_bInputInvalid = false;
    public static int moreThanHalfNum(int[] arr, int length){

        if(checkArrayInvalid(arr, length)){
            return 0;
        }

        int middle = length >> 1;
        int low = 0;
        int high = length - 1;
        int index = partition(arr, low, high);
        while (index != middle){
            if(index < middle){
                index = partition(arr, index + 1, high);
            }else{
                index = partition(arr, low, index - 1);
            }
        }

        int result = arr[middle];
        if(!checkMoreThanHalf(arr, length, result)){
            result = 0;
        }
        return result;
    }


    public static boolean checkArrayInvalid(int[] arr, int length){
        g_bInputInvalid = false;
        if(arr == null || length <= 0){
            g_bInputInvalid = true;
        }
        return g_bInputInvalid;
    }

    /**
     * 测试number是否是arr中超过一半的数字，
     * */
    public static boolean checkMoreThanHalf(int[] arr, int length, int number){
        int times = 0;
        for(int i = 0; i < length; i++){
            if(arr[i] == number){
                times++;
            }
        }

        boolean isMoreThanHalf = true;
        g_bInputInvalid = false;
        if(times * 2 < length){
            isMoreThanHalf = false;
            g_bInputInvalid = true;
        }
        return isMoreThanHalf;
    }

    /**
     * 找到数组中次数超过一半的数（解法二）
     * 解法二的思路很奇妙，就是说，次数超过一半的数字，其出现次数一定比其它数字出现次数之和还要多。
     * 因此，可以用number记录数字，用times记录出现次数，如果是同一个number，则times+1，如果是不同的number，则times - 1
     * 如果times已经减到0了，则把number更换成当前的数
     * 这样，遍历一次数组，最终number指向的数字就是最有可能是次数超过一半的数字。当然，还要检查是否有超过一半
     *
     * */
    public static int moreThanHalfNum2(int[] arr, int length){
        if(checkArrayInvalid(arr,length)){
            return 0;
        }

        int number = arr[0];
        int times = 1;
        for(int i = 1; i < length; i++){
            if(times == 0){
                number = arr[i];
                times = 1;
            }else if(arr[i] == number){
                times++;
            }else{
                times--;
            }
        }

        if(!checkMoreThanHalf(arr, length, number)){
            return 0;
        }
        return number;
    }

    public static int partition(int[] arr, int low, int high){
        int key = arr[high];
        int i = low - 1;
        for(int j = low; j < high; j++){
            if(arr[j] < key){
                i++;
                if(i != j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        int divideIndex = i + 1;
        int temp = arr[divideIndex];
        arr[divideIndex] = arr[high];
        arr[high] = temp;
        return divideIndex;
    }

    /**找到数组中第k小的数，即把数组从小到大排序，第1个数就是第1小的，第2个数就是第2小。
     * 用partition的思想来实现，
     * test: (null), (空数组)， （只有1个元素的数组）， （有9个元素的数组）
     * 如果没有找到，则返回0
     * */
    public static int findKMin(int[] arr,int low, int high, int k) {
        if(arr == null || arr.length == 0 || k > arr.length){
            return 0;
        }

        int index = partition(arr, low, high); //比划分元小的都在划分元左边，其它的在划分元右边，返回划分元的index. partition找到的是第(index + 1)小的元素

        int leftNum = index - low + 1; //划分后左区间的元素个数。

        if(k == leftNum){
            return arr[index];
        }else if(k < leftNum){
            return findKMin(arr, low, index - 1, k);
        }else{
            return findKMin(arr, index + 1, high, k - leftNum);
        }
    }

    /**
     * 找第k大的元素
     * 思路：事实上就是找第(length - k + 1)小的元素
     * */
    public static int findKMax(int[] arr, int low, int high, int k){
        if(arr == null || arr.length == 0 || k > arr.length){
            return 0;
        }

        return findKMin(arr, low, high, arr.length - k + 1);
    }

    /**
     * 同时找到最小值和最大值，并打印
     * 思路：不是循环两次分别找到最小值和最大值。而是先找出最大值和最小值，然后接下来两个元素比较，大的与最大值比较，小的与最小值比较。
     * 这样，每两个元素需要3次比较，所以总的时间复杂度是O(3n/2)。思路很好，但代码相对复杂，在实际开发中视情况而定。
     * test:(null),(1个元素的数组)，（2个元素相同的数组），（7个元素的数组）
     * */
    public static void printMaxMin(int[] arr){
        if(arr == null || arr.length == 0){
            return;
        }

        int min, max, begin;
        //根据数组长度是奇数还是偶数，初始化min和max
        if((arr.length & 0x1) == 1){
            min = arr[0];
            max = arr[0];
            begin = 1;
        }else{
            if(arr[0] <= arr[1]){
                min = arr[0];
                max = arr[1];
            }else{
                min = arr[1];
                max = arr[0];
            }
            begin = 2;
        }

        //循环两个两个比较，最后一趟比较是arr[length - 2]和arr[length - 1]
        for(int i = begin; i < arr.length - 1; i+=2){
            if(arr[i] <= arr[i + 1]){
                if(min > arr[i]){
                    min = arr[i];
                }
                if(max < arr[i + 1]){
                    max = arr[i + 1];
                }
            }else{
                if(min > arr[i + 1]){
                    min = arr[i + 1];
                }
                if(max < arr[i]){
                    max = arr[i];
                }
            }
        }

        System.out.println("min:"+ min + "\tmax:"+ max);
    }



}
