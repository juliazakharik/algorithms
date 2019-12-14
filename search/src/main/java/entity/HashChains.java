package entity;

import java.util.LinkedList;

import static entity.Hash.hash1;
import static entity.Hash.hash2;
import static help.MathHelper.getNextPrime;
import static help.MathHelper.isPrime;

public class HashChains {
    int size;
    public LinkedList<Entry>[] lists;
    HashFunc func;
    HashFuncWithConst funcWithConst;
    Double constant = Double.NaN;

    public HashChains(int size, HashFunc func) {
        this.size = isPrime(size) ? size : getNextPrime(size);
        this.lists = new LinkedList[this.size];
        this.func = func;
    }
    public HashChains(int size, HashFuncWithConst func, Double constant) {
        this.size = isPrime(size) ? size : getNextPrime(size);
        this.lists = new LinkedList[this.size];
        this.funcWithConst = func;
        this.constant = constant;
    }

    public void put(int key, int value) throws Exception {
        Entry newElem = new Entry(key, value);
        int index;
        if (constant.isNaN()) {
            index = func.HashFunc(key, size);
        } else {
            index = funcWithConst.HashFunc(key, size, constant);
        }
        if (lists[index] == null) {
            lists[index] = new LinkedList<>();
        }
        if (lists[index].contains(newElem.getKey())) {
            throw new Exception("Such element is already exist");
        }
        lists[index].add(newElem);
    }

    public Integer get(int key) {
        int res = 0;
        int index = Hash.hash1(key, size);
        LinkedList<Entry> list = lists[index];
        if (lists[index] == null) {
            return null;
        }
        for (Entry entry: list) {
            if (entry.getKey() == key) {
                res = entry.getValue();
            }
        }
        return res;
    }
}
