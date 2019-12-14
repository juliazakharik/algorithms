package entity;

import static help.MathHelper.getNextPrime;
import static help.MathHelper.isPrime;

public class HashMapOpenAddressing {
    int size;
    Entry[] arrOfElements;

    public HashMapOpenAddressing(int size) {
        this.size = isPrime(size) ? size : getNextPrime(size);
        this.arrOfElements = new Entry[this.size];
    }

    public void put(int key, int value) throws Exception {
        Entry newElem = new Entry(key, value);
        int index = Hash.hash1(key, size);
        int coef = 4;
        int step = coef - key % coef;
        if (arrOfElements[index] != null) {
            for (int i = 0; i < size; i+=step) {
                if(arrOfElements[i] != null) {
                    if (arrOfElements[i].getKey() == key) {
                        throw new Exception("Such element is already exist");
                    }
                }
            }
        }
        while(arrOfElements[index] != null) {
            index += step;
            index %= size;
        }
        arrOfElements[index] = newElem;
    }

    public Integer get(int key) {
        int index = Hash.hash1(key, size);
        int coef = 4;
        int step = coef - key % coef;
        while (arrOfElements[index] != null) {
            if (arrOfElements[index].getKey() == key) {
                return arrOfElements[index].getValue();
            }
            index += step;
        }
        return null;
    }
}
