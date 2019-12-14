//package search;
//
//import java.util.Objects;
//
//public class Node implements Comparable {
//    private Node left;
//    private Node right;
//    private int data;
//
//    public Node(Node left, Node right, int data) {
//        this.left = left;
//        this.right = right;
//        this.data = data;
//    }
//
//    public Node(int data) {
//        this.data = data;
//    }
//
//    public Node getLeft() {
//        return left;
//    }
//
//
//    public void setLeft(Node left) {
//        this.left = left;
//    }
//
//    public Node getRight() {
//        return right;
//    }
//
//    public void setRight(Node right) {
//        this.right = right;
//    }
//
//    public int getData() {
//        return data;
//    }
//
//    public void setData(int data) {
//        this.data = data;
//    }
//
//    public Node getParent(int x) {
//        return getParent(x, root, null);
//    }
//
//    private static Node getParent(int x, Node t, Node parent) {
//        if (t == null) {
//            return null;
//        } else {
//            if (x.compareTo(t.data) < 0) {
//                return getParent(x, t.left, t);
//            } else if (x.compareTo(t.data) > 0) {
//                return getParent(x, t.right, t);
//            } else {
//                return parent;
//            }
//        }
//    }
//
//    private Node addNode(Node current, int value) {
//        if (current == null) {
//            return new Node(value);
//        }
//
//
//        if (value < current.data) {
//            current.left = addNode(current.left, value);
//        } else if (value > current.data) {
//            current.right = addNode(current.right, value);
//        } else {
//            // value already exists
//            return current;
//        }
//
//        return current;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Node)) return false;
//        Node node = (Node) o;
//        return getData() == node.getData() &&
//                Objects.equals(getLeft(), node.getLeft()) &&
//                Objects.equals(getRight(), node.getRight());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getLeft(), getRight(), getData());
//    }
//
//}
