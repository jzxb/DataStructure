package org.lhx.linkedList;

/**
 * @author lhx
 * @date 2019/6/5 - 14:57
 */
public class SingleLinkedList {

    private HeroNode head = new HeroNode(0, "", "");

    public void add(HeroNode heroNode){
        //head指针不能动，需要一个辅助变量辅助遍历
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public void list(){
        //判断链式是否为空
        if (head.next == null){
            throw new RuntimeException("链表为空");
        }
        HeroNode temp = head.next;
        while (true){
            if (temp == null){
                return;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode{
    private int no;
    private String name;
    private String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
