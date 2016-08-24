package main.other;

/**
 * Created by Administrator on 2016/8/23.
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 遇到这个问题，首先考虑在原字符串上替换，还是新创建的字符串，逐渐将结果存储到新字符串。但通常是原字符串上做修改。
 * 这个算法的关键是控制时间复杂度，不是从前往后遍历，因为这样会多次将空格后面的字符串往后移动，应该是从后往前遍历。
 * 并且如果是char[]就要计算好长度
 */
public class ReplaceSpace {
    public static void main(String[] args){
        StringBuffer str = new StringBuffer();
        str.append("We Are Happy");
        char[] strArr = initStrArr(" We Are Happy  ", 30);
        System.out.println(replaceSpace3(str));
        replaceSpace2(strArr, 30);
        System.out.println(strArr);

        //test mergeArray
        int[] arr1 = new int[12];
        arr1[0] = 2;
        arr1[1] = 5;
        arr1[2] = 8;
        arr1[3] = 19;
        arr1[4] = 29;
//        int[] arr1 = null;
        int[] arr2 = new int[5];
        arr2[0] = 4;
        arr2[1] = 19;
        arr2[2] = 20;
        arr2[3] = 36;
        arr1[4] = 29;
//        int[] arr2 = null;
        mergeArray(arr1, arr2, 12);
        printArr(arr1);
    }


    public static void printArr(int[] arr){
        if(isArrayEmpty(arr)){
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        int i = 0;
        while(arr[i] != '\0') {
            System.out.print(arr[i] + ",");
            ++i;
        }
        System.out.println("]");
    }

    public static char[] initStrArr(String str, int capacity){
        char[] strArr = new char[capacity];

        for(int i = 0; i < str.length(); i++){
            strArr[i] = str.charAt(i);
        }

        return strArr;
    }

    /**确定从后往前遍历，就要设置两个指针，第1个指向旧字符串的结尾，第2个指向新字符串的结尾。
     * 两个指针在遍历的过程中，把旧的字符复制到新的位置，如果遇到空格，就替换成%20，直到两个指针相遇
     * test: "We Are Happy", "WeAreHappy", " We Are Happy  ","", null
     * */
    public static String replaceSpace1(StringBuffer str){
        if(str == null || str.length() == 0){
            return "";
        }

        int numberOfSpace = 0;
        for(int i = 0; i<str.length();i++){
            if(str.charAt(i) == ' '){
                numberOfSpace++;
            }
        }

        int newLength = str.length() + numberOfSpace * 2;
        int oldIndex = str.length() - 1;  //指向旧字符串的结尾
        int newIndex = newLength - 1;   //指向新字符串的结尾
        str.setLength(newLength);
        while(oldIndex >= 0 && oldIndex < newIndex){
            if(str.charAt(oldIndex) == ' '){
                str.setCharAt(newIndex--,'0');
                str.setCharAt(newIndex--,'2');
                str.setCharAt(newIndex--,'%');

            }else{
                str.setCharAt(newIndex--, str.charAt(oldIndex));
            }
            --oldIndex;
        }
        return str.toString();
    }

    /**如果传入的是char类型数组，不是StringBuffer，按照数组的方式处理*/
    public static void replaceSpace2(char[] strArr, int capacity){
        if(strArr == null || strArr.length == 0) return;
        //获取空格数量
        int numberOfSpace = 0;
        int oldLength = 0;
        int i = 0;
        while(strArr[i] != '\0'){
            ++oldLength;
            if(strArr[i] == ' '){
                ++numberOfSpace;
            }
            ++i;
        }
        //计算新的长度
        int newLength = oldLength + numberOfSpace * 2;
        if(newLength > capacity) return;

        int newIndex = newLength - 1;
        int oldIndex = oldLength - 1;
        while(oldIndex >= 0 && oldIndex < newIndex){
            if(strArr[oldIndex] == ' '){
                strArr[newIndex--] = '0';
                strArr[newIndex--] = '2';
                strArr[newIndex--] = '%';
            }else{
                strArr[newIndex--] = strArr[oldIndex];
            }
            --oldIndex;
        }

    }
    /**利用正则表达式直接替换，这个没有考察什么，但是应该是最常用的*/
    public static String replaceSpace3(StringBuffer str){
        return str.toString().replaceAll("\\s", "%20");
    }

    /**新创建一个字符串，存储替换后的结果。*/
    public static String replaceSpace4(StringBuffer str){
        StringBuffer result = new StringBuffer();
        for(int i = 0; i< str.length(); i++){
            if(str.charAt(i) == ' '){
                result.append("%20");
            }else{
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    /**举一反三：合并已排序的数组，同样是用从后往前遍历的方法.
     * 此方法将两个排好序的数组合并到数组arr1中
     * test: (null, null ,0),(new int[]{}, new int[]{}, 0) ([2,5,8,19,29],[4,19,20,36], 12), (null,[4,19,20,36], 7),([2,5,8,19,29],null, 12)*/
    public static void mergeArray(int[] arr1, int[] arr2, int capacity){
        if(isArrayEmpty(arr1) && !isArrayEmpty(arr2)){
            arr1 = new int[arr2.length];
            for(int i = 0; i < arr2.length; i++){
                arr1[i] = arr2[i];
            }
            return;
        }

        if(isArrayEmpty(arr2)) return;

        int arr1RealLength = 0;
        int arr2RealLength = 0;
        int i=0;
        while(arr1[i] != '\0'){
            ++arr1RealLength;
            ++i;
        }
        i=0;
        while(arr2[i] != '\0'){
            ++arr2RealLength;
            ++i;
        }
        int newLength =arr1RealLength + arr2RealLength;
        if(newLength > capacity) return;
        int arr1Index = arr1RealLength - 1;
        int arr2Index = arr2RealLength - 1;
        int newIndex = newLength - 1;
        while(arr2Index >= 0){
            if(arr1[arr1Index] >= arr2[arr2Index]){
                arr1[newIndex--] = arr1[arr1Index--];
            }else{
                arr1[newIndex--] = arr2[arr2Index--];
            }

        }
    }

    public static boolean isArrayEmpty(int[] arr){
        return arr == null || arr.length == 0;
    }
}
