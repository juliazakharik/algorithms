import constants.ArrConstants;
import fill.ArrFiller;
import sort.MergeSort;

import java.util.Arrays;

import static constants.ArrConstants.ARR_LENGTH;

public class Main {
    public static void main(String[] args){
        int[] array = new int[ARR_LENGTH];
        int[] arr = new int[ARR_LENGTH];
        int k = 50;
        long time = 0;
//        for(int i = 0; i<k;i++) {
//            MergeSort ob = new MergeSort();
//            int nums[];
//            nums = ArrFiller.fillArr(ARR_LENGTH);
//            long start = System.currentTimeMillis();
//            ob.sort(nums, 0, nums.length-1);
//            long finish = System.currentTimeMillis();
//            long timeConsumedMillis = finish - start;
//            time+=timeConsumedMillis;
//            System.out.println(i+": " + timeConsumedMillis + " ms");
//        }

        //hybrid
        int Cs = 0;
        MergeSort ob = new MergeSort();
        int nums[];
        for(int i = 0; i<k;i++) {

            long best = ARR_LENGTH;
            int bestC = 0;

            ArrConstants.C = 10;
            for(;ArrConstants.C<100;ArrConstants.C+=5) {
                nums = ArrFiller.fillArr(ARR_LENGTH);
                long start = System.currentTimeMillis();
                ob.sortHybrid(nums, 0, nums.length - 1);
                long finish = System.currentTimeMillis();
                long timeConsumedMillis = finish - start;
                if(timeConsumedMillis<best) {
                    best = timeConsumedMillis;
                    bestC = ArrConstants.C;

                }

            }
            Cs+=bestC;
            time+=best;
            System.out.println(i+": " + best + " ms with c = "+bestC);
        }
        System.out.println("average: " + time/k + " ms average C: "+Cs/k    );

    }
}
