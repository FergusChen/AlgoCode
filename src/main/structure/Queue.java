package main.structure;

import java.util.Stack;

/**
 * Created by Administrator on 2016/8/24.
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * 思路：有两个栈，栈1和栈2.当入栈的时候，我们将它全放进栈1中，直接用栈1的push方法。
 * 当需要出栈的时候，我们将栈1出栈到栈2中，然后再将栈2依次出栈。这样就实现了队列的先进先出
 * test：push(1,3,2),pop();  不push，直接pop()；
 */
public class Queue {
    private Stack<Integer> stack1 = new Stack<Integer>();
    private Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int data){
        stack1.push(new Integer(data));
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("Queue is Empty!");
        }
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop().intValue();
    }

    public boolean isEmpty(){
        return stack1.isEmpty() && stack2.isEmpty();
    }


}

