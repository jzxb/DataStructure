package org.lhx.linkedlist;

/**
 * @author lhx
 * @date 2019/6/16 - 13:24
 */
public class Josephu {

    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(25);
        circleSingleLinkedList.list();

        circleSingleLinkedList.countBoy(1,2,25);
    }

}

//创建一个单向环形链表
class CircleSingleLinkedList {
    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点，构建成一个环状
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums过小");
            return;
        }
        //辅助指针，帮助构建环形链表
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                //构建成环
                boy.setNext(first);
                //指向第一个节点
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    public void list(){
        if (first == null){
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号为 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    //根据输入，计算出圈顺序

    /**
     *
     * @param startNo 表示从第几个小孩开始数
     * @param countNum 表示共数几次
     * @param nums 表示初始圈中共有多少个小孩
     */
    public void countBoy(int startNo, int countNum, int nums){
        //数据校验
        if (first == null || startNo < 0 || startNo > nums || nums < 1){
            System.out.println("参数有误");
            return;
        }
        //辅助指针帮助出圈
        Boy helper = first;
        //将helper指向最后一个节点
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //移动到起始位置
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //报数
        while (true){
            if (helper == first){
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后在圈中的小孩编号%d \n", first.getNo());
    }

}

class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}