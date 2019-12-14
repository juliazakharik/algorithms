package search;

import constants.ArrConstants;
import fill.ArrFiller;
import sort.HybridQS;
import sort.QuickSort;

import java.util.Arrays;

import static constants.ArrConstants.ARR_LENGTH;

public class InterpolationSearch {
    private static int k = 0;
    public static int interpolationSearch(int[] arr, int el) {
        int index = -1;
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int pos = start + (((end - start) * (el - arr[start])) / (arr[end] - arr[start]));
            if (arr[pos] == el) {
                index = pos;
                break;
            } else if (el < arr[pos]) {
                end = pos - 1;
            } else {
                start = pos + 1;
            }
            k++;
        }
        return index;
    }

    public static void main(String [] args){
//        int k = 10;
//        int[]nums = new int[]{0, 0, 1, 1, 3, 5, 5, 5, 5, 9, 9, 10, 14, 17, 18, 18, 19, 20, 22, 22, 23, 23, 24, 24, 25, 28, 30, 30, 31, 31, 32, 32, 32, 34, 34, 35, 35, 41, 41, 42, 43, 43, 44, 44, 48, 48, 49, 49, 49, 49};
//        nums = ArrFiller.fillArr(ArrConstants.ARR_LENGTH);
//        nums = {0, 0, 1, 1, 3, 5, 5, 5, 5, 9, 9, 10, 14, 17, 18, 18, 19, 20, 22, 22, 23, 23, 24, 24, 25, 28, 30, 30, 31, 31, 32, 32, 32, 34, 34, 35, 35, 41, 41, 42, 43, 43, 44, 44, 48, 48, 49, 49, 49, 49};
//        int el = (int)(Math.random()*ArrConstants.ARR_LENGTH);
//        nums = ArrFiller.fillArr(1000000);
        int el = 1;
//        int interPos = 0;
//        long startLin = System.currentTimeMillis();
//        int linearPos = LinearSearch.linearSearch(nums, el);
//        long finishLin = System.currentTimeMillis();
//        long timeConsumedMillisLin = finishLin - startLin;
        int[]nums = new int[]{5,4,2,6,1,7,12,43,12,1,6,3,2};
        nums = ArrFiller.fillArr(ARR_LENGTH);
        int search = 4323;
        QuickSort.sortLast(nums);
        long startInt = System.currentTimeMillis();
        int interPos = InterpolationSearch.interpolationSearch(nums, search);
        long finishInt = System.currentTimeMillis();
        long timeConsumedMillisInt = finishInt - startInt;
        System.out.println(interPos+ " "+timeConsumedMillisInt+" ");
        System.out.println(k);

    }
}
