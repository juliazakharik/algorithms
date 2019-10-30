package sort;

import java.util.Arrays;

public class InsertionSort {
    public static int[] insertionSort(int[] arr, int start, int end)
    {
        int n = arr.length;
        for (int i = start; i < end; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
    public static void main(String[] args){
        InsertionSort ob = new InsertionSort();
        int nums[] = {7, -5, 3, 2, 1, 0, 45};
        System.out.println("Original Array:");
        System.out.println(Arrays.toString(nums));
        ob.insertionSort(nums,0, nums.length-1);
        System.out.println("Sorted Array");
        System.out.println(Arrays.toString(nums));
    }
}
