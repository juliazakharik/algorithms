package entity;

import constants.Constants;

import java.util.*;

import static constants.Constants.INF;
import static help.Math.min;

public class Graph {
    private boolean adjMatrix[][];
    private int[][] adj;
    private LinkedList<Integer> adjLists[];
    private int dimension;
    private int[] a;
    private int i = 0;
    private int vertices;
    public int getVertices() {
        return vertices;
    }

    public Graph(int dimension) {
        this.dimension = dimension;
        adjMatrix = new boolean[dimension][dimension];
        adjLists = new LinkedList[dimension];

        for (int i = 0; i < dimension; i++)
            adjLists[i] = new LinkedList();
    }

    public Graph(int vertices, int[][] adjacency) {
        this.vertices = vertices;
        this.adj =adjacency;
    }


    public boolean[][] getAdjMatrix() {
        return adjMatrix;
    }

    public int[][] getAdj(){
        return adj;
    }

    public int[][] getAdjMatrixModified(){
        int[][] t = new int[getDimension()][getDimension()];
        for(int i = 0;i<getAdjMatrix().length;i++){
            for(int j = 0; j<getAdjMatrix().length;j++){
                if(getAdjMatrix()[i][j]){
                    t[i][j]=1;
                }
                else {
                    t[i][j]=0;
                }
            }
        }
        return t;
    }

    public void setAdjMatrix(boolean[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    public LinkedList<Integer>[] getAdjLists() {
        return adjLists;
    }

    public void setAdjLists(LinkedList<Integer>[] adjLists) {
        this.adjLists = adjLists;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void addEdgeAdjLists(int src, int dest)
    {
        adjLists[src].add(dest);
        adjLists[dest].add(src);
    }


    public void addEdgeAdjMatrix(int i, int j) {
        adjMatrix[i][j] = true;
        adjMatrix[j][i] = true;
    }

    public void removeEdge(int i, int j) {
        adjMatrix[i][j] = false;
        adjMatrix[j][i] = false;
    }

    public boolean isEdge(int i, int j) {
        return adjMatrix[i][j];
    }

    void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.println(v);
        for (int n : getAdjLists()[v]) {
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    boolean isConnected() {
        boolean[] visited = new boolean[getDimension()];
        int i;
        for (i = 0; i < getDimension(); i++)
            visited[i] = false;

        for (i = 0; i < getDimension(); i++)
            if (getAdjLists()[i].size() != 0)
                break;

        if (i == getDimension())
            return true;

        DFSUtil(i, visited);

        for (i = 0; i < getDimension(); i++)
            if (!visited[i] && getAdjLists()[i].size() > 0)
                return false;

        return true;
    }

    int isEulerian() {
        if (!isConnected())
            return 0;

        int odd = 0;
        for (int i = 0; i < dimension; i++)
            if (getAdjLists()[i].size()%2!=0)
                odd++;

        if (odd > 2)
            return 0;

        return (odd==2)? 1 : 2;
    }
    void euler() {
        int res = isEulerian();
        if (res == 0)
            System.out.println("graph is not Eulerian");
        else if (res == 1)
            System.out.println("graph has a Euler path");
        else
            System.out.println("graph has a Euler cycle");
    }




//Prim

    int minKey(int key[], boolean mstSet[], int len) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < len; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }

        return min_index;
    }


    void printPrim(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < getDimension(); i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }

    void prim(int[][] graph) {
        int[] parent = new int[graph.length];
        int[] key = new int[graph.length];
        boolean[] mstSet = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < graph.length - 1; count++) {

            int u = minKey(key, mstSet,graph.length);
            System.out.println("u "+u);
            mstSet[u] = true;
            for (int v = 0; v < graph.length; v++)
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        printPrim(parent, graph);
    }

    int minKeyK(int key[], boolean mstSet[], int[][] g, int u){
        int min = Integer.MAX_VALUE, min_index = -1;
        for(int i =0; i< g.length;i++){
                if(g[u][i]!=0) {
                    if (!mstSet[i] && key[i] < min) {
                        min = key[i];
                        min_index = i;
                    }
                }
        }
        return min_index;
    }




    void kruskal(int[][] graph){
        int[] parent = new int[graph.length];
        int[] key = new int[graph.length];
        boolean[] mstSet = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;
        int u =0;
        for (int count = 0; count < graph.length - 1; count++) {

            u = minKeyK(key, mstSet, graph, u);
            System.out.println("u "+u+" ");
            mstSet[u] = true;
            for (int v = 0; v < graph.length; v++)
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        printPrim(parent, graph);

    }
    public static void main(String args[]) {
        // Let us create and test graphs shown in above figures
        Graph g1 = new Graph(5);
        g1.addEdgeAdjLists(1, 0);
        g1.addEdgeAdjLists(0, 2);
        g1.addEdgeAdjLists(2, 1);
        g1.addEdgeAdjLists(0, 3);
        g1.addEdgeAdjLists(2, 4);
        g1.addEdgeAdjLists(3, 4);
        g1.euler();
        int graph[][] = new int[][] { { 0, 2, 0, 6, 0 },
                { 2, 0, 3, 8, 5 },
                { 0, 3, 0, 0, 7 },
                { 6, 8, 0, 0, 9 },
                { 0, 5, 7, 9, 0 } };

        g1.prim(graph);
        g1.kruskal(graph);
    }

    }
