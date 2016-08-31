package main.other;

import main.util.NumberUtil;

/**
 * Created by Administrator on 2016/8/30.
 */
public class BitOper {
    public static void main(String[] args) {

        /**test: getNumberOf1*/
//        int n = -14;
//        System.out.println(getNumberOf1(n));
//        System.out.println(getNumberOf1ForBest(n));
//        System.out.println(Integer.toBinaryString(n));

        /**test: powerWithUnsignExponent*/
        double base = 3.0;
        int exponent = 3;
        System.out.println(powerWithUnsignExponent(base, exponent));

    }

    /**
     * 获取整数二进制中1的个数.
     * 思路: 结合右移操作和与运算可以很快解决这个问题.
     * test: (0) (5) (-3)
     *
     * @param n 待查找的整数
     * @return int 返回整数中1的个数.
     */
    public static int getNumberOf1(int n) {
        //此算法可以作为演示:正数右移,然后最后一位与1进行与运算,如果结果是大于0,表示最后1位是1.然后继续右移,直到最后n变为0.
        //但是如果是负数,右移之后左侧补1, 这样的话就陷入死循环了.因此,可以使数字1不断左移,直到左移了32位,然后变为0.
        if (n == 0) {
            return 0;
        }
        int count = 0;
        if (n > 0) {
            while (n != 0) {
                if ((n & 1) != 0) {
                    count++;
                }
                n = n >> 1;
            }
        } else if (n < 0) {
            int flag = 1;
            while (flag != 0) {
                if ((n & flag) != 0) {
                    ++count;
                }
                flag = flag << 1;

            }

        }

        return count;
    }

    /**
     * 获取整数二进制中1的个数.
     * 思路: 发现 n - 1的二进制规律:将n的最右边的1变为0,并且1后面的所有位变为1. 这样,利用n和n-1进行与运算,就可以去掉最右边的1.以此进行运算,直到n变为0;
     * 举个例子:1100 - 1 = 1011. 这样 1100 & 1011 = 1000 > 0, 继续1000 - 1 = 0111, 1000&0111 = 0. 结束.这样,有几个1,就循环几次.
     * test: (0) (5) (-3)
     *
     * @param n 待查找的整数
     * @return int 返回整数中1的个数.
     */
    public static int getNumberOf1ForBest(int n) {
        int count = 0;
        while (n != 0) {
            ++count;
            n = n & (n - 1);
        }

        return count;

    }

    /**
     * 计算数值的正整数次幂 (不考虑大数问题)
     * 用位运算实现高效地算法.位运算主要在这里是进行除2和判断奇数和偶数, 其思路如下:
     * x ^(n/2) * x ^(n/2)   n为偶数
     * x^n =
     * x ^((n-1)/2) * x ^((n-1)/2) * x
     * test:(0.0,0),(0.0, 3),(3.0, 0), (3.0, 1), (3.0, -3), (3.0, 3)
     */

    public static double powerWithUnsignExponent(double base, int exponent) {
        if(exponent < 0){
            throw  new RuntimeException("powerWithUnsignExponent中指数不能为负数");
        }
        if (exponent == 0 ) {
            return 1.0;
        }
        if (exponent == 1) {
            return base;
        }

        double result = powerWithUnsignExponent(base, exponent >> 1);
        result *= result;
        if ((exponent & 1) == 1) {
            result *= base;
        }

        return result;
    }


}
