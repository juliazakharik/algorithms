package sort;

import constants.ArrConstants;

import java.util.Arrays;

public class MergeSort {

    public static void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int lef[] = new int [n1];
        int rig[] = new int [n2];

        for (int i=0; i<n1; ++i)
            lef[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            rig[j] = arr[m + 1+ j];



        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (lef[i] <= rig[j]) {
                arr[k] = lef[i];
                i++;
            }
            else {
                arr[k] = rig[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = lef[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rig[j];
            j++;
            k++;
        }
    }


    public static int[] sort(int arr[], int l, int r) {
        if (l < r) {
            int m = (l+r)/2;

            sort(arr, l, m);
            sort(arr , m+1, r);
            merge(arr, l, m, r);
        }
        return arr;
    }

    public static int[] sortHybrid(int arr[], int l, int r) {
        if (l < r) {
            if((r-l)>ArrConstants.C) {
                int m = (l + r) / 2;

                sort(arr, l, m);
                sort(arr, m + 1, r);

                merge(arr, l, m, r);
            }
            else{
                InsertionSort.insertionSort(arr,l,r);
            }
        }
        return arr;
    }


}
