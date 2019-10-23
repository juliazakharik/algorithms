package sort;

import constants.ArrConstants;
import fill.ArrFiller;

import java.util.Arrays;

import static constants.ArrConstants.ARR_LENGTH;
import static sort.InsertionSort.insertionSort;

public class HybridSort {

    public void sortLast(int[] nums) {

        if (nums == null || nums.length == 0) {
            return;
        }
        quickSortByLastElement(nums,0, nums.length - 1);
    }

    public static int[] quickSortByLastElement(int[] arr, int start, int end){

        if (start < end) {
            if((end-start)> ArrConstants.INS) {
                int partitionIndex = partitionByLastElement(arr, start, end);

                quickSortByLastElement(arr, start, partitionIndex - 1);
                quickSortByLastElement(arr, partitionIndex + 1, end);
            }else{
                insertionSort(arr, start, end);

            }
        }
        return arr;
    }

    public static int partitionByLastElement(int[] arr, int start, int end){

        int pivot = arr[end];

        for(int i=start; i<end; i++){
            if(arr[i]<pivot){
                int temp= arr[start];
                arr[start]=arr[i];
                arr[i]=temp;
                start++;
            }
        }

        int temp = arr[start];
        arr[start] = pivot;
        arr[end] = temp;

        return start;
    }
    public static void main(String args[])
    {
        int k = 100;
        long time = 0;
        for(int i = 0; i<k;i++) {
            HybridSort ob = new HybridSort();
            int nums[];
            nums = ArrFiller.fillArr(ARR_LENGTH);
            long start = System.currentTimeMillis();
            ob.sortLast(nums);
            long finish = System.currentTimeMillis();
            long timeConsumedMillis = finish - start;
            time+=timeConsumedMillis;
            System.out.println(i+": " + timeConsumedMillis + " ms");
        }
        System.out.println("average: " + time/k + " ms");
    }
}
