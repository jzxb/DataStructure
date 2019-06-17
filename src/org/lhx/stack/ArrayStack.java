package org.lhx.stack;

/**
 * @author lhx
 * @date 2019/6/16 - 16:49
 */
public class ArrayStack {

    private int maxSize;
    private int[] stack;
    private int top = -1;

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int[] getStack() {
        return stack;
    }

    public void setStack(int[] stack) {
        this.stack = stack;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void push(int value){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        stack[++top] = value;
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        return stack[top--];
    }

    public void list(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        for (int i = top; i >= 0; i--){
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

}
