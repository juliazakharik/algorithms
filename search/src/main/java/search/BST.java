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

//    int couuntV(){
//
//    }


    Node insertNode(Node root, int key) {

        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.getData())
            root.setLeft(insertNode(root.getLeft(), key));
        else if (key > root.getData())
            root.setRight(insertNode(root.getRight(), key));

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

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.getLeft());
            System.out.println(root.getData());
            inorderRec(root.getRight());
        }
    }

    public int smallestV(int k){
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


//    обходы
    void visit(boolean n){
        if(!n)
            n=true;
    }
//    public void dfs(int start) {
//        boolean[] isVisited = new boolean[.size()];
//        dfsRecursive(start, isVisited);
//    }
//
//    private void dfsRecursive(int current, boolean[] isVisited) {
//        isVisited[current] = true;
//        visit(current);
//        for (int dest : adjVertices.get(current)) {
//            if (!isVisited[dest])
//                dfsRecursive(dest, isVisited);
//        }
//    }


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

        int r = tree.smallestV(1);


//        if(tree.search(60)){
//            System.out.println(1);
//        }else{
//            System.out.println(2);
//        }
//       |\ tree.inorder();
    }
}
