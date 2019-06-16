package org.lhx.linkedlist;

/**
 * @author lhx
 * @date 2019/6/16 - 10:18
 */
public class DoubleLinkedList {

    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    public void list(){
        //判断链式是否为空
        if (head.next == null){
            throw new RuntimeException("链表为空");
        }
        HeroNode2 temp = head.next;
        while (true){
            if (temp == null){
                return;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加一个节点到链表的最后
    public void add(HeroNode2 heroNode){
        //head指针不能动，需要一个辅助变量辅助遍历
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true){
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void update(HeroNode2 heroNode){
        if (head.next == null){
            throw new RuntimeException("链表为空");
        }

        HeroNode2 temp = head.next;
        //表示是否找到该节点
        boolean flag = false;
        while(true){
            if (temp == null){
                //链表遍历结束
                break;
            }
            if (temp.getNo() == heroNode.getNo()){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag == true){
            temp.setName(heroNode.getName());
            temp.setNickname(heroNode.getNickname());
        }else{
            System.out.println("没有找到对应的节点");
        }
    }

    public void del(int no){

        if (head.next == null){
            System.out.println("链表为空，不能删除");
            return;
        }
        HeroNode2 temp = head.next;
        //标记是否找到待删除的节点
        boolean flag = false;
        while (true){
            if (temp == null){
                //链表遍历结束
                break;
            }
            if(temp.getNo() == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag){
            temp.pre.next = temp.next;
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.println("要删除的节点不存在");
        }
    }

}

class HeroNode2{
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
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}