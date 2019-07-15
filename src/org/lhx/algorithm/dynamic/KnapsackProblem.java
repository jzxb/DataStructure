package org.lhx.algorithm.dynamic;

/**
 * @author lhx
 * @date 2019/7/15 - 10:38
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        //物品的重量
        int[] w = {1, 4, 3};
        //物品价值
        int[] val = {1500, 3000, 2000};
        //背包容量
        int m = 4;
        //物品个数
        int n = val.length;

        //表示前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];
        //为了记录放入商品的情况，定义一个二维数组
        int[][] path = new int[n + 1][m + 1];
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[i].length; j++) {
                //程序从1开始，因此为w[i - 1]
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //i从1开始
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //为了记录商品存放情况,不能直接使用上面的公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + "  ");
            }
            System.out.println();
        }

        //输出放入了哪些商品
        //行的最大下标
        int i = path.length - 1;
        //列的最大下标
        int j = path[0].length - 1;
        //从最后开始遍历
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }

}
