package main.structure;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by Administrator on 2016/9/23.
 */
public class StackWithMin {
    public Stack<Integer> data_stack;
    private Stack<Integer> min_stack;

    public StackWithMin() {
        this.data_stack = new Stack();
        this.min_stack = new Stack();
    }

    public void push(Integer value) {
        this.data_stack.push(value);
        if (!this.min_stack.empty()) {
            if (value < this.min_stack.peek()) {
                this.min_stack.push(value);
            } else {
                this.min_stack.push(this.min_stack.peek());
            }
        } else {
            this.min_stack.push(value);
        }

    }

    public Integer pop() {
        if (!this.data_stack.empty() && !this.min_stack.empty()) {
            this.min_stack.pop();
            return this.data_stack.pop();
        } else {
            throw new EmptyStackException();
        }
    }

    public Integer min() {
        if (!this.data_stack.empty() && !this.min_stack.empty()) {
            return this.min_stack.peek();
        } else {
            throw new EmptyStackException();
        }
    }
}
