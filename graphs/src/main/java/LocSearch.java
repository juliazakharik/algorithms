

import entity.Graph;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class LocSearch {
        public static double search(Graph graph){
            int[]temp = new int[graph.getAdj().length];

            for(int i = 0;i<temp.length;i++){
                temp[i]=i;
            }
            double tempWeight = graph.getCycleWeight(temp);
            System.out.println("ORIGINAL CYCLE WEIGHT "+tempWeight);

            int[]bestSolution = temp.clone();
            double bestWeight = tempWeight;

            boolean change = true;
            for(int i = 0;;i++){
                double prevWeight = bestWeight;

                for(int j =0;j<graph.getAdj().length;j++) {
                    if(j != i && j != i + 1 && j!= i - 1) {
                        swap(temp, i, j);

                        tempWeight = graph.getCycleWeight(temp);
                        if (tempWeight < bestWeight) {
                            bestSolution = temp.clone();
                            bestWeight = tempWeight;
                        }

                        swap(temp, j, i);
                    }
                }
                if(bestWeight<prevWeight){
                    temp = bestSolution.clone();
                }
                else{break;}

            }
            //System.out.println(Arrays.toString(bestSolution));
            return bestWeight;

        }
        public static void swap(int[]curr, int i, int j){
            int t = curr[i];
            curr[i] = curr[j];
            curr[j] = t;
        }

        public static void main(String[]args){
            int d = 10;
            double[][] a = new double[d][d];
            Graph graph = new Graph(d, a);
            for(int i = 0; i<d; i++){
                for(int j = i; j<d;j++){
                    graph.addEdgeAdj(i, j, 10);
                }
            }
            for(int i = 0; i<d; i++){
                for(int j = i; j<d;j++){
                    if((i%2==0&&j%2==0)||(i%2==1&&j%2==1))
                        graph.addEdgeAdj(i, j, 1);
                }
            }
            double loc = search(graph);
            System.out.println("LOCAL SEARCH CYCLE WEIGHT "+loc+"\n");

            long start = System.currentTimeMillis();
            int d1 = 1000;
            double[][] a1 = new double[d1][d1];
            Graph graph1 = new Graph(d1, a1);
            for(int i = 0; i<d1; i++){
                for(int j = i; j<d1;j++){
                    graph1.addEdgeAdj(i, j, Math.random());
                }
            }
            double loc1 = search(graph1);
            System.out.println("LOCAL SEARCH CYCLE WEIGHT "+loc1);
            System.out.println("TIME "+(System.currentTimeMillis()-start));
        }

}