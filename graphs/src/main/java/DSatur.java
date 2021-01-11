//import entity.Graph;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.LinkedList;
//
//public class DSatur {
//    public static Graph graph;
//    public static int dimension;
//    public static int[] colors;
//    public DSatur(Graph graph){
//        this.graph = graph;
//
//    }
//    public static int getVertexDegree(int vertex){
//        int degree = 0;
//        for(int i = 0; i<dimension;i++){
//            if(graph.getAdj()[vertex][i]==1){
//                degree++;
//            }
//        }
//        return degree;
//    }
//    public static int getVertexHighestDegree(){
//        int max = 0;
//        int vertex = -1;
//        for(int i = 0;i<dimension;i++){
//            if(colors[i]==0){
//                int deg = getVertexDegree(i);
//                if(deg>max){
//                    max = deg;
//                    vertex = i;
//                }
//            }
//        }
//        return vertex;
//    }
//    public static int getVertexHighestDegreeSatur(LinkedList<Integer> arr){
//        int max = 0;
//        int vertex = -1;
//        for(int i = 0;i<arr.size();i++){
//                int deg = getVertexDegree(arr.get(i));
//                if (deg >= max) {
//                    max = deg;
//                    vertex = arr.get(i);
//                }
//
//        }
//        return vertex;
//    }
//    public static int getSaturationDegree(int vertex){
//        int deg = 0;
//        for(int i = 0;i<dimension;i++){
//            if(graph.getAdj()[vertex][i]==1){
//                if(colors[i]!=0){
//                    deg++;
//                }
//            }
//        }
//        return deg;
//    }
//
//    public static LinkedList<Integer> getHighestSaturationDegree(){
//        int max = 0;
//        LinkedList<Integer> vertex = new LinkedList<>();
//        for(int i = 0;i<dimension;i++){
//            if(colors[i]==0){
//                int deg = getSaturationDegree(i);
//                if(deg>max){
//                    max = deg;
//                }
//            }
//        }
//        for(int i = 0;i<dimension;i++){
//            if(colors[i]==0){
//                int deg = getSaturationDegree(i);
//                if(deg==max){
//                    vertex.add(i);
//                }
//            }
//        }
//        return vertex;
//    }
//    public static int findColorForVertex(int vertex){
//        LinkedList<Integer> arr = new LinkedList<>();
//        for(int i = 0;i<dimension;i++){
//            if(colors[i]!=0) {
//                if (graph.getAdj()[vertex][i] == 1) {
//                    arr.add(i);
//                }
//            }
//        }
//        LinkedList<Integer> colorVert = new LinkedList<>();
//        for(int i = 0; i< arr.size();i++){
//            colorVert.add(colors[arr.get(i)]);
//        }
//        colorVert.sort(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1<o2?o1:o2;
//            }
//        });
//        for(int i = 1;i<=dimension;i++){
//            if(i<=colorVert.size()) {
//                if (colorVert.get(i - 1) != i) {
//                    return i;
//                }
//            }
//            else{
//                return i;
//            }
//        }
//        return 1;
//    }
//    public static void colorVertex(int vertex, int color){
//        colors[vertex] = color;
//    }
//    public static boolean isAllColored(){
//        for(int i = 0;i<colors.length;i++){
//            if(colors[i]==0){
//                return false;
//            }
//        }
//        return true;
//    }
//    public static void satur(){
//        int color = 1;
//        int vertexHightestDegree = getVertexHighestDegree();
//        colorVertex(vertexHightestDegree, color);
//        while (true){
//            LinkedList<Integer> verteciesHighestSaturationDegree = getHighestSaturationDegree();
//            int vertex = getVertexHighestDegreeSatur(verteciesHighestSaturationDegree);
//            color = findColorForVertex(vertex);
//            colorVertex(vertex,color);
//            if(isAllColored()){
//                printColors();
//                break;
//            }
//        }
//
//
//
//    }
//
//
//    public static ArrayList<Integer> available(){
//        ArrayList<Integer> av = new ArrayList<>();
//        for(int i = 0;i<colors.length;i++){
//            if(colors[i]==0){
//                av.add(i);
//            }
//        }
//        return av;
//    }
//    public static int vertexMinDegreeSubGraph(ArrayList<Integer>available){
//        int min = Integer.MAX_VALUE;
//        int minInd = 0;
//        for(int i =0;i<available.size();i++){
//            int sum = 0;
//            for(int j = 0;j<available.size();j++){
//                if(graph.getAdj()[available.get(i)][available.get(j)]==1){
//                    sum++;
//                }
//            }
//            if(sum<min){
//                min = sum;
//                minInd=available.get(i);
//            }
//        }
//        return minInd;
//    }
//    public static ArrayList<Integer> adjVert(int vertex, ArrayList<Integer> available){
//        ArrayList<Integer> adj = new ArrayList<>();
//        for(int i =0 ;i<available.size();i++){
//            if(graph.getAdj()[vertex][available.get(i)]==1){
//                adj.add(available.get(i));
//            }
//        }
//        return adj;
//    }
//    public static ArrayList<Integer> removeVandAdj(int vertex, ArrayList<Integer> available){
//        ArrayList<Integer> adj = adjVert(vertex, available);
//        for(int i = 0;i<adj.size();i++){
//            available.remove(available.indexOf(adj.get(i)));
//        }
//        available.remove(available.indexOf(vertex));
//        return available;
//    }
//    public static void gis(){
//        int color = 1;
//        while (!isAllColored()){
//            ArrayList<Integer> available = available();
//            while (available.size()!=0){
//                int v = vertexMinDegreeSubGraph(available);
//                colorVertex(v, color);
//                available = removeVandAdj(v, available);
//
//            }
//            color++;
//        }
//        printColors();
//    }
//    public static void printColors(){
//        System.out.println(Arrays.toString(colors));
//    }
//    public static void main(String[]args){
//        int V = 5;
//        int[][] a = {{0,1,0,1},
//                {1,0,1,0},
//                {0,1,0,1},
//                {1,0,1,0}};
////        int[][]a1={{0,1,0,0,0},
////                {1,0,1,1,0},
////                {0,1,0,1,0},
////                {0,1,1,0,1},
////                {0,0,0,1,0}};
////        // int[][] a = {{0,1,0,1}, {0,0,0,4}, {0,1,0,4}, {0,0,0,0}};
//        int[][]a1={
//                {0,1,0,0,1},
//                {1,0,1,0,0},
//                {0,1,0,1,0},
//                {0,0,1,0,1},
//                {1,0,0,1,0}};
//        graph = new Graph(V, a1);
//        dimension = V;
//        colors = new int[dimension];
//        for(int i = 0;i<dimension;i++){
//            colors[i]=0;
//        }
//        gis();
//        graph = new Graph(V, a1);
//        dimension = V;
//        colors = new int[dimension];
//        for(int i = 0;i<dimension;i++){
//            colors[i]=0;
//        }
//        satur();
//
//    }
//}
