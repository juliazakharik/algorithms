package search;

public class Node {
    private Node left;
    private Node right;
    private int data;

    public Node(Node left, Node right, int data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public Node(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    private Node addNode(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }


        if (value < current.data) {
            current.left = addNode(current.left, value);
        } else if (value > current.data) {
            current.right = addNode(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }
}
