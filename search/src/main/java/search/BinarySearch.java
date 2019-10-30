package search;

import constants.ArrConstants;
import fill.ArrFiller;
import sort.HybridQS;

import java.util.Arrays;

public class BinarySearch {
    public static int binarySearch(int arr[], int l, int r, int s) {

        if (r >= l) {
            int m = l + (r - l) / 2;
            if (arr[m] == s)
                return m;
            if (arr[m] > s)
                return binarySearch(arr, l, m - 1, s);

            return binarySearch(arr, m + 1, r, s);
        }

        return -1;
    }



    public static void main(String [] args){
        int[]nums = new int[]{5,4,2,6,1,7,12,43,12,1,6,3,2};
        int search = 7;
        int pos = 0;
        HybridQS.sortLast(nums);
        long startBin = System.currentTimeMillis();
        pos = BinarySearch.binarySearch(nums, 0, nums.length-1, search);
        long finishBin = System.currentTimeMillis();
        System.out.println((finishBin-startBin)+" el: "+pos+" arr: "+ Arrays.toString(nums));
    }
}
