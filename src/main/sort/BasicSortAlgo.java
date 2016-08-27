package main.sort;

/**
 * Created by Administrator on 2016/8/25.
 * 一些基本排序算法的分析和实现。
 * 主要包括：
 * --插入排序
 */

public class BasicSortAlgo {

    public static void main(String[] args) {

        int[] arr1 = null;
        int[] arr2 = {};
        int[] arr3 = {1};
        int[] arr4 = {3, 2, 8, 9, 5};
        int[] arr5 = {-1, 2, 7, 5, 19, 12, 17};


        /**test： ------插入排序------*/
        insertSort(arr4);
        printArray(arr4);

        /**test: -------冒泡排序-------*/
//        bubbleSort(arr5);
//        printArray(arr5);

        /**test: -------选择排序------*/
//        selectSort(arr5);
//        printArray(arr5);
        /**test: ------归并排序------*/
//        mergeSort(arr5, 0 , arr5.length-1);
//        printArray(arr5);

        /**test: ------快速排序------*/
//        int[] arr6 = {3, 2, 7, 6, 8, 4, 5};
//        quickSort(arr6, 0, arr6.length - 1);
//        printArray(arr6);

        /**test: -------堆排序-------*/
//        heapSort(arr5);
//        printArray(arr5);


    }

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

    /**
     * ------插入排序------
     *
     * @param array 待排序数组
     *              test: (null), （1个元素的数组），（空数组），(5个无序整数数组)，（6个无序整数数组）
     */
    public static void insertSort(int[] array) {
        if (array == null || array.length <= 1) {
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
                    break;//0到i-1位置的元素都已经有序，所有大于curData的元素右移之后就可以结束
                }
            }
            array[posForInsert] = curData;
        }
    }

    /**
     * 冒泡排序
     *
     * @param array 待排序数组
     *              test: (null), （1个元素的数组），（空数组），(5个无序整数数组)，（6个无序整数数组）
     */
    public static void bubbleSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    exchangeElements(array, j, j + 1);
                }

            }
        }

    }

    /**
     * 选择排序
     *
     * @param array 待排序数组
     *              test: (null), （1个元素的数组），（空数组），(5个无序整数数组)，（6个无序整数数组）
     */
    public static void selectSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            exchangeElements(array, i, minIndex);
        }
    }

    /**
     * 归并排序
     *
     * @param array 待排序数组
     * @param low   待排序数组序列的最低位置
     * @param high  待排序数组序列的最高位置
     *              test: (null), （1个元素的数组），（空数组），(5个无序整数数组)，（6个无序整数数组）
     */
    public static void mergeSort(int[] array, int low, int high) {
        if (array == null || array.length <= 1) {
            return;
        }
        int mid = (low + high) / 2;
        if (low < high) {
            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
    }

    /**
     * 归并过程
     *
     * @param array 待合并的数组
     * @param low   待合并数组序列的最低位置
     * @param mid   待合并数组序列中划分两个有序序列的位置
     * @param high  待合并数组序列的最高位置
     *              将array中 low到high的元素归并到有序序列，其中，low到mid的序列是有序的，mid+1到high的序列是有序的。
     */
    public static void merge(int[] array, int low, int mid, int high) {
        int[] tempArr = new int[high - low + 1];

        int i = low; //i指向已排好序的左序列
        int j = mid + 1;  //j指向已排好序的右序列

        int k = 0; //合并到新数组的指针

        //合并过程
        while (i <= mid && j <= high) {
            if (array[i] < array[j]) {
                tempArr[k++] = array[i++];
            } else {
                tempArr[k++] = array[j++];
            }
        }

        //若左边有序序列有剩余元素，将剩余元素合并到tempArr中
        while (i <= mid) {
            tempArr[k++] = array[i++];
        }

        //若右边有序序列有剩余元素，将剩余元素合并到tempArr中
        while (j <= high) {
            tempArr[k++] = array[j++];
        }

        //用合并后的有序序列覆盖原数组中的左右两部分序列
        for (k = 0; k < tempArr.length; k++) {
            array[k + low] = tempArr[k];
        }

    }

    /**
     * 堆排序
     */
    public static void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        buildMaxHeap(array);
        for (int i = array.length - 1; i >= 1; i--) {
            exchangeElements(array, 0, i);

            maxHeapify(array, i, 0);
        }
    }

    /**
     * 维护堆
     * 在这个堆中，只有index位置的元素违背了最大堆的性质，调整该元素的位置，使index位置元素为结点为根的堆仍然是最大堆。
     * 思想：index位置的元素与其左右孩子比较，将三个元素中的最大值与index位置的元素交换，然后递归地向下调整
     */
    public static void maxHeapify(int[] array, int heapSize, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        int largest = index;
        if (left < heapSize && array[left] > array[index]) {
            largest = left;
        }
        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != index) {
            exchangeElements(array, index, largest);

            maxHeapify(array, heapSize, largest);//递归地向下维护堆
        }
    }

    /**
     * 新建最大堆
     */
    public static void buildMaxHeap(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        int mid = (array.length - 1) / 2;
        for (int i = mid; i >= 0; i--) {
            maxHeapify(array, array.length, i);
        }
    }

    /**
     * 快速排序
     *
     * @param array 带排序的数组
     * @param low   带排序数组的最低位置
     * @param high  带排序的数组的最高位置
     *              test: (null), （1个元素的数组），（空数组），(5个无序整数数组)，（6个无序整数数组）
     */
    public static void quickSort(int[] array, int low, int high) {
        if (array == null || array.length <= 1) {
            return;
        }
        if (low < high) {
            int p = randomPartition(array, low, high);
            quickSort(array, low, p - 1);
            quickSort(array, p + 1, high);
        }
    }

    /**
     * 随机选取数组中的一个数进行划分
     *
     * @param array 带划分的数组序列
     * @param low   带划分数组序列的最低位置
     * @param high  带划分数组序列的最高位置
     * @return int 返回最后划分后，主元的位置    *
     */
    public static int randomPartition(int[] array, int low, int high) {
        int i = (int) (low + Math.random() * (high - low + 1)); //产生low到high之间的随机数
        exchangeElements(array, high, i);
        return partition(array, low, high);
    }

    /**
     * 划分数组
     * 将array中low 到 high序列划分为两部分，并返回划分元的位置
     *
     * @param array 带划分的数组序列
     * @param low   带划分数组序列的最低位置
     * @param high  带划分数组序列的最高位置
     * @return int 返回最后划分后，主元的位置
     */
    public static int partition(int[] array, int low, int high) {
        int key = array[high]; //主元，即用于划分的元素


        int i = low - 1; //i指向 小于key的序列的尾部
        //j来遍历整个序列，将小于key的元素放到i的左边
        for (int j = low; j < high; j++) {
            if (array[j] <= key) {//不仅是小于key，连等于key的元素也交换，以保证划分过程是原址重排
                i++;
                //如果i == j 就无需交换了。
                if (i != j) {
                    exchangeElements(array, i, j);
                }
            }
        }
        //此时，i指向小于key的序列的尾部，则将key与i+1的元素交换，就将整个序列划分为A、B两个部分，其中A：小于或等于key的元素，B：大于key的元素。
        exchangeElements(array, i + 1, high);
        //返回用于划分的主元的位置。
        return i + 1;
    }


}
