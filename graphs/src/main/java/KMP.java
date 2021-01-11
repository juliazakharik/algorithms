import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class KMP {
    //KMP START
    public static int[] pattern;
    public static HashMap<Character, Integer> bmPattern;
    public static HashMap<Character,Integer> rkPattern;

    public static void kmp(String text, String pattern){
        KMP.pattern = new int[pattern.length()];
        prefixTable(pattern);
        ArrayList<Integer> res = search(text, pattern);
        System.out.println("KMP");
        if (res.isEmpty())
            System.out.println("NO MATCH");
        else
            System.out.println("MATCH AT: "+ res.toString());
    }

    public static void prefixTable(String pattern){
        int n = pattern.length();
        KMP.pattern[0] = 0;
        int j = 0;
        for(int i = 1;i<n;i++){
            if(pattern.charAt(i)==pattern.charAt(j)){
                KMP.pattern[i] = j+1;
                j++;
            }else{
                if(j>0)
                    j = KMP.pattern[j-1];
            }
        }
    }
    public static ArrayList<Integer> search(String text, String pattern) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        int lenText = text.length();
        int lenPattern = pattern.length();
        while (i < lenText) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            else if (j==0)
                i++;
            else
                j = KMP.pattern[j - 1];
            if(j==lenPattern){
                int match =i - lenPattern;
                result.add(match);
                j=0;
            }
        }

        return result;
    }

    //KMP END


    //BM SRART
    public static void bm(String text, String pattern){
        bmPattern = new HashMap<Character, Integer>();
        alfTable(pattern);
        ArrayList<Integer> res = searchBm(text, pattern);
        System.out.println("BM");
        if (res.isEmpty())
            System.out.println("NO MATCH");
        else
            System.out.println("MATCH AT: "+ res.toString());
    }
    public static void alfTable(String pattern) {
        for (int i = 0; i <= 255; i++) {
            bmPattern.put((char) i, pattern.length());
        }
        for (int i = 0; i < pattern.length() - 1; i++) {
            bmPattern.put(pattern.charAt(i), Math.max(1, pattern.length() - i - 1));
        }
        bmPattern.put((char)65533,pattern.length());
    }

    public static ArrayList<Integer> searchBm(String text, String pattern) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = pattern.length() - 1;
        int j, k;
        int lenText = text.length();
        int lenPattern = pattern.length();
        while (i < lenText) {
            j = lenPattern - 1;
            k = i;
            while (j >= 0 && text.charAt(k) == pattern.charAt(j)) {
                k--;
                j--;
            }
            i += bmPattern.get(text.charAt(i));
            if(j<0){
                result.add(k+1);
            }
        }
        return result;
    }


    //BM END

    //START RK

    public static void RK(String text, String pattern, int prime){
        fillRKPattern();
        long startRK = System.currentTimeMillis();
        ArrayList<Integer> res = searchRK(text, pattern, prime);
        System.out.println("RK");
        System.out.println("TIME RK: "+(System.currentTimeMillis()-startRK));

        if (res.isEmpty())
            System.out.println("NO MATCH");
        else
            System.out.println("MATCH AT: "+ res.toString());
        System.out.println("");


        System.out.println("RK MODIFIED");
        long startRKM = System.currentTimeMillis();
        ArrayList<Integer> res1 = searchRKModified(text, pattern, prime);

        System.out.println("TIME RK MODIFIED: "+(System.currentTimeMillis()-startRKM));

        if (res.isEmpty())
            System.out.println("NO MATCH");
        else
            System.out.println("MATCH AT: "+ res1.toString());

    }

    public static void fillRKPattern(){
        rkPattern = new HashMap<Character, Integer>();
        Integer []table = table();
        for (int i = 0; i <= 255; i++) {
            rkPattern.put((char) i,(int) table[i]);
        }
        rkPattern.put((char)65533,table[256]);
    }

    public static ArrayList<Integer> searchRK(String text, String pattern, int prime){
        ArrayList<Double> pows = new ArrayList<>();
        for(int i = 0;i<pattern.length();i++){
            pows.add(Math.pow(prime, i));
        }
        int hashPattern = 0;
        int len = pattern.length();
        ArrayList<Integer> result = new ArrayList<>();
        for(int j = 0;j<len;j++){
            hashPattern+=rkPattern.get(pattern.charAt(j))*pows.get(j);
        }

        for(int i = 0;i<text.length()-len;i++){
            int hash = 0;
            for(int j = 0;j<len;j++){
                hash+=rkPattern.get(text.charAt(i+j))*pows.get(j);
            }
            if(hash == hashPattern){
                boolean isSame = true;
                for(int j = 0;j<len;j++){
                    if(text.charAt(i+j)!=pattern.charAt(j)){
                        isSame = false;
                    }
                }
                if(isSame){
                    result.add(i);
                }
            }

        }
        return result;
    }
    public static Integer[] table(){
        Integer[] arr = new Integer[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Collections.shuffle(Arrays.asList(arr));
        return arr;
    }
    public static ArrayList<Integer> searchRKModified(String text, String pattern, int prime){
        ArrayList<Double> pows = new ArrayList<>();
        for(int i = 0;i<pattern.length();i++){
            pows.add(Math.pow(prime, i));
        }
        int hashPattern = 0;
        int len = pattern.length();
        ArrayList<Integer> result = new ArrayList<>();
        for(int j = 0;j<len;j++){
            hashPattern+=rkPattern.get(pattern.charAt(j))*pows.get(j);
        }
        int hash = 0;
        for(int j = 0;j<len;j++){
            hash+=rkPattern.get(text.charAt(j))*pows.get(j);
        }
        for(int i = 1;i<text.length()-len;i++){

            hash-=rkPattern.get(text.charAt(i-1));
            hash/=prime;
            hash+= rkPattern.get(text.charAt(i+len-1))*pows.get(len-1);

            if(hash == hashPattern){
                boolean isSame = true;
                for(int j = 0;j<len;j++){
                    if(text.charAt(i+j)!=pattern.charAt(j)){
                        isSame = false;
                    }
                }
                if(isSame){
                    result.add(i);
                }
            }

        }
        return result;
    }
    //END RK

    private static String readUsingFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
    public static void main(String[] args) throws IOException {
        String fileName = "me.txt";
        String text = readUsingFiles(fileName);
        String pattern ="death";
        long startKMP = System.currentTimeMillis();
        kmp(text,pattern);
        System.out.println("TIME KMP: "+(System.currentTimeMillis()-startKMP));
        System.out.println("");
        long startBM = System.currentTimeMillis();
        bm(text, pattern);
        System.out.println("TIME BM: "+(System.currentTimeMillis()-startBM));
        System.out.println("");
        RK(text,pattern, 101);
    }
}
