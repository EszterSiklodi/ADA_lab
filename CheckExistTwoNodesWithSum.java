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
public boolean checkExistTwoNodesWithSum(int s) {
    Node left = findSmallest(root);
    Node right = findLargest(root);

    while (left != null && right != null && left != right) {
        int sum = left.key + right.key;
        if (sum == s) {
            return true;
        } else if (sum < s) {
            left = inorderSuccessor(left);
        } else {
            right = inorderPredecessor(right);
        }
    }
    return false;
}

private Node findSmallest(Node x) {
    while (x.left != null) {
        x = x.left;
    }
    return x;
}

private Node findLargest(Node x) {
    while (x.right != null) {
        x = x.right;
    }
    return x;
}

private Node inorderSuccessor(Node x) {
    if (x.right != null) {
        return findSmallest(x.right);
    }
    Node y = x.p;
    while (y != null && x == y.right) {
        x = y;
        y = y.p;
    }
    return y;
}

private Node inorderPredecessor(Node x) {
    if (x.left != null) {
        return findLargest(x.left);
    }
    Node y = x.p;
    while (y != null && x == y.left) {
        x = y;
        y = y.p;
    }
    return y;
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






/*public boolean checkExistTwoNodesWithSum(int s) {
    Node left = findSmallest(root);
    Node right = findLargest(root);

    while (left != null && right != null && left != right) {
        int sum = left.key + right.key;
        if (sum == s) {
            return true;
        } else if (sum < s) {
            left = inorderSuccessor(left);
        } else {
            right = inorderPredecessor(right);
        }
    }
    return false;
}

private Node findSmallest(Node x) {
    while (x.left != null) {
        x = x.left;
    }
    return x;
}

private Node findLargest(Node x) {
    while (x.right != null) {
        x = x.right;
    }
    return x;
}

private Node inorderSuccessor(Node x) {
    if (x.right != null) {
        return findSmallest(x.right);
    }
    Node y = x.p;
    while (y != null && x == y.right) {
        x = y;
        y = y.p;
    }
    return y;
}

private Node inorderPredecessor(Node x) {
    if (x.left != null) {
        return findLargest(x.left);
    }
    Node y = x.p;
    while (y != null && x == y.left) {
        x = y;
        y = y.p;
    }
    return y;
}
*/
