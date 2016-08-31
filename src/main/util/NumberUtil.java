package main.util;

/**
 * Created by ferguschen on 16/8/31.
 */
public class NumberUtil {

    /**
     * 判断两个double数据是否相等
     * */
    public static boolean isEqual(double a, double b) {
        return (a - b > -0.0000001 && a - b < 0.0000001);
    }
}
