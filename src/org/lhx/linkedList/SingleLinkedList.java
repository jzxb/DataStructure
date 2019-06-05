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

    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;
        //添加的编号是否存在
        boolean flag = false;
        while (true){
            //说明temp在链表的最后
            if (temp.next == null){
                break;
            }
            //位置找到，在temp后面插入
            if (temp.next.getNo() > heroNode.getNo()){
                break;
            }else if (temp.next.getNo() == heroNode.getNo()){//希望添加的hero已经存在
                //说明编号存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag，为真则说明存在，不能添加
        if (flag == true){
            System.out.println("要插入的已经存在");
        }else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
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

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

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
