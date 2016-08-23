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
        System.out.println(replaceSpace3(str));
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
            oldIndex--;
        }
        return str.toString();
    }

    /**如果传入的是char类型数组，不是StringBuffer，按照数组的方式处理*/
    public static void replaceSpace2(char[] strArr, int capacity){
        
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
}
