package org.lhx.binarysorttree;

/**
 * @author lhx
 * @date 2019/7/11 - 14:33
 */
public class BinarySortTree {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        }
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     *
     * @param node 传入的结点（当作二叉排序树的根节点）
     * @return 返回的是以node为根节点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node) {
        Node temp = node;
        //循环查找左结点
        while (temp.left != null) {
            temp = temp.left;
        }
        //这时temp指向了最小结点
        //删除最小结点
        delNode(temp.value);
        return temp.value;
    }

    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = root.search(value);
            if (targetNode == null) {
                return;
            }
            if (root.left == null && root.right == null) {
                root = null;
            }
            Node parent = root.searchParent(value);
            //如果删除的是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父节点的哪个结点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {//删除有两颗子节点
                int min = delRightTreeMin(targetNode.right);
                targetNode.value = min;
            } else {//删除有一颗子结点
                //如果要删除的结点有左子结点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果targetNode是parent的左子结点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {//targetNode是parent的右子结点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {//要删除的结点有右子结点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }

        }
    }

}

class TestBinarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        binarySortTree.infixOrder();

        System.out.println();
//        binarySortTree.delNode(2);
//        binarySortTree.delNode(5);
//        binarySortTree.delNode(9);
//        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.infixOrder();
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 查找要删除的结点
     * @param value 要删除结点的值
     * @return 返回找到的结点，若没有找到，返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {//要查找的值小于当前结点，向左递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {//要查找的值大于当前结点，向右递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除结点的父节点
     * @param value 要查找的结点的值
     * @return 返回要删除结点的父节点。，没有则返回null
     */
    public Node searchParent(int value) {
        //如果当前结点是要删除结点的父节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前结点的值，并且当前左子结点不为空
            if (value < this.value && this.left != null) {
                //向左子树递归
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                //向右子树递归
                return this.right.searchParent(value);
            } else {
                //没有找到父节点
                return null;
            }
        }
    }

    /**
     * 添加结点的方法
     * 递归形式添加结点满足二叉排序树的要求
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }

        //判断传入结点的值和当前子树的根节点的值的关系
        if (node.value < this.value) {
            //当前结点左子节点为空
            if (this.left == null) {
                this.left = node;
            } else {
                //递归向左子树添加
                this.left.add(node);
            }
        } else {//添加结点大于当前结点的值
            if (this.right == null) {
                this.right = node;
            } else {
                //递归向右子树添加
                this.right.add(node);
            }
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
