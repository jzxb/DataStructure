package org.lhx.algorithm.kruskal;

import java.util.Arrays;

/**
 * @author lhx
 * @date 2019/7/16 - 19:43
 */
public class Kruskal {

    //使用INF表示两个顶点不能联通
    private static final int INF = Integer.MAX_VALUE;
    private int edgeNum;//边的个数
    private char[] vertexs;//顶点个数
    private int[][] matrix;//邻接矩阵

    public Kruskal(char[] vertexs, int[][] matrix) {
        int vLen = vertexs.length;
        this.vertexs = new char[vLen];
        for (int i = 0; i < vLen; i++) {
            this.vertexs[i] = vertexs[i];
        }
        this.matrix = new int[vLen][vLen];
        for (int i = 0; i < vLen; i++) {
            for (int j = 0; j < vLen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < vLen; i++) {
            for (int j = i + 1; j < vLen; j++) {
                if (matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}};

        Kruskal kruskal = new Kruskal(vertexs, matrix);
        kruskal.print();
        kruskal.kruskal();
//
//        EData[] edges = kruskal.getEdges();
//        System.out.println(Arrays.toString(edges));
//        kruskal.sortEdges(edges);
//        System.out.println(Arrays.toString(edges));
    }

    public void kruskal() {
        int index = 0;//表示结果数组的索引
        int[] ends = new int[edgeNum];//用于保存已有最小生成树中的每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的结果生成树
        EData[] result = new EData[vertexs.length - 1];
        EData[] edges = getEdges();
        //按照权值大小进行排序
        sortEdges(edges);
        //遍历edges,将边添加到最小生成树中时，判断是否和准备加入的边形成回路。
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
            //获取p1顶点在已有最小生成树中的终点
            int m = getEnds(ends, p1);
            //获取p2顶点在已有最小生成树中的终点
            int n = getEnds(ends, p2);
            //判断是否构成回路
            if (m != n) {
                //未构成回路
                //设置m在已有生成树中的终点
                ends[m] = n;
                result[index++] = edges[i];
            }
        }

        System.out.println(Arrays.toString(result));

    }

    public void print() {
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 对边进行排序
     *
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * @param ch 传入顶点的值
     * @return 返回顶点对应的下标，没找到为-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取图的边，放到EData[]中，后面需要遍历数组
     * 通过matrix邻接矩阵来获取
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] eData = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    eData[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return eData;
    }

    /**
     * 获取下标为i的顶点的终点，用于判断两个顶点的终点是否相同
     *
     * @param ends 记录了各个顶点对应的终点是哪个，ends数组实在遍历过程中逐渐形成的
     * @param i    对应顶点的下标
     * @return 传入顶点对应的终点下标
     */
    private int getEnds(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

}

//对象的实例表示一条边
class EData {

    char start;//边的一个点
    char end;//边的另一个点
    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
