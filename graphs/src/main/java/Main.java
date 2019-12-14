import action.DFS;
import action.Search;
import entity.Graph;

public class Main {
    public static void main (String[] args){
        int V = 4;
        int[][] a = {{0,1,1,1},
                {0,0,0,0},
                {0,1,0,0},
                {0,0,0,0}};
        // int[][] a = {{0,1,0,1}, {0,0,0,4}, {0,1,0,4}, {0,0,0,0}};
        Graph G = new Graph(V, a);

        System.out.println("DFS:\n" + DFS.DepthFirstSearch(G));

        int[]search= Search.topologicalSearch(G);
        System.out.println("Topological Search:");
        for (int i=0; i<V; i++)
            System.out.print(search[i]+" ");
    }
}
