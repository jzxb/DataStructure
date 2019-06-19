package org.lhx.recursion;

/**
 * @author lhx
 * @date 2019/6/19 - 10:45
 */
public class Queue8 {

    //定义一个max表示有多少个皇后
    int max = 8;
    //定义array，保存皇后放置的结果
    int[] array = new int[max];

    static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("一共有" + count + "种解法");
    }

    private void check(int n) {
         if (n == max) {
             print();
             return;
         }

        for (int i = 0; i < max; i++) {
            array[n] = i;
            //判断是否冲突
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    /**
     * 放置第n个皇后的时候，检测该皇后是否与前面已经摆放的皇后冲突
     * @param n 第n个皇后
     * @return
     */
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //array[i] == array[n] 判断第n个皇后是否和前面n-1个皇后在同一列
            //Math.abs(n - i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i个皇后在同意斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
        System.out.println();
    }

}