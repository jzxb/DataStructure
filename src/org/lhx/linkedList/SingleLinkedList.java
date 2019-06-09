package org.lhx.linkedList;

import java.util.Stack;

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

    //修改节点信息，根据no编号修改
    public void update(HeroNode heroNode){
        if (head.next == null){
            throw new RuntimeException("链表为空");
        }

        HeroNode temp = head.next;
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
        HeroNode temp = head;
        //标记是否找到待删除的节点
        boolean flag = false;
        while (true){
            if (temp == null){
                //链表遍历结束
                break;
            }
            if(temp.next.getNo() == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if (flag == true){
            temp.next = temp.next.next;
        }else {
            System.out.println("要删除的节点不存在");
        }
    }

    public HeroNode mergeTwoLists(HeroNode l1, HeroNode l2) {
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        HeroNode cur = new HeroNode(0,"","");
        HeroNode res = cur;
        while (l1 != null && l2 != null){
            if (l1.getNo() <= l2.getNo()){
                res.next = l1;
                res = res.next;
                l1 = l1.next;
            }else {
                res.next = l2;
                res = res.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            res.next = l2;
        } else {
            res.next = l1;
        }
        return cur.next;
    }

    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if (head.next == null){
            return null;
        }

        //遍历得到链表长度
        int length = getLength(head);
        //校验index是否有效
        if (index <= 0 || index > length){
            return null;
        }

        HeroNode cur = head.next;
        //循环遍历到index的位置
        for (int i = 0; i < length - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public static void reversePrint(HeroNode head){
        if (head.next == null){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    public static void reverseList(HeroNode head){
        //当前链表为空，或只有一个节点，无需反转直接返回
        if (head.next == null || head.next.next == null){
            return;
        }

        //定义一个辅助变量，帮助遍历链表
        HeroNode cur = head.next;
        //指向当前节点【cur】的下一个节点
        HeroNode next = null;
        HeroNode reverseNode = new HeroNode(0,"","");
        //遍历链表，每遍历一个节点，就将其取出，并放在新的链表的reverseNode的最前端
        while (cur != null){
            //先暂时保存当前节点的下一个节点
            next = cur.next;
            //将cur的下一个节点指向新的链表的最前端
            cur.next = reverseNode.next;
            reverseNode.next = cur;
            //cur后移
            cur = next;
        }
        head.next = reverseNode.next;
    }

    /**
     *
     * @param head 链表的头节点
     * @return  有效节点的个数
     */
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }

        int length = 0;
        HeroNode cur = head.next;
        while (cur != null){
            length++;
            cur = cur.next;
        }
        return length;
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
