package robot;

import java.util.Arrays;

public class Robot {
    public static Integer robot(int size) {
        Integer[][] matrix = new Integer[size][size];
        for (int i = 0; i < size; i++)
            Arrays.fill(matrix[i], 1);

        for (int i = 1; i <size; i++) {
            for (int j = 1; j < size; j++) {
                matrix[i][j] = matrix[i][j - 1] + matrix[i - 1][j];
            }
        }
        return matrix[size-1][size - 1];
    }

    public static void main(String []args){
        System.out.println(robot(4));
    }
}
