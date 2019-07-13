package org.lhx.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lhx
 * @date 2019/7/13 - 10:29
 */
public class Graph {

    //存储顶点的集合
    private List<String> vertexList;

    //存储图对应的邻接矩阵
    private int[][] edges;

    //表示边的个数
    private int numOfEdges;

    //记录各个节点是否被访问过
    private boolean[] isVisited;

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    /**
     * 得到第一个邻接节点的下标
     *
     * @param index
     * @return 如果存在，返回对应下标，不存在返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //根据邻接节点的下标获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    //i 第一次为0
    private void dfs(boolean[] isVisited, int i) {
        //首先访问该节点
        System.out.print(getValueByIndex(i) + "->");
        //将节点设置为已访问
        isVisited[i] = true;

        //查找i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            //说明有节点
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w节点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行重载，遍历所有的节点，并进行dfs
    public void dfs() {
        isVisited = new boolean[getNumOfVertex()];
        //遍历所有的节点进行dfs
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    //对一个结点进行广度优先遍历
    public void bfs(boolean[] isVisited, int i) {
        //表示队列头结点对应的下标
        int u;
        //邻接节点w
        int w;
        //队列，记录结点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //访问节点
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = queue.removeFirst();
            //得到第一个邻近的的下标w
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                //以u为前驱找w后面的下一个邻接节点
                w = getNextNeighbor(u, w);//体现出广度优先
            }
        }
    }

    public void bfs() {
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    /**
     * 插入节点
     *
     * @param vertex 节点内容
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     顶点1的下标 表示第几个顶点
     * @param v2     顶点2的下标 表示第几个顶点
     * @param weight 边的权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //图中常用的方法
    //返回节点个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回节点i对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

}

class TestGraph {
    public static void main(String[] args) {
        //顶点个数
        int n = 8;

//        String[] vertexValue = {"A", "B", "C", "D", "E"};
        String[] vertexValue = {"1", "2", "3", "4", "5", "6", "7", "8"};

        Graph graph = new Graph(n);
        for (String s : vertexValue) {
            graph.insertVertex(s);
        }
        //A-B A-C B-C B-D B-E
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);



        graph.showGraph();

        graph.dfs();
        System.out.println();
        graph.bfs();
    }
}
