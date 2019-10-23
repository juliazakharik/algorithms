package fill;

import static constants.ArrConstants.ARR_LENGTH;

public class ArrFiller {
    public static int[] fillArr(int arrLen){
        int[] array = new int[arrLen];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int)(Math.random() * ARR_LENGTH));
        }
        return array;
    }
}
