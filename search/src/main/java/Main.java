import constants.ArrConstants;
import entity.Hash;
import fill.ArrFiller;
import help.OutPutHash;
import search.BinarySearch;
import search.InterpolationSearch;
import search.LinearSearch;
import sort.HybridQS;
import sort.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[]args) throws Exception {
//        int k = 10;
//        int[]nums;
//        int[]temp;
//        long timeLin=0;
//        long timeBin=0;
//        long timeInt=0;
//        for(int i = 0;i<k;i++){
//            nums = ArrFiller.fillArr(ArrConstants.ARR_LENGTH);
//            int el = (int)(Math.random()*ArrConstants.ARR_LENGTH);
//            int linearPos = 0;
//            int binaryPos = 0;
//            int interPos = 0;
//            long startLin = System.currentTimeMillis();
//            linearPos = LinearSearch.linearSearch(nums, el);
//            long finishLin = System.currentTimeMillis();
//            long timeConsumedMillisLin = finishLin - startLin;
//
//            QuickSort.sortLast(nums);
////            System.out.println(Arrays.toString(nums));
////            System.out.println(el);
//            long startBin = System.currentTimeMillis();
//            binaryPos = BinarySearch.binarySearch(nums, 0, nums.length-1, el);
//            long finishBin = System.currentTimeMillis();
//            long timeConsumedMillisBin = finishBin - startBin;
//            long startInt = System.currentTimeMillis();
//            interPos = InterpolationSearch.interpolationSearch(nums, el);
//            long finishInt = System.currentTimeMillis();
//            long timeConsumedMillisInt = finishInt - startInt;
//
//
//            timeBin+=timeConsumedMillisBin;
//            timeLin+=timeConsumedMillisLin;
//            timeInt+=timeConsumedMillisInt;
//           // System.out.println("lin: "+linearPos+" bin: "+binaryPos+" int: "+interPos+" el: "+el);
//            System.out.println("lin: "+timeConsumedMillisLin+" bin: "+timeConsumedMillisBin+" int: "+timeConsumedMillisInt);
//
//        }
//
//        System.out.println("lin: "+timeLin/k+" bin: "+timeBin/k+" int: "+timeInt/k);
        int value = 10;
        int size = 20013;
        int range = 40000;
        List<Integer[]> array = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int randomKey = (int) Math.ceil(Math.random()*range);
            array.add(new Integer[]{randomKey, value});
        }
        
        System.out.println("**Кнут**");
        OutPutHash.outputInfo(size, array, Hash::hash3);
        System.out.println("----------------------------------------");
        System.out.println("**key % size**");
        OutPutHash.outputInfoWithConstant(size, array, Hash::hash2, Math.sqrt(15));
        System.out.println("----------------------------------------");
        System.out.println("**Math.sqrt(7)**");
        OutPutHash.outputInfoWithConstant(size, array, Hash::hash2, Math.sqrt(7));
        System.out.println("----------------------------------------");
        System.out.println("**Math.sqrt(2) % 5**");
        OutPutHash.outputInfoWithConstant(size, array, Hash::hash2, Math.sqrt(2) % 5);


    }
}
