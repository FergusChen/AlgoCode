package main.find;

/** 面试题3
 * 有一个二维数组，其每一行都按照从左到右的顺序递增，每一列都按照从上到下的顺序递增。
 *  完成一个函数，输入这样的一个二维数组和一个整数，判断该数组中是否包含此整数 数组示例如下图：
 *  1   3   5   10
 *  2   4   9   11
 *  6   7   10  16
 *  9   10  18  21
 *  */

public class FindSortedDim2Array {
    public static void main(String[] args){

        int[][] testArr1 = null;
        int[][] testArr2 = new int[][]{{1,3,6,7,8,9,10,11,13,16}};
        int[][] testArr3 = new int[][]{{1,3,5,10,12},{2,4,9,11,15},{6,7,10,16,18},{9,10,18,21,25}};
        System.out.println(find(testArr3,0));

    }

    /**算法思想： 因为是排序的，则关键是找到一个合适的初始位置，每次比较都缩小一些范围。盲区：习惯性地从中间开始查找。
     * 从右上角开始比较，如果number小于此位置的数，则该列就排除；如果number大于此位置上的数，则该行就被排除。
     * 继续与剩余元素的右上角的比较，用同样的方法，排除元素，直到找到相应的数，或者比较到左下角的数字仍不相等
     * test: (null,7)、 (1*10个元素,7)、(4*5个元素,7), (4*5个元素,0)
     * */
    public static boolean find(int[][] matrix, int key){
        boolean found = false;
        if(matrix == null) return false;

        int rows = matrix.length;
        int columns = 0;
        if(rows > 0){
            columns = matrix[0].length;
        }

        if(rows > 0 && columns > 0){
            int row = 0;
            int column = columns - 1;
            while(row < rows && column >=0){
                int number = matrix[row][column];
                System.out.print(number + "\t");
                if(number == key){
                    found = true;
                    break;
                }else if(number > key){
                    column--;
                }else{
                    row++;
                }
            }

        }
        return found;
    }


}


