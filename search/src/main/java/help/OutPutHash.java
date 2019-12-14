package help;

import entity.HashChains;
import entity.HashFunc;
import entity.HashFuncWithConst;

import java.util.List;

import static help.MathHelper.getAverageChainLength;
import static help.MathHelper.getMediumChainLength;

public class OutPutHash {
    public static void outputInfo(int size, List<Integer[]> arr, HashFunc func) throws Exception{
        HashChains hashChains = new HashChains(size, func);
        print(size, arr, hashChains);
    }

    private static void print(int size, List<Integer[]> arr, HashChains hashChains) throws Exception {
        for (int i = 0; i < size; i++) {
            hashChains.put(arr.get(i)[0], 10);
        }
        System.out.println("Average chain length: " +getAverageChainLength(hashChains));
        System.out.println("Medium chain length: " + getMediumChainLength(hashChains));
    }

    public static void outputInfoWithConstant(int size, List<Integer[]> arr, HashFuncWithConst func, Double constant) throws Exception{
        HashChains hashChains = new HashChains(size, func, constant);
        print(size, arr, hashChains);
    }


}
