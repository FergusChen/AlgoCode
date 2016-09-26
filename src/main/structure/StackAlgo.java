package main.structure;

import java.util.Stack;

/**
 * Created by Administrator on 2016/9/23.
 */
public class StackAlgo {


    /**
     * 判断一个序列是否为栈的弹出序列。
     * 思路：构造一个辅助栈，利用栈顶元素与弹出序列比较。可以画图表示。
     * test：（null，null），（[1],[1]）,([1,2,3],[1])，([1,2,3],[2,1,3]), ([1,2,3,4,5],[4,3,5,1,2])
     * */
    public static boolean isPopOrder(int[] pushOrder, int[] popOrder){
        if(pushOrder == null || popOrder == null || pushOrder.length != popOrder.length || pushOrder.length == 0){
            return false;
        }

        boolean bPossible = false;

        int pPush = 0;
        int pPop = 0;
        Stack stackData = new Stack();
        while (pPop < popOrder.length){

            //向辅助栈中添加元素。
            while(stackData.empty() || (int)stackData.peek() != popOrder[pPop]){
                if(pPush == pushOrder.length){
                    break;
                }
                stackData.push(pushOrder[pPush]);
                pPush++;
            }

            //以上while保证辅助栈栈顶与popOrder[pPop]相同，除非pushOrder已经全部入栈
            //如果此时栈顶元素不等于popOrder[pPop]，则证明此序列非弹出序列。
            if((int)stackData.peek() != popOrder[pPop]){
                break;
            }

            //必须保证stackData非空
            stackData.pop();
            pPop++;

        }

        if(stackData.empty() && pPop == popOrder.length){
            bPossible = true;
        }
        return bPossible;
    }
}
