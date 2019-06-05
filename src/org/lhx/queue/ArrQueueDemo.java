package org.lhx.queue;

/**
 * @author lhx
 * @date 2019/6/5 - 10:12
 */
public class ArrQueueDemo {
}

class ArrQueue {
    //数组最大容量
    private int maxSize;
    //队列头
    private int front;
    //队列尾
    private int rear;
    //存放数据
    private int[] arr;

    public ArrQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部，指向队列头的前一个位置
        rear = -1;//指向队列尾部，指向队列尾的具体数据（就是队尾的最后一个数据）
    }

    public boolean isFull(){
        return rear == maxSize - 1;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public void addQueue(int n){
        if (isFull()){
            throw new RuntimeException("队列满");
        }

        arr[++rear] = n;
    }

    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }

        return arr[++front];
    }

    public void showQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d=%d\t]",i,arr[i]);
        }
    }

    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空");
        }
        return arr[front + 1];
    }
}
