public class Test {
    public static void main(String[]args){
        int[]a1 = new int[]{-2,10,5,-15, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89};
        int[]a2 = new int[10];//размерность надо указывать
        a2[3]=6;//[0,0,0,6,0,0,0,0
        a2[5]=13;//[0,0,0,6,0,13,0,0,0
        //System.out.println(a1[6]);
        //printLess5(a1);
        //printFirstAndLast(a1);
        int min = minEl(a1);
        System.out.println(min);
    }
    //найти минимальный элемент в массиве
    static int minEl(int[]arr){
        int min = arr[0];
        for(int i =0;i<arr.length;i++){
            if(arr[i]<min){
                min = arr[i];
            }
        }
        return min;
    }
    //Выведите первый и последний элемент списка.
    static void printFirstAndLast(int[]arr){
        System.out.println(arr[0]);
        System.out.println(arr[arr.length-1]);
    }
    //Есть список a = [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89].
    //
    //Выведите все элементы, которые меньше 5.
    static void printLess5(int[]arr){
        for(int i =0;i<arr.length;i++){
            if(arr[i]<5){
                System.out.println(arr[i]);
            }
        }
    }
}
