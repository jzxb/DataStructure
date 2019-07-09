package org.lhx.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lhx
 * @date 2019/7/9 - 13:26
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        }
    }

    public static Node createHuffmanTree(int[] arr) {
        //为了操作方便，遍历arr，将arr每个元素构成Node，将Node放入ArrayList
        List<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }

        while (nodes.size() > 1) {

            //排序 从小到大
            Collections.sort(nodes);
            System.out.println(nodes);

            //取出权值最小的两颗二叉树
            Node left = nodes.get(0);
            Node right = nodes.get(1);

            //构建新的二叉树
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            //从List中删除用过的结点
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }

        //返回哈夫曼树的root结点
        return nodes.get(0);
    }

}

class Node implements Comparable<Node> {
    //结点权值
    int value;
    Node left;
    Node right;

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
