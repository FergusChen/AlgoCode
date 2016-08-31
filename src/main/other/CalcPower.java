package main.other;

import main.util.NumberUtil;

/**
 * Created by ferguschen on 16/8/31.
 */
public class CalcPower {
    static boolean  g_InvalidInput = false;
    public static void main(String[] args) {

        /**test: power*/
        double base = 3.0;
        int exponent = 3;
        System.out.println(power(base, exponent));
    }

    /**
     * 计算数值的整数次幂
     *
     * test:(0.0, 0), (0.0, 3),(-3.0, 0),(3.0, 0),(-3.0, 1) (3.0, 1),(-3.0, -3) (3.0, -3), (3.0, 3)
     * @param base 底数
     * @param exponent 指数
     */
    public static double power(double base, int exponent) {
        g_InvalidInput = false;
        //排除计算负整数次幂时,除数为0的情况
        if(NumberUtil.isEqual(base, 0.0) && exponent < 0){
            g_InvalidInput = true;
            return 0.0;
        }
        //0.0的任何次幂设定为0, 这里要事先讨论一下0.0的0次幂,该结果可以为0.0,也可以为1.0; 这一步也可以在下面的方法中计算.
        if (NumberUtil.isEqual(base, 0.0)) {
            return 0.0;
        }

        int absExponent = exponent > 0 ? exponent : -exponent;

        double result = BitOper.powerWithUnsignExponent(base, absExponent);
        if (exponent < 0) {
            result = 1.0 / result;
        }

        return result;

    }
}
