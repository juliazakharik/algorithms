package search;

import java.util.Arrays;

public class SMP {
    static int N = 4;

    static boolean prPreferst1OverT(int prefer[][], int pr, int t, int t1) {

        for (int i = 0; i < N; i++) {
            if (prefer[pr][i] == t1)
                return true;
            if (prefer[pr][i] == t)
                return false;
        }
        return false;
    }


    static void stableMarriage(int[][] prefer) {
        int[] pr = new int[N];
        boolean[] taskfree = new boolean[N];
        int[]tasks = new int[N];

        Arrays.fill(pr, -1);
        int freeCount = N;

        while (freeCount > 0){
            int m;
            for (m = 0; m < N; m++)
                if (!taskfree[m])
                    break;

            for (int i = 0; i < N && !taskfree[m]; i++) {
                int w = prefer[m][i];
                if (pr[w-N] == -1) {
                    //pr gets task m
                    pr[w-N] = m;
                    taskfree[m] = true;
                    freeCount--;
                }
                else {
                    int m1 = pr[w - N];

                    if (!prPreferst1OverT(prefer, w, m, m1)) {
                        pr[w - N] = m;
                        taskfree[m] = true;
                        taskfree[m1] = false;
                    }
                }
            }

        }

        print(pr, prefer);


    }



    public static void print(int[] wPartner, int[][]prefer){
        System.out.println("Programmer Time");
        for (int i = 0; i < N; i++)
        {
            System.out.print(" ");
            System.out.println(i + N + "     " +
                    wPartner[i]+" "+prefer[i+N][i]);
        }
    }


    public static void main(String[] args) {
        int prefer[][] = new int[][]{{7, 5, 6, 4},
                {5, 4, 6, 7},
                {4, 5, 6, 7},
                {4, 5, 6, 7},
                {0, 1, 2, 3},
                {0, 1, 2, 3},
                {2, 1, 0, 3},
                {0, 1, 2, 3}};
        int[][]time = new int[][]{{0, 1, 2, 3},
                {2, 1, 3, 0},
                {0, 1, 2, 3},
                {1, 3, 2, 0},
                {0, 1, 2, 3},
                {1, 3, 2, 0}};
        stableMarriage(prefer);
    }
}
