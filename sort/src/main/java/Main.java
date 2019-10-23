import fill.ArrFiller;
import sort.QuickSort;

import java.util.Arrays;

import static constants.ArrConstants.ARR_LENGTH;

public class Main {
    public static void main(String[] args){
        int[] array = new int[ARR_LENGTH];
        int[] qsl_arr = new int[ARR_LENGTH];
        int[] qsr_arr = new int[ARR_LENGTH];
        int[] h_arr = new int[ARR_LENGTH];
        array = ArrFiller.fillArr(ARR_LENGTH);
        long start = System.currentTimeMillis();
        qsl_arr = QuickSort.quickSortByLastElement(array, 0, array.length-1);
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        System.out.println("Alg with last element "+timeConsumedMillis+" ms");
        long start1 = System.currentTimeMillis();
//        qsr_arr = QuickSort.sortRand(array, 0, array.length-1);
        long finish1 = System.currentTimeMillis();
        long timeConsumedMillis1 = finish - start;
        System.out.println("Alg with last element "+timeConsumedMillis1+" ms");
System.out.println(array);
    }
}
