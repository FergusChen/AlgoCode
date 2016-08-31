package main.other;

/**
 * Created by Administrator on 2016/8/30.
 * 生成斐波那契数，
 * 0    n = 0
 * f(n) =   1    n = 1
 * f(n-1) + f(n - 2)  n > 1
 */
public class Fibonacci {
    public static void main(String[] args) {

        System.out.println(fibonacciRecursion(9));
        System.out.println(fibonacci(9));
    }

    /**
     * 生成斐波那契数列的第n个数
     *
     * 分析：递归实现是最常规的实现，但是这样的实现有个严重的效率问题，就是大量的重复计算（可以手动画一下，计算f(10)的过程试试）
     * 这样，其实随着n的增大，重复计算也会迅速增加。其实该算法的时间复杂度是以n的指数的方式递增。
     * test: (-1), (0), (1),(5), (7)
     * @param n 数列第n个元素，要求n非负
     * @return long 斐波那契数列的第n个数。
     */
    public static long fibonacciRecursion(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
    }

    /**
     * 斐波那契数列的第n个数
     *
     * 避免递归算法的重复计算，从下往上计算第n个数。一次循环，时间复杂度是O(n)
     * test: (-1), (0), (1),(5), (7)
     * @param n 数列第n个元素，要求n非负
     * @return long 斐波那契数列的第n个数。
     */

    public static long fibonacci(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            int fibN = 0;
            int fibPrev1 = 1;
            int fibPrev2 = 0;
            for (int i = 2; i <= n; i++) {
                fibN = fibPrev1 + fibPrev2;
                fibPrev2 = fibPrev1;
                fibPrev1 = fibN;
            }
            return fibN;
        }
    }

    /**【拓展】
     * 斐波那契数有很多应用，只要规律服从f(n) = f(n-1) + f(n-2)即可
     * 1、青蛙跳台阶的问题：
     *      一只青蛙一次可以跳1级台阶，也可以跳2个台阶，问该青蛙跳上一个n级的台阶总共有几种跳法
     *      分析：我们把n级台阶的跳法看成是n的函数，记作f(n)，不难看出：f(1) = 1; f(2) = 2.（一次跳1级；一次跳2级）。则对于n级台阶，可以这样分析：
     *      若该青蛙第1步跳1个台阶，则剩下的有多少种跳法相当于求解f(n-1)
     *      若该青蛙第1步跳2个台阶，则剩下的有多少种跳法相当于求解f(n-2)
     *      则可以得出：f(n) = f(n-1) + f(n-2) 就是斐波那契数列。
     *
     * 2、填充方格的方法数：
     *      用 2*1 的小矩形覆盖 2*8 的矩形，总共有多少种方法？
     *      分析：可以动手画一下， 可以将覆盖2*n的矩形的方法数看成是n的函数，记作f(n)，
     *      同样的，先用2*1的矩形竖着覆盖最左侧，则剩下的相当于求解f(n-1)
     *      用2个2*1句矩形横着覆盖最左侧的两列，则剩下的相当于求解f(n-2)
     *      则可以得出：f(n) = f(n-1) + f(n-2) 就是斐波那契数列.
     *
     * 注：这两个问题的初始值不一样。但是解法和斐波那契数列是一样的。
     * */
}
