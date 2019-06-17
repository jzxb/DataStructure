package org.lhx.stack;

/**
 * @author lhx
 * @date 2019/6/17 - 14:32
 */
public class LinkedListStack<T>{

    private ListNode<T> top;
    private int maxSize;
    private int length;

    public LinkedListStack(int maxSize) {
        top = new ListNode<>();
        length = 0;
        this.maxSize = maxSize;
    }

    public boolean isFull(){
        return length == maxSize;
    }

    public boolean isEmpty(){
        return length == 0;
    }

    public void push(T t){
        if (isFull()){
            System.out.println("栈满");
            return;
        }

        ListNode node = new ListNode(t);
        node.setNext(top);
        top = node;
        length++;
    }

    public T pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        ListNode node = top;
        top = top.getNext();
        length--;
        return (T) node.getData();
    }

    public void list(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        ListNode cur = top;
        for (int i = 0; i < length; i++) {
            System.out.printf("stack[%d] = %d\n", i, cur.getData());
            cur = cur.getNext();
        }
    }

}

class ListNode<T>{

    private T data;
    private ListNode next;

    public ListNode() {
    }

    public ListNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
