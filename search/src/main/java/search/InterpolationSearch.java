package search;

import constants.ArrConstants;
import fill.ArrFiller;
import sort.HybridQS;
import sort.QuickSort;

import java.util.Arrays;

public class InterpolationSearch {
    public static int interpolationSearch(int[] arr, int el) {
        int l = 0;
        int r = (arr.length - 1);


        while ((el >= arr[l]) && (el <= arr[r])) {
            int pos = l + (int)(((long)(el - arr[l])*(long)(r-l)) / (arr[r]-arr[l]));
//            System.out.println(pos+" "+l+" "+r+" "+arr[l]+" "+arr[r]+" "+el+" "+(long)((long)(el - arr[l])*(long)(r-l))+" "+(el-arr[l])+" "+(r-l)+" "+(el-arr[l])*(r-l)+" "+(arr[r]-arr[l]));


            if (arr[pos] < el)
                l = pos + 1;
            else if (arr[pos]>el)
                r = pos - 1;
            else
                return pos;

        }

        if(arr[l] == el)
            return l;
        else if(arr[r]==el)
            return r;
        else
            return -1;
    }

    public static void main(String [] args){
        int k = 10;
        int[]nums = new int[]{0, 0, 1, 1, 3, 5, 5, 5, 5, 9, 9, 10, 14, 17, 18, 18, 19, 20, 22, 22, 23, 23, 24, 24, 25, 28, 30, 30, 31, 31, 32, 32, 32, 34, 34, 35, 35, 41, 41, 42, 43, 43, 44, 44, 48, 48, 49, 49, 49, 49};
//        nums = ArrFiller.fillArr(ArrConstants.ARR_LENGTH);
//        nums = {0, 0, 1, 1, 3, 5, 5, 5, 5, 9, 9, 10, 14, 17, 18, 18, 19, 20, 22, 22, 23, 23, 24, 24, 25, 28, 30, 30, 31, 31, 32, 32, 32, 34, 34, 35, 35, 41, 41, 42, 43, 43, 44, 44, 48, 48, 49, 49, 49, 49};
//        int el = (int)(Math.random()*ArrConstants.ARR_LENGTH);
        int el = 24;
        int interPos = 0;
        long startLin = System.currentTimeMillis();
        int linearPos = LinearSearch.linearSearch(nums, el);
        long finishLin = System.currentTimeMillis();
        long timeConsumedMillisLin = finishLin - startLin;

        QuickSort.sortLast(nums);
        long startInt = System.currentTimeMillis();
        interPos = InterpolationSearch.interpolationSearch(nums, el);
        long finishInt = System.currentTimeMillis();
        long timeConsumedMillisInt = finishInt - startInt;
        System.out.println(interPos+ " "+timeConsumedMillisInt+" "+timeConsumedMillisLin+" "+el);
        System.out.println(Arrays.toString(nums));
    }
}
