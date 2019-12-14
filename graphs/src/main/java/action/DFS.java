package action;

import entity.Graph;

import java.util.Stack;

public class DFS {
    public static Stack<Integer> DepthFirstSearch(Graph G){
        Stack<Integer> S=new Stack<>();
        int n=G.getVertices();
        int[] isVertexVisited=new int[n];
        int[][] adjacency=G.getAdj();
        DepthFirstSearch(G, 0, adjacency, S, isVertexVisited);
        return S;
    }
    private static void DepthFirstSearch(Graph G, int startVertex, int[][] adjacency, Stack<Integer> S, int[]isVertexVisited){
        int n=G.getVertices();
        if(isVertexVisited[startVertex]!=1) {
            S.push(startVertex);
            isVertexVisited[startVertex]=1;
        }
        for(int i=0; i<n; i++){
            if(adjacency[startVertex][i]==1 && isVertexVisited[i]!=1){
                DepthFirstSearch(G, i, adjacency, S, isVertexVisited);
            }
        }
    }

}
