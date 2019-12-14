package action;

import entity.Graph;

import java.util.Stack;

public class Search {
    public static int[] topologicalSearch(Graph G) {
        int n=G.getVertices();
        int[] isVertexVisited=new int[n];
        int[][]adjacency=G.getAdj();
        Stack<Integer> S=new Stack<>();
        Stack<Integer>sortResult=new Stack<>();
        topologicalSearch(G, adjacency, 0, S, isVertexVisited, sortResult);

        int[] sortedTree=new int[n];
        for (int i=0; i<n; i++)
            sortedTree[i]=sortResult.pop();
        return sortedTree;
    }
    private static void topologicalSearch(Graph G, int[][]adjacency, int startVertex, Stack<Integer> S, int[]isVertexVisited, Stack<Integer>sortResult) {
        int n=G.getVertices();
        if(isVertexVisited[startVertex]!=1) {
            S.push(startVertex);
            isVertexVisited[startVertex]=1;
        }
        for(int i=0; i<n; i++){
            if(adjacency[startVertex][i]==1 && isVertexVisited[i]!=1){
                topologicalSearch(G, adjacency, i, S, isVertexVisited, sortResult);
            }
        }
        sortResult.push(startVertex);
    }
}
