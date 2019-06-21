package org.lhx.hashtab;

import java.util.Scanner;

/**
 * @author lhx
 * @date 2019/6/21 - 15:47
 */
public class HashTab {

    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTab(int size) {
        this.size = size;
        this.empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public int hashFun(int id) {
        return id % size;
    }

    public void add(Emp emp) {
        int empLinkedListNo = hashFun(emp.id);
        empLinkedLists[empLinkedListNo].add(emp);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
    }

    public void del(int id) {
        int empLinkedListNo = hashFun(id);
        empLinkedLists[empLinkedListNo].del(id);
    }

    public void findEmpById(int id) {
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedLists[empLinkedListNo].findById(id);
        if (emp != null) {
            System.out.println("在第"+ (empLinkedListNo + 1) +"条链表中找到");
        } else {
            System.out.println("未找到");
        }
    }
}

class TestHashTab {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("del:  删除雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "del":
                    System.out.println("输入要删除的id");
                    id = scanner.nextInt();
                    hashTab.del(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class EmpLinkedList {
    private Emp head;

    /**
     * 假定添加雇员时id是自增长的，id的分配总是从小到大
     * 因此直接将雇员加入到链表最后
     * @param emp 待加入的雇员
     */
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp cueEnp = head;
        while (true) {
            if (cueEnp.next == null) {
                break;
            }
            cueEnp = cueEnp.next;
        }
        cueEnp.next = emp;
    }

    public void list(int no) {
        if (head == null) {
            System.out.println("第"+ (no + 1) +"链表为空");
            return;
        }
        System.out.println("第"+ (no + 1) +"链链表信息为");
        Emp curEmp = head;
        while (true) {
            System.out.println(curEmp);
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
    }

    public Emp findById(int id) {
        if (head == null) {
            System.out.println("链表空");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break;
            }
            if (curEmp.next == null) {
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }

        return curEmp;
    }

    public void del(int id) {
        if (head.id == id) {
            head = head.next;
            return;
        }
        Emp temp = head;
        boolean flag = false;
        while (true){
            if (temp.next == null){
                //链表遍历结束
                break;
            }
            if(temp.next.id == id){
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
}
