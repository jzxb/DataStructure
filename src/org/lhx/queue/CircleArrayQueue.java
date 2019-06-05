package org.lhx.queue;

/**
 * @author lhx
 * @date 2019/6/5 - 13:31
 */
public class CircleArrayQueue {

    //数组最大容量
    private int maxSize;
    //队列头
    private int front;
    //队列尾
    private int rear;
    //存放数据
    private int[] arr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addqueue(int n){
        if (isFull()){
            throw new RuntimeException("队列满");
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        //先将front对应的值保留到一个临时变量
        //把front后移
        //将临时保存的变量返回
        int val = arr[front];
        front = (front + 1) % maxSize;
        return val;
    }

    public void showQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d=%d\t]",i % maxSize,arr[i % maxSize]);
        }
    }

    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        return arr[front];
    }
}
