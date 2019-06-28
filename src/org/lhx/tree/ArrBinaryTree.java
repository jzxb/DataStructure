package org.lhx.tree;

/**
 * @author lhx
 * @date 2019/6/28 - 19:15
 */
public class ArrBinaryTree {

    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    public void postOrder() {
        this.postOrder(0);
    }

    public void preOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        //输出当前的元素
        System.out.println(arr[index]);
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    public void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        if ((index * 2 + 1) < arr.length) {
            infixOrder(index * 2 + 1);
        }
        //输出当前的元素
        System.out.println(arr[index]);

        if ((index * 2 + 2) < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    public void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空");
            return;
        }
        if ((index * 2 + 1) < arr.length) {
            postOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }
        //输出当前的元素
        System.out.println(arr[index]);
    }
}

class TestArrBinaryTree {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
        System.out.println();
        arrBinaryTree.infixOrder();
        System.out.println();
        arrBinaryTree.postOrder();
    }
}
