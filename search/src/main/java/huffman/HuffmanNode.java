package huffman;

import java.util.Comparator;

public class HuffmanNode implements Comparable<HuffmanNode>{
    private int data;
    private char ch;
    private HuffmanNode left;
    private HuffmanNode right;
    HuffmanNode(char ch, int data, HuffmanNode left, HuffmanNode right) {
        this.ch    = ch;
        this.data  = data;
        this.left  = left;
        this.right = right;
    }

    public HuffmanNode() {
    }

    private boolean isLeaf() {
        assert ((left == null) && (right == null)) || ((left != null) && (right != null));
        return (left == null) && (right == null);
    }

    public int compareTo(HuffmanNode node) {
        return data - node.data;
    }

    public int getData() {
        return data;
    }

    public char getCh() {
        return ch;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }
}
