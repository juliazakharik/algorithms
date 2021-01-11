package action;

import java.util.Arrays;

public class MatrixCalculator {
    public static void main(String []args){
        int n = 7;
        int m = 5;
        int k = 6;
        int[][] matrix1 = new int[n][m];
        int[][] matrix2 = new int[n][m];
        matrix1 = fillMatrixRandom(n, m);
        matrix2 = fillMatrixRandom(n, m);


        //printMatrix(matrix1);
        System.out.println("----------------------------");
        //multNum(matrix1, 6);
        //printMatrix(matrix1);


        //printMatrix(plus(matrix1, matrix2));
        int[]a = new int[]{1,2,3};
        int[] b = a;//{1,2,3}
        System.out.println(Arrays.toString(b));
        b[0]=100;
        System.out.println(Arrays.toString(a));
        int a1 = 5;
        int b1 = a1;//b1=5
        a1 = 6;
        System.out.println(b1);
        printMatrix(multNumber(matrix1, k));
        printMatrix(matrix1);

        multNumberVoid(matrix1, 7);
        printMatrix(matrix1);
    }

    static void multNum(int[][]matrix, int k){
        for(int i =0;i<matrix.length; i++){
            for(int j = 0; j<matrix[i].length;j++){
                matrix[i][j]*=k;
            }
        }
    }

    static void printMatrix(int[][]matrix){
        for(int i = 0;i<matrix.length;i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    //fill matrix
    static int[][] fillMatrixRandom(int n, int m){
        int[][] matrix = new int[n][m];
        for(int i =0;i<n; i++){
            for(int j = 0; j<m;j++){
                matrix[i][j]=(int)(Math.random()*10);
            }
        }
        return matrix;
    }

    //+
    static int[][] plus(int[][] matrix1, int[][] matrix2) {
        int[][]matrixres = new int[matrix1.length][matrix1[0].length];
        for(int i =0;i<matrix1.length; i++){
            for(int j = 0; j<matrix2[i].length;j++){
                matrixres[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return matrixres;
    }


    //-
    static int[][] minus(int[][] matrix1, int[][] matrix2) {
        int[][]matrixres = new int[matrix1.length][matrix1[0].length];
        for(int i =0;i<matrix1.length; i++){
            for(int j = 0; j<matrix2[i].length;j++){
                matrixres[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return matrixres;
    }
    //*
    static int[][] multNumber(int[][] matrix1, int k){
        int[][]matrixres = new int[matrix1.length][matrix1[0].length];
        for(int i=0;i<matrix1.length; i++){
            for(int j = 0; j<matrix1[0].length; j++){
                matrixres[i][j] = matrix1[i][j] * k;
            }
        }
        return matrixres;
    }

    static void multNumberVoid(int[][] matrix1, int k){
        for(int i=0;i<matrix1.length; i++){
            for(int j = 0; j<matrix1[0].length; j++){
                matrix1[i][j] *= k;
            }
        }
    }

}
