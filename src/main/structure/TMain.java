package main.structure;

import main.structure.Queue;
/**
 * Created by Administrator on 2016/8/24.
 */
public class TMain {
    public static void main(String[] args){
        Queue testQueue = new Queue();

//        testQueue.push(1);
//        testQueue.push(3);
//        testQueue.push(2);
//        while (!testQueue.isEmpty()){
//            System.out.println(testQueue.pop());
//        }
        try {
            testQueue.pop();
        }catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }
    }
}
