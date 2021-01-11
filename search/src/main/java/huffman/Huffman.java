package huffman;

//import sun.security.util.BitArray;



import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Huffman {
    private static Map<Character, String> charPrefixHashMap = new HashMap<>();
    static HuffmanNode root;



    private static HuffmanNode buildTree(Map<Character, Integer> freq) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        Set<Character> keySet = freq.keySet();
        for (Character c : keySet) {
            HuffmanNode huffmanNode = new HuffmanNode();
            huffmanNode.setCh(c);
            huffmanNode.setData(freq.get(c));
            huffmanNode.setLeft(null);
            huffmanNode.setRight(null);
            priorityQueue.offer(huffmanNode);
        }
        assert priorityQueue.size() > 0;

        while (priorityQueue.size() > 1) {

            HuffmanNode x = priorityQueue.peek();
            priorityQueue.poll();
//            System.out.println(x.getCh());

            HuffmanNode y = priorityQueue.peek();
            priorityQueue.poll();

            HuffmanNode sum = new HuffmanNode();

            if (y != null) {
                sum.setData(x.getData() + y.getData());
            }
            sum.setCh('-');

            sum.setLeft(x);

            sum.setRight(y);
            root = sum;

            priorityQueue.offer(sum);
        }

        return priorityQueue.poll();
    }


    private static void setPrefixCodes(HuffmanNode node, StringBuilder prefix) {

        if (node != null) {
            if (node.getLeft() == null && node.getRight() == null) {
                charPrefixHashMap.put(node.getCh(), prefix.toString());

            } else {
                prefix.append('0');
                setPrefixCodes(node.getLeft(), prefix);
                prefix.deleteCharAt(prefix.length() - 1);

                prefix.append('1');
                setPrefixCodes(node.getRight(), prefix);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

    }

    private static BitSet fromString(String binary) {
        BitSet bitset = new BitSet(binary.length());
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                bitset.set(i);
            }
        }
        return bitset;
    }

    private static String encode(String test) throws IOException {
        Map<Character, Integer> freq = new HashMap<>();
        FileReader fr = new FileReader(new File("london.txt"));
        int ch;
        while ((ch = fr.read()) != -1) {
            if (!freq.containsKey((char) ch)) {
                freq.put((char) ch, 0);
            }
            freq.put((char) ch, freq.get((char) ch) + 1);
        }
        fr.close();

        System.out.println("Character Frequency Map = " + freq);

        root = buildTree(freq);

        setPrefixCodes(root, new StringBuilder());
        System.out.println("Character Map = " + charPrefixHashMap);
        StringBuilder s = new StringBuilder();
        FileReader fr1 = new FileReader(new File("london.txt"));
        int ch1;
        int k = 0;
        while ((ch1 = fr1.read()) != -1) {
            char c = (char) ch1;
            k++;
            s.append(charPrefixHashMap.get(c));
        }
        System.out.println(k * 8);
        String s1 = new String();
        s1 += s;


        try (FileOutputStream writer = new FileOutputStream("notes3")) {
            char[] chars = s1.toCharArray();
//            byte[] b = new byte[s1.length()];
//            byte byt;
//            for (char a : chars) {
//                writer.write(a);
//            }
            for(int i = 0;i<s1.length();i+=8){
                writer.write(Integer.parseInt(s1.substring(i,Math.min(i+8, s1.length()))));
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
//        }
//        boolean[] outputArray = new boolean[s1.length()];
//        for(int i = 0; i<s1.length();i++){
//            if(chars[i]==1){
//                outputArray[i] = true;
//            }
//            else{
//                outputArray[i]=false;
//            }
//        }
//        System.out.println(s1.length());
//        BitOutputStream fout = new BitOutputStream(new BufferedOutputStream(new FileOutputStream("booleans.bin")));
//
//        for (int index = 0; index < s1.length(); index++){
//            fout.write(outputArray[index]);
//        }
//        fout.close();
            return s.toString();
        }

//    public static void writeBit(FileOutputStream writer, int num) throws IOException {
//        int count = 0;
//        boolean[] buffer = new boolean[8];
//        count++;
//        buffer[8-count] = true;
//        if (count == 8){
//            for (int index = 0; index < 8; index++){
//                num = 2*num + (buffer[index] ? 1 : 0);
//            }
//
//            writer.write(num - 128);
//
//            count = 0;
//        }
//    }


    private static void decode(String s) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        HuffmanNode temp = root;

        int ch;
        FileReader fr = new FileReader("notes3");
        StringBuilder s1 =new StringBuilder();

        for(int i = 0;i<s.length();i++){
            int j = Integer.parseInt(s.substring(i,Math.min(i+1, s.length())));
            if (j == 0) {
                temp = temp.getLeft();
                if (temp.getLeft() == null && temp.getRight() == null) {
                    stringBuilder.append(temp.getCh());
                    temp = root;
                }
            }
            if (j == 1) {
                temp = temp.getRight();
                if (temp.getLeft() == null && temp.getRight() == null) {
                    stringBuilder.append(temp.getCh());
                    temp = root;
                }
            }
        }
//        while ((ch = fr.read()) != -1) {
//            int j = Character.getNumericValue(ch);
//            if (j == 0) {
//                temp = temp.getLeft();
//                if (temp.getLeft() == null && temp.getRight() == null) {
//                    stringBuilder.append(temp.getCh());
//                    temp = root;
//                }
//            }
//            if (j == 1) {
//                temp = temp.getRight();
//                if (temp.getLeft() == null && temp.getRight() == null) {
//                    stringBuilder.append(temp.getCh());
//                    temp = root;
//                }
//            }
//        }

//        for (int i = 0; i < s.length(); i++) {
//            int j = Integer.parseInt(String.valueOf(s.charAt(i)));
//
//            if (j == 0) {
//                temp = temp.getLeft();
//                if (temp.getLeft() == null && temp.getRight() == null) {
//                    stringBuilder.append(temp.getCh());
//                    temp = root;
//                }
//            }
//            if (j == 1) {
//                temp = temp.getRight();
//                if (temp.getLeft() == null && temp.getRight() == null) {
//                    stringBuilder.append(temp.getCh());
//                    temp = root;
//                }
//            }
//        }
        String s2 = new String();
        s2+=stringBuilder;
        try(FileWriter writer = new FileWriter("notes4.txt", false)) {
            writer.write(s2);
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


//        System.out.println("Decoded string is " + stringBuilder.toString());

    }




    public static void main(String[] args) throws IOException {
//        String FILE_NAME = "sd.txt";
//        String s = "";
//        Scanner in = new Scanner(new File(FILE_NAME));
//        int k = 100;
//        int c = 0;
//        FileReader fr = new FileReader(new File("sd.txt"));
//        int ch;
//        while ((ch = fr.read()) != -1) {
//            System.out.print((char)ch);
//        }
//        while(in.hasNext()) {
//            System.out.println(in.nextLine());
//            String s1 = encode(in.nextLine());
//            System.out.println(s1.length());
//            try(FileWriter writer = new FileWriter("notes3.txt", false))
//            {
//                writer.write(s1);
//
//                writer.flush();
//            }
//            catch(IOException ex){
//
//                System.out.println(ex.getMessage());
//            }
//        }
//        in.close();


//        File fi = new File(FILE_NAME);
//        Reader f = new InputStreamReader(new FileInputStream(fi), "UTF-8");
////        FileReader f = new FileReader("D:\\уник\\алгоритмы\\search\\src\\main\\resources\\gogol.txt");
//        String fullLine = null;
//        List<String> lines = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);
//
//
//        LineNumberReader reader = new LineNumberReader(f);
//        String line = null;
//
//        List<String> list = new ArrayList<String>();
//        while ((line = reader.readLine()) !=null) {
//            System.out.println(line);
//            fullLine+=line;
//        }
//
//        String test = "ABSDDDDDDSSSAAACD1%$^";
//        System.out.println("Original Text = "+test);
//
//
//        decode(s1);

        String r = "";
        String s = encode(r);
       decode(s);
//        FileOutputStream f = new FileOutputStream("notes3");
//        f.write();

    }
}



