package test;

import java.util.Scanner;

/**
 * @author lhx
 * @date 2019/6/30 - 19:26
 */
public class LinkedList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        Node head = new Node(-1);
        Node cur = head;
        for (int i = 0; i < length; i++) {
            int num = sc.nextInt();
            Node node = new Node(num);
            cur.setNext(node);
            cur = cur.getNext();
        }
        int k = sc.nextInt();
        if (k >= length) {
            System.out.println((String) null);
            return;
        }
        System.out.println(kNum(head, k, length));
    }

    public static int kNum(Node head, int k, int length) {
        Node cur = head.getNext();
        for (int i = 1; i < length - k; i++) {
            cur = cur.getNext();
        }
        return cur.getData();
    }

}

class Node {
    private Node next;
    private int data;

    public Node(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
