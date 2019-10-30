package sort;

import fill.ArrFiller;

import static constants.ArrConstants.ARR_LENGTH;

public class QuickSort {

    private int[] temp_array;
    private int len;

    public static void sortLast(int[] nums) {

        if (nums == null || nums.length == 0) {
            return;
        }
        quickSortByLastElement(nums,0, nums.length - 1);
    }

    public static int[] quickSortByLastElement(int[] arr, int start, int end){

        if (start < end) {
            int partitionIndex = partitionByLastElement(arr, start, end);

            quickSortByLastElement(arr, start, partitionIndex-1);
            quickSortByLastElement(arr, partitionIndex+1, end);
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



    public void sortRand(int[] nums) {

        if (nums == null || nums.length == 0) {
            return;
        }
        this.temp_array = nums;
        len = nums.length;
        quickSortByRandElement(nums, 0, len - 1);
    }
    private void quickSortByRandElement(int[] arr, int start, int end) {

        int i = start;
        int j = end;
        // calculate pivot number
        int pivot = arr[(int)Math.random()*end+start];
        // Divide into two arrays
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int temp= arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (start < j)
            quickSortByRandElement(arr, start, j);
        if (i < end)
            quickSortByRandElement(arr, i, end);
    }

//    private void swap(int i, int j) {
//        int temp = temp_array[i];
//        temp_array[i] = temp_array[j];
//        temp_array[j] = temp;
//    }

    // Method to test above
    public static void main(String args[])
    {
        int k = 100;
        long time = 0;
        for(int i = 0; i<k;i++) {
            QuickSort ob = new QuickSort();
            int nums[];
            nums = ArrFiller.fillArr(ARR_LENGTH);
            long start = System.currentTimeMillis();
            ob.sortRand(nums);
            long finish = System.currentTimeMillis();
            long timeConsumedMillis = finish - start;
            time+=timeConsumedMillis;
            System.out.println(i+": " + timeConsumedMillis + " ms");
        }
        System.out.println("average: " + time/k + " ms");
    }
}
