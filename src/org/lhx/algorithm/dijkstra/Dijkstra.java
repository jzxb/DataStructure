package org.lhx.algorithm.dijkstra;

import java.util.Arrays;

/**
 * @author lhx
 * @date 2019/7/17 - 10:21
 */
public class Dijkstra {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dsj(6);
        graph.showDijkstra();
    }

}

class Graph {

    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex visitedVertex;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public void showDijkstra() {
        visitedVertex.show();
    }

    /**
     * 迪杰斯特拉算法实现
     * @param index 出发顶点对应的下标
     */
    public void dsj(int index) {
        visitedVertex = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            index = visitedVertex.updateArr();
            update(index);
        }
    }

    /**
     * 更新index下标顶点到周围顶点的距离和周围顶点的前驱结点
     * @param index
     */
    private void update(int index) {
        int len = 0;
        //
        for (int j = 0; j < matrix[index].length; j++) {
            //出发顶点到index顶点的距离+从index顶点到j顶点距离的和
            len = visitedVertex.getDis(index) + matrix[index][j];
            //如果j顶点没有被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
            if (!visitedVertex.in(j) && len < visitedVertex.getDis(j)) {
                //更新j顶点的前驱为index顶点
                visitedVertex.updatePre(j, index);
                //更新出发顶点到j的距离
                visitedVertex.updateDis(j, len);
            }
        }
    }

}

class VisitedVertex {

    //记录各个顶点是否访问过，1表示访问过，0表示未访问
    public int[] alreadyArr;

    //每个顶点下标对应的值为前一个顶点的下标
    public int[] preVisited;

    //记录出发顶点到其他顶点的距离
    public int[] dis;

    /**
     *
     * @param length 顶点的个数
     * @param index 出发顶点对应的下标
     */
    public VisitedVertex(int length, int index) {
        this.alreadyArr = new int[length];
        this.preVisited = new int[length];
        this.dis = new int[length];
        //初始化dis
        Arrays.fill(dis, 65535);
        //设置出发顶点的访问距离为0
        this.dis[index] = 0;
        //设置出发顶点被访问过
        this.alreadyArr[index] = 1;
    }

    /**
     * 判断index是否被访问过
     * @param index 顶点下标
     * @return 访问过返回true，未访问返回false
     */
    public boolean in(int index) {
        return alreadyArr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新pre顶点的前驱为index
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        preVisited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点
     * @return
     */
    public int updateArr() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if (alreadyArr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        alreadyArr[index] = 1;
        return index;
    }

    /**
     * 显示最终结果，即将三个数组情况输出
     */
    public void show() {
        System.out.println(Arrays.toString(alreadyArr));
        System.out.println(Arrays.toString(preVisited));
        System.out.println(Arrays.toString(dis));
    }

}
