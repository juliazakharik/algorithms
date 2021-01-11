package action;

import java.util.LinkedList;

public class Floud {

    int matr[][];
    private int dimension;


    public Floud(int dimension) {
        this.dimension = dimension;
    }

    public Floud(int[][] matr) {
        this.dimension = matr.length;
        this.matr = matr;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int[][] makeSymmetric(){
        int[][] floyd = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++){
                if(matr[i][j] == 0 && matr[j][i] == 0)
                    floyd[i][j] = Integer.MAX_VALUE;
                else if(matr[i][j] == 0)
                    floyd[i][j] = matr[j][i];
                else if(matr[j][i] == 0)
                    floyd[i][j] = matr[i][j];
                else
                    floyd[i][j] = Math.min(matr[i][j], matr[j][i]);
            }
        return floyd;
    }

    public int fireStationLoc(){
        int[][]matr = floud();
        int fireStationLoc = -1;
        int minTime = Integer.MAX_VALUE;
        for (int i = 0; i < dimension; i++) {
            int maxTime = matr[i][0];
            for (int j = 1; j < dimension; j++) {
                if (j != i)
                    maxTime = Math.max(maxTime, matr[i][j]);
            }
            if (maxTime < minTime) {
                fireStationLoc = i;
                minTime = maxTime;
            }
        }
        return fireStationLoc;
    }

    public int[][] floud() {
        int[][] matr= makeSymmetric();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (j != i) {
                    for (int k = 0; k < dimension; k++) {
                        if (i != k && matr[j][i] != Integer.MAX_VALUE && matr[i][k] != Integer.MAX_VALUE) {
                            matr[j][k] = Math.min(matr[j][k], matr[j][i] + matr[i][k]);
                            matr[k][j] = Math.min(matr[j][k], matr[j][i] + matr[i][k]);
                        }
                    }
                }
            }
        }

        return matr;
    }

    public static void main(String []args){
        int[][] matrix = new int[][]{{0,1,0},{0,0,2},{3,0,0}};
        Floud f = new Floud(matrix);
        System.out.println(f.fireStationLoc());
    }
}
