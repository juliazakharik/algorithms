//package search;
//
//public class BST {
//    protected Node root = null;
//
//    /**
//     * Comparator used to order the items in the tree.  If null,
//     * the natural order of the items will be used.
//     */
//
//
//    /**
//     * Constructs an empty BST that can only accept Comparables
//     * as items.
//     */
//    public BST() {
//    }
//
//    /**
//     * Constructs a BST that orders its items according to the
//     * given comparator.
//     */
//
//    /**
//     * Returns whether or not the tree contains an object with
//     * the given value.
//     */
//    public boolean contains(int data) {
//        return nodeContaining(data) != null;
//    }
//
//    /**
//     * Adds a single data item to the tree.  If there is already an
//     * item in the tree that compares equal to the item being inserted,
//     * it is "overwritten" by the new item.
//     */
//    public void add(int data) {
//        if (root == null) {
//            root = new Node(data);
//        }
//        Node n = root;
//        while (true) {
//            int comparisonResult = compare(data, n.getData());
//            if (comparisonResult == 0) {
//                n.setData(data);
//                return;
//            } else if (comparisonResult < 0) {
//                if (n.getLeft() == null) {
//                    n.setLeft(new Node(data));
//                    return;
//                }
//                n = n.getLeft();
//            } else { // comparisonResult > 0
//                if (n.getRight() == null) {
//                    n.setRight(new Node(data));
//                    return;
//                }
//                n = n.getRight();
//            }
//        }
//    }
//
//    /**
//     * Removes the node containing the given value.  Does nothing
//     * if there is no such node.
//     */
//    public void remove(int data) {
//        Node node = nodeContaining(data);
//        if (node == null) {
//            // No such object, do nothing.
//            return;
//        } else if (node.getLeft() != null && node.getRight() != null) {
//            // Node has two children, we cannot delete it.  Copy
//            // predecessor data here and get ready to delete predecessor.
//            Node predecessor = predecessor(node);
//            node.setData(predecessor.getData());
//            node = predecessor;
//        }
//        // At this point node has zero or one child
//        Node pullUp =
//                (node.getLeft() == null) ? node.getRight() : node.getLeft();
//        if (node == root) {
//            setRoot(pullUp);
//        } else if (node.getParent().getLeft() == node) {
//            node.getParent().setLeft(pullUp);
//        } else {
//            node.getParent().setRight(pullUp);
//        }
//    }
//
//    // Best to put the comparison code in a single place so that we don't have
//    // to check for comparators and cast all over the place.
//
//    protected int compare(int x, int y) {
//        return x>y?x:y;
//    }
//
//    // Methods relating to nodes, not part of public interface.
//
//    /**
//     * Returns the root of the tree.
//     */
//
//    /**
//     * Makes the given node the new root of the tree.
//     */
//    protected void setRoot(Node node) {
//        if (node != null) {
//            node.removeFromParent();
//        }
//        root = node;
//    }
//
//    /**
//     * Rotates left around the given node.
//     */
//    protected void rotateLeft(Node n) {
//        if (n.getRight() == null) {
//            return;
//        }
//        Node oldRight = n.getRight();
//        n.setRight(oldRight.getLeft());
//        if (n.getParent() == null) {
//            root = oldRight;
//        } else if (n.getParent().getLeft() == n) {
//            n.getParent().setLeft(oldRight);
//        } else {
//            n.getParent().setRight(oldRight);
//        }
//        oldRight.setLeft(n);
//    }
//
//    /**
//     * Rotates right around the given node.
//     */
//    protected void rotateRight(Node n) {
//        if (n.getLeft() == null) {
//            return;
//        }
//        Node oldLeft = n.getLeft();
//        n.setLeft(oldLeft.getRight());
//        if (n.getParent() == null) {
//            root = oldLeft;
//        } else if (n.getParent().getLeft() == n) {
//            n.getParent().setLeft(oldLeft);
//        } else {
//            n.getParent().setRight(oldLeft);
//        }
//        oldLeft.setRight(n);
//    }
//
//    /**
//     * Returns the rightmost node in the left subtree.
//     */
//    protected Node predecessor(Node node) {
//        Node n = node.getLeft();
//        if (n != null) {
//            while (n.getRight() != null) {
//                n = n.getRight();
//            }
//        }
//        return n;
//    }
//
//    /**
//     * A special helper method that returns the node containing
//     * an object that compares equal to the given object.  This
//     * is used in both contains and remove.
//     * @param data
//     */
//    protected BinaryTreeNode<E> nodeContaining(int data) {
//        for (BinaryTreeNode<E> n = root; n != null;) {
//            int comparisonResult = compare(data, n.getData());
//            if (comparisonResult == 0) {
//                return n;
//            } else if (comparisonResult < 0) {
//                n = n.getLeft();
//            } else {
//                n = n.getRight();
//            }
//        }
//        return null;
//    }
//}
//
//    public static void main(String [] args){
//        Node root = null;
//        BST tree= new BST(root);
//        tree.insert(50);
//        tree.insert(30);
//        tree.insert(20);
//        tree.insert(40);
//        tree.insert(70);
//        tree.insert(60);
//        tree.insert(80);
//
//        int r = tree.smallestV(1);
//
//
////        if(tree.search(60)){
////            System.out.println(1);
////        }else{
////            System.out.println(2);
////        }
////       |\ tree.inorder();
//    }
//}
