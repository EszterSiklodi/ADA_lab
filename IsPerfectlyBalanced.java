class Node<T extends Comparable> {
    public T key;

    public Node left, right, p;

    public Node(T key) {
        this.key = key;
    }
}


public class BST <T extends Comparable>{

private Node<T> root;             // root of BST


public BST() {
    root=null;
}


public void insert(T k) {
    Node x, y;
    Node z= new Node(k);
    y=null;
    x=root;
    while (x!=null) {
        y=x;
        if (x.key.compareTo(z.key)<0)
            x=x.right;
        else
            x=x.left;
    }
    z.p=y;
    if (y==null)
        root=z;
    else
        if (y.key.compareTo(z.key)<0)
            y.right=z;
        else
            y.left=z;

}

public boolean isPerfectlyBalanced() {
    return isPerfectlyBalanced(root) != -1;
}

private int isPerfectlyBalanced(Node x) {
    if (x == null) return 0;//we first check if the root node is null
    
    int leftHeight = isPerfectlyBalanced(x.left);
    if (leftHeight == -1) return -1;
    System.out.println("The BST is not perfectly balanced. ");
    
    int rightHeight = isPerfectlyBalanced(x.right);
    if (rightHeight == -1) return -1;
    System.out.println("The BST is not perfectly balanced. ");
    
    if (Math.abs(leftHeight - rightHeight) > 1) return -1;
    
    return Math.max(leftHeight, rightHeight) + 1;
}



public static void main(String[] args) {

    BST<Integer> st1=new BST<Integer>();
    st1.insert(5);
    st1.insert(2);
    st1.insert(10);
    st1.insert(8);
    st1.insert(15);
    

    BST<String> st2=new BST<String>();
    st2.insert("dog");
    st2.insert("bear");
    st2.insert("cat");
    st2.insert("fish");
    st2.insert("wolf");
    

}

}











/*public boolean isPerfectlyBalanced() {
    return isPerfectlyBalanced(root) != -1;
}

private int isPerfectlyBalanced(Node x) {
    if (x == null) return 0;//we first check if the root node is null
    
    int leftHeight = isPerfectlyBalanced(x.left);
    if (leftHeight == -1) return -1;
    
    int rightHeight = isPerfectlyBalanced(x.right);
    if (rightHeight == -1) return -1;
    
    if (Math.abs(leftHeight - rightHeight) > 1) return -1;
    
    return Math.max(leftHeight, rightHeight) + 1;
}
*/
