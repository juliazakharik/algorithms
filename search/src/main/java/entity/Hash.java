package entity;

public class Hash {

    public static int hash1(int key, int size) {
        return key % size;
    }


    public static int hash2(int key, int size, double constant) {
        return (int) Math.floor(size * ((key * constant) - Math.floor(key * constant)));
    }

    public static int hash3(int key, int size) {
        return (int) Math.floor(size * (key * ((Math.sqrt(5) - 1) / 2) - Math.floor(key * (Math.sqrt(5) - 1) / 2)));
    }
}