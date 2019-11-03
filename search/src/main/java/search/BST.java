package search;

public class BST {
    Node root;

    public BST() {
        this.root = null;
    }

    public BST(Node root) {
        this.root = root;
    }

    void insert(int key) {
        root = insertNode(root, key);
    }


    Node insertNode(Node root, int key) {

        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new Node(key);
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key < root.getData())
            root.setLeft(insertNode(root.getLeft(), key));
        else if (key > root.getData())
            root.setRight(insertNode(root.getRight(), key));

        /* return the (unchanged) node pointer */
        return root;
    }

    public boolean search(int value) {
        return searchNode(root, value);
    }
    private boolean searchNode(Node root, int value) {
        if (root == null) {
            return false;
        }
        if (value == root.getData()) {
            return true;
        }
        return value < root.getData()
                ? searchNode(root.getLeft(), value)
                : searchNode(root.getRight(), value);
    }

    void inorder()  {
        inorderRec(root);
    }

    // A utility function to do inorder traversal of BST
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.getLeft());
            System.out.println(root.getData());
            inorderRec(root.getRight());
        }
    }

    public int d(int k){
        return kthSmallest(root, k);
    }

    public int kthSmallest (Node pivot, int k){
        if(pivot == null )
            return k;
        k = kthSmallest(pivot.getLeft(), k);
        k--;
        if(k == 0){
            System.out.println(pivot.getData());
        }
        k = kthSmallest(pivot.getLeft(), k);
        return k;
    }


    public static void main(String [] args){
        Node root = null;
        BST tree= new BST(root);
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        int r = tree.d(3);


//        if(tree.search(60)){
//            System.out.println(1);
//        }else{
//            System.out.println(2);
//        }
//       |\ tree.inorder();
    }
}
