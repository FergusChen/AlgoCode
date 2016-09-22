package main.other;

/**
 * Created by Administrator on 2016/9/21.
 */
public class SnakeMatrix {
    /**
     * 打印蛇形矩阵。如矩阵：
     * 1,2,3,4,5
     * 16,17,18,19,6
     * 15,24,25,20,7
     * 14,23,22,21,8
     * 13,12,11,10,9
     * 则打印结果为：1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25
     * <p>
     * 思路：观察规律，就是从外围一圈一圈打印矩阵，只是要控制打印结束条件和每一圈打印的下标。
     * 规律：每一圈的start坐标总是相同的行列索引，即(0,0),(1,1),(2,2)...总是满足：rows>start * start，
     * 例如当矩阵行数为5时，最后一圈是(2,2)，满足，2*2 < 5 < 3*3， 矩阵行数为6时，同样如此，可以以此控制打印矩阵圈的循环。
     * 打印矩阵一圈时，索引控制比较复杂，可以画出来。用start作为突破口，循环打印。如矩阵行数为5时，
     * 第1圈打印行：(0,0)->(0,4)，打印列：(1,4)->(4,4), 打印行：(4,3)->(4,0), 打印列:(3,0)
     * <p>
     * test: (null), (0个元素) (1个元素)，（5*5的矩阵），（6*6的矩阵） （5*6的矩阵）
     */
    public static void printSnakeMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        int rows = matrix.length;
        int columns = matrix[0].length;
        int start = 0;

        while (columns > start * 2 && rows > start * 2) {
            printCircleInMatrix(matrix, rows, columns, start);
            ++start;
        }
    }

    private static void printCircleInMatrix(int[][] matrix, int rows, int columns, int start) {
        int endX = columns - 1 - start;
        int endY = rows - 1 - start;

        //按照 → ↓ ← ↑ 的方向打印矩阵中的圈。
        //从左到右打印一行，无论奇数还是偶数，都需要打印这样
        for (int i = start; i <= endX; i++) {
            System.out.print(matrix[start][i] + "\t");
        }

        //从上到下打印一列， 如果这一圈是1*n的，则不需要再继续打印，因此需要判断start < endY
        if (start < endY) {
            for (int i = start + 1; i <= endY; i++) {
                System.out.print(matrix[i][endX] + "\t");
            }
        }

        //从右到左打印一行，如果这一圈是n*1或1*n的，就不需要从右到左打印，因此要这样判断。
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; i--) {
                System.out.print(matrix[endY][i] + "\t");
            }
        }

        //从下到上打印一列，如果这一圈是2*n，则最后一列就不用打印，因此需要判断start < endY - 1
        if (start < endX && start < endY - 1) {
            for (int i = endY - 1; i > start; i--) {
                System.out.print(matrix[i][start] + "\t");
            }
        }
    }

    public static void main(String[] args) {
        int[][] s1 = null;
        int[][] s2 = {};
        int[][] s3 = {{1}};


        int[][] n1 = {{1, 2, 3, 4, 5}, {16, 17, 18, 19, 6}, {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8}, {13, 12, 11, 10, 9}};

        int[][] n2 = {{1, 2, 3, 4, 5, 6}, {20, 21, 22, 23, 24, 7},
                {19, 32, 33, 34, 25, 8}, {18, 31, 36, 35, 26, 9},
                {17, 30, 29, 28, 27, 10}, {16, 15, 14, 13, 12, 11}};

        int[][] n3 = {{1, 2, 3, 4, 5, 6}, {18, 19, 20, 21, 22, 7},
                {17, 28, 29, 30, 23, 8}, {16, 27, 26, 25, 24, 9},
                {15, 14, 13, 12, 11, 10}};

        printSnakeMatrix(n3);
    }

}
