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

public Node searchClosest(int k) {
    return searchClosest(root, k, root);
}

private Node searchClosest(Node x, int k, Node closest) {  //we check if the current node x is null
    if (x == null) return closest;
    
    if (x.key == k) {
        return x;
    }
    
    if (Math.abs(x.key - k) < Math.abs(closest.key - k)) {
        closest = x;
    }
    
    if (k < x.key) {
        return searchClosest(x.left, k, closest);
    } else {
        return searchClosest(x.right, k, closest);
    }
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
    
System.out.println("The closest value to 6 is:" +st1.searchClosest(6));
}

}



/*public Node searchClosest(int k) {
    return searchClosest(root, k, root);
}

private Node searchClosest(Node x, int k, Node closest) {
    if (x == null) return closest;
    
    if (x.key == k) {
        return x;
    }
    
    if (Math.abs(x.key - k) < Math.abs(closest.key - k)) {
        closest = x;
    }
    
    if (k < x.key) {
        return searchClosest(x.left, k, closest);
    } else {
        return searchClosest(x.right, k, closest);
    }
} */