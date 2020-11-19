import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges; //边的条数


    public static void main(String[] args) {
        int n = 5;
        String vertexs[] = {"A","B","C","D","E"};
        Graph graph = new Graph(n);
        for(String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        //显示邻接矩阵
        graph.showGraph();
        System.out.println(graph.getNumOfEdges());
        System.out.println(graph.getNumOfVertex());
        System.out.println(graph.getValueByIndex(1));
        System.out.println(graph.getWeight(0, 1));
        //深度优先遍历
        graph.dfs();
        //广度优先遍历
        graph.bfs();
    }
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
    }
    //得到i下标的第一个邻接结点的下标w
    public int getFirstNeighbor(int index) {
        for(int j = 0;j < vertexList.size();j++) {
            if(edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接结点的下标来获取下一个邻接节点的下标
    public int getNextNeighbor(int v1,int v2) {
        for(int j = v2 + 1;j < vertexList.size();j++) {
            if(edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }
    //深度优先遍历算法
    private void dfs(boolean[] isVisited,int i) {
        System.out.print(getValueByIndex(i) + "->");
        //将结点设置为已访问
        isVisited[i] = true;
        //查找下标为i的节点的第一个邻接节点的下标w
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if(!isVisited[w]) {
                dfs(isVisited,w);
            }
            w = getNextNeighbor(i,w);
        }
    }
    //遍历所有节点进行dfs
    public void dfs() {
        boolean[] isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                dfs(isVisited,i);
            }
        }
        System.out.println();
    }
    //
    private void bfs(boolean[] isVisited,int i) {
        int u;
        int w;
        Queue<Integer> queue = new LinkedList<>();
        System.out.print(getValueByIndex(i) + "->");
        isVisited[i] = true;
        queue.add(i);
        while(!queue.isEmpty()) {
            u = queue.poll();
            //找u第一个邻接节点
            w = getFirstNeighbor(u);
            while (w != -1) {
                if(!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[w] = true;
                    queue.add(w);
                }
                //找u在w后的下一个邻接节点
                w = getNextNeighbor(u,w);
            }
        }
    }
    public void bfs() {
        boolean[] isVisited = new boolean[getNumOfVertex()];
        for (int i = 0;i < getNumOfVertex();i++) {
            if(!isVisited[i]) {
                bfs(isVisited,i);
            }
        }
        System.out.println();
    }
    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }
    //添加边
    public void insertEdge(int v1,int v2,int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
    //返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }
    //返回边的条数
    public int getNumOfEdges() {
        return numOfEdges;
    }
    //返回下标为i的节点
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1,int v2) {
        return edges[v1][v2];
    }
    //显示对应的矩阵
    public void showGraph() {
        for(int[] nums : edges) {
            System.out.println(Arrays.toString(nums));
        }
    }
}
