package org.lhx.avl;

/**
 * @author lhx
 * @date 2019/7/12 - 13:52
 */
public class AVLTree {

    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public Node getRoot() {
        return root;
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

class TestAVLTree {

    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        avlTree.infixOrder();
        System.out.println("平衡前");
        System.out.println("树的高度" + avlTree.getRoot().height());
        System.out.println("树的左子树高度" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度" + avlTree.getRoot().rightHeight());
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
     * @return 返回左子树高度
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * @return 返回右子树高度
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * @return 返回以当前结点为根节点的树的高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 左旋转方法
     */
    private void leftRotate() {
        //以当前根结点的值创建新结点
        Node newNode = new Node(value);
        //新的结点的左子树设置为当前结点的左子树
        newNode.left = left;
        //新节点的右子树设置成当前结点右子树的左子树
        newNode.right = right.left;
        //当前结点的值换成右子结点的值
        this.value = right.value;
        //把当前结点的右子树设置成右子树的右子树
        right = right.right;
        //当前结点的左子树设置成新的结点
        left = newNode;
    }

    /**
     * 右旋转方法
     */
    private void rightRotate() {
        //以当前根结点的值创建新结点
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    /**
     * 查找要删除的结点
     *
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
     *
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
     *
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

        //当添加完一个节点后，如果右子树的高度-左子树 > 1 ,左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果当前右子树的左子树的高度大于右子树的高度
            if (right != null && right.rightHeight() < right.leftHeight()) {
                //先对右子树进行右旋转
                //然后再对当前结点进行左旋转
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }

        //当添加完一个节点后，如果左子树的高度-右子树 > 1 ,右旋转
        if (leftHeight() - rightHeight() > 1) {
            //如果当前左子树的右子树的高度大于左子树的高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前结点的左结点进行左旋转
                left.leftRotate();
                //再对当前结点进行右旋转
                rightRotate();
            } else {
                rightRotate();
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
