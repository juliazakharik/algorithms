package entity;

import java.util.*;

public class Graph {
    private boolean adjMatrix[][];
    private LinkedList<Integer> adjLists[];
    private int dimension;
    private int[] a;
    private int i = 0;

    public Graph(int dimension) {
        this.dimension = dimension;
        adjMatrix = new boolean[dimension][dimension];
        adjLists = new LinkedList[dimension];

        for (int i = 0; i < dimension; i++)
            adjLists[i] = new LinkedList();
    }


    public boolean[][] getAdjMatrix() {
        return adjMatrix;
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

    void DFSUtil(int v,boolean visited[])
    {
        visited[v] = true;
        System.out.println(v);
        Iterator<Integer> i = getAdjLists()[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    boolean isConnected()
    {
        boolean visited[] = new boolean[getDimension()];
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
    void test()
    {
        int res = isEulerian();
        if (res == 0)
            System.out.println("graph is not Eulerian");
        else if (res == 1)
            System.out.println("graph has a Euler path");
        else
            System.out.println("graph has a Euler cycle");
    }



    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            s.append(i + ": ");
            for (boolean j : adjMatrix[i]) {
                s.append((j?1:0) + " ");
            }
            s.append("\n");
        }
        return s.toString();
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
        g1.test();
    }

    }
