package entity;

import constants.Constants;

import java.lang.reflect.Array;
import java.util.*;

import static constants.Constants.INF;
import static help.Math.min;
import static java.util.Arrays.fill;

public class Graph {
    private boolean adjMatrix[][];
    private double[][] adj;
    private LinkedList<Integer> adjLists[];
    private int dimension;
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

    public Graph(int vertices, double[][] adjacency) {
        this.vertices = vertices;
        this.adj =adjacency;
        this.dimension = adjacency.length;
    }


    public boolean[][] getAdjMatrix() {
        return adjMatrix;
    }

    public double[][] getAdj(){
        return adj;
    }
    public double getCycleWeight(int[]sol){
        double k = 0;
        for(int i = 0;i<getAdj().length-1;i++){
            k+=getAdj()[sol[i]][sol[i+1]];
        }
        k+=getAdj()[sol[0]][sol[sol.length-1]];
        return k;
    }
    public void setAdj(double[][] adj){this.adj = adj;}

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

    public void removeEgdeLists(int src, int dest){
        adjLists[src].remove(dest);
        adjLists[dest].remove(src);
    }

    public void addVertexLists(int vertex) throws RuntimeException {
        if (!this.adjLists[vertex].isEmpty()) {
            throw new RuntimeException("Such vertex has already exist");
        }
        this.adjLists[vertex].add(null);
    }

    public void removeVertexLists(int vertex){
        if (!this.adjLists[vertex].isEmpty()) {
            throw new RuntimeException("Such vertex has already exist");
        }
        this.adjLists[vertex].remove();
    }


    public void addEdgeAdjMatrix(int i, int j) {
        adjMatrix[i][j] = true;
        adjMatrix[j][i] = true;
    }

    public void removeEdgeMatrix(int i, int j) {
        adjMatrix[i][j] = false;
        adjMatrix[j][i] = false;
    }
    public void addEdgeAdj(int i, int j, double k) {
        adj[i][j] = k;
        adj[j][i] = k;
    }



    public void addEdgeList(int vertex1, int vertex2) {
        if (!this.adjLists[vertex1].isEmpty()) {
            this.adjLists[vertex1].add(vertex2);
        } else {
            addVertexLists(vertex1);
            this.adjLists[vertex1].add(vertex2);
        }
        if (!this.adjLists[vertex2].isEmpty()) {
            this.adjLists[vertex2].add(vertex1);
        } else {
            addVertexLists(vertex2);
            this.adjLists[vertex2].add(vertex1);
        }
    }

    public List<Integer> getVertexEnvironmentLists(int vertex) {
        if (!adjLists[vertex].isEmpty()) {
            return null;
        }
        return this.adjLists[vertex];
    }

    public List<Integer> getVertexEnvironmentMatrix(int vertex) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0;i<getDimension();i++){
            if(adjMatrix[vertex][i]){
                res.add(i);
            }
        }
        return res;
    }
    public boolean isAdjacentVerticesLists(int vertex1, int vertex2) {
        List<Integer> environmentOfVertex1 = this.getVertexEnvironmentLists(vertex1);
        if (environmentOfVertex1.isEmpty()) {
            return false;
        }
        return environmentOfVertex1.contains(vertex2);
    }

    public boolean isAdjacentVerticesMatrix(int vertex1, int vertex2) {
        return adjMatrix[vertex1][vertex2];
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

        for (i = 0; i < getDimension(); i++)//ищем ненулевую вершину
            if (getAdjLists()[i].size() != 0)
                break;

        if (i == getDimension())
            return true;

        DFSUtil(i, visited);

        for (i = 0; i < getDimension(); i++)
            if (!visited[i] && getAdjLists()[i].size() > 0)//все ли вершины просмотрены
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

        if (odd > 1)//если кол-во вершин с неч степенями > 2, то граф не эйлеров
            return 0;

        return (odd==2)? 1 : 2;
    }
    void euler() {
        int res = isEulerian();
        if (res == 0)
            System.out.println("graph is not Eulerian");
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
        System.out.println("Edge \tCost");
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

            mstSet[u] = true;
            for (int v = 0; v < graph.length; v++)
                if (graph[v][u]!=0 && !mstSet[v] && graph[u][v] < key[v]) {
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

    static int find (int i, int[]parent) {
        while(parent[i] != i)
            i = parent[i];
        return i;
    }


    static void union(int i, int j, int[]parent) {

        int a = find(i, parent);
        int b = find(j, parent);
        parent[a] = b;

    }

     void kruskal(int graph[][]) {
        System.out.println("kruskal");
        int mincost = 0; // Cost of min MST.
        int[]parent = new int[graph.length];
        int[] init = new int[graph.length];
        for(int i = 0; i < graph.length; i++)
            parent[i] = i;
        int edge_count = 0;
        while(edge_count < graph.length - 1) {
            int min = INF, a = -1, b = -1;
            for(int i = 0; i < graph.length; i++) {
                for(int j = 0; j < graph.length; j++) {
                    if(graph[i][j]!=0) {
//                    System.out.println(Arrays.toString(parent));
                        if (find(i, parent) != find(j, parent) && graph[i][j] < min) {
                            min = graph[i][j];
                            a = i;
                            b = j;
                        }
                    }
                }
            }

            union(a, b, parent);
            System.out.print(a+ " - "+ b+"\t"+ min+"\n");
            edge_count++;
            mincost += min;
        }
        System.out.println(Arrays.toString(parent));
//        System.out.printf("\n Minimum cost= %d \n", mincost);
    }

    boolean BFS(int s) {
        LinkedList<Integer> queue = new LinkedList<>();
        int[] arr = new int[adjLists.length];
        queue.add(s);
        arr[s] = 2;
        int k = 0;
        while (queue.size() != 0) {
            s = queue.poll();
            Iterator<Integer> i = adjLists[s].iterator();
            int c = arr[s];
            while (i.hasNext()) {
                int n = i.next();
                queue.add(n);
                if(arr[n]==c)
                    return false;
                else
                    arr[n] = invert(c);
            }
            k++;
            if(k>getDimension())
                break;
        }
        return true;
    }



    int invert(int c) {
        return c == 1 ? 2 : 1;
    }


    boolean dfsUtil(int v, int c, int[]arr){
        arr[v] = c;
        for(Integer u: adjLists[v]){
            if(arr[u]==0){
                dfsUtil(u, invert(c), arr);
            }else if(arr[u]==c){
                return false;
            }
        }
        return true;
    }

    boolean bipartite(){
        int[]arr = new int[dimension];
        for (int i = 0; i < dimension; i++) {
            if (arr[i] == 0) {
                if(!dfsUtil(i, 1,arr))
                    return false;
            }
        }
        return true;
    }

    void printBipartite(){
        if(BFS(0))
            System.out.println("Graph is bipartite");
        else
            System.out.println("Graph isnt bipartite");
    }




    public static void main(String args[]) {
//        Graph g1 = new Graph(8);
//        g1.addEdgeAdjLists(1, 0);
//        g1.addEdgeAdjLists(0, 2);
//        g1.addEdgeAdjLists(2, 1);
//        g1.addEdgeAdjLists(0, 3);
//        g1.addEdgeAdjLists(2, 4);
//        g1.addEdgeAdjLists(0,5);
//        g1.addEdgeAdjLists(3, 4);
//        g1.addEdgeAdjLists(1,6);
//        g1.addEdgeAdjLists(1,7);
//        g1.addEdgeAdjLists(2,6);
//        g1.addEdgeAdjLists(2,7);
//
        Graph g2 = new Graph(4);
        g2.addEdgeAdjLists(0,1);
        g2.addEdgeAdjLists(2,3);
        g2.addEdgeAdjLists(0,2);
        g2.addEdgeAdjLists(1,2);


        g2.printBipartite();
//        System.out.println(g1.isConnectedGraph());
//        System.out.println(g2.isConnectedGraph());

//        Graph g1 = new Graph(3);
//        g1.addEdgeAdjLists(0,1);
//        g1.addEdgeAdjLists(1,2);
////        g1.addEdgeAdjLists(0,2);
//        g1.printBipartite();
    }

    }
