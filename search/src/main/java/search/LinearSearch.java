package search;

public class LinearSearch {
    public static int linearSearch(int arr[], int el) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == el)
                return i;
        }
        return -1;
    }
}
