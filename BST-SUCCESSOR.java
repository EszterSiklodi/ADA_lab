
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

public Node search(int key) {
    return search(root, key);
}

private Node search(Node x, int key) {
    if (x == null || x.key == key) return x;
    if (key < x.key) return search(x.left, key);
    else return search(x.right, key);
}
public Node successor(Node x) {
    if (x.right != null) {
        return minimum(x.right);
    }
    Node y = x.p;
    while (y != null && x == y.right) {
        x = y;
        y = y.p;
    }
    return y;
}
private Node minimum(Node x) {
    while (x.left != null) {
        x = x.left;
    }
    return x;
}



public static void main(String[] args) {

    BST<Integer> st1=new BST<Integer>();
    st1.insert(5);
    st1.insert(2);
    st1.insert(10);
    st1.insert(8);
    st1.insert(15);
    //st1.successor( 5);

    BST<String> st2=new BST<String>();
    st2.insert("dog");
    st2.insert("bear");
    st2.insert("cat");
    st2.insert("fish");
    st2.insert("wolf");
    //st2.successor( "mouse");
    System.out.println("Successor(5): " + st1.successor(st1.search(5)).key);
    System.out.println("Successor(15): " + st1.successor(st1.search(15)).key);
    System.out.println("Successor(8): " + st1.successor(st1.search(8)));


    System.out.println("Successor(dog)): " + st2.successor(st2.search("dog")).key);
    System.out.println("Successor(cat): " + st2.successor(st2.search("cat")).key);
    System.out.println("Successor(wolf): " + st2.successor(st2.search("wolf")));
}

}

