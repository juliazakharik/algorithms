package huffman;

//import sun.security.util.BitArray;


import java.io.*;
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

    private static String encode(String test) throws IOException {
        Map<Character, Integer> freq = new HashMap<>();
        FileReader fr = new FileReader(new File("london.txt"));
        int ch;
        while ((ch = fr.read()) != -1) {
            if (!freq.containsKey((char)ch)) {
                freq.put((char)ch, 0);
            }
            freq.put((char)ch, freq.get((char)ch) + 1);
        }
//        for (int i = 0; i < test.length(); i++) {
//            if (!freq.containsKey(test.charAt(i))) {
//                freq.put(test.charAt(i), 0);
//            }
//            freq.put(test.charAt(i), freq.get(test.charAt(i)) + 1);
//        }
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
            char c = (char)ch1;
            k++;
            s.append(charPrefixHashMap.get(c));
        }
        System.out.println(k*8);
        String s1 = new String();
        s1+=s;
        System.out.println(s1);

        try(FileOutputStream writer = new FileOutputStream("notes3");) {
            char[] chars = s1.toCharArray();
            byte[] b;
//            System.out.println((byte)1+" "+(byte)Integer.parseInt(String.valueOf('1')));
            for (char aChar : chars) {
//                System.out.println((byte)((int)aChar));
                writer.
                writer.write((int)Integer.parseInt(String.valueOf(aChar)));
            }
//            System.out.println(chars.length);
//            BitSet arr = new BitSet(s1.length());
//            writer.write(arr.toByteArray().toString());
//            writer.write(s1);
            writer.flush();
            writer.close();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }


        return s.toString();
    }

    private static void decode(String s) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        HuffmanNode temp = root;
        FileReader fr = new FileReader(new File("notes3"));
        int ch;
        while ((ch = fr.read()) != -1) {
            int j = Character.getNumericValue(ch);
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
        try(FileWriter writer = new FileWriter("notes4.bin", false)) {
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
        encode(r);
       decode(r);
//        FileOutputStream f = new FileOutputStream("notes3");
//        f.write();

    }
}



