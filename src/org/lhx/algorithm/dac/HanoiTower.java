package org.lhx.algorithm.dac;

/**
 * @author lhx
 * @date 2019/7/13 - 20:18
 */
public class HanoiTower {

    public static void main(String[] args) {
        hanoiTower(64,'A', 'B', 'C');
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        //只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            //把最上面的盘子先从a移动的b，移动过程会用到c
            hanoiTower(num - 1, a, c, b);
            //把最下面的盘子从a移动到c
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            //把b塔的所有盘子移动到c，移动过程中使用到a
            hanoiTower(num - 1, b, a, c);
        }
    }

}
