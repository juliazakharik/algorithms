package help;

import entity.Entry;
import entity.HashChains;

import java.util.Arrays;
import java.util.LinkedList;

public class MathHelper {
    public static double getAverageChainLength(HashChains map) {
        LinkedList<Entry>[] lists = map.lists;
        double elems = 0;
        int count = 0;
        for (LinkedList<Entry> list: lists) {
            if (list != null) {
                elems += list.size();
                if (list.size() > 0) {
                    count++;
                }
            }
        }
        return elems / count;
    }

    public static double getMediumChainLength(HashChains map) {
        LinkedList<Entry>[] lists = map.lists;
        Arrays.sort(lists, new SortHash());
        return lists[lists.length / 2].size();
    }


    public static boolean isPrime(int number) {
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i < number; i+=2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int getNextPrime(int numberToCheck) {
        for(int i = numberToCheck; true; i++) {
            if (isPrime(i))
                return i;
        }
    }

//    public static void increaseArraySize(int arraySize) {
//        int newArraySize = getNextPrime(arraySize);
//        moveOldArray(newArraySize);
//    }

}
