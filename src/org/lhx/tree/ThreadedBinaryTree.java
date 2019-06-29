package org.lhx.tree;


/**
 * @author lhx
 * @date 2019/6/28 - 20:09
 */

class TestThreadedBinaryTree {
    public static void main(String[] args) {
        HeroNode2 root = new HeroNode2(1, "1");
        HeroNode2 node2 = new HeroNode2(3, "2");
        HeroNode2 node3 = new HeroNode2(6, "3");
        HeroNode2 node4 = new HeroNode2(8, "4");
        HeroNode2 node5 = new HeroNode2(10, "5");
        HeroNode2 node6 = new HeroNode2(14, "6");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        tree.threadedNodes();

        System.out.println(node5);

        HeroNode2 left = node5.getLeft();
        HeroNode2 right = node5.getRight();
        System.out.println(left);
        System.out.println(right);

        tree.threadedList();
    }
}

public class ThreadedBinaryTree {

    private HeroNode2 root;

    //为了实现线索化，需要创建一个指向当前结点的前驱结点的指针,在递归线索化时，pre总是保留前一个结点
    private HeroNode2 pre = null;

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * 遍历线索化二叉树
     */
    public void threadedList() {
        //定义一个遍历，存储当前遍历的结点，从root开始
        HeroNode2 node = root;
        while (node != null) {
            //循环找到leftType == 1的结点
            //node随着遍历而变化
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }

            //打印当前结点
            System.out.println(node);
            //当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            //替换遍历的结点
            node = node.getRight();
        }
    }

    //二叉树中序线索化方法
    /**
     *
     * @param node 当前需要线索化的结点
     */
    public void threadedNodes(HeroNode2 node) {
        if (node == null) {
            return;
        }
        //先线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前结点
        //处理当前结点的前驱结点
        if (node.getLeft() == null) {
            //让当前结点左指针指向前驱结点
            node.setLeft(pre);
            //修改当前接结点的左指针类型
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            //前驱结点的右指针指向当前结点
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;
        //线索化右子树
        threadedNodes(node.getRight());
    }

    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("空树无法删除");
        }
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public HeroNode2 preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode2 infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode2 postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

}

class HeroNode2 {
    private int no;
    private String name;
    private HeroNode2 left;
    private HeroNode2 right;

    //如果leftType == 0，表示指向左子树，为1则表示指向前驱结点
    private int leftType;
    //如果rightType == 0，表示指向左子树，为1则表示指向后继结点
    private int rightType;

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

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    public void preOrder() {
        System.out.println(this);
        //递归向左子树遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public void infixOrder() {
        //递归向左子树遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        //递归向右子树遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public HeroNode2 preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        HeroNode2 res = null;
        if (this.left != null) {
            res = this.left.preOrderSearch(no);
        }
        if (res != null) {
            return res;
        }
        if (this.right != null) {
            res = this.right.preOrderSearch(no);
        }
        return res;
    }

    public HeroNode2 infixOrderSearch(int no) {
        HeroNode2 res = null;
        if (this.left != null) {
            res = this.left.infixOrderSearch(no);
        }
        if (res != null) {
            return res;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            res = this.right.infixOrderSearch(no);
        }
        return res;
    }

    public HeroNode2 postOrderSearch(int no) {
        HeroNode2 res = null;
        if (this.left != null) {
            res = this.left.postOrderSearch(no);
        }
        if (res != null) {
            return res;
        }
        if (this.right != null) {
            res = this.right.postOrderSearch(no);
        }
        if (res != null) {
            return res;
        }
        if (this.no == no) {
            return this;
        }
        return res;
    }
}
