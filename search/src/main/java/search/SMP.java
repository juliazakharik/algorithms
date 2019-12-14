package search;

import java.util.Arrays;

public class SMP {
    static int N = 4;

    static boolean wPrefersM1OverM(int prefer[][], int w, int m, int m1) {

        for (int i = 0; i < N; i++) {
            if (prefer[w][i] == m1)
                return true;
            if (prefer[w][i] == m)
                return false;
        }
        return false;
    }


    static void stableMarriage(int[][] prefer) {
        int[] wPartner = new int[N];
        boolean[] mFree = new boolean[N];

        Arrays.fill(wPartner, -1);
        int freeCount = N;

        while (freeCount > 0){
            int m;
            for (m = 0; m < N; m++)
                if (!mFree[m])
                    break;

            for (int i = 0; i < N && !mFree[m]; i++) {
                int w = prefer[m][i];

                if (wPartner[w - N] == -1) {
                    wPartner[w - N] = m;
                    mFree[m] = true;
                    freeCount--;
                }
                else {
                    int m1 = wPartner[w - N];

                    if (!wPrefersM1OverM(prefer, w, m, m1)) {
                        wPartner[w - N] = m;
                        mFree[m] = true;
                        mFree[m1] = false;
                    }
                }
            }

        }

        print(wPartner);


    }

    static void stableMarriageTime(int[][] prefer) {
        int[] wPartner = new int[N];
        boolean[] mFree = new boolean[N];

        Arrays.fill(wPartner, -1);
        int freeCount = N;

        while (freeCount > 0){
            int m;
            for (m = 0; m < N; m++)
                if (!mFree[m])
                    break;

            for (int i = 0; i < N && !mFree[m]; i++) {
                int w = prefer[m][i];

                if (wPartner[w - N] == -1) {
                    wPartner[w - N] = m;
                    mFree[m] = true;
                    freeCount--;
                }
                else {
                    int m1 = wPartner[w - N];

                    if (!wPrefersM1OverM(prefer, w, m, m1)) {
                        wPartner[w - N] = m;
                        mFree[m] = true;
                        mFree[m1] = false;
                    }
                }
            }

        }

        print(wPartner);


    }

    public static void print(int[] wPartner){
        System.out.println("Woman Man");
        for (int i = 0; i < N; i++)
        {
            System.out.print(" ");
            System.out.println(i + N + "     " +
                    wPartner[i]);
        }
    }


    public static void main(String[] args) {
        int prefer[][] = new int[][]{{7, 5, 6, 4},
                {5, 4, 6, 7},
                {4, 5, 6, 7},
                {4, 5, 6, 7},
                {0, 1, 2, 3},
                {0, 1, 2, 3},
                {0, 1, 2, 3},
                {0, 1, 2, 3}};
        stableMarriage(prefer);
    }
}
