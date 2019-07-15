package org.lhx.algorithm.prim;

import java.util.Arrays;

/**
 * @author lhx
 * @date 2019/7/15 - 20:27
 */
public class Prim {

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        int[][] weight = {{10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};
        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);
        minTree.showGraph(mGraph);
        minTree.prim(mGraph, 2);
    }

}

//创建最小生成树
class MinTree {

    /**
     * 创建图的邻接矩阵
     *
     * @param graph  图对象
     * @param verxs  图对应顶点个数
     * @param data   图各个顶点值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] ints : graph.weight) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * 编写prim算法得到最小生成树
     *
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成
     */
    public void prim(MGraph graph, int v) {
        //标记顶点是否被访问过,默认元素都为0
        int[] visited = new int[graph.verxs];
        //把当前结点标记为已访问
        visited[v] = 1;
        //h1和h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        //有graph.verxs个顶点,产生graph.verxs-1条边,因此从1开始遍历
        for (int k = 1; k < graph.verxs; k++) {
            //确定每一次生成的子图和哪个结点距离最近
            for (int i = 0; i < graph.verxs; i++) {//i表示已经被访问过的结点
                for (int j = 0; j < graph.verxs; j++) {//j表示未被访问过的结点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println(graph.data[h1] + "," + graph.data[h2] + "," + minWeight);
            visited[h2] = 1;
            minWeight = 10000;
        }
    }

}

class MGraph {

    int verxs;//表示图的结点个数
    char[] data;//存放节点数据
    int[][] weight;//邻接矩阵,存放边

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }

}
