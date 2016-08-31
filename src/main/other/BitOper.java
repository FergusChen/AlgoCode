package main.other;

/**
 * Created by Administrator on 2016/8/30.
 */
public class BitOper {
    public static void main(String[] args){

        /**test: getNumberOf1*/
        System.out.println(getNumberOf1(5));

    }

    /**
     * 获取整数二进制中1的个数.
     * 思路: 结合右移操作和与运算可以很快解决这个问题.
     * test: (0) (5) (-3)
     * */
    public static int getNumberOf1(int n){
        //此算法可以作为演示:正数右移,然后最后一位与1进行与运算,如果结果是大于0,表示最后1位是1.然后继续右移,直到最后n变为0.
        //但是如果是负数,右移之后左侧补1, 这样的话就陷入死循环了.因此,可以使数字1不断左移,直到左移了32位,然后变为0.
        if(n == 0){
            return 0;
        }
        int count = 0;
        if(n > 0){
            while(n != 0){
                if(n & 1){
                    count++;
                }
                n >> 1;
            }
        }else if(n < 0){
            int flag = 1;
            while (flag != 0){
                if(n & flag){
                    count++;
                }
                flag << 1;
            }
        }

        return count;

    }
}
